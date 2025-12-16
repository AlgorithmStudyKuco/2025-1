#include <iostream>
#include <vector>
#include <algorithm>
#include <limits>

using namespace std;

struct Hotel
{
	int cost;
	int customer;
};

const int INF = numeric_limits<int>::max();
int C, N;
vector<Hotel> hotels;
int dp[2000];

void input() {
	cin >> C >> N;
	hotels.resize(N);
	fill (dp, dp + 2000, INF);
	for (int i = 0; i < N; i++) {
		cin >> hotels[i].cost >> hotels[i].customer;
	}
	sort(hotels.begin(), hotels.end(), [](const Hotel& a, const Hotel& b) {
		return (double)(a.customer / a.cost) > (double)(b.customer / b.cost);
		}); // (customer / cost)를 기준으로 내림차순으로 정렬
}

void solve() {
	dp[0] = 0;
	vector<int> startPoint;
	startPoint.push_back(0);
	for (int i = 0; i < hotels.size(); i++) {
		for (int j = 0; j < startPoint.size(); j++) {
			for (int k = startPoint[j]; k <= C; k += hotels[i].customer) {
				if (dp[k + hotels[i].customer] > dp[k] + hotels[i].cost) {
					dp[k + hotels[i].customer] = dp[k] + hotels[i].cost;
					startPoint.push_back(k + hotels[i].customer);
				}
				else {
					break;
				}
			}
		}
	}
	sort(startPoint.rbegin(), startPoint.rend());
	int result = INF;
	for (int i = 0; i < startPoint.size(); i++) {
		result = min(result, dp[startPoint[i]]);
		if (startPoint[i + 1] < C) break;
	}
	cout << result << "\n";
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	input();
	solve();

	return 0;
}