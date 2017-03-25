package contest17;

import java.util.Scanner;

public class InTheYear2025 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int a = input.nextInt();
    int b = input.nextInt();

    for (int value = a; value <= b; ++value) {
      int n = 1;
      while (getCubeSum(1, n, 0) < value) {
        ++n;
      }
      if (getCubeSum(1, n, 0) == value) {
        System.out.println(value + " Perfect\t1 " + n);
      }

      int m = 2;
      n = 2;
      while (getCubeSum(m, n, 0) < value) {
        while (getCubeSum(m, n, 0) < value) {
          ++n;
        }
        if (getCubeSum(m, n, 0) == value) {
          break;
        } else {
          n = ++m;
        }
      }
      if (getCubeSum(m, n, 0) == value) {
        System.out.println(value + " Normal\t" + m + " " + n);
      }

      m = 1;
      int k = 2;
      n = 3;
      while (getCubeSum(m, n, k) < value) {
        while (getCubeSum(m, n, k) < value) {
          while (getCubeSum(m, n, k) < value && k > m) {
            --k;
          }
          if (getCubeSum(m, n, k) == value && k > m) {
            break;
          } else {
            ++n;
            k = n - 1;
          }
        }
        if (getCubeSum(m, n, k) == value && k > m) {
          break;
        } else {
          k = ++m + 1;
          n = k + 1;
        }
      }
      if (getCubeSum(m, n, k) == value && k > m) {
        System.out.println(value + " Near\t\t" + m + " " + n + " " + k);
      }

    }
  }

  public static int getCubeSum(int a, int b, int exclude) {
    int sum = 0;
    for (int n = a; n <= b; ++n) {
      sum += Math.pow(n, 3);
    }
    return sum - (int) Math.pow(exclude, 3);
  }

}
