#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int main() {
    int N, K;
    cin >> N >> K;

    vector<pair<int, int>> jewels(N);
    vector<int> bags(K);

    for (int i = 0; i < N; i++)
        cin >> jewels[i].first >> jewels[i].second;
    for (int i = 0; i < K; i++)
        cin >> bags[i];

    sort(jewels.begin(), jewels.end());
    sort(bags.begin(), bags.end());

    priority_queue<int> pq; // 가격을 오름차순으로 정렬
    long long answer = 0;
    int idx = 0;

    for (int i = 0; i < K; i++) {
        while (idx < N && jewels[idx].first <= bags[i]) {
            pq.push(jewels[idx].second);
            idx++;
        }
        if (!pq.empty()) {
            answer += pq.top();
            pq.pop();
        }
    }
    cout << answer << '\n';
    return 0;
}
