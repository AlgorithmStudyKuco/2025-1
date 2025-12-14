#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <algorithm>

using namespace std;

int N, M;
vector<vector<int>> board;
// visited[x][y][broken]: 0은 안 부숨, 1은 부숨
int visited[1001][1001][2];

int dx[4] = { 0,1,0,-1 };
int dy[4] = { 1,0,-1,0 };

struct idx {
  int x, y, d;
  int broken;
};

void input() {
  cin >> N >> M;
  board.resize(N, vector<int>(M));
  for (int i = 0; i < N; i++) {
    string s;
    cin >> s;
    for (int j = 0; j < M; j++) {
      board[i][j] = s[j] - '0';
    }
  }
}

int bfs() {
  queue<idx> q;
  q.push({ 0,0,1,0 }); // 시작점
  visited[0][0][0] = 1;

  while (!q.empty()) {
    idx index = q.front();
    q.pop();

    // 목적지 도착 시 즉시 거리 반환 (최단 거리 보장)
    if (index.x == N - 1 && index.y == M - 1) return index.d;

    for (int i = 0; i < 4; i++) {
      int nx = index.x + dx[i];
      int ny = index.y + dy[i];

      if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

      // 1. 벽을 만났을 때 (아직 안 부쉈다면 부수고 이동)
      if (board[nx][ny] == 1 && index.broken == 0) {
        if (visited[nx][ny][1] == 0) { // 부순 상태로 방문한 적 없으면
          visited[nx][ny][1] = 1;
          q.push({ nx, ny, index.d + 1, 1 });
        }
      }
      // 2. 길을 만났을 때 (현재 상태 유지하며 이동)
      else if (board[nx][ny] == 0) {
        if (visited[nx][ny][index.broken] == 0) {
          visited[nx][ny][index.broken] = 1;
          q.push({ nx, ny, index.d + 1, index.broken });
        }
      }
    }
  }
  return -1;
}

void solve() {
  cout << bfs() << "\n";
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);
  input();
  solve();
  return 0;
}