#include <iostream>
#include <vector>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int T;
	cin >> T;
	vector<long long> sum(1000001, 1);
	
	// 약수의 합을 미리 계산
	for (int i = 2; i <= 1000000; i++) {
		for (int j = i; j <= 1000000; j += i) {
			sum[j] += i;
		}
		sum[i] += sum[i - 1]; // 누적합 계산
	}
	
	// 각 테스트 케이스에 대해 결과 출력
	for (int i = 0; i < T; i++) {
		int N;
		cin >> N;
		cout << sum[N] << "\n";
	}
	
	return 0;
}






// N의 약수의 합을 구하는 문제인줄 알고 풀이한 코드ㅠㅠ

//#include <iostream>
//#include <unordered_map>
//#include <cmath>
//
//using namespace std;
//
//// 주어진 수를 소인수분해하는 함수
//void factorization(int N, unordered_map<int, int>& divisors) {
//	int prime = 2;
//	while (N > 1) {
//		if (N % prime == 0) {
//			divisors[prime]++;
//			N /= prime;
//		}
//		else {
//			prime += 1;
//		}
//	}
//}
//
//// 약수의 합을 구하는 함수
//long long divisors_sum(unordered_map<int, int> divisors) {
//	long long result = 1;
//	for (auto& pair : divisors) {
//		int prime_sum = (pow(pair.first, pair.second + 1) - 1) / (pair.first - 1);
//		result *= prime_sum;
//	}
//	return result;
//}
//
//int main() {
//	int T;
//	cin >> T;
//	for (int i = 0; i < T; i++) {
//		int N;
//		cin >> N;
//		unordered_map<int, int> divisors; // first = 소수, second = 지수
//
//		factorization(N, divisors); // 주어진 수를 소인수 분해
//		long long result = divisors_sum(divisors);
//		cout << result << endl;
//	}
//	return 0;
//}