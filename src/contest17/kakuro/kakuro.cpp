#include <cassert>
#include <iostream>
#include <vector>
#include "grid.h"

void debug_print(const Grid& g) {
  std::cout << g;
}

void solve(Grid& grid, unsigned int r, unsigned int c, std::vector<Grid>& solutions) {
  while (!grid.isWhite(r, c)) {
    if (!grid.nextSquare(r, c)) {
      solutions.push_back(grid);
    }
  }

  for (int square = 1; square <= 9; ++square) {
    grid.setSquare(r, c, square);
    if (grid.isValid(r, c)) {
      unsigned int nextRow = r, nextColumn = c;
      if (!grid.nextSquare(nextRow, nextColumn)) {
        solutions.push_back(grid);
      } else {
        solve(grid, nextRow, nextColumn, solutions);
      }
    }
  }
  grid.setSquare(r, c, 0);
}

int main() {
  unsigned int height, width;
  std::cin >> height >> width;

  Grid grid(width, height);

  std::string square;
  for (unsigned int r = 0; r < height; ++r) {
    for (unsigned int c = 0; c < width; ++c) {
      std::cin >> square;
      if (square == "B") {
        grid.setClues(r, c, 0, 0);
      } else if (square == "W") {
        grid.setSquare(r, c, 0);
      } else {
        int horizontalClue = 0;
        int verticalClue = 0;

        int slash = square.find('\\');
        assert(slash != std::string::npos);
        if (slash == 0) {
          horizontalClue = std::stoi(square.substr(1));
        } else if (slash == square.length() - 1) {
          verticalClue = std::stoi(square.substr(0, square.length() - 1));
        } else {
          verticalClue = std::stoi(square.substr(0, slash));
          horizontalClue = std::stoi(square.substr(slash + 1));
        }

        grid.setClues(r, c, horizontalClue, verticalClue);
      }
    }
  }

  std::vector<Grid> solutions;
  solve(grid, 0, 0, solutions);

  assert(!solutions.empty());
  std::cout << solutions.front();
}
