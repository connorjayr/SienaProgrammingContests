#include <iostream>
#include <vector>

void shuffle(const std::vector<int>& deck, std::vector<int>& shuffled) {
  int mid = (deck.size() + 1) / 2;
  shuffled.clear();
  for (int i = 0, j = mid; i < mid; ++i, ++j) {
    shuffled.push_back(deck[i]);
    if (j < deck.size()) {
      shuffled.push_back(deck[j]);
    }
  }
}

bool is_original_deck(const std::vector<int>& deck) {
  for (int i = 0; i < deck.size() - 1; ++i) {
    if (deck[i] > deck[i + 1]) return false;
  }
  return true;
}

int main() {
  int n;
  std::cin >> n;

  std::vector<int> deck;
  for (int card = 0; card < n; ++card) {
    deck.push_back(card + 1);
  }

  std::vector<int> shuffled;
  int shuffles = 0;
  do {
    shuffle(deck, shuffled);
    deck = shuffled;
    
    ++shuffles;
  } while (!is_original_deck(deck));
  std::cout << shuffles << std::endl;
}