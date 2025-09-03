#include <iostream>
#include <vector>
#include <algorithm>
#define int long long

using namespace std;

int N, M;
vector<int> nums;

main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;
	nums.resize(N + 1);
	nums[0] = 0;

	for (int i = 1; i < N + 1; i++) {
		cin >> nums[i];
	}

	vector<int> u;

	for (int i = 0; i < nums.size(); i++) {
		for (int j = 0; j < nums.size(); j++) {
			u.push_back(nums[i] + nums[j]);
		}
	}

	vector<int> w(u);

	sort(u.begin(), u.end());
	sort(w.begin(), w.end());

	int result = 0;

	for (int i = 0; i < u.size(); i++) {
		int x = upper_bound(w.begin(), w.end(), M - u[i]) - w.begin();

		if (x == 0) continue;

		result = max(result, u[i] + w[x - 1]);
	}

	cout << result << "\n";
}