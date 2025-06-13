//#include <iostream>
//#include <cstring>
//
//using namespace std;
//
//int main() {
//	int T;
//	cin >> T;
//	for (int i = 0; i < T; i++) {
//		char text[31];
//		cin >> text;
//		int length = strlen(text);
//		int left_point = 0;
//		int right_point = length - 1;
//		int is_palindrome = 0;
//		while (right_point > left_point) {
//			if (text[right_point] == text[left_point]) {
//				left_point++, right_point--;
//				continue;
//			}
//			else {
//				is_palindrome++;
//				if (is_palindrome >= 2) break;
//
//				if (text[left_point + 1] == text[right_point]) {
//					left_point++;
//				}
//				else if (text[left_point] == text[right_point - 1]) {
//					right_point--;
//				}
//				else {
//					is_palindrome++;
//					break;
//				}
//			}
//		}
//		cout << is_palindrome << "\n";
//	}
//	return 0;
//}



//위 코드의 문제점
//1
//abca 이 예시에서 is_palindrome이 2로 출력되어야 하는데, 1로 출력.


// 개선방향 => 일치하는 문자가 나오지 않은경우, 문자를 하나 제거해서 회문인지 확인 (String 사용)
#include <iostream>
#include <string>

using namespace std;

int main() {
    int T;
    cin >> T;
    for (int i = 0; i < T; i++) {
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
        }
        else if (isAlmostPalindrome) {
            cout << "1\n";
        }
        else {
            cout << "2\n";
        }
    }
    return 0;
}
