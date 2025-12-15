#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

struct Edge {
	int from, to;
	double weight;
	bool operator<(const Edge& other) const {
		return weight < other.weight;
	}
};

struct UnionFind {
	vector<int> parent;
	UnionFind(int n) : parent(n) { // Python => def __init__ (self, n):
		for (int i = 0; i < n; i++) parent[i] = i;
	}
	int find(int x) {
		if (parent[x] != x) parent[x] = find(parent[x]); //경로 압축
		return parent[x];
	}
	bool unite(int x, int y) {
		x = find(x); y = find(y);
		if (x == y) return false;
		parent[y] = x;
		return true;
	}
};

int main() {
	int n;
	cin >> n;
	vector<pair<double, double>> stars(n);

	for (int i = 0; i < n; i++) {
		cin >> stars[i].first >> stars[i].second;
	}

	vector<Edge> edges;
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			double dx = stars[i].first - stars[j].first;
			double dy = stars[i].second - stars[j].second;
			double dist = sqrt(dx * dx + dy * dy);
			edges.push_back({i, j, dist});
		}
	}

	sort(edges.begin(), edges.end());
	UnionFind uf(n);
	double total = 0;
	for (const auto& e : edges) {
		if (uf.unite(e.from, e.to)) {
			total += e.weight;
		}
	}

	// 소수점 2번째 자리까지만 출력
	cout << fixed;
	cout.precision(2);
	cout << total << "\n";
	return 0;
}