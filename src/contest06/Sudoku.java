package contest06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sudoku {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int[][] grid = new int[9][9];

    for (int row = 0; row < 9; ++row) {
      String rowStr = input.nextLine();
      for (int column = 0; column < 9; ++column) {
        grid[row][column] = Integer.parseInt(rowStr.charAt(column) + "");
      }
    }

    List<Integer> present = new ArrayList<>();
    for (int row = 0; row < 9; ++row) {
      present.clear();
      for (int column = 0; column < 9; ++column) {
        if (present.contains(grid[row][column])) {
          System.out.println((row + 1) + " " + (column + 1) + " row");
          return;
        }
        present.add(grid[row][column]);
      }
    }

    for (int column = 0; column < 9; ++column) {
      present.clear();
      for (int row = 0; row < 9; ++row) {
        if (present.contains(grid[row][column])) {
          System.out.println((row + 1) + " " + (column + 1) + " column");
          return;
        }
        present.add(grid[row][column]);
      }
    }

    for (int subgridX = 0; subgridX < 3; ++subgridX) {
      for (int subgridY = 0; subgridY < 3; ++subgridY) {
        present.clear();
        for (int row = subgridX * 3; row < (subgridX + 1) * 3; ++row) {
          for (int column = subgridY * 3; column < (subgridY + 1) * 3; ++column) {
            if (present.contains(grid[row][column])) {
              System.out.println((row + 1) + " " + (column + 1) + " subgrid");
              return;
            }
            present.add(grid[row][column]);
          }
        }
      }
    }
  }

}
