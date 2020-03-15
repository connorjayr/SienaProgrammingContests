#include <cmath>
#include <iostream>
#include <string>
#include <vector>

void solve(
    std::vector<std::string>& board,
    std::vector<int>& row_bal, std::vector<int>& col_bal,
    int r = 0, int c = 0
) {
  if (r >= board.size()) {
    for (int bal : row_bal) {
      if (bal != 0) return;
    }
    for (int bal : col_bal) {
      if (bal != 0) return;
    }

    for (const std::string& row : board) {
      std::cout << row << std::endl;
    }
    exit(0);
  }

  if (abs(row_bal[r]) > board[r].size() - c ||
      abs(col_bal[c]) > board.size() - r) {
    return;
  }

  char init_square = board[r][c];
  if (init_square != 'O') {
    int offset = init_square == 'B' ? 1 : -1;
    row_bal[r] += offset;
    col_bal[c] += offset;

    solve(
        board, row_bal, col_bal,
        r + (c + 1 == board[r].size() ? 1 : 0), (c + 1) % board[r].size()
    );

    row_bal[r] -= offset;
    col_bal[c] -= offset;
    return;
  }
  
  std::vector<char> squares = {'B', 'W'};
  for (char square : squares) {
    board[r][c] = square;
    if ((r >= 2 && board[r - 1][c] == square && board[r - 2][c] == square) ||
        (c >= 2 && board[r][c - 1] == square && board[r][c - 2] == square)) {
      continue;
    }

    int offset = square == 'B' ? 1 : -1;
    row_bal[r] += offset;
    col_bal[c] += offset;

    solve(
        board, row_bal, col_bal,
        r + (c + 1 == board[r].size() ? 1 : 0), (c + 1) % board[r].size()
    );

    row_bal[r] -= offset;
    col_bal[c] -= offset;
  }
  board[r][c] = init_square;
}

int main() {
  int n;
  std::cin >> n;

  std::string tmp;
  std::vector<std::string> board;
  for (int r = 0; r < n; ++r) {
    std::cin >> tmp;
    board.push_back(tmp);
  }

  std::vector<int> row_bal(n, 0);
  std::vector<int> col_bal(board.front().size(), 0);
  solve(board, row_bal, col_bal);
}