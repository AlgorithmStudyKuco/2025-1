#include <iostream>
#include <string>

using namespace std;

int main() {
		int T;
	cin >> T;
	while (T--) {
		string s;
		cin >> s;
		int left = 0, right = s.size() - 1;
		bool isPalindrome = true, isAlmostPalindrome = false;
		while (left < right) {
			if (s[left] != s[right]) {
				isPalindrome = false;
				// Check if removing one character makes it a palindrome
				string withoutLeft = s.substr(left + 1, right - left);
				string withoutRight = s.substr(left, right - left);
				
				// Check if either of the modified strings is a palindrome
				isAlmostPalindrome = (withoutLeft == string(withoutLeft.rbegin(), withoutLeft.rend())) ||
									 (withoutRight == string(withoutRight.rbegin(), withoutRight.rend()));
				break;
			}
			left++;
			right--;
		}
		if (isPalindrome) {
			cout << "0\n";
		} else if (isAlmostPalindrome) {
			cout << "1\n";
		} else {
			cout << "2\n";
		}
	}
	return 0;
}

