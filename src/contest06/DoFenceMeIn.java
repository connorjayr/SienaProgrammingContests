package contest06;

import java.util.Scanner;

public class DoFenceMeIn {

  public static void main(String[] args) {
    int diameter = new Scanner(System.in).nextInt();

    System.out.println((int) Math.round(Math.pow(diameter, 2) - 3.1415 * Math.pow(diameter / 2.0, 2)));
  }

}
