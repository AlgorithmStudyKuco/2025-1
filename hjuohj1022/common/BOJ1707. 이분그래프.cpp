#include <iostream>
#include <vector>
#include <string>

using namespace std;

bool dfs(int node, vector<vector<int>>& graph, vector<int>& color, int c) {
	if (color[node] != 0) return color[node] == c;
	color[node] = c;
	for (int next : graph[node]) {
		if (!dfs(next, graph, color, -c)) return false;
	}
	return true;
}

int main() {
	int K;
	cin >> K;
	for (int i = 0; i < K; i++) {
		int V, E;
		cin >> V >> E;
		vector<vector<int>> graph(V + 1);
		vector<int> color(V + 1, 0); // 0: 미방문, 1: 빨강, -1: 파랑

		// 인접리스트 구성
		for (int i = 0; i < E; i++) {
			int u, v;
			cin >> u >> v;
			graph[u].push_back(v);
			graph[v].push_back(u);
		}

		bool bipartite = true;
		for (int i = 1; i < V + 1; i++) {
			if (color[i] == 0) { // 아직 방문하지 않은 노드
				if (!dfs(i, graph, color, 1)) {
					bipartite = false;
					break;
				}
			}
		}

		cout << (bipartite ? "YES" : "NO") << '\n';
	}
	return 0;
}