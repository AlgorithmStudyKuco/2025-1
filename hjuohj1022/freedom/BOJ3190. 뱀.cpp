#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	int N, K;
	cin >> N >> K;
	vector<vector<int>> board(N + 1, vector<int>(N + 1, 0));
	vector<pair<int, int>> snake; 
	snake.push_back({ 1, 1 });
	board[1][1] = 1;

	for (int i = 0; i < K; i++) {
		int x, y;
		cin >> x >> y;
		board[x][y] = -1;
	}

	int dir = 0; // 처음에는 오른쪽을 바라보고 있음
	int L;
	cin >> L;
	vector<pair<int, char>> turns(L);
	for (int i = 0; i < L; i++) {
		int x;
		char c;
		cin >> x >> c;
		turns[i] = { x, c };
	}

	int time = 0;
	int turnIndex = 0;
	while (true) {
		time++;
		int headX = snake.back().first;
		int headY = snake.back().second;

		if (dir == 0) headY++;
		else if (dir == 1) headX++;
		else if (dir == 2) headY--;
		else if (dir == 3) headX--;

		// 벽이거나 자기 몸에 부딪히는지 확인
		if (headX < 1 || headX > N || headY < 1 || headY > N || board[headX][headY] == 1) {
			break;
		}

		// 사과를 먹는지 확인
		if (board[headX][headY] == -1) {
			board[headX][headY] = 1;
			snake.push_back({ headX, headY });
		} else { // 사과를 먹지 않은 경우
			board[headX][headY] = 1;
			snake.push_back({ headX, headY });
			auto tail = snake.front();
			board[tail.first][tail.second] = 0;
			snake.erase(snake.begin());
		}

		// 방향 전환
		if (turnIndex < L && time == turns[turnIndex].first) {
			if (turns[turnIndex].second == 'D') {
				dir = (dir + 1) % 4;
			} else {
				dir = (dir + 3) % 4;
			}
			turnIndex++;
		}
	}
	
	cout << time << endl;
	return 0;
}