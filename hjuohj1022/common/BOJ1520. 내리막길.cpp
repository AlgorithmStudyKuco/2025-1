#include <iostream>
#include <vector>

using namespace std;

int m, n;
vector<vector<int>> board;
vector<vector<int>> dp;

int dx[] = {0, 1, 0, -1};
int dy[] = {1, 0, -1, 0};

int dfs(int x, int y) {
	if (x == m - 1 && y == n - 1) return 1;

	if (dp[x][y] != -1) return dp[x][y];

	dp[x][y] = 0;

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx >= 0 && nx <= m - 1 && ny >= 0 && ny <= n - 1) {
			if (board[x][y] > board[nx][ny]) {
				dp[x][y] += dfs(nx, ny);
			}
		}
	}
	return dp[x][y];
}

int main() {
	cin >> m >> n;
	board.resize(m, vector<int>(n));
	dp.assign(m, vector<int>(n, -1));

	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			cin >> board[i][j];
		}
	}

	cout << dfs(0, 0) << "\n";
	cout << dp[0][0] << "\n";
	return 0;
}