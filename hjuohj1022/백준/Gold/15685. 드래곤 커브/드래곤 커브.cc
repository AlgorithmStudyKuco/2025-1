#include <iostream>
#include <vector>
using namespace std;

int N;
vector<vector<bool>> graph(101, vector<bool>(101, false));
vector<vector<int>> curve;

int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, -1, 0, 1 };

int main() {
    cin >> N;
    curve.resize(N, vector<int>(4));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < 4; j++) {
            cin >> curve[i][j];
        }
    }

    for (int i = 0; i < N; i++) {
        int x = curve[i][0];
        int y = curve[i][1];
        int d = curve[i][2];
        int g = curve[i][3];

        vector<int> dirs;
        dirs.push_back(d);

        for (int gen = 0; gen < g; gen++) {
            int sz = dirs.size();
            for (int k = sz - 1; k >= 0; k--) {
                dirs.push_back((dirs[k] + 1) % 4);
            }
        }

        graph[y][x] = true;
        for (int dir : dirs) {
            x += dx[dir];
            y += dy[dir];
            if (x >= 0 && x <= 100 && y >= 0 && y <= 100)
                graph[y][x] = true;
        }
    }

    int result = 0;
    for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++) {
            if (graph[i][j] && graph[i + 1][j] && graph[i][j + 1] && graph[i + 1][j + 1]) result++;
        }
    }

    cout << result << "\n";
    return 0;
}
