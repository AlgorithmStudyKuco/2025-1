#include <iostream>
#include <vector>
#include <algorithm> // swap 함수 활용(배열의 인덱스를 바꿈)
#include <string> 

using namespace std;

int verdict(vector<string> board, int N, int x, int y) {
	int maxLen = 1, currLen = 1;
	// 행을 판별
	for (int i = 1; i < N; i++) {
		if (board[y][i] == board[y][i - 1])
			currLen++;
		else {
			maxLen = max(maxLen, currLen);
			currLen = 1;
		}
		if (maxLen == N) return N;
	}
	// 마지막 반복문 이후 최대 길이를 판정하기 위해 삽입
	maxLen = max(maxLen, currLen);

	// 새로 최대 길이를 판단하기 때문에 현재 길이를 초기화
	currLen = 1;
	// 열을 판별
	for (int i = 1; i < N; i++) {
		if (board[i][x] == board[i - 1][x])
			currLen++;
		else {
			maxLen = max(maxLen, currLen);
			currLen = 1;
		}
		if (maxLen == N) return N;
	}
	return max(maxLen, currLen);
}

int Bomboni(vector<string> board, int N) {
	// 인접한 두칸을 고르기 위해 선택한 한칸의 주변을 선택하기 위해 dx, dy를 활용
	int dx[4] = { 0, 1, 0, -1 };
	int dy[4] = { 1, 0, -1, 0 };
	int max_eat = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < 4; k++) {
				int nx = j + dx[k];
				int ny = i + dy[k];
				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;

				// 인접한 두칸의 사탕을 교환
				swap(board[i][j], board[ny][nx]);
				int eaten_candy = verdict(board, N ,j ,i);
				max_eat = max(max_eat, eaten_candy);
				// 다음 연산을 위해 원래대로 돌려놓음
				swap(board[i][j], board[ny][nx]);
			}
		}
	}
	return max_eat;
}


int main() {
    // 문제에서 제공하는 입력 데이터를 입력
	int N;
	cin >> N;
	vector<string> board;
	board.resize(N);
	for (int i = 0; i < N; i++) {
		cin >> board[i];
	}
	// 봄보니 함수 호출
	int result = Bomboni(board, N);
	cout << result << endl;
}