#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n;
vector<vector<pair<int, int>>> graph;
vector<bool> visited;
int diameter = 0;

void dfs(int node, int weight) {
	visited[node] = true;
	for (int i = 0; i < graph[node].size(); i++) {
		if (!visited[graph[node][i].first]) {
			dfs(graph[node][i].first, weight + graph[node][i].second);
			visited[graph[node][i].first] = false;
		}
	}
	diameter = max(diameter, weight);
}

void bfs(int node) {
	queue<pair<int, int>> q;
	visited[node] = true;
	q.push({ node, 0 });
	while (!q.empty()) {
		auto [n, w] = q.front();
		q.pop();
		bool is_leaf = true;
		for (auto& e : graph[n]) {
			int next = e.first, cost = e.second;
			if (!visited[next]) {
				visited[next] = true;
				q.push({ next, w + cost });
				is_leaf = false;
			}
		}
		if (is_leaf) diameter = max(diameter, w);
	}
}

int main() {
	cin >> n;
	graph.resize(n + 1);
	visited.resize(n + 1);
	for (int i = 0; i < n - 1; i++) {
		int s, e, w;
		cin >> s >> e >> w;
		graph[s].push_back({e, w});
		graph[e].push_back({s, w});
	}
	
	for (int i = 1; i < n + 1; i++) {
		if (graph[i].size() != 1) continue;

		// bfs 전용
		//fill(visited.begin(), visited.end(), false);
		//bfs(i);

		// dfs 전용
		dfs(i, 0);
	}

	cout << diameter << "\n";

	return 0;
}