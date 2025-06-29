#include <iostream>
#include <vector>
#include <limits>
#include <climits>

using namespace std;

const long long INF = numeric_limits<long long>::max(); // 오버플로우 방지

int main() {
    int N, M;
    cin >> N >> M;
    vector<vector<pair<int, int>>> graph(N + 1);

    for (int i = 0; i < M; i++) {
        int f, t, w;
        cin >> f >> t >> w;
        graph[f].push_back({ t, w });
    }

    vector<long long> dist(N + 1, INF);
    dist[1] = 0;

    // 벨만-포드 알고리즘
    for (int i = 1; i <= N - 1; i++) {
        for (int j = 1; j <= N; j++) {
            if (dist[j] == INF) continue;
            for (const auto& edge : graph[j]) {
                int next = edge.first;
                int weight = edge.second;
                if (dist[j] + weight < dist[next]) {
                    dist[next] = dist[j] + weight;
                }
            }
        }
    }

    // 음수 사이클 검출
    bool negative_cycle = false;
    for (int j = 1; j <= N; j++) {
        if (dist[j] == INF) continue;
        for (const auto& edge : graph[j]) {
            int next = edge.first;
            int weight = edge.second;
            if (dist[j] + weight < dist[next]) {
                negative_cycle = true;
                break;
            }
        }
        if (negative_cycle) break;
    }

    if (negative_cycle) {
        cout << -1 << endl;
    }
    else {
        for (int i = 2; i <= N; i++) {
            if (dist[i] == INF) cout << -1 << "\n";
            else cout << dist[i] << "\n";
        }
    }

    return 0;
}
