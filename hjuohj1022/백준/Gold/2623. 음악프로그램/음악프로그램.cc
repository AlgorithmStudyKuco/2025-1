#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int main() {
  int N, M;
  cin >> N >> M;
  vector<vector<int>> graph(N + 1);
  vector<int> degree(N + 1, 0);

  for (int i = 0; i < M; i++) {
    int k;
    cin >> k;
    vector<int> seq(k);
    for (int j = 0; j < k; j++) cin >> seq[j];
    for (int j = 0; j < k - 1; j++) {
      graph[seq[j]].push_back(seq[j + 1]);
      degree[seq[j + 1]]++;
    }
  }

  queue<int> q;
  for (int i = 1; i <= N; i++) {
    if (degree[i] == 0) q.push(i);
  }

  vector<int> result;
  while (!q.empty()) {
    int now = q.front();
    q.pop();
    result.push_back(now);

    for (int nxt : graph[now]) {
      degree[nxt]--;
      if (degree[nxt] == 0) q.push(nxt);
    }
  }

  if (result.size() != N) {
    cout << 0 << "\n";
  }
  else {
    for (int x : result) cout << x << "\n";
  }

  return 0;
}
