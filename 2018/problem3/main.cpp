#include <algorithm>
#include <iostream>
#include <string>

std::string& to_lower(std::string& str) {
  for (char& c : str) {
    if (isupper(c)) {
      c = tolower(c);
    }
  }
  return str;
}

bool is_palindrome(std::string str) {
  std::string reverse_str = str;
  std::reverse(reverse_str.begin(), reverse_str.end());
  return to_lower(str) == to_lower(reverse_str);
}

int main() {
  std::string str;
  std::cin >> str;

  std::string longest_palindrome = "";
  for (int i = 0; i < str.length(); ++i) {
    for (int j = i; j < str.length(); ++j) {
      std::string substr = str.substr(i, j - i + 1);
      bool replace = substr.size() > longest_palindrome.size() || (
          substr.size() == longest_palindrome.size() &&
          substr < longest_palindrome
      );
      if (is_palindrome(substr) && replace) {
        longest_palindrome = substr;
      }
    }
  }

  std::cout << longest_palindrome << std::endl;
}