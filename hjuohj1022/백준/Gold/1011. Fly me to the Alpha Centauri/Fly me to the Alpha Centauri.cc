#include <iostream>
#include <cmath>

using namespace std;

int main() {
	int T;
	cin >> T;
	for (int i = 0; i < T; i++) {
		long long x, y;
		cin >> x >> y;
		long long distance = y - x;
		int count = 0;

		long long n = (long long)sqrt(distance);

		if (distance >= n * (n + 1)) {
			count += 2 * n;
			distance -= n * n + n;
		} else {
			count += 2 * n - 1;
			distance -= n * n;
		}

		if (distance > 0) {
			if (distance <= n) {
				count += 1;
			} else {
				count += 2;
			}
		}
		cout << count << endl;
	}
	return 0;
}


