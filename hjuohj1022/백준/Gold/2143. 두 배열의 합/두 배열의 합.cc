#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

long long T;
int n;
vector<int> first;
int m;
vector<int> second;

vector<long long> sum(const vector<int>& arr) {
	vector<long long> sums;
	int n = arr.size();
	for (int i = 0; i < n; i++) {
		int sum = 0;
		for (int j = i; j < n; j++) {
			sum += arr[j];
			sums.push_back(sum);
		}
	}
	return sums;
}

int main() {
	cin >> T;
	cin >> n;
	first.resize(n);
	for (int i = 0; i < n; i++) {
		cin >> first[i];
	}
	cin >> m;
	second.resize(m);
	for (int i = 0; i < m; i++) {
		cin >> second[i];
	}

	vector<long long> sumFirst = sum(first);
	vector<long long> sumSecond = sum(second);

	sort(sumSecond.begin(), sumSecond.end());

	long long result = 0;
	for (auto x : sumFirst) {
		int target = T - x;
		auto lower = lower_bound(sumSecond.begin(), sumSecond.end(), target);
		auto upper = upper_bound(sumSecond.begin(), sumSecond.end(), target);
		result += upper - lower;
	}

	cout << result << "\n";
	return 0;
}