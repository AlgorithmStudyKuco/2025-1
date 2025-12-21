#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <unordered_map>
#include <algorithm>

using namespace std;

int N;
unordered_map<string, int> idName;
vector<string> nameId;
vector<int> degree;
set<string> distinctName;
vector<vector<int>> graph;
vector<pair<string,string>> inputs;

void input() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		string s, f;
		cin >> s >> f;
		distinctName.insert(s);
		distinctName.insert(f);
		inputs.push_back({s, f});
	}
	int idCnt = 0;
	int graphSize = distinctName.size();
	nameId.resize(graphSize);
	degree.resize(graphSize);
	graph.resize(graphSize);
	for (string s : distinctName) {
		idName[s] = idCnt;
		nameId[idCnt] = s;
		idCnt++;
	}

	for (auto& s : inputs) {
		graph[idName[s.first]].push_back(idName[s.second]);
		degree[idName[s.second]]++;
	}
}

void solve() {
	vector<int> result;
	vector<bool> visited(graph.size(),false);
	vector<int> currq;
	for (int i = 0; i < graph.size(); i++) {
		if (!visited[i] && degree[i] == 0) {
			currq.push_back(i);
			visited[i] = true;
		}
	}
	while (!currq.empty()) {
		sort(currq.begin(), currq.end());
		vector<int> nextq;
		for (int idx : currq) {
			result.push_back(idx);
			for (int i : graph[idx]) {
				degree[i]--;
				if (degree[i] == 0) nextq.push_back(i);
			}
		}
		currq = nextq;
	}

	if (result.size() != graph.size()) cout << -1 << "\n";
	else {
		for (int i = 0; i < result.size(); i++) {
			cout << nameId[result[i]] << "\n";
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
    
	input();
	solve();
    
	return 0;
}