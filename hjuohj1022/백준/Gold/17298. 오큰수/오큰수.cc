#include <iostream>
#include <vector>
#include <stack>

using namespace std;

int N;
vector<int> sequence;
vector<int> nge;

void input() {
	cin >> N;
	sequence.resize(N);
	nge.resize(N, -1);
	for (int i = 0; i < N; i++) {
		cin >> sequence[i];
	}
}

void solve() {
	stack<int> stack;
	for (int i = 0; i < N; i++) {
		while (!stack.empty() && sequence[i] > sequence[stack.top()]) {
			nge[stack.top()] = sequence[i];
			stack.pop();
		}
		stack.push(i);
	}

	/*while (!stack.empty()) {
		sequence[stack.top()] = -1;
		stack.pop();
	}*/

	for (int i = 0; i < N; i++) {
		cout << nge[i] << " ";
	}
	cout << "\n";
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	input();
	solve();

	return 0;
}