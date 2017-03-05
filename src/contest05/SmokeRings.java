package contest05;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SmokeRings {

  private static final List<Character> ALPHABET = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
      'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int n = input.nextInt();
    int c = input.nextInt() - 1;
    int r = input.nextInt() - 1;

    Grid grid = new Grid(n);
    grid.fill();
    System.out.println(grid.getLetterAt(c, r));
  }

  public static char getNextLetter(char currentLetter) {
    int index = ALPHABET.indexOf(currentLetter) + 1;
    while (index >= ALPHABET.size()) {
      index -= ALPHABET.size();
    }
    return ALPHABET.get(index);
  }

  static class Grid {
    private final char[][] grid;
    private Map.Entry<Integer, Integer> direction;


    public Grid(int n) {
      grid = new char[n][n];
      for (int r = 0; r < n; ++r) {
        for (int c = 0; c < n; ++c) {
          grid[r][c] = ' ';
        }
      }
      direction = new AbstractMap.SimpleEntry<>(0, 1);
    }

    public boolean isFull() {
      for (char[] row : grid) {
        for (char letter : row) {
          if (letter == ' ') {
            return false;
          }
        }
      }
      return true;
    }

    public void fill() {
      int r = 0;
      int c = 0;
      char previous = 'Z';
      while (!isFull()) {
        grid[r][c] = getNextLetter(previous);
        previous = grid[r][c];

        int nextRow = r + direction.getKey();
        int nextColumn = c + direction.getValue();
        if (nextRow < 0 || nextRow >= grid.length || nextColumn < 0 || nextColumn >= grid.length || getLetterAt(nextRow, nextColumn) != ' ') {
          rotateDirection();
        }
        r += direction.getKey();
        c += direction.getValue();
      }
    }

    public void rotateDirection() {
      int r = direction.getKey();
      int c = direction.getValue();

      if (r == 0 && c == 1) {
        direction = new AbstractMap.SimpleEntry<>(1, 0);
      } else if (r == 1 && c == 0) {
        direction = new AbstractMap.SimpleEntry<>(0, -1);
      } else if (r == 0 && c == -1) {
        direction = new AbstractMap.SimpleEntry<>(-1, 0);
      } else {
        direction = new AbstractMap.SimpleEntry<>(0, 1);
      }
    }

    public char getLetterAt(int r, int c) {
      return grid[r][c];
    }
  }

}
