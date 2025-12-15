#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

const double MAX = 10000000;
double result = MAX;
int N;

void comb(int idx, int count, vector<bool>& selected, vector<pair<int, int>>& coordinates) {
	// 조합 완성: N/2개 선택 완료
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

	// 남은 인원이 부족하면 종료
	if (idx >= N || count > N / 2) return;

	// 가지치기: 현재 인덱스 선택
	selected[idx] = true;
	comb(idx + 1, count + 1, selected, coordinates);

	// 현재 인덱스 선택하지 않음
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

		// 중복 제거를 위해 첫 번째 원소를 true로 고정
		selected[0] = true;
		comb(1, 1, selected, coordinates);

		cout << fixed;
		cout.precision(10);
		cout << result << "\n";
	}

	return 0;
}
