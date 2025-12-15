#include <iostream>

using namespace std;


// 배열을 전역 변수로 선언한 이유
// 전역 변수는 스택이 아닌 데이터 세그먼트에 저장되기 때문에 스택 메모리의 크기 제한에 영향을 받지 않는다.
// 따라서 큰 배열을 선언할 때 전역으로 선언하면 stack overflow를 방지할 수 있습니다.

int arr[500001] = { 0 };
int stack[500001] = { 0 }, result[500001] = { 0 };

int main() {
	int N;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	int top = 0;

	for (int i = 1; i <= N; i++) {
		while (top > 0 && arr[stack[top]] < arr[i]) {
			top--;
		}
		if (top > 0) {
			result[i] = stack[top];
		}
		stack[++top] = i;
	}
	for (int i = 1; i <= N; i++) {
		cout << result[i] << " ";
	}
	return 0;
}