#include <iostream>
#include <vector>

using namespace std;

int N , M;
vector<int> parent;
vector<vector<int>> parties;

int find(int x) {
	if (parent[x] == x) return x;
	return parent[x] = find(parent[x]);
}

void unite(int x, int y) {
	int rootX = find(x);
	int rootY = find(y);

	if (rootX == rootY) return;

	if (rootX == 0) parent[rootY] = 0;
	else if(rootY == 0) parent[rootX] = 0;
	else parent[rootY] = rootX;
}

void input() {
	cin >> N >> M;
	parent.resize(N + 1);
	// 루트 노드를 자기자신으로 지정
	for (int i = 0; i <= N; i++) {
		parent[i] = i;
	}

	int truthCount;
	cin >> truthCount;
	for (int i = 0; i < truthCount; i++) {
		int person;
		cin >> person;
		unite(0, person); // 진실을 아는 사람을 0으로 설정
	}

	for (int i = 0; i < M; i++) {
		int partySize;
		cin >> partySize;
		vector<int> party;
		party.resize(partySize);
		for (int j = 0; j < partySize; j++) {
			cin >> party[j];
		}
		parties.push_back(party);

		if (partySize < 2) continue;
		int firstPerson = party[0];
		for (int j = 1; j < partySize; j++) {
			unite (firstPerson, party[j]);
		}
	}
	return;
}

void solve() {
	int cnt = 0;
	for (const auto& party : parties) {
		if (party.empty()) {
			cnt++;
			continue;
		}

		int root = find(party[0]);
		if (root != 0) {
			cnt++;
		}
	}
	cout << cnt << "\n";
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	input();
	solve();

	return 0;
}