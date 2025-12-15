#include <iostream>
#include <vector>

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
		dfs(i, 0);
	}

	cout << diameter << "\n";

	return 0;
}