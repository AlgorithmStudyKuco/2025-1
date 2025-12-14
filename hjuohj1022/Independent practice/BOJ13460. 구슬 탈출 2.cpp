#include <iostream>
#include <vector>
#include <string>
#include <queue>

using namespace std;

int N, M;
vector<string> board;
bool visited[11][11][11][11];

struct State {
	int rx, ry, bx, by, depth;
};

struct Ball {
	int x, y, move;
};

int dx[4] = {0, 1, 0, -1}; 
int dy[4] = {1, 0, -1, 0}; // 남, 동, 북, 서

Ball Move(int x, int y, int dir) {
	int move = 0;
	while (true) {
		if (board[y + dy[dir]][x + dx[dir]] == '#') break;
		move++;
		x = x + dx[dir];
		y = y + dy[dir];
		if (board[y][x] == 'O') break;
	}
	return { x, y, move };
}

int bfs(int rx, int ry, int bx, int by) {
	queue<State> q;
	q.push({rx, ry, bx, by, 0});
	visited[rx][ry][bx][by] = true;
	while (!q.empty()) {
		State state = q.front();
		q.pop();
		if (state.depth >= 10) continue;

		for (int i = 0; i < 4; ++i) {
			Ball rNext = Move(state.rx, state.ry, i);
			Ball bNext = Move(state.bx, state.by, i);

			if (rNext.move == 0 && bNext.move == 0) continue;
			if (board[bNext.y][bNext.x] == 'O') continue;
			if (board[rNext.y][rNext.x] == 'O') return state.depth + 1;

			// 빨간 공과 파란 공이 겹치는 경우
			if (rNext.y == bNext.y && rNext.x == bNext.x) {
				if (rNext.move > bNext.move) {
					rNext.y -= dy[i];
					rNext.x -= dx[i];
				}
				else {
					bNext.y -= dy[i];
					bNext.x -= dx[i];
				}
			}

			if (!visited[rNext.x][rNext.y][bNext.x][bNext.y]) {
				visited[rNext.x][rNext.y][bNext.x][bNext.y] = true;
				q.push({ rNext.x, rNext.y, bNext.x, bNext.y, state.depth + 1 });
			}
		}
	}

	return -1;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;
	board.resize(N);

	for (int i = 0; i < N; ++i) {
		cin >> board[i];
	}

	int rx, ry, bx, by;
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < M; ++j) {
			if (board[i][j] == 'B') {
				bx = j;
				by = i;
			}
			else if (board[i][j] == 'R') {
				rx = j;
				ry = i;
			}
		}
	}

	cout << bfs(rx, ry, bx, by) << "\n";
	return 0;
}