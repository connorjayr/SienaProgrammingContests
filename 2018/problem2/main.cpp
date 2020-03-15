#include <iostream>

int main() {
  int h1, m1, h2, m2;
  std::cin >> h1 >> m1 >> h2 >> m2;

  h2 += 24;

  int h = h2 - h1;
  int m = m2 - m1;
  if (m < 0) {
    h -= 1;
    m += 60;
  }
  h %= 24;

  std::cout << h << " " << m << std::endl;
}