#include <iostream>

using namespace std;

int main() {
	int N;
	cin >> N;

	int dp[1516][3] = { 0 };
	dp[2][0] = 1; dp[2][1] = 1;

	for (int i = 3; i < N + 1; i++) {
		dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % 1000000007;
		dp[i][1] = (dp[i - 1][2] + dp[i - 1][0]) % 1000000007;
		dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 1000000007;
	}

	cout << dp[N][0];
	return 0;
}