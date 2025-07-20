#include <iostream>
#include <vector>
#include <string>
#include <sstream>
#include <algorithm>

using namespace std;

int T; // 테스트 개수
string p; // 수행할 함수
int n; // 배열에 들어있는 수
vector<int> lst;

bool execute() {
	int rotate = 0;
	for (auto c : p) {
		if (c == 'D') {
			if (lst.empty()) return false; // 배열이 비어서 더이상 삭제할 수 없는 경우

			if (rotate % 2 == 1) lst.pop_back();
			else lst.erase(lst.begin());
		}
		else if (c == 'R') {
			rotate++;
		}
	}
	if (rotate % 2 == 1) reverse(lst.begin(), lst.end());
	return true;
}

int main() {
	cin >> T;
	while (T--) {
		lst.clear();
		cin >> p;
		cin >> n;
		string lstInput;
		cin >> lstInput;
		lstInput = lstInput.substr(1, lstInput.size() - 2); // 대괄호 제거

		stringstream ss(lstInput); // 문자열을 스트림처럼 다룰 수 있게해주는 객체 ss를 생성
		string num;
		while (getline(ss, num, ',')) { // 쉼표를 기준으로 원소를 나누어서 벡터에 저장
			lst.push_back(stoi(num));
		}

		if (execute()) {
			cout << "[";
			for (int i = 0; i < lst.size(); i++) {
				cout << lst[i];
				if (i != lst.size() - 1) cout << ",";
			}
			cout << "]\n";
		}
		else {
			cout << "error" << "\n";
		}
	}
	return 0;
}