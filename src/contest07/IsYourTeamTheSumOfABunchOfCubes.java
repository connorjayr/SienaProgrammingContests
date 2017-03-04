package contest07;

import java.util.Scanner;

public class IsYourTeamTheSumOfABunchOfCubes {

  public static void main(String[] args) {
    int n = new Scanner(System.in).nextInt();

    System.out.println((int) Math.pow(n * (n + 1) / 2, 2));
  }

}
