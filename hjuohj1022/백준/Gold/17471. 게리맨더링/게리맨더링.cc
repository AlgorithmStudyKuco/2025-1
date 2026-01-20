#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <cmath>

using namespace std;

int N;
vector<int> district;
vector<vector<int>> graph;
int total = 0;

void input() {
  cin >> N;
  district.assign(N + 1, 0);
  graph.resize(N + 1);
  for (int i = 1; i <= N; i++) {
    cin >> district[i];
    total += district[i];
  }
  for (int i = 1; i <= N; i++) {
    int num;
    cin >> num;
    for (int j = 0; j < num; j++) {
      int idx;
      cin >> idx;
      graph[i].push_back(idx);
    }
  }
  return;
}

bool bfs(int visit, int targetBit) {
  int startNode = -1;
  for (int i = 1; i <= N; i++) {
    bool isMyArea = (visit & (1 << i)) ? 1 : 0;
    if (isMyArea == targetBit) {
      startNode = i;
      break;
    }
  }
  if (startNode == -1) return false;
  queue<int> q;
  q.push(startNode);

  int checkMask = 0;
  checkMask |= (1 << startNode);

  while (!q.empty()) {
    int curr = q.front();
    q.pop();
    for (int next : graph[curr]) {
      if (checkMask & (1 << next)) continue;
      bool isNextMyArea = (visit & (1 << next)) ? 1 : 0;
      if (isNextMyArea == targetBit) {
        checkMask |= (1 << next);
        q.push(next);
      }
    }
  }
  int originalTargetNodes = 0;
  for (int i = 1; i <= N; i++) {
    bool isMyArea = (visit & (1 << i)) ? 1 : 0;
    if (isMyArea == targetBit) originalTargetNodes |= (1 << i);
  }

  return checkMask == originalTargetNodes;
}

void solve() {
  int minDiff = total;
  bool success = false;
  for (int i = 1; i < (1 << (N + 1)) - 1; i++) {
    if (bfs(i, 1) && bfs(i, 0)) {
      int currentZone = 0;
      for (int j = 1; j <= N; j++) {
        if (i & (1 << j)) {
          currentZone += district[j];
        }
      }
      minDiff = min(minDiff, abs(total - 2 * currentZone));
      success = true;
    }
  }
  cout << ((success) ? minDiff : -1) << "\n";
  return;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  input();
  solve();

  return 0;
}