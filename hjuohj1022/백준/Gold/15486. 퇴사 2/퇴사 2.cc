#include <iostream>

using namespace std;
int main() {
	int N;
	cin >> N;
	int t[1500001], p[1500001], dp[1500001] = { 0 };
	
	for (int i = 1; i <=  N; i++) {
		cin >> t[i] >> p[i];
	}
	
	for (int i = 1; i <= N; i++) {
		if (i + t[i] - 1 <= N) {
			dp[i + t[i] - 1] = max(dp[i + t[i] - 1], dp[i - 1] + p[i]);
		}
		dp[i] = max(dp[i], dp[i - 1]);
	}
	
	cout << dp[N] << endl;
	return 0;
 }