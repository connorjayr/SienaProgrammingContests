#include <iostream>
#include <list>
#include <string>
#include <vector>

std::string convert_to_base(int n, int base) {
  std::list<char> digits;
  while (n > 0) {
    digits.push_front(n % base);
    n /= base;
  }
  
  std::string converted = "";
  for (int digit : digits) {
    converted += "0123456789ABCDEF"[digit];
  }
  return converted;
}

bool is_monodigit(const std::string& n) {
  char digit = n.front();
  for (char c : n) {
    if (c != digit) return false;
  }
  return true;
}

int main() {
  int n;
  std::cin >> n;

  bool monodigit_any_base = false;
  for (int base = 2; base <= 16; ++base) {
    std::string converted = convert_to_base(n, base);
    if (is_monodigit(converted)) {
      monodigit_any_base = true;
      std::cout << n << " Base " << base << ": " << converted << std::endl;
    }
  }
  if (!monodigit_any_base) {
    std::cout << "NO" << std::endl;
  }
}