package contest04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TouringWithTheKnights {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int[] moves = new int[64];
    for (int i = 0; i < 64; ++i) {
      moves[i] = input.nextInt();
    }

    List<Integer> visited = new ArrayList<>();
    int invalidLine = -1;
    for (int i = 0; i + 1 < 64; ++i) {
      if (!isValidMove(moves[i], moves[i + 1]) || visited.contains(moves[i + 1])) {
        invalidLine = i + 1;
        break;
      }
      visited.add(moves[i]);
    }

    if (invalidLine == -1) {
      System.out.println(isValidMove(moves[63], moves[0]) ? "Knight's Cycle" : "Knight's Tour");
    } else {
      System.out.println("Not a valid tour: " + moves[invalidLine] + " (line " + (invalidLine + 1) + ")");
    }

  }

  public static boolean isValidMove(int pos1, int pos2) {
    int pos1Row = pos1 / 10;
    int pos1Col = pos1 % 10;

    int pos2Row = pos2 / 10;
    int pos2Col = pos2 % 10;

    int rowDiff = Math.abs(pos1Row - pos2Row);
    int colDiff = Math.abs(pos1Col - pos2Col);

    return rowDiff == 2 && colDiff == 1
        || colDiff == 2 && rowDiff == 1;
  }

}
