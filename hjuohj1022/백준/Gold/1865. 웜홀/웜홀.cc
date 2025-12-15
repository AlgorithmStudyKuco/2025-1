	#include <iostream>
#include <vector>
#include <queue>
#include <limits>

using namespace std;

int T;
const int INF = numeric_limits<int>::max();

bool bellman_ford(int node, const vector<vector<pair<int, int>>>&graph, vector<int>& distances, int N) {
	distances[node] = 0;

	for (int i = 0; i < N - 1; i++) {
		for (int j = 1; j <= N; j++) {
			for (auto& edge : graph[j]) {
				int v = edge.first;
				int weight = edge.second;
				int cost = distances[j] + weight;
				if (distances[j] != INF && cost < distances[v]) {
					distances[v] = cost;
				}
			}
		}
	}

	// 음수 사이클 확인
	for (int i = 1; i <= N; ++i) {
		for (auto& edge : graph[i]) {
			int v = edge.first;
			int weight = edge.second;
			if (distances[i] != INF && distances[i] + weight < distances[v]) return true;
		}
	}

	return false;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;
	while (T--) {
		int N, M, W;
		cin >> N >> M >> W;
		vector<vector<pair<int, int>>> graph(N + 1);
		for (int i = 0; i < M; i++) {
			int s, e, t;
			cin >> s >> e >> t;
			graph[s].push_back({e, t});
			graph[e].push_back({s, t});
		}
		for (int i = 0; i < W; i++) {
			int s, e, t;
			cin >> s >> e >> t;
			graph[s].push_back({e, -t});
		}

		bool isCycle = false;
		vector<int> distances(N + 1, INF);
		for (int i = 1; i <= N; i++){
			if (distances[i] == INF) {
				if (bellman_ford(i, graph, distances, N)) {
					isCycle = true;
					break;
				}
			}
		}
		cout << (isCycle ? "YES" : "NO") << "\n";
	}

	return 0;
}