#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<vector<int>> hits;
int result;
vector<bool> visited(9);
vector<int> sequence(9);

void input() {
	cin >> N;
	hits.resize(N, vector<int>(9));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < 9; j++) {
			cin >> hits[i][j];
		}
	}
	result = 0;
}

void setSequence(int depth) {
	if (depth == 9) {
		int total = 0;
		int idx = 0;
		for (int i = 0; i < N; i++) {
			int bases = 0;
			int outCnt = 0;
			while (outCnt < 3) {
				if (hits[i][sequence[idx]] != 0) {
					bases = bases << hits[i][sequence[idx]];
					bases |= (1 << (hits[i][sequence[idx]] - 1));
					int score = (bases >> 3);
					while (score > 0) {
						total += (score & 1);
						score >>= 1;
					}
					bases &= 7;
				}
				else {
					outCnt++;
				}
				idx = (idx + 1) % 9;
			}
		}
		result = max(result, total);
		return;
	}
	if (depth == 3) {
		setSequence(depth + 1);
		return;
	}
	for (int i = 1; i < 9; i++) {
		if (!visited[i]) {
			visited[i] = true;
			sequence[depth] = i;
			setSequence(depth+1);
			visited[i] = false;
		}
	}
}

void solve() {
	sequence[3] = 0;	
	visited[0] = true;
	setSequence(0);
	cout << result << "\n";
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	input();
	solve();

	return 0;
}