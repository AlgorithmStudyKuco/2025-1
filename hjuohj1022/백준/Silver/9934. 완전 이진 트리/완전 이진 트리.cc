#include <iostream>
#include <vector>

using namespace std;

int K;
vector<int> nodes;
vector<vector<int>> tree;
int idx = 0;

void buildTree(int level) {
	if (level == K) return;
	buildTree(level + 1);
	tree[level].push_back(nodes[idx++]);
	buildTree(level + 1);
}

int countNodes(int k) {
	int result = 2; 
	for (int i = 1; i < k; ++i) {
		result *= 2;
	}
	result--;
	return result;
}

int main() {
	cin >> K;
	tree.resize(K);
	int cnt = countNodes(K);
	nodes.resize(cnt);
	for (int i = 0; i < cnt; i++) {
		cin >> nodes[i];
	}

	buildTree(0);

	for (int i = 0; i < K; i++) {
		for (int j = 0; j < tree[i].size(); j++) {
			cout << tree[i][j] << " ";
		}
		cout << "\n";
	}

	return 0;
}