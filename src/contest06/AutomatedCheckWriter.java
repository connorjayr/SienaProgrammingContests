package contest06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutomatedCheckWriter {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    List<String> numbers = new ArrayList<>();
    while (true) {
      String num = input.nextLine();
      if (!num.equals("0")) {
        numbers.add(num);
      } else {
        break;
      }
    }

    for (String num : numbers) {
      boolean first = true;
      for (NumberGroup group : NumberGroup.fromNumber(num)) {
        System.out.print((!first ? " " : "") + group);
        if (first) {
          first = false;
        }
      }
      System.out.println();
    }
  }

  static class NumberGroup {
    private int[] digits;
    private String suffix;

    public NumberGroup(char hundreds, char tens, char ones, String suffix) {
      digits = new int[]{
          Integer.parseInt(hundreds + ""),
          Integer.parseInt(tens + ""),
          Integer.parseInt(ones + "")
      };
      this.suffix = suffix;
    }

    public static List<NumberGroup> fromNumber(String num) {
      List<NumberGroup> groups = new ArrayList<>();
      while (num.length() % 3 != 0) {
        num = "0" + num;
      }

      boolean thousand = num.length() > 3;
      for (int i = 0; i < num.length(); i += 3) {
        groups.add(new NumberGroup(num.charAt(i), num.charAt(i + 1), num.charAt(i + 2), thousand ? "thousand" : ""));
        if (thousand) {
          thousand = false;
        }
      }

      return groups;
    }

    public int getValue() {
      return digits[0] * 100 + digits[1] * 10 + digits[2];
    }

    @Override
    public String toString() {
      if (getValue() != 0) {
        int hundreds = digits[0];
        return (getPlaceValue(hundreds, false, 0) + (hundreds != 0 ? "hundred " : "")
            + getPlaceValue(digits[1], true, digits[2])
            + (digits[1] != 1 ? getPlaceValue(digits[2], false, 0) : "")
            + suffix).trim();
      } else {
        return "";
      }
    }
  }

  public static String getPlaceValue(int value, boolean tens, int ones) {
    if (!tens) {
      switch (value) {
        case 1: return "one ";
        case 2: return "two ";
        case 3: return "three ";
        case 4: return "four ";
        case 5: return "five ";
        case 6: return "six ";
        case 7: return "seven ";
        case 8: return "eight ";
        case 9: return "nine ";
        default: return "";
      }
    } else {
      switch (value) {
        case 1: {
          if (ones != 0) {
            switch (ones) {
              case 1: return "eleven ";
              case 2: return "twelve ";
              case 3: return "thirteen ";
              case 4: return "fourteen ";
              case 5: return "fifteen ";
              case 6: return "sixteen ";
              case 7: return "seventeen ";
              case 8: return "eighteen ";
              case 9: return "nineteen ";
              default: return "ten ";
            }
          } else {
            return "ten ";
          }
        }
        case 2: return "twenty ";
        case 3: return "thirty ";
        case 4: return "forty ";
        case 5: return "fifty ";
        case 6: return "sixty ";
        case 7: return "seventy ";
        case 8: return "eighty ";
        case 9: return "ninety ";
        default: return "";
      }
    }
  }

}
