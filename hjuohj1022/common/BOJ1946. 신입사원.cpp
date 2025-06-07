#include <iostream>

using namespace std;

int main() {
	int T;
	cin >> T;
	for (int i = 0; i < T; i++) {
		int N;
		cin >> N;
		// 1. 점수 배열 초기화(전부 0으로)
		int score[100001] = { 0 };
		for (int j = 0; j < N; j++) {
			int a, b;
			cin >> a >> b;
			// 2. 점수 배열에 지원자의 점수 저장
			score[a] = b;
		}
		// 3. 합격자 수 계산
		int cnt = 1; // 첫 번째 지원자는 무조건 합격
		int minScore = score[1]; // 첫 번째 지원자의 점수로 초기화
		for (int j = 2; j <= N; j++) {
			if (score[j] < minScore) { // 현재 지원자의 점수가 이전 합격자 중 가장 낮은 점수보다 낮으면 합격
				cnt++;
				minScore = score[j];
			}
		}
		// 4. 결과 출력
		cout << cnt << endl;
	}
	return 0;
}