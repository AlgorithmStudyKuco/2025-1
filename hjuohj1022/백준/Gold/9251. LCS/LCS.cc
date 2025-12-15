#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

string s1, s2;
vector<vector<int>> dp; 

int main() {
	cin >> s1 >> s2;
	dp.assign(s1.size() + 1, vector<int>(s2.size() + 1, 0));
	for (int i = 1; i < s1.size() + 1; i++) {
		for (int j = 1; j < s2.size() + 1; j++) {
			if (s1[i - 1] == s2[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
			else dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
		}
	}
	cout << dp[s1.size()][s2.size()] << "\n";
}