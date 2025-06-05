#include <iostream>
#include <cmath>

using namespace std;

int main() {
	int T;
	cin >> T;
	for (int i = 0; i < T; i++) {
		// long long 타입 : 이번 문제에서 주어지는 x와 y의 값이 최대 2**31이므로 int 대신 활용
		long long x, y;
		cin >> x >> y;
		// x와 y의 차이 = 거리
		long long distance = y - x;
		int count = 0;

		// 이동 장치 작동 횟수의 최솟값은 작동 횟수에 대한 이동거리의 그래프로 나타낼때, 피라미드 형태로 나타난다.
		// 따라서, 피라미드의 최고 높이를 구하는 방법은, distance의 제곱근을 구하고 소수점을 버리는 방법을 활용. 
		long long n = (long long)sqrt(distance);

		// 피리미드의 최고 높이가 2개가 있는지, 1개가 있는지 판정
		if (distance >= n * (n + 1)) {
			count += 2 * n;
			distance -= n * n + n;
		}
		else {
			count += 2 * n - 1;
			distance -= n * n;
		}

		// 남은 거리가 0보다 크면, 이동 장치 작동 횟수를 추가로 계산
		if (distance > 0) {
			if (distance <= n) {
				count += 1;
			}
			else {
				count += 2;
			}
		}
		cout << count << endl;
	}
	return 0;
}
