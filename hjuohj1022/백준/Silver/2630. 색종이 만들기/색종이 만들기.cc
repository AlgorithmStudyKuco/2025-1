#include <iostream>
#include <vector>

using namespace std;

int N;
vector<vector<int>> board;
int white;
int blue;

int dx[4] = { 0, 1, 0, 1};
int dy[4] = { 0, 0, 1, 1};

void input() {
	cin >> N;
	board.resize(N, vector<int>(N));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> board[i][j];
		}
	}
	white = blue = 0;
}

void divide(int n, int x, int y) {
	if (n == 1) {
		if (board[x][y] == 0) {
			white++;
			return;
		}
		else {
			blue++;
			return;
		}
	}
	for (int i = x; i < x + n; i++) {
		for (int j = y; j < y + n; j++) {
			if (board[x][y] != board[i][j]) {
				divide(n / 2, x + dx[0] * (n / 2), y + dy[0] * (n / 2));
				divide(n / 2, x + dx[1] * (n / 2), y + dy[1] * (n / 2));
				divide(n / 2, x + dx[2] * (n / 2), y + dy[2] * (n / 2));
				divide(n / 2, x + dx[3] * (n / 2), y + dy[3] * (n / 2));
				return;
			}
		}
	}
	if (board[x][y] == 0) {
		white++;
		return;
	}
	else {
		blue++;
		return;
	}
}

void solve() {
	divide(N, 0 ,0);
	cout << white << "\n" << blue << "\n";
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	input();
	solve();

	return 0;
}