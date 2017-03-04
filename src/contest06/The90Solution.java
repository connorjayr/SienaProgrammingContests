package contest06;

import java.util.Scanner;

public class The90Solution {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int m = input.nextInt();
    int n = input.nextInt();

    int count = 0;
    for (int num = m; num <= n; ++num) {
      int divisibleCount = 1;
      for (int x = 2; x <= 9; ++x) {
        if (num % x == 0) {
          ++divisibleCount;
        }
      }
      if (divisibleCount == 9) {
        ++count;
      }
    }

    System.out.println(count);
  }

}
