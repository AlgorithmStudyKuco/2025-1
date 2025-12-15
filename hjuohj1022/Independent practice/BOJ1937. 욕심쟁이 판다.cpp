#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n;
vector<vector<int>> board;
vector<vector<int>> dp;
int result = 0;

int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

void input() {
  cin >> n;
  board.resize(n, vector<int>(n));
  dp.resize(n, vector<int>(n, 0));
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> board[i][j];
    }
  }
}

int dfs(int x, int y) {
  if (dp[x][y] != 0) return dp[x][y];

  dp[x][y] = 1;

  for (int i = 0; i < 4; i++) {
    int nx = x + dx[i];
    int ny = y + dy[i];

    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

    if (board[nx][ny] > board[x][y]) {
      dp[x][y] = max(dp[x][y], dfs(nx, ny) + 1);
    }
  }
  return dp[x][y];
}

void solve() {
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      result = max(result, dfs(i, j));
    }
  }
  cout << result << "\n";
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  input();
  solve();

  return 0;
}