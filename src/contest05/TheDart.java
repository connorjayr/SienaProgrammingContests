package contest05;

import java.util.Scanner;

public class TheDart {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int r = input.nextInt();
    double x = input.nextDouble();
    double y = input.nextDouble();

    double distance = Math.hypot(x, y);
    if (distance > 1) {
      System.out.println("OFF");
      return;
    }
    if (distance < 1.0 / r) {
      System.out.println("GRAY");
      return;
    }

    int ring = (int) Math.ceil(r * distance);

    double angle = Math.atan2(y, x);
    if (angle < 0) angle += 2 * Math.PI;

    /* A "standard" angle is an angle in which the first alternating ring is white from 0 to 45 degrees */
    boolean standard = angle > 0 && angle < Math.PI / 4
        || angle > Math.PI / 2 && angle < 3 * Math.PI / 4
        || angle > Math.PI && angle < 5 * Math.PI / 4
        || angle > 3 * Math.PI / 2 && angle < 7 * Math.PI / 4;


    System.out.println((ring % 2 == 0) == standard ? "WHITE" : "BLACK");
  }

}
