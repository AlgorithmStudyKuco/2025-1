#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int M, N;
vector<vector<int>> graph;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};
int days = 0;

bool is_perfect() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (graph[i][j] == 0) return false;
		}
	}
	return true;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> M >> N;
	graph.resize(N, vector<int>(M));
	queue<pair<int, int>> q;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> graph[i][j];
			if (graph[i][j] == 1) q.push({i, j});
		}
	}

	while (!q.empty()) {
		bool matured = false;
		int spread_nums = q.size();
		for (int j = 0; j < spread_nums; j++) {
			int y = q.front().first;
			int x = q.front().second;
			q.pop();
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < M && nx >= 0 && ny < N && ny >= 0) {
					if (graph[ny][nx] == 0) {
						q.push({ny, nx});
						graph[ny][nx] = 1;
						matured = true;
					}
				}
			}	
		}
		if (matured) days++;
	}

	cout << (is_perfect() ? days : -1) << "\n";
	return 0;
}