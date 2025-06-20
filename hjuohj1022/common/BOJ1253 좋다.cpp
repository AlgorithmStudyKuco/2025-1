#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	int N;
	cin >> N;

	vector<int> numbers(N);
	vector<int> is_good;

	for (int i = 0; i < N; i++) {
		cin >> numbers[i];
	}

	// 오름차순으로 정렬
	sort(numbers.begin(), numbers.end());

	int good = 0;


	for (int i = 0; i < N; i++) {
		int left = 0, right = N - 1;
		while (left < right) {
			if (left == i) {
				left++; continue;
			}
			else if (right == i) {
				right--; continue;
			}

			if (numbers[left] + numbers[right] < numbers[i]) {
				left++;
				continue;
			}
			else if (numbers[left] + numbers[right] > numbers[i]) {
				right--;
				continue;
			}
			else {
				good++;
				break;
			}
		}
	}

	cout << good;
	return 0;
}