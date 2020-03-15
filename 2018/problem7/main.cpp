#include <iostream>
#include <string>
#include <vector>

typedef std::pair<int, int> Point;

int main() {
  int rows, cols, k;
  std::cin >> rows >> cols >> k;

  int r1, c1, r2, c2;
  std::vector<std::pair<Point, Point>> terminals;
  for (int i = 0; i < k; ++i) {
    std::cin >> r1 >> c1 >> r2 >> c2;
    terminals.push_back(std::make_pair(Point(r1, c1), Point(r2, c2)));
  }

  int board[rows][cols];
  for (int r = 0; r < rows; ++r) {
    for (int c = 0; c < cols; ++c) {
      board[r][c] = -1;
    }
  }
  for (int i = 0; i < terminals.size(); ++i) {
    const std::pair<Point, Point>& terminal_pair = terminals[i];
    board[terminal_pair.first.first][terminal_pair.first.second] = i + 1;
    board[terminal_pair.second.first][terminal_pair.second.second] = i + 1;
  }
}