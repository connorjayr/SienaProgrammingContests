package contest08;

import java.util.Scanner;

public class IntercalaryYears {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int year = input.nextInt();

    if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
      System.out.println("LEAP");
    } else {
      System.out.println("COMMON");
    }
  }

}
