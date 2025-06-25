#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<vector<int>> board;
int result = 0;

// 실제로 활용하지는 않지만 방향의 파악하기 위해 사용
int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

void move(int dir, vector<vector<int>>& board) {
	if (dir == 0) {
		for (int i = 0; i < N; i++) {
			vector<int> natural;
			for (int j = N - 1; j >= 0; j--) {
				if (board[i][j] != 0)
					natural.push_back(board[i][j]);
			}
			vector<int> merged;
			for (int k = 0; k < natural.size(); ) {
				if (k + 1 < natural.size() && natural[k] == natural[k + 1]) {
					merged.push_back(natural[k] * 2);
					k += 2;
				}
				else {
					merged.push_back(natural[k]);
					k++;
				}
			}
			for (int j = 0; j < N; j++) {
				if (j < merged.size()) board[i][N - 1 - j] = merged[j];
				else board[i][N - 1 - j] = 0;
			}
		}
	}

	else if (dir == 1) { 
		for (int j = 0; j < N; j++) {
			vector<int> natural;
			for (int i = N - 1; i >= 0; i--) {
				if (board[i][j] != 0) natural.push_back(board[i][j]);
			}

			vector<int> merged;
			for (int k = 0; k < natural.size(); ) {
				if (k + 1 < natural.size() && natural[k] == natural[k + 1]) {
					merged.push_back(natural[k] * 2);
					k += 2;
				}
				else {
					merged.push_back(natural[k]);
					k++;
				}
			}

			for (int i = 0; i < N; i++) {
				if (i < merged.size()) board[N - 1 - i][j] = merged[i];
				else board[N - 1 - i][j] = 0;
			}
		}
	}
	else if (dir == 2) {
		for (int i = 0; i < N; i++) {
			vector<int> natural;
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 0) natural.push_back(board[i][j]);
			}

			vector<int> merged;
			for (int k = 0; k < natural.size(); ) {
				if (k + 1 < natural.size() && natural[k] == natural[k + 1]) {
					merged.push_back(natural[k] * 2);
					k += 2;
				}
				else {
					merged.push_back(natural[k]);
					k++;
				}
			}

			for (int j = 0; j < N; j++) {
				if (j < merged.size()) board[i][j] = merged[j];
				else board[i][j] = 0;
			}
		}
	}
	else if (dir == 3) {
		for (int j = 0; j < N; j++) {
			vector<int> natural;
			for (int i = 0; i < N; i++) {
				if (board[i][j] != 0) natural.push_back(board[i][j]);
			}

			vector<int> merged;
			for (int k = 0; k < natural.size(); ) {
				if (k + 1 < natural.size() && natural[k] == natural[k + 1]) {
					merged.push_back(natural[k] * 2);
					k += 2;
				}
				else {
					merged.push_back(natural[k]);
					k++;
				}
			}

			for (int i = 0; i < N; i++) {
				if (i < merged.size()) board[i][j] = merged[i];
				else board[i][j] = 0;
			}
		}
	}
}

void dfs(int idx, vector<vector<int>>& board) {
	if (idx == 5) {
		int max_number = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max_number = max(max_number, board[i][j]);
			}
		}
		result = max(max_number, result);
		return;
	}

	for (int i = 0; i < 4; i++) {
		vector<vector<int>> next_board = board;
		move(i, next_board);
		dfs(idx + 1, next_board);
	}
}

int main() {
	cin >> N;
	board.resize(N, vector<int>(N));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++)
			cin >> board[i][j];
	}
	dfs(0, board);
	cout << result << "\n";
	return 0;
}