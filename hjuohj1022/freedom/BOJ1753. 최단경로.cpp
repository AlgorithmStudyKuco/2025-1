﻿// 반복문을 활용한 풀이

#include <iostream>
#include <vector>
#include <limits>

using namespace std;

int V, E, start;
vector<vector<pair<int, int>>> graph;
vector<bool> visited;
const int INF = numeric_limits<int>::max();

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> V >> E >> start;
    graph.resize(V + 1);
    visited.assign(V + 1, false);

    for (int i = 0; i < E; i++) {
        int s, e, w;
        cin >> s >> e >> w;
        graph[s].push_back({ e, w });
    }

    vector<int> dist(V + 1, INF);
    dist[start] = 0;

    for (int i = 0; i < V; i++) {
        int minDist = INF;
        int u = -1;

        for (int j = 1; j <= V; j++) {
            if (!visited[j] && dist[j] < minDist) {
                minDist = dist[j];
                u = j;
            }
        }

        if (u == -1) break;
        visited[u] = true;

        for (auto& edge : graph[u]) {
            int v = edge.first;
            int w = edge.second;
            if (visited[v]) continue;
            if (dist[u] != INF && dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
            }
        }
    }

    for (int i = 1; i <= V; i++) {
        if (dist[i] == INF) cout << "INF\n";
        else cout << dist[i] << "\n";
    }
}


// -------------------------------------------
// 우선순위 큐를 활용한 풀이

#include <iostream>
#include <vector>
#include <queue>
#include <limits>

using namespace std;

int V, E, start;
vector<vector<pair<int, int>>> graph;
vector<int> dist;
const int INF = numeric_limits<int>::max();

struct Compare {
    bool operator()(const pair<int, int>& a, const pair<int, int>& b) {
        return a.second > b.second;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> V >> E >> start;
    graph.resize(V + 1);
    dist.assign(V + 1, INF);
    dist[start] = 0;

    for (int i = 0; i < E; i++) {
        int s, e, w;
        cin >> s >> e >> w;
        graph[s].push_back({ e, w });
    }

    priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> pq;
    pq.push({ start, 0 });

    while (!pq.empty()) {
        int u = pq.top().first;
        int cur_dist = pq.top().second;
        pq.pop();

        // 현재 노드의 거리가 갱신 전 정보라면 무시
        if (cur_dist > dist[u]) continue;

        for (auto& edge : graph[u]) {
            int v = edge.first;
            int w = edge.second;
            int next_dist = dist[u] + w;

            // 더 짧은 경로 발견 시 갱신
            if (next_dist < dist[v]) {
                dist[v] = next_dist;
                pq.push({ v, next_dist });
            }
        }
    }

    for (int i = 1; i <= V; i++) {
        if (dist[i] == INF) cout << "INF\n";
        else cout << dist[i] << "\n";
    }
}
