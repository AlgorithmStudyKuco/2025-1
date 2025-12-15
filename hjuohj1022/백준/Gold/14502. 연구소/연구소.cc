#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int N, M;
vector<vector<int>> map;
vector<pair<int, int>> empty_spaces; // 빈 칸 배열 
vector<pair<int, int>> viruses;

int dx[] = { -1, 1, 0, 0 };
int dy[] = { 0, 0, -1, 1 };

void spread(vector<vector<int>>& temp) {
	queue<pair<int, int>> q;
	for (auto& v : viruses) q.push(v);

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			if (temp[nx][ny] == 0) {
				temp[nx][ny] = 2;
				q.push({ nx, ny });
			}
		}
	}
}

int count_safe(vector<vector<int>>& temp) {
	int safe = 0;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			if (temp[i][j] == 0) safe++;
	return safe;
}

int main() {
	cin >> N >> M;
	map.resize(N, vector<int>(M));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> map[i][j];
			if (map[i][j] == 0) empty_spaces.push_back({ i, j });
			else if (map[i][j] == 2) viruses.push_back({ i, j });
		}
	}
	int max_safe = 0;
	int total_empty = empty_spaces.size();
	vector<int> indices(total_empty);
	for (int i = 0; i < total_empty; i++) indices[i] = i;
	vector<bool> comb(total_empty, false);

	// 조합 생성: 3개 선택
	fill(comb.end() - min(3, total_empty), comb.end(), true);
	do {
		// 깊은 복사
		vector<vector<int>> temp = map;

		for (int i = 0; i < total_empty; i++) {
			if (comb[i]) {
				int x = empty_spaces[i].first;
				int y = empty_spaces[i].second;
				temp[x][y] = 1;
			}
		}

		spread(temp);
		int safe = count_safe(temp);
		max_safe = max(max_safe, safe);

	} while (next_permutation(comb.begin(), comb.end()));

	cout << max_safe << "\n";
	return 0;
}