package contest05;

import java.util.Scanner;

public class HoldTheLine {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int nailCount = input.nextInt();
    int replaced = input.nextInt();

    int permutations = 0;

    for (int leftBound = 1; leftBound < nailCount; ++leftBound) {
      for (int rightBound = leftBound + 1; rightBound <= nailCount; ++rightBound) {
        if (leftBound > replaced) {
          ++permutations;
        } else if (leftBound < replaced) {
          if (rightBound < replaced) {
            ++permutations;
          }
        }
      }
    }

    System.out.println(permutations);
  }

}
