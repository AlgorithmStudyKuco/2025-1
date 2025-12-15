#include <iostream>

using namespace std;

int main() {
	int T;
	cin >> T;
	for (int i = 0; i < T; i++) {
		int N;
		cin >> N;
		int score[100001] = { 0 };
		for (int j = 0; j < N; j++) {
			int a, b;
			cin >> a >> b;
			score[a] = b;
		}
		int cnt = 1, min = score[1];
		for (int j = 2; j <= N; j++) {
			if (score[j] < min) {
				cnt++;
				min = score[j];
			}
		}
		cout << cnt << endl;
	}
	
	return 0;
}