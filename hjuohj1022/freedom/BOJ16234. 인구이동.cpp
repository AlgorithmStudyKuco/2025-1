#include <iostream>
#include <vector>

using namespace std;

int N, L, R;
vector<vector<int>> countries;

int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, 1, 0, -1 };

void dfs(int x, int y, vector<vector<bool>>& visited, vector<pair<int, int>>& coalition) {
	coalition.push_back({ x,y });
	visited[y][x] = true;

	// 국경 오픈
	for (int i = 0; i < 4; i++) {
		int nx, ny;
		nx = x + dx[i]; ny = y + dy[i];
		if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
		if (visited[ny][nx]) continue;
		int difference = abs(countries[y][x] - countries[ny][nx]);
		if (difference <= R && difference >= L) {
			dfs(nx, ny, visited, coalition);
		}
	}
}

int main() {
	cin >> N >> L >> R;
	countries.resize(N, vector<int>(N));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> countries[i][j];
		}
	}
	int days = 0;
	while (true) {
		vector<vector<bool>> visited(N, vector<bool>(N, false));
		bool isMoved = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					vector<pair<int, int>> coalition;
					dfs(j, i, visited, coalition);

					if (coalition.size() > 1) {
						int sum = 0;
						// 연합의 인구의 총합을 계산
						for (const auto& c : coalition) {
							sum += countries[c.second][c.first];
						}
						int population = sum / coalition.size();
						// 인구 이동
						for (const auto& c : coalition) {
							countries[c.second][c.first] = population;
						}
						isMoved = true;
					}
				}
			}
		}
		if (!isMoved) break;
		days++;
	}
	cout << days << "\n";
	return 0;
}