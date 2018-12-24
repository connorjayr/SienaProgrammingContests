#ifndef __GRID_H__
#define __GRID_H__

#include <iostream>
#include <set>
#include <utility>

class Grid {
  unsigned int width;
  unsigned int height;
  
  int *squares;

  int *horizontalClues;
  int *verticalClues;

  void addToRange(unsigned int r, unsigned int c, std::pair<int, int>& range) const;

  void clear();
  void copy(const Grid& g);

public:
  Grid(unsigned int width_, unsigned int height_);
  Grid(const Grid& g) { copy(g); }
  Grid& operator=(const Grid& g);
  ~Grid() { clear(); }

  unsigned int getWidth() const { return width; }
  unsigned int getHeight() const { return height; }

  bool isWhite(unsigned int r, unsigned int c) const { return getSquare(r, c) != -1; }

  int getSquare(unsigned int r, unsigned int c) const { return squares[r * width + c]; }
  int getHorizontalClue(unsigned int r, unsigned int c) const;
  int getVerticalClue(unsigned int r, unsigned int c) const;

  void setSquare(unsigned int r, unsigned int c, int square) { squares[r * width + c] = square; }
  void setClues(unsigned int r, unsigned int c, int horizontal, int vertical);

  bool nextSquare(unsigned int& r, unsigned int& c) const;
  bool isUniqueSum(unsigned int r, unsigned int c, std::set<int>& addends) const;
  bool isValid(unsigned int r, unsigned int c) const;
};
std::ostream& operator<<(std::ostream& output, const Grid& g);

#endif
