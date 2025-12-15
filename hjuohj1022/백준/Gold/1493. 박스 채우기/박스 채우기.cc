#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int length, width, height;
int n;
vector<int> cubes(20, 0);

int main() {
    cin >> length >> width >> height;
    cin >> n;

    int maxA = 0;
    for (int i = 0; i < n; i++) {
        int a, b;
        cin >> a >> b;
        cubes[a] = b;
        maxA = max(maxA, a);
    }

    vector<long long> used(20, 0);
    long long filled = 0;
    long long total = (long long)length * width * height;

    for (int i = maxA; i >= 0; i--) {
        long long size = 1LL << i; // 비트 연산자 => 1LL(long long 타입) << i = 2의 i제곱
        long long fit = (length / size) * (width / size) * (height / size);

        for (int j = i + 1; j < 20; j++) {
            fit -= used[j] * (1LL << (3 * (j - i)));
        }
        fit = max(0LL, fit);
        used[i] = min((long long)cubes[i], fit);
        filled += used[i] * (size * size * size);
    }

    if (filled == total) {
        long long result = 0;
        for (int i = 0; i < 20; i++) result += used[i];
        cout << result << "\n";
    }
    else {
        cout << -1 << "\n";
    }

    return 0;
}
