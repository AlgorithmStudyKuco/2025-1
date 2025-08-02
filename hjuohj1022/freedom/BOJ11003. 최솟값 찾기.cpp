//#include <iostream>
//#include <vector>
//#include <queue>
//#include <set>
//
//using namespace std;
//
//int main() {
//	ios::sync_with_stdio(false);
//	cin.tie(NULL);
//
//	int N, L;
//	cin >> N >> L;
//	vector<int> lst(N);
//	vector<int> result(N, 0);
//	for (int i = 0; i < N; i++) {
//		cin >> lst[i];
//	}
//
//	queue<int> q;
//	multiset<int> s;
//
//	for (int i = 0; i < lst.size(); i++) {
//		q.push(lst[i]);
//		s.insert(lst[i]);
//		if (q.size() > L) {
//			int content = q.front();
//			q.pop();
//			auto it = s.find(content);
//			if (it != s.end()) s.erase(it);
//		}
//		result[i] = *s.begin();
//	}
//
//	for (int i = 0; i < result.size(); i++) {
//		cout << result[i] << " ";
//	}
//
//	return 0;
//}
//--- O(NlogL)

#include <iostream>
#include <vector>
#include <deque>

using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	int N, L;
	cin >> N >> L;
	vector<int> lst(N);
	for (int i = 0; i < N; i++) {
		cin >> lst[i];
	}

	deque<int> dq;
	for (int i = 0; i < N; i++) {
		while (!dq.empty() && dq.front() <= i - L) dq.pop_front();

		while (!dq.empty() && lst[dq.back()] > lst[i]) dq.pop_back();

		dq.push_back(i);

		cout << lst[dq[0]] << ' ';
	}
	cout << '\n';

	return 0;
}

//----O(N)