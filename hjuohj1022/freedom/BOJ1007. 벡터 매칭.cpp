#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

const double MAX = 10000000;
double result = MAX;
int N;

void comb(int idx, int count, vector<bool>& selected, vector<pair<int, int>>& coordinates) {

	if (count == N / 2) {
		long long x = 0, y = 0;
		for (int i = 0; i < N; i++) {
			if (selected[i]) {
				x += coordinates[i].first;
				y += coordinates[i].second;
			}
			else {
				x -= coordinates[i].first;
				y -= coordinates[i].second;
			}
		}
		double dist = sqrt(x * x + y * y);
		result = min(result, dist);
		return;
	}

	if (idx >= N || count > N / 2) return;

	selected[idx] = true;
	comb(idx + 1, count + 1, selected, coordinates);
	selected[idx] = false;
	comb(idx + 1, count, selected, coordinates);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int T;
	cin >> T;
	while (T--) {
		result = MAX;
		cin >> N;
		vector<pair<int, int>> coordinates(N);
		for (int i = 0; i < N; i++) {
			cin >> coordinates[i].first >> coordinates[i].second;
		}
		vector<bool> selected(N, false);

		selected[0] = true;
		comb(1, 1, selected, coordinates);

		cout << fixed;
		cout.precision(10);
		cout << result << "\n";
	}

	return 0;
}
