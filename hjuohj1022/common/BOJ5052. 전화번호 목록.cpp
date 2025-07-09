#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int t;
int n;
vector<string> phoneNumbers;

bool check() {
	for (int i = 0; i < phoneNumbers.size() - 1; i++) {
		for (int j = i + 1; j < phoneNumbers.size(); j++) {
			if (phoneNumbers[i].size() == phoneNumbers[j].size()) continue; // 두 전화번호가 같은 경우는 없기 때문
			for (int k = 0; k < phoneNumbers[i].size(); k++) {
				if (phoneNumbers[i][k] != phoneNumbers[j][k]) break;
				if (k == phoneNumbers[i].size() - 1) return false;
			}
		}
	}
	return true;
}

int main() {
	cin >> t;
	while (t--) {
		phoneNumbers.clear();
		cin >> n;
		phoneNumbers.resize(n);
		for (int i = 0; i < n; i++) {
			cin >> phoneNumbers[i];
		}
		// 람다 함수의 기본 구조 => [캡처리스트](매개변수) { 함수본문 }
		sort(phoneNumbers.begin(), phoneNumbers.end(), [](const string& a, const string& b) {
			return (a.size() == b.size() ? stoi(a) < stoi(b) : a.size() < b.size());
			}); // 숫자의 길이가 짧은순, 같으면 숫자가 작은순으로 정렬

		cout << (check() ? "YES" : "NO") << "\n";
	}
	return 0;
}