#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int N;
int dp[5001] = { -1 };

void input() {
	fill(dp, dp+5001, -1);
	cin >> N;
}

void solve() {
	int i = 5;
	int idx = 1;
	while (i <= N) {
		dp[i] = idx;
		i += 5;
		idx++;
	}
	for (int j = 0; j < idx; j++) {
		i = 5 * j;
		int curr = 1 * j;
		while (i <= N) {
			if (dp[i] == -1 || dp[i] > curr)	dp[i] = curr;
			curr++;
			i += 3;
		}
	}
	cout << dp[N] << "\n";
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	input();
	solve();

	return 0;
}