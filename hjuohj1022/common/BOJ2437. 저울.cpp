#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<int> weights;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	cin >> N;
	weights.resize(N);
	for (int i = 0; i < N; i++) {
		cin >> weights[i];
	}

	sort(weights.begin(), weights.end());

	int result = 0;
	for (int i = 0; i < weights.size(); i++) {
		if (weights[i] >= result + 2) break;
		else result += weights[i];
	}

	cout << result + 1 << "\n";
}