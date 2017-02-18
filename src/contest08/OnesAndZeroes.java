package contest08;

import java.math.BigInteger;
import java.util.Scanner;

public class OnesAndZeroes {

  public static void main(String[] args) {
    BigInteger input = BigInteger.valueOf(new Scanner(System.in).nextInt());

    int m;
    int n = 0;
    boolean found = false;
    for (m = 1; m <= 1000; ++m) {
      for (n = 0; n <= 1000 - m; ++n) {
        StringBuilder oneZeroStr = new StringBuilder();
        for (int i = 0; i < m; ++i) {
          oneZeroStr.append('1');
        }
        for (int i = 0; i < n; ++i) {
          oneZeroStr.append('0');
        }
        if (new BigInteger(oneZeroStr.toString()).mod(input).equals(BigInteger.ZERO)) {
          found = true;
          break;
        }
      }
      if (found) break;
    }
    if (found) {
      System.out.println(m + " " + n);
    } else {
      System.out.println("NONE");
    }
  }

}
