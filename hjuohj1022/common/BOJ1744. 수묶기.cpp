#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
	int N;
	cin >> N;
	vector<int> sequence;
	vector<int> positive;
	vector<int> nagative;

	for (int i = 0; i < N; i++) {
		int num;
		cin >> num;
		sequence.push_back(num);
	}

	// 배열을 정렬
	sort(sequence.begin(), sequence.end());
	
	for (int i = 0; i < N; i++) {
		if (sequence[i] <= 0) nagative.push_back(sequence[i]);
		else positive.push_back(sequence[i]);
	}

	int sum = 0;

	// 음수 배열에서의 최댓값을 계산
	for (int i = 0; i < nagative.size(); i++) {
		if (i == nagative.size() - 1 && nagative.size() % 2 == 1) {
			sum += nagative[i];
			break;
		}

		if (i % 2 == 0) continue;
		else {
			sum += nagative[i] * nagative[i - 1];
		}
	}

	// 양수 배열에서의 최댓값을 계산
	for (int i = positive.size() - 1; i >= 0; i--) {
		if (i == 0 && positive.size() % 2 == 1) {
			sum += positive[i];
			break;
		}

		if ((positive.size() - 1 - i) % 2 == 0) continue;
		else {
			// 이 경우는 (1 * n) < (1 + n) 이기 때문
			if (positive[i] == 1) sum += (1 + positive[i + 1]);
			else sum += positive[i] * positive[i + 1];
		}
	}

	cout << sum;
	return 0;
}
