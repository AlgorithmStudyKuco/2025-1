#include <iostream>
#include <string>
#include <set>

using namespace std;

int M;

void input() {
	cin >> M;
	cin.ignore();
}

void solve() {
	
	int set = 0;

	for (int i = 0; i < M; ++i) {

		string com;
		cin >> com;

		if (com == "add") {
			int num;
			cin >> num;
			set |= (1 << num);
		}
		else if (com == "remove") {
			int num;
			cin >> num;
			set &= ~(1 << num);
		}
		else if (com == "check") {
			int num;
			cin >> num;
			cout << ((set >> num) & 1) << '\n';
		}
		else if (com == "toggle") {
			int num;
			cin >> num;
			set ^= (1 << num);
		}
		else if (com == "all") {
			set = (1 << 21) - 1;
		}
		else if (com == "empty") {
			set = 0;
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	input();
	solve();

	return 0;
}