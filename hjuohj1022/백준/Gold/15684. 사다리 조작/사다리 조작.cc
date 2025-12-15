#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, H, M;
vector<vector<bool>> ladder;
int result = -1;

bool check() {
	for (int start = 1; start <= N; start++) {
		int k = start;
		for (int row = 1; row <= H; row++) {
			if (ladder[row][k]) k++; // 오른쪽으로 이동
			else if (ladder[row][k-1]) k--; // 왼쪽으로 이동
		}
		if (k != start) return false;
	}
	return true;
}

bool canAddLadder(int row, int col) {
	return !ladder[row][col] && !ladder[row][col - 1] && !ladder[row][col + 1]; // 가로선 설치 가능 조건
}

void dfs(int cnt, int row, int col) {
	if (result != -1 && cnt >= result) return;
	if (check()) { //모든 새로줄이 같은 새로줄에 도달했을 경우
		if (result == -1 || result > cnt) result = cnt;
		return;
	}

	if (cnt == 3) return;

	for (int i = row; i <= H; i++) {
		for (int j = (i == row ? col : 1); j < N; j++) {
			if (canAddLadder(i, j)) {
				ladder[i][j] = true;
				dfs(cnt + 1, i, j + 2);
				ladder[i][j] = false;
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	cin >> N >> M >> H;
	ladder.assign(H + 2, vector<bool>(N + 2, false));

	for (int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		ladder[a][b] = true;
	}

	dfs(0, 1, 1);
	cout << result << endl;
	return 0;
}