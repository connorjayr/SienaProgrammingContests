package contest08;

import java.util.Scanner;

public class ThisContestIsHeatingUp {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int temperature = input.nextInt();

    double gasMark;
    if (temperature <= 275) {
      gasMark = Math.pow(2, (temperature - 275) / 25);
    } else {
      gasMark = (temperature - 275) / 25.0 + 1;
    }
    System.out.printf("%.2f", gasMark);
  }

}
