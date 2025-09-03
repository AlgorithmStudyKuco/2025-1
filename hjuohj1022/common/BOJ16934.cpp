#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <algorithm>
using namespace std;

struct Trie {
  Trie* child[26];
  bool isEnd;

  Trie() {
    fill(child, child + 26, nullptr);
  }
};

class NicknameSystem {
  Trie* root;
  unordered_map<string, int> nameCount; // 닉네임별 등장 횟수 기록

public:
  NicknameSystem() {
    root = new Trie();
  }

  string insert(const string& name) {
    Trie* node = root;
    string prefix = "";
    string result = "";

    nameCount[name]++;

    bool found = false;

    for (int i = 0; i < (int)name.size(); i++) {
      int c = name[i] - 'a';
      prefix += name[i];

      if (node->child[c] == nullptr) {
        node->child[c] = new Trie();
        if (!found) {
          result = prefix;
          found = true;
        }
      }
      node = node->child[c];
    }

    if (found) {
      node->isEnd = true;
      return result;
    }

    int cnt = nameCount[name];
    if (cnt == 1) return name;
    else return name + to_string(cnt);
  }
};

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int N;
  cin >> N;
  NicknameSystem system;

  for (int i = 0; i < N; i++) {
    string name;
    cin >> name;
    cout << system.insert(name) << "\n";
  }

  return 0;
}
