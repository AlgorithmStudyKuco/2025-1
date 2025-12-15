#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, K;
vector<pair<int, int>> stuffs;


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> K;
	stuffs.resize(N);
	for (int i = 0; i < N; i++) {
		cin >> stuffs[i].first >> stuffs[i].second;
	}
	
	vector<int> dp(K + 1, 0);
	for (int i = 0; i < N; i++) {
		int w = stuffs[i].first, p = stuffs[i].second;
		for (int j = K; j >= w; j--) {
			dp[j] = max(dp[j], dp[j - w] + p);
		}
	}
	cout << dp[K] << "\n";
  return 0;
}