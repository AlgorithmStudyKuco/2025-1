#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N;
vector<int> people;
int result;

void input() {
	cin >> N;
	people.resize(N);
	for (int i = 0; i < N; i++) {
		cin >> people[i];
	}
	result = 0;
}

void solve() {
	int dasom = people[0];
	priority_queue<int> pq;
	for (int i = 1; i < N; i++) {
		pq.push(people[i]);
	}
	while (!pq.empty() && dasom <= pq.top())  {
		int top = pq.top();
		pq.pop();
		top--;
		dasom++;
		pq.push(top);
		result++;
	}
	cout << result << "\n";
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	input();
	solve();

	return 0;
}