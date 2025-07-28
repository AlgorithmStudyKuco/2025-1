#include <iostream>
#include <vector>
using namespace std;

int N, M;
vector<vector<int>> graph;
vector<int> plan;

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  cin >> N >> M;

  graph.resize(N + 1, vector<int>(N + 1, 0));
  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
      cin >> graph[i][j];
    }
  }

  for (int i = 1; i <= N; i++) {
    graph[i][i] = 1;
  }

  plan.resize(M);
  for (int i = 0; i < M; i++) {
    cin >> plan[i];
  }

  // 플로이드-워셜 알고리즘
  for (int k = 1; k <= N; k++) {    
    for (int i = 1; i <= N; i++) {    
      for (int j = 1; j <= N; j++) {
        if (graph[i][k] && graph[k][j])
          graph[i][j] = 1;
      }
    }
  }

  bool possible = true;
  for (int i = 0; i < M - 1; i++) {
    if (!graph[plan[i]][plan[i + 1]]) {
      possible = false;
      break;
    }
  }

  cout << (possible ? "YES" : "NO") << "\n";

  return 0;
}

//--------------------------------

#include <iostream>
#include <vector>
using namespace std;

vector<int> parent;

int find(int x) {
  if (parent[x] == x) return x;
  return parent[x] = find(parent[x]);
}

void unite(int a, int b) {
  a = find(a);
  b = find(b);
  if (a != b) parent[b] = a;
}

int main() {
  int N, M;
  cin >> N >> M;
  parent.resize(N + 1);
  for (int i = 1; i <= N; ++i) parent[i] = i;

  for (int i = 1; i <= N; ++i) {
    for (int j = 1; j <= N; ++j) {
      int t; cin >> t;
      if (t) unite(i, j);
    }
  }

  vector<int> plan(M);
  for (int i = 0; i < M; ++i) {
    cin >> plan[i];
  }

  int root = find(plan[0]);
  bool possible = true;
  for (int i = 1; i < M; ++i) {
    if (find(plan[i]) != root) {
      possible = false;
      break;
    }
  }

  cout << (possible ? "YES" : "NO") << endl;
  return 0;
}
