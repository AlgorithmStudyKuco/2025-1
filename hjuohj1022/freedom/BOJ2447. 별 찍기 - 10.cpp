#include <iostream>
#include <vector>

using namespace std;

int N, k;
vector<vector<char>> board;

void make_star(int x, int y, int n) {
	if (n == 1) {
		board[x][y] = '*';
		return;
	}
	int size = n / 3;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			if (i == 1 && j == 1) {
				for (int dx = 0; dx < size; dx++) {
					for (int dy = 0; dy < size; dy++) {
						board[size + dx][size + dy] = ' ';
					}
				}
			}
			else {
				make_star(x + i * size, y + j * size, size);
			}
		}
	}
}

int main() {
	cin >> N;
	k = (int)(log(N)/log(3));
	board.resize(N, vector<char>(N, ' '));
	make_star(0, 0, N);
	
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << board[i][j];
		}
		cout << "\n";
	}
	return 0;
}