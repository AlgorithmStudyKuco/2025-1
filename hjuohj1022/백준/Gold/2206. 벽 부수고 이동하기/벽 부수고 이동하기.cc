#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <algorithm>

using namespace std;

int N, M;

// [최적화 1] vector<vector<int>> -> char 정적 배열 (4MB -> 1MB)
char board[1001][1001]; 

// [최적화 2] int -> bool (8MB -> 2MB)
bool visited[1001][1001][2]; 

int dx[4] = {0,1,0,-1};
int dy[4] = {1,0,-1,0};

// [최적화 3] 구조체 크기 축소
struct idx {
	short x, y;  // int(4) -> short(2)
	int d;
	bool broken; // int(4) -> bool(1)
};

void input() {
	cin >> N >> M;
	// 전역 변수는 자동으로 0으로 초기화되므로 resize 등이 필요 없음
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < M; j++) {
			board[i][j] = s[j] - '0';
		}
	}
}

int bfs() {
	queue<idx> q;
	q.push({0,0,1,false});
	visited[0][0][0] = true;

	while (!q.empty()) {
		idx curr = q.front();
		q.pop();

		if (curr.x == N - 1 && curr.y == M - 1) return curr.d;

		for (int i = 0; i < 4; i++) {
			int nx = curr.x + dx[i];
			int ny = curr.y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

			// 1. 벽을 만났을 때 (아직 안 부쉈고 && 방문 안 했으면)
			if (board[nx][ny] == 1) {
				if (curr.broken == false && visited[nx][ny][1] == false) {
					visited[nx][ny][1] = true;
					q.push({(short)nx, (short)ny, curr.d + 1, true});
				}
			}
			// 2. 길을 만났을 때 (현재 상태로 방문 안 했으면)
			else if (board[nx][ny] == 0) {
				if (visited[nx][ny][curr.broken] == false) {
					visited[nx][ny][curr.broken] = true;
					q.push({(short)nx, (short)ny, curr.d + 1, curr.broken });
				}
			}
		}
        // result = max(...) 삭제: BFS는 목적지 도착 순간이 최단 거리이므로 불필요
	}
	return -1;
}

void solve() {
	cout << bfs() << "\n";
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	input();
	solve();

	return 0;
}