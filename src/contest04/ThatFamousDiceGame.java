package contest04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ThatFamousDiceGame {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    List<Integer> rolls = new ArrayList<>();
    for (int i = 0; i < 5; ++i) {
      rolls.add(input.nextInt());
    }

    Map<Integer, Integer> frequency = new HashMap<>();
    boolean threeOfAKind = false;
    boolean fourOfAKind = false;
    for (int roll : rolls) {
      frequency.putIfAbsent(roll, 0);

      int newFrequency = frequency.get(roll) + 1;
      if (newFrequency == 3) {
        threeOfAKind = true;
      } else if (newFrequency == 4) {
        fourOfAKind = true;
      }
      frequency.put(roll, newFrequency);
    }

    String result = "Garbage";
    switch (frequency.size()) {
      case 1: result = "Five of a Kind"; break;
      case 2:
        if (fourOfAKind) {
          result = "Four of a Kind";
          break;
        } else {
          result = "Full House";
          break;
        }
      case 3:
        if (threeOfAKind) {
          result = "Three of a Kind";
          break;
        } else {
          result = "Two Pair";
          break;
        }
      case 4: result = "One Pair";
    }

    System.out.println(result);
  }

}
