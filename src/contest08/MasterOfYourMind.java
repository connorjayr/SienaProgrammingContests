package contest08;

import java.util.Scanner;

public class MasterOfYourMind {

  public static void main(String[] args) {
    String in = new Scanner(System.in).nextLine();

    char[] guess = in.split(" ")[0].toCharArray();
    char[] sequence = in.split(" ")[1].toCharArray();

    int blackPegs = 0;
    int whitePegs = 0;
    for (int i = 0; i < guess.length; ++i) {
      if (guess[i] == sequence[i]) {
        ++blackPegs;
        guess[i] = ' ';
        sequence[i] = ' ';
      }
    }

    for (char guessChar : guess) {
      if (guessChar != ' ') {
        for (int i = 0; i < sequence.length; ++i) {
          if (guessChar == sequence[i]) {
            ++whitePegs;
            sequence[i] = ' ';
            break;
          }
        }
      }
    }

    for (int i = 0; i < blackPegs; ++i) {
      System.out.print("B");
    }
    for (int i = 0; i < whitePegs; ++i) {
      System.out.print("W");
    }
    if (blackPegs == 0 && whitePegs == 0) {
      System.out.print("NONE");
    }
  }

}
