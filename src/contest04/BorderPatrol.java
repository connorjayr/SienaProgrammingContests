package contest04;

import java.util.Scanner;

public class BorderPatrol {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int width = input.nextInt();
    int height = input.nextInt();

    int border = 2 * width + 2 * (height - 2);
    int interior = width * height - border;

    System.out.println(border + " on border");
    System.out.println(interior + " in interior");
    if (border == interior) {
      System.out.println("Same size!");
    }
  }

}
