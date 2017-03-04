package contest00;

import java.util.Scanner;

public class OnlyTheShadowKnows {

  public static void main(String[] args) {
    double n = 4.0 / new Scanner(System.in).nextInt();

    double x = 1, y, z = 0;
    do {
      ++x;
    } while (1 / x > n);
    double init = x;

    boolean found = false;
    while (true) {
      for (y = init; y < x; ++y) {
        z = Math.pow(n - 1 / x - 1 / y, -1);
        if (z > 0 && z == Math.round(z)) {
          found = true;
          break;
        }
      }
      if (found) break;
      ++x;
    }

    System.out.println((int) z + " " + (int) y + " " + (int) x);
  }

}
