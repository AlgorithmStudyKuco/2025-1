#include <iostream>
#include <queue>
#include <vector>
#include <limits>
#include <algorithm>

using namespace std;

const int INF = numeric_limits<int>::max();
int N, M, X; // N = 학생수, M = 도로의 개수, X = 도착지점

void dijkstra(int node, const vector<vector<pair<int, int>>>& graph, vector<int>& distances) {
	
	distances.assign(N + 1, INF);
	distances[node] = 0;

	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
	pq.push({ 0, node });

	while (!pq.empty()) {
		int dist = pq.top().first;
		int node = pq.top().second;
		pq.pop();

		// 이미 처리된 노드이거나, 더 짧은 경로를 찾은 경우 스킵(visited 포함되어 있음)
		if (dist > distances[node]) continue;

		for (const auto& next : graph[node]) {
			int next_node = next.first;
			int next_dist = next.second;
			int weight = dist + next_dist;

			if (weight < distances[next_node]) {
				distances[next_node] = weight;
				pq.push({ weight, next_node });
			}
		}
	}
}
 
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M >> X;

	vector<vector<pair<int, int>>> graph(N + 1);
	vector<vector<pair<int, int>>> reverse_graph(N + 1);

	for (int i = 0; i < M; i++) {
		int s, e, w;
		cin >> s >> e >> w;
		graph[s].push_back({e, w});
		reverse_graph[e].push_back({s, w});
	}

	// X에서 집까지의 최단거리 계산
	vector<int> dist_from_X(N + 1);
	dijkstra(X, graph, dist_from_X);

	// 집에서 X까지의 최단거리 계산
	vector<int> dist_to_X(N + 1);
	dijkstra(X, reverse_graph, dist_to_X);

	int max_dist = 0;
	for (int i = 1; i <= N; i++) {
		max_dist = max(max_dist, dist_from_X[i] + dist_to_X[i]);
	}

	cout << max_dist << "\n";
	return 0;
}