package contest07;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FriendlyNumbers {

  private static final Map<Integer, Integer> sumCache = new HashMap<>();

  public static void main(String[] args) {
    String[] range = new Scanner(System.in).nextLine().split(" ");
    int min = Integer.parseInt(range[0]);
    int max = Integer.parseInt(range[1]);

    List<Map.Entry<Integer, Integer>> friendlyPairs = new ArrayList<>();
    for (int num = min; num <= max; ++num) {
      int numFactorSum = getFactorSum(num);
      int sumFactorSum = getFactorSum(numFactorSum);
      sumCache.put(num, numFactorSum);
      sumCache.put(numFactorSum, sumFactorSum);
      if (num == sumFactorSum && num < numFactorSum) {
        friendlyPairs.add(new AbstractMap.SimpleEntry<>(num, numFactorSum));
      }
    }

    System.out.println(friendlyPairs.size() + " friendly pairs");
    for (Map.Entry<Integer, Integer> friendlyPair : friendlyPairs) {
      System.out.println(friendlyPair.getKey() + "\t" + friendlyPair.getValue());
    }
  }

  public static int getFactorSum(int num) {
    if (sumCache.containsKey(num)) {
      return sumCache.get(num);
    }
    int sum = 1;
    for (int factor = 2; factor <= Math.sqrt(num); ++factor) {
      if (num % factor == 0) {
        sum += factor + num / factor;
      }
    }
    return sum;
  }

}
