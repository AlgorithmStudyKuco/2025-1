#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct CCTV {
	int x, y, type;
};

int N, M;
vector<CCTV> cctvs;
vector<vector<int>> board;
int result = 100;

int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

// CCTV 유형별 방향 설정
vector<vector<vector<int>>> cctv_dir = {
	{}, // 0번 CCTV는 없음
	{{0}, {1}, {2}, {3}},
	{{0,2}, {1,3}},   
	{{0,1}, {1,2}, {2,3}, {3,0}},
	{{0,1,2}, {1,2,3}, {2,3,0}, {3,0,1}},
	{{0,1,2,3}}
};

void watch(int dir, int x, int y, vector<vector<int>>& board) {
	int nx = x + dx[dir];
	int ny = y + dy[dir];
	while (0 <= nx && nx < N && 0 <= ny && ny < M) {
		if (board[nx][ny] == 6) break;
		if (board[nx][ny] == 0) board[nx][ny] = 7;
		nx += dx[dir];
		ny += dy[dir];
	}
}

void dfs(int idx, vector<vector<int>>& board) {
	if (result == 0) return;

	if (idx == cctvs.size()) {
		int blind_spots = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 0) blind_spots++;
			}
		}
		result = min(result, blind_spots);
		return;
	}

	int x = cctvs[idx].x;
	int y = cctvs[idx].y;
	int type = cctvs[idx].type;
	for (const auto& dirs : cctv_dir[type]) {
		vector<vector<int>> next_board = board;
		for (int dir : dirs) {
			watch(dir, x, y, next_board);
		}
		dfs(idx + 1, next_board);
	}
}

int main() {
	cin >> N >> M;
	board.resize(N, vector<int>(M));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> board[i][j];
			if (1 <= board[i][j] && board[i][j] <= 5)
				cctvs.push_back({ i, j, board[i][j] });
		}
	}
	dfs(0, board);
	cout << result << "\n";
	return 0;
}