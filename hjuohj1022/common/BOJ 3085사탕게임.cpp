#include <iostream>
#include <vector>
#include <algorithm> // swap �Լ� Ȱ��(�迭�� �ε����� �ٲ�)
#include <string> 

using namespace std;

int verdict(vector<string> board, int N, int x, int y) {
	int maxLen = 1, currLen = 1;
	// ���� �Ǻ�
	for (int i = 1; i < N; i++) {
		if (board[y][i] == board[y][i - 1])
			currLen++;
		else {
			maxLen = max(maxLen, currLen);
			currLen = 1;
		}
		if (maxLen == N) return N;
	}
	// ������ �ݺ��� ���� �ִ� ���̸� �����ϱ� ���� ����
	maxLen = max(maxLen, currLen);

	// ���� �ִ� ���̸� �Ǵ��ϱ� ������ ���� ���̸� �ʱ�ȭ
	currLen = 1;
	// ���� �Ǻ�
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
	// ������ ��ĭ�� ���� ���� ������ ��ĭ�� �ֺ��� �����ϱ� ���� dx, dy�� Ȱ��
	int dx[4] = { 0, 1, 0, -1 };
	int dy[4] = { 1, 0, -1, 0 };
	int max_eat = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < 4; k++) {
				int nx = j + dx[k];
				int ny = i + dy[k];
				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;

				// ������ ��ĭ�� ������ ��ȯ
				swap(board[i][j], board[ny][nx]);
				int eaten_candy = verdict(board, N, i, j);
				max_eat = max(max_eat, eaten_candy);
				// ���� ������ ���� ������� ��������
				swap(board[i][j], board[ny][nx]);
			}
		}
	}
	return max_eat;
}


int main() {
	// �������� �����ϴ� �Է� �����͸� �Է�
	int N;
	cin >> N;
	vector<string> board;
	board.resize(N);
	for (int i = 0; i < N; i++) {
		cin >> board[i];
	}
	// ������ �Լ� ȣ��
	int result = Bomboni(board, N);
	cout << result << endl;
}