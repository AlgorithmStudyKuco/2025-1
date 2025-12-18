#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int N;
vector<vector<int>> graph;
vector<int> times;
vector<int> degree;
vector<int> result;

void input() {
	cin >> N;
	graph.resize(N + 1);
	degree.resize(N + 1);
	times.resize(N + 1);
	result.resize(N + 1);
	for (int i = 1; i <= N; i++) {
		int time;
		cin >> time;
		times[i] = time;
		int deg = 0;
		while (true) {
			int idx;
			cin >> idx;
			if (idx == -1) {
				degree[i] = deg;
				break;
			}
			graph[idx].push_back(i);
			deg++;
		}
	}
}

void topologicalSort() {
	queue<int> q;
	for (int i = 1; i <= N; i++) {
		if (degree[i] == 0) q.push(i);
		result[i] += times[i];
	}
	while (!q.empty()) {
		int idx = q.front();
		q.pop();
		for (int next : graph[idx]) {
			result[next] = max(result[next], result[idx] + times[next]);
			degree[next]--;
			if (degree[next] == 0) q.push(next);
		}
	}
}

void solve() {
	topologicalSort();
	for (int i = 1; i <= N; i++) {
		cout << result[i] << "\n";
	}
	return;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	input();
	solve();

	return 0;
}