#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

long long a, b;
vector<bool> numbers;

void SquareNoNo(long long a, long long b) {
	for (long long i = 2; i * i <= b; i++) {
		long long square = i * i;
		
		// a 이상이면서 square의 배수인 가장 작은 수를 찾음
		long long start = ((a + square - 1) / square) * square;
			for (long long j = start; j <= b; j += i * i) {
				numbers[j - a] = false;
			}
		}    
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> a >> b;

	numbers.assign(b - a + 1, true);

	SquareNoNo(a, b);

	long long cnt = 0;
	for (long long i = 0; i < numbers.size(); i++) {
		if (numbers[i]) cnt++;
	}

	cout << cnt << "\n";
	return 0;
}
