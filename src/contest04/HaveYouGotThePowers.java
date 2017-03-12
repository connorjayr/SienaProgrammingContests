package contest04;

import java.util.Scanner;

public class HaveYouGotThePowers {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int a = input.nextInt();
    int b = input.nextInt();
    int c = input.nextInt();

    int solution = (int) Math.pow(a, b) - (int) Math.pow(b, a);

    System.out.println(c == solution ? "YES" : "NO; c should be " + solution);
  }

}
