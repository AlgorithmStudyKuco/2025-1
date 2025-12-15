#include <iostream>
#include <vector>
#include <tuple>

using namespace std;

int n,m;
vector<int> disjointSet;

int find(int x) {
	if (disjointSet[x] == x)
		return x;
	return disjointSet[x] = find(disjointSet[x]);
}

void unite(int a, int b) {
	a = find(a);
	b = find(b);
	if (a != b) {
		disjointSet[b] = a;
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	
	cin >> n >> m;
	disjointSet.resize(n + 1);
	for (int i = 0; i <= n; i++) {
		disjointSet[i] = i;
	}

	for (int i = 0; i < m; i++) {
		int op, a, b;
		cin >> op >> a >> b;
		if (op == 0) {
			unite(a, b);
		}
		else {
			cout << (find(a) == find(b) ? "YES" : "NO") << "\n";
		}
	}
	return 0;
}