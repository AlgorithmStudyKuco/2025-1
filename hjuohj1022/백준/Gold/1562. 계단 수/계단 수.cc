#include <iostream>
#include <vector>

using namespace std;

int N;
const int MOD = 1000000000;
vector<vector<vector<int>>> stairNumbers;
int result = 0;

int main() {
	cin >> N;
	stairNumbers.clear();
	stairNumbers.resize(N, vector<vector<int>>(10, vector<int>(1024, 0)));

	// 첫번째 자리 수에 각 숫자가 올 경우의 수를 모두 1로 지정
	for (int i = 0; i < 10; i++) {
		stairNumbers[0][i][1 << i] = 1;
	}

	for (int i = 1; i < N; i++) {
		for (int j = 0; j < 10; j++) {
			for (int k = 0; k < 1024; k++) {
				if (j + 1 < 10) stairNumbers[i][j][k | (1 << j)] += stairNumbers[i - 1][j + 1][k];
				if (j - 1 >= 0) stairNumbers[i][j][k | (1 << j)] += stairNumbers[i - 1][j - 1][k];
				stairNumbers[i][j][k | (1 << j)] %= MOD;
			}
		}
	}

	for (int i = 1; i < 10; i++) {
		result += stairNumbers[N - 1][i][1023];
		result %= MOD;
	}
	
	cout << result << endl;
	return 0;
}