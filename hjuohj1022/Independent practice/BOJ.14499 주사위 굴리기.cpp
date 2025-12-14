#include <iostream>
#include <vector>

using namespace std;

const int EAST = 1;
const int WEST = 2;
const int NORTH = 3;
const int SOUTH = 4;

int N, M, x, y, K;
vector<vector<int>> board;
vector<int> commands;
vector<int> dice(6,0);
int dx[5] = {0,0,0,-1,1 };
int dy[5] = {0,1,-1,0,0};

void movingDice(int dir) {
	vector<int> temp = dice;
	if (dir == EAST) {
		dice[0] = temp[3];
		dice[2] = temp[0];
		dice[5] = temp[2];
		dice[3] = temp[5];
	}
	else if (dir == WEST) {
		dice[0] = temp[2];
		dice[2] = temp[5];
		dice[5] = temp[3];
		dice[3] = temp[0];
	}
	else if (dir == NORTH) {
		dice[0] = temp[4];
		dice[4] = temp[5];
		dice[5] = temp[1];
		dice[1] = temp[0];
	}
	else {
		dice[0] = temp[1];
		dice[1] = temp[5];
		dice[5] = temp[4];
		dice[4] = temp[0];
	}
}

void input() {
	cin >> N >> M >> x >> y >> K;
	board.resize(N,vector<int>(M,0));
	commands.resize(K);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> board[i][j];
		}
	}
	for (int i = 0; i < K; i++) {
		cin >> commands[i];
	}
}

void solve() {
	int currx = x;
	int curry = y;

	int temp;
	for (int i = 0; i < K; i++) {
		int nx = currx + dx[commands[i]];
		int ny = curry + dy[commands[i]];
		if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

		currx = nx; curry = ny;
		movingDice(commands[i]);
		if (board[currx][curry] == 0) {
			board[currx][curry] = dice[5];
		}
		else {
			dice[5] = board[currx][curry];
			board[currx][curry] = 0;
		}
		cout << dice[0] << "\n";
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	input();
	solve();

	return 0;
}