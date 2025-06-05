#include <iostream>
#include <cmath>

using namespace std;

int main() {
	int T;
	cin >> T;
	for (int i = 0; i < T; i++) {
		// long long Ÿ�� : �̹� �������� �־����� x�� y�� ���� �ִ� 2**31�̹Ƿ� int ��� Ȱ��
		long long x, y;
		cin >> x >> y;
		// x�� y�� ���� = �Ÿ�
		long long distance = y - x;
		int count = 0;

		// �̵� ��ġ �۵� Ƚ���� �ּڰ��� �۵� Ƚ���� ���� �̵��Ÿ��� �׷����� ��Ÿ����, �Ƕ�̵� ���·� ��Ÿ����.
		// ����, �Ƕ�̵��� �ְ� ���̸� ���ϴ� �����, distance�� �������� ���ϰ� �Ҽ����� ������ ����� Ȱ��. 
		long long n = (long long)sqrt(distance);

		// �Ǹ��̵��� �ְ� ���̰� 2���� �ִ���, 1���� �ִ��� ����
		if (distance >= n * (n + 1)) {
			count += 2 * n;
			distance -= n * n + n;
		}
		else {
			count += 2 * n - 1;
			distance -= n * n;
		}

		// ���� �Ÿ��� 0���� ũ��, �̵� ��ġ �۵� Ƚ���� �߰��� ���
		if (distance > 0) {
			if (distance <= n) {
				count += 1;
			}
			else {
				count += 2;
			}
		}
		cout << count << endl;
	}
	return 0;
}
