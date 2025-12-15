#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
	int N;
	cin >> N;

	vector<pair<int, int>> meetings(N);
	for (int i = 0; i < N; i++) {
		cin >> meetings[i].first >> meetings[i].second;
	}

	// 종료 시간 기준으로 정렬
	sort(meetings.begin(), meetings.end(), [](pair<int, int>& a, pair<int, int>& b) {
		return (a.second == b.second) ? a.first < b.first : a.second < b.second;
	});

	int count = 0, lastEnd = 0;
	for (int i = 0; i < N; i++) {
		if (meetings[i].first >= lastEnd) {
			count++;
			lastEnd = meetings[i].second;
		}
	}
	cout << count << endl;
	return 0;
}