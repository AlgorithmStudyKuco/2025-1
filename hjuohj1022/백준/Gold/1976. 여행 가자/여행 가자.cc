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
    parent.resize(N);
    for (int i = 0; i < N; ++i) parent[i] = i;

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            int t; cin >> t;
            if (t) unite(i, j);
        }
    }

    vector<int> plan(M);
    for (int i = 0; i < M; ++i) {
        cin >> plan[i];
        plan[i] -= 1; // 0-based
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
