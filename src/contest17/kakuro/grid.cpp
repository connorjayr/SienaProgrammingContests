#include "grid.h"

void Grid::clear() {
  delete[] squares;
  delete[] horizontalClues;
  delete[] verticalClues;
}

void Grid::copy(const Grid& g) {
  width = g.width;
  height = g.height;

  squares = new int[width * height];
  horizontalClues = new int[width * height];
  verticalClues = new int[width * height];
  for (unsigned int r = 0; r < height; ++r) {
    for (unsigned int c = 0; c < width; ++c) {
      unsigned int i = r * width + c;
      squares[i] = g.squares[i];
      horizontalClues[i] = g.horizontalClues[i];
      verticalClues[i] = g.verticalClues[i];
    }
  }
}

Grid::Grid(unsigned int width_, unsigned int height_) : width(width_),
                                                        height(height_) {
  squares = new int[width * height];
  horizontalClues = new int[width * height];
  verticalClues = new int[width * height];
}

Grid& Grid::operator=(const Grid& g) {
  clear();
  copy(g);

  return *this;
}

int Grid::getHorizontalClue(unsigned int r, unsigned int c) const {
  while (isWhite(r, --c));
  return horizontalClues[r * width + c];
}

int Grid::getVerticalClue(unsigned int r, unsigned int c) const {
  while (isWhite(--r, c));
  return verticalClues[r * width + c];
}

void Grid::setClues(unsigned int r, unsigned int c,
                    int horizontal, int vertical) {
  squares[r * width + c] = -1;

  horizontalClues[r * width + c] = horizontal;
  verticalClues[r * width + c] = vertical;
}

void Grid::addToRange(unsigned int r, unsigned int c, std::pair<int, int>& range) const {
  int square = getSquare(r, c);
  if (square != 0) {
    range.first += square;
    range.second += square;
  } else {
    range.first += 1;
    range.second += 9;
  }
}

bool Grid::nextSquare(unsigned int& r, unsigned int& c) const {
  ++c;
  if (c == width) {
    ++r;
    c = 0;

    if (r == height) return false;
  }
  return true;
}

bool Grid::isUniqueSum(unsigned int r, unsigned int c, std::set<int>& addends) const {
  int square = getSquare(r, c);
  return square == 0 || addends.insert(square).second;
}

bool Grid::isValid(unsigned int r, unsigned int c) const {
  int square = getSquare(r, c);

  // Validate the horizontal sum for this square
  std::set<int> addends;
  addends.insert(square);
  std::pair<int, int> range(square, square);
  for (int dc = 1; c + dc < width && isWhite(r, c + dc); ++dc) {
    if (!isUniqueSum(r, c + dc, addends)) return false;
    addToRange(r, c + dc, range);
  }
  for (int dc = -1; c + dc >= 0 && isWhite(r, c + dc); --dc) {
    if (!isUniqueSum(r, c + dc, addends)) return false;
    addToRange(r, c + dc, range);
  }
  int horizontalClue = getHorizontalClue(r, c);
  if (horizontalClue < range.first || horizontalClue > range.second) return false;

  // Validate the vertical sum for this square
  addends.clear();
  addends.insert(square);
  range = std::pair<int, int>(square, square);
  for (int dr = 1; r + dr < height && isWhite(r + dr, c); ++dr) {
    if (!isUniqueSum(r + dr, c, addends)) return false;
    addToRange(r + dr, c, range);
  }
  for (int dr = -1; r + dr >= 0 && isWhite(r + dr, c); --dr) {
    if (!isUniqueSum(r + dr, c, addends)) return false;
    addToRange(r + dr, c, range);
  }
  int verticalClue = getVerticalClue(r, c);
  if (verticalClue < range.first || verticalClue > range.second) return false;

  return true;
}

std::ostream& operator<<(std::ostream& output, const Grid& g) {
  for (int r = 0; r < g.getHeight(); ++r) {
    bool white = false;
    for (int c = 0; c < g.getWidth(); ++c) {
      if (g.isWhite(r, c)) {
        if (!white) white = true;
        else output << " ";

        output << g.getSquare(r, c);
      }
    }
    if (!white) output << "0";
    output << std::endl;
  }

  return output;
}
