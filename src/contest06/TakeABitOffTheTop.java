package contest06;

import java.util.Scanner;

public class TakeABitOffTheTop {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int x = input.nextInt();

    int n = 2;
    int m;
    boolean found = false;
    while (true) {
      for (m = 1; m < n; ++m) {
        if (n * (n + 1) / 2 - m * (m + 1) / 2 == x) {
          found = true;
          break;
        }
      }
      if (found) break;
      ++n;
    }

    System.out.println(m + " " + n);
  }

}
