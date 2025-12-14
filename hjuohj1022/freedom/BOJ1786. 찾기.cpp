#include <iostream>
#include <vector>
#include <string>

using namespace std;

vector<int> getPi(const string& p) {
  int m = p.size();
  vector<int> pi(m, 0);
  int j = 0;
  for (int i = 1; i < m; i++) {
    while (j > 0 && p[i] != p[j]) j = pi[j - 1];
    if (p[i] == p[j]) pi[i] = ++j;
  }
  return pi;
}

vector<int> kmp(const string& t, const string& p) {
  vector<int> pi = getPi(p);
  vector<int> positions;
  int n = t.size();
  int m = p.size();
  int j = 0;
  for (int i = 0; i < n; i++) {
    while (j > 0 && t[i] != p[j]) j = pi[j - 1];
    if (t[i] == p[j]) {
      if (j == m - 1) {
        positions.push_back(i - m + 2);
        j = pi[j];
      }
      else {
        j++;
      }
    }
  }
  return positions;
}

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  string T, P;
  getline(cin, T);
  getline(cin, P);

  vector<int> ans = kmp(T, P);

  cout << ans.size() << "\n";
  for (int i = 0; i < (int)ans.size(); i++) {
    cout << ans[i] << " ";
  }
  cout << "\n";
}
