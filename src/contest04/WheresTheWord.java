package contest04;

import java.util.Scanner;

public class WheresTheWord {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    String text = input.nextLine().replace(" ", "");
    String word = input.nextLine();
    int length = word.length();

    boolean found = false;
    int index;
    int step = 0;

    for (index = 0; index < text.length(); ++index) {
      for (step = getMinStep(text, index, length); step <= getMaxStep(text, index, length); ++step) {
        if (step == 0) {
          continue;
        }
        if (getWordFromStep(text, index, step).startsWith(word)) {
          found = true;
          break;
        }
      }
      if (found) break;
    }

    System.out.println(found ? "Word starts at " + (index + 1) + " and steps by " + step + "." : "Not Found");
  }

  public static int getMinStep(String text, int index, int length) {
    String subtext = text.substring(0, index);
    if (subtext.length() + 1 < length) {
      return -1;
    }
    return -1 * subtext.length() - 1 / length;
  }

  public static int getMaxStep(String text, int index, int length) {
    String subtext = text.substring(index + 1, text.length());
    if (subtext.length() + 1 < length) {
      return -1;
    }
    return subtext.length() + 1 / length;
  }

  public static String getWordFromStep(String text, int index, int step) {
    StringBuilder word = new StringBuilder();
    while (index >= 0 && index < text.length()) {
      word.append(text.charAt(index));
      index += step;
    }
    return word.toString();
  }

}
