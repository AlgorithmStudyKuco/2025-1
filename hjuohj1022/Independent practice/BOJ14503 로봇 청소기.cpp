#include <iostream>
#include <vector>

using namespace std;

int N, M;
int r, c, dir; // 변수명을 row, col에서 r, c로 변경하여 명확화
vector<vector<int>> board;
int result = 0;

int dx[] = { -1, 0, 1, 0 };
int dy[] = { 0, 1, 0, -1 };

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cin >> N >> M;
  cin >> r >> c >> dir;
  board.resize(N, vector<int>(M));
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      cin >> board[i][j];
    }
  }

  while (true) {
    // 1번 조건
    if (board[r][c] == 0) {
      board[r][c] = 2;
      result++;
    }

    bool rotate = false;
    for (int i = 0; i < 4; i++) {
      int nr = r + dx[i];
      int nc = c + dy[i];

      if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
        if (board[nr][nc] == 0) { 
          rotate = true;
          break;
        }
      }
    }

    if (rotate) {
      dir = (dir + 3) % 4;

      int nx = r + dx[dir];
      int ny = c + dy[dir];
      if (board[nx][ny] == 0) {
        r = nx;
        c = ny;
      }
    }
    else {
      int back_r = r - dx[dir];
      int back_c = c - dy[dir];

      if (board[back_r][back_c] != 1) {
        r = back_r;
        c = back_c;
      }
      else {
        break;
      }
    }
  }

  cout << result << "\n";
  return 0;
}