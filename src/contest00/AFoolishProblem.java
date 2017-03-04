package contest00;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AFoolishProblem {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int a = input.nextInt();
    int b = input.nextInt();

    List<Integer> primes = getPrimes(a, b);
    Map<Integer, Integer> pairs = new LinkedHashMap<>();
    for (int i = 0; i < primes.size() - 1; ++i) {
      int prime1 = primes.get(i), prime2 = primes.get(i + 1);
      if ((prime1 + prime2) % 3 != 0) {
        pairs.put(prime1, prime2);
      }
    }

    List<Integer> lambda = new ArrayList<>();
    for (int prime : pairs.keySet()) {
      if (pairs.containsValue(prime)) {
        lambda.add(prime);
      }
    }

    System.out.println("There are " + pairs.size() + " Sigma pairs in the given range.");
    for (int prime : pairs.keySet()) {
      System.out.println("\t" + prime + " " + pairs.get(prime));
    }

    System.out.println("There are " + lambda.size() + " Lambda primes in the given range.");
    for (int prime : lambda) {
      System.out.println("\t" + prime);
    }
  }

  public static List<Integer> getPrimes(int min, int softMax) {
    List<Integer> primes = new ArrayList<>();

    int n = min + 1;
    while (true) {
      boolean prime = true;
      for (int i = 2; i <= Math.sqrt(n); ++i) {
        if (n % i == 0) {
          prime = false;
          break;
        }
      }
      if (prime) {
        primes.add(n);
        if (n >= softMax) {
          break;
        }
      }
      ++n;
    }
    return primes;
  }

}
