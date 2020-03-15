#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

int main() {
  std::string str;
  int n;
  std::vector<int> indices;

  std::cin >> str >> n;
  int tmp;
  for (int i = 0; i < n; ++i) {
    std::cin >> tmp;
    indices.push_back(tmp);
  }

  std::sort(indices.begin(), indices.end(), std::greater<int>());
  for (int i : indices) {
    str.erase(str.begin() + (i - 1));
  }

  std::cout << str << std::endl;
}