#include <algorithm>
#include <iostream>
#include <string>
#include <unordered_set>
#include <vector>

bool is_set(
    const std::string& card1, const std::string& card2, const std::string& card3
) {
  for (int c = 0; c < card1.size(); ++c) {
    std::unordered_set<char> set;
    set.insert(card1[c]);
    set.insert(card2[c]);
    set.insert(card3[c]);
    if (set.size() != 1 && set.size() != 3) return false;
  }
  return true;
}

int main() {
  int n;
  std::vector<std::string> cards;

  std::cin >> n;
  std::string tmp;
  for (int i = 0; i < n; ++i) {
    std::cin >> tmp;
    cards.push_back(tmp);
  }

  std::sort(cards.begin(), cards.end());
  bool found = false;
  for (int i = 0; i < cards.size() - 2; ++i) {
    for (int j = i + 1; j < cards.size() - 1; ++j) {
      for (int k = j + 1; k < cards.size(); ++k) {
        if (is_set(cards[i], cards[j], cards[k])) {
          found = true;
          std::cout << cards[i] << " " << cards[j] << " " << cards[k]
              << std::endl;
        }
      }
    }
  }
  if (!found) {
    std::cout << "NO SETS" << std::endl;
  }
}