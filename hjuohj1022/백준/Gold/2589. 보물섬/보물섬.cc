#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

struct index{
	int x, y, d;
};

int H, W;
vector<vector<char>> board;
vector<vector<bool>> visited;
int result;

int dx[4] = {0,1,0,-1};
int dy[4] = {1,0,-1,0};

void input() {
	cin >> H >> W;
	board.resize(H, vector<char>(W));
	for (int i = 0; i < H; i++) {
		for (int j = 0; j < W; j++) {
			cin >> board[i][j];
		}
	}
	result = 0;
}

void bfs(int x, int y) {
	visited.clear();
	visited.resize(H, vector<bool>(W, false));

	int dis = 0;
	visited[x][y] = true;
	queue<index> q;
	q.push({x,y,0});
	while (!q.empty()) {
		index idx = q.front();
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = idx.x + dx[i];
			int ny = idx.y + dy[i];

			if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
			if (board[nx][ny] == 'W' || visited[nx][ny]) continue;

			else {
				visited[nx][ny] = true;
				q.push({nx, ny, idx.d + 1});
			}
		}
		result = max(idx.d, result);
	}
}

void solve() {
	for (int i = 0; i < H; i++) {
		for (int j = 0; j < W; j++) {
			if (board[i][j] == 'L')	bfs(i, j);
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