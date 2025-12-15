#include <iostream>
#include <vector>
using namespace std;

struct dot { int x, y; };

int N, M;
vector<vector<int>> board;
vector<vector<bool>> visited;
vector<vector<int>> adjacentToSea; // 녹을 양 저장 (동시성 처리)

//방향: 우, 하, 좌, 상
int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

void input() {
  cin >> N >> M;
  board.resize(N, vector<int>(M, 0));
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) cin >> board[i][j];
  }
}

// 탐색 시작점 찾기 (첫 번째 빙산)
dot initDot() {
  for (int i = 1; i < N; i++) {
    for (int j = 1; j < M; j++) {
      if (board[i][j] != 0) return { i, j };
    }
  }
  return { 0, 0 }; // 더미 반환
}

// 방문 배열 초기화 및 녹을 양 미리 계산
bool resetVisited() {
  bool iceExists = false;
  visited.assign(N, vector<bool>(M, true)); // 기본 true
  adjacentToSea.assign(N, vector<int>(M, 0));

  for (int i = 1; i < N - 1; i++) {
    for (int j = 1; j < M - 1; j++) {
      if (board[i][j] != 0) {
        iceExists = true;
        visited[i][j] = false; // 빙산인 곳만 false (방문 필요)

        // 4방향 바다 개수 카운트
        for (int k = 0; k < 4; k++) {
          int nx = i + dx[k];
          int ny = j + dy[k];
          if (board[nx][ny] == 0) adjacentToSea[i][j]++;
        }
      }
    }
  }
  return iceExists;
}

void dfs(int x, int y) {
  visited[x][y] = true;
  for (int i = 0; i < 4; i++) {
    int nx = x + dx[i];
    int ny = y + dy[i];
    if (!visited[nx][ny] && board[nx][ny] != 0) {
      dfs(nx, ny);
    }
  }
}

// 모든 빙산이 연결되어 있는지 확인
bool checkSeparation() {
  for (int i = 1; i < N - 1; i++) {
    for (int j = 1; j < M - 1; j++) {
      if (!visited[i][j]) return false; // 방문 안 된 빙산 존재 = 분리됨
    }
  }
  return true;
}

void solve() {
  int year = 0;
  while (true) {
    // 1. 초기화 및 빙산 존재 확인
    if (!resetVisited()) {
      cout << 0 << "\n"; // 빙산이 다 녹을 때까지 분리 안 됨
      return;
    }

    // 2. 한 덩어리 탐색
    dot start = initDot();
    dfs(start.x, start.y);

    // 3. 분리 여부 확인
    if (checkSeparation()) {
      // 분리 안 됨 -> 빙산 녹이기 (시간 경과)
      year++;
      for (int i = 1; i < N - 1; i++) {
        for (int j = 1; j < M - 1; j++) {
          if (board[i][j] != 0) {
            board[i][j] -= adjacentToSea[i][j];
            if (board[i][j] < 0) board[i][j] = 0;
          }
        }
      }
    }
    else {
      // 분리 됨 -> 정답 출력
      cout << year << "\n";
      break;
    }
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);
  input();
  solve();
  return 0;
}