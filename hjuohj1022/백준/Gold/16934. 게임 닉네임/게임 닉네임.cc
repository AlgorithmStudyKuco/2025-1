#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <algorithm>
using namespace std;

struct Trie {
  Trie* child[26];
  bool isEnd; // 이 접두사가 이전에 등장했는지 여부

  Trie() {
    fill(child, child + 26, nullptr);
    isEnd = false;
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

    // 닉네임별 등장 횟수 증가
    nameCount[name]++;

    bool found = false; // 아직 고유 접두사를 찾지 못했는가?

    for (int i = 0; i < (int)name.size(); i++) {
      int c = name[i] - 'a';
      prefix.push_back(name[i]);

      if (node->child[c] == nullptr) {
        node->child[c] = new Trie();
        if (!found) {
          result = prefix; // 처음 고유 접두사 발견
          found = true;
        }
      }
      node = node->child[c];
    }

    // 최단 접두사가 발견된 경우
    if (found) {
      node->isEnd = true;
      return result;
    }

    // 고유 접두사가 없으면 → 닉네임 중복 처리
    int cnt = nameCount[name];
    if (cnt == 1) return name; // 첫 등장
    else return name + to_string(cnt); // 2번째 이후
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
