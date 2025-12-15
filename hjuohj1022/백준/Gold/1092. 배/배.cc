
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int N, M;
    cin >> N;
    vector<int> cranes(N);
    for (int i = 0; i < N; i++)
        cin >> cranes[i];

    cin >> M;
    vector<int> boxes(M);
    for (int i = 0; i < M; i++)
        cin >> boxes[i];

    // 내림차순 정렬
    sort(cranes.rbegin(), cranes.rend());
    sort(boxes.rbegin(), boxes.rend());

    // 가장 무거운 박스를 옮길 수 있는 크레인이 없으면 불가능
    if (boxes[0] > cranes[0]) {
        cout << -1 << '\n';
        return 0;
    }

    vector<bool> moved(M, false); // 박스 옮겼는지 체크
    int count = 0;
    int time = 0;

    while (count < M) {
        int crane_idx = 0;
        int box_idx = 0;

        while (crane_idx < N && box_idx < M) {
            if (!moved[box_idx] && cranes[crane_idx] >= boxes[box_idx]) {
                moved[box_idx] = true;
                crane_idx++;
                count++;
                box_idx++;
            }
            else {
                box_idx++;
            }
        }
        time++;
    }

    cout << time << '\n';
    return 0;
}
