package contest06;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PrimeCuts {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    List<Map.Entry<Integer, Integer>> cuts = new ArrayList<>();
    int testCases = input.nextInt();
    for (int i = 0; i < testCases; ++i) {
      int n = input.nextInt();
      int c = input.nextInt();

      cuts.add(new AbstractMap.SimpleEntry<>(n, c));
    }

    for (Map.Entry<Integer, Integer> cut : cuts) {
      int n = cut.getKey();
      int c = cut.getValue();

      List<Integer> primes = getPrimes(n);
      int size = primes.size();
      int cutAmount = (size - (size % 2 == 0 ? c * 2 : c * 2 - 1)) / 2;

      for (int i = 0; i < cutAmount; ++i) {
        primes.remove(0);
        primes.remove(primes.size() - 1);
      }

      System.out.print(n + " " + c);
      for (int prime : primes) {
        System.out.print(" " + prime);
      }
      System.out.println();
    }

  }

  public static List<Integer> getPrimes(int max) {
    List<Integer> primes = new ArrayList<>();
    for (int num = 2; num <= max; ++num) {
      boolean prime = true;
      for (int i = 2; i <= Math.sqrt(num); ++i) {
        if (num % i == 0) {
          prime = false;
          break;
        }
      }
      if (prime) {
        primes.add(num);
      }
    }
    return primes;
  }

}
