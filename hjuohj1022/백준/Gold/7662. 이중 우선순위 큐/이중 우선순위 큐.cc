#include <iostream>
#include <set>
#include <string>

using namespace std;

int T;

int main() {
  cin >> T;
  while (T--) {
    int k;
    cin >> k;
    multiset<int> ms;
    for (int i = 0; i < k; ++i) {
      string oper;
      int n;
      cin >> oper >> n;
      if (oper == "I") {
        ms.insert(n);
      }
      else if (oper == "D") {
        if (ms.empty()) continue;
        if (n == 1) {
          // 최대값 삭제
          auto it = prev(ms.end());
          ms.erase(it);
        }
        else if (n == -1) {
          // 최소값 삭제
          auto it = ms.begin();
          ms.erase(it);
        }
      }
    }
    if (ms.empty()) {
      cout << "EMPTY\n";
    }
    else {
      cout << *prev(ms.end()) << ' ' << *ms.begin() << '\n';
    }
  }
  return 0;
}