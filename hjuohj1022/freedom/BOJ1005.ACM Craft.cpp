#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int T;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;
	while (T--) {
		int N, K;
		cin >> N >> K;

		vector<int> times(N + 1, 0);
		for (int i = 1; i <= N; ++i) {
			cin >> times[i];
		}

		vector<vector<int>> graph(N + 1);
		vector<int> degrees(N + 1, 0);
		for (int i = 0; i < K; ++i) {
			int s, e;
			cin >> s >> e;
			graph[s].push_back(e);
			degrees[e]++;
		}

		int W;
		cin >> W;

		vector<int> dp(N + 1, 0);
		queue<int> q;

		for (int i = 1; i <= N; ++i) {
			if (degrees[i] == 0) q.push(i);
			dp[i] = times[i];
		}

		while (!q.empty()) {
			int node = q.front();
			q.pop();

			for (int next : graph[node]) {
				dp[next] = max(dp[node] + times[next], dp[next]);
				degrees[next]--;

				if (degrees[next] == 0) q.push(next);
			}
		}
		cout << dp[W] << "\n";
	}
	return 0;
}