package contest00;

import java.math.BigInteger;
import java.util.Scanner;

public class GoForthAndMultiply {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String aStr = input.nextLine();
    String bStr = input.nextLine();

    BigInteger a = new BigInteger(aStr.substring(0, aStr.length() - 1));
    BigInteger b = new BigInteger(bStr.substring(0, bStr.length() - 1));

    System.out.println(a.multiply(b).toString());
  }

}
