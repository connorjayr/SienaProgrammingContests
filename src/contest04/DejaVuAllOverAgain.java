package contest04;

import java.math.BigDecimal;
import java.util.Scanner;

public class DejaVuAllOverAgain {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int numerator = input.nextInt();
    int denominator = input.nextInt();

    int scale = (denominator - 1) * 3;

    String decimal = new BigDecimal(numerator).divide(new BigDecimal(denominator), scale, 1).toString().substring(2);

    boolean found = false;
    for (int init = 0; init < denominator; ++init) {
      for (int cycleLength = 1; cycleLength < denominator; ++cycleLength) {
        String cyclingDecimal = decimal.substring(init);
        String cycle = cyclingDecimal.substring(0, cycleLength);
        if (doesCycle(cyclingDecimal, cycle)) {
          System.out.println("proper fraction is\t\t" + numerator + "/" + denominator);
          System.out.println("cycle length is\t\t\t" + cycleLength);
          System.out.println("decimal expansion is\t0." + decimal.substring(0, init) + (!cycle.equals("0") ? "(" + cyclingDecimal.substring(0, cycleLength) + ")" : ""));

          found = true;
          break;
        }
      }
      if (found) break;
    }

  }

  public static boolean doesCycle(String text, String cycleText) {
    int cycleLength = cycleText.length();
    for (int i = cycleLength; i < text.length(); i += cycleLength) {
      if (!text.substring(i - cycleLength, i).equals(cycleText)) {
        return false;
      }
    }
    return true;
  }

}
