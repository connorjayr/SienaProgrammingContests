package contest08;

import java.util.Scanner;

public class WhatALovelyQuadYouHave {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();

    System.out.println((n * (2 * n + 1) * (n + 1) * (3 * (int) Math.pow(n, 2) + 3 * n - 1)) / 30);
  }

}
