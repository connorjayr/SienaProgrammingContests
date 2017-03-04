package contest07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IsASpecialYear {

  public static void main(String[] args) {
    String[] range = new Scanner(System.in).nextLine().split(" ");
    int min = Integer.parseInt(range[0]);
    int max = Integer.parseInt(range[1]);

    List<Integer> years = new ArrayList<>();
    for (int year = min; year <= max; ++year) {
      if (isPalindrome(toHexadecimal(year))) {
        years.add(year);
      }
    }

    for (int i = 0; i < years.size(); ++i) {
      int year = years.get(i);
      System.out.println((i + 1) + "\t" + toHexadecimal(year) + "\t" + year);
    }
    if (years.size() == 0) {
      System.out.println("0");
    }
  }

  public static String toHexadecimal(int decimal) {
    int placeValues = 0;
    while (decimal >= Math.pow(16, placeValues)) {
      ++placeValues;
    }
    --placeValues;

    String hex = "";
    for (int placeValue = placeValues; placeValue >= 0; --placeValue) {
      int digit = 0;
      while (decimal >= digit * Math.pow(16, placeValue)) {
        ++digit;
      }
      hex += toChar(--digit);
      decimal -= digit * Math.pow(16, placeValue);
    }

    return hex;
  }

  public static boolean isPalindrome(String str) {
    return new StringBuffer(str).reverse().toString().equals(str);
  }

  public static String toChar(int digit) {
    if (digit >= 0 && digit <= 9) {
      return digit + "";
    } else {
      switch (digit) {
        case 10: return "A";
        case 11: return "B";
        case 12: return "C";
        case 13: return "D";
        case 14: return "E";
        case 15: return "F";
        default: return "";
      }
    }
  }

}
