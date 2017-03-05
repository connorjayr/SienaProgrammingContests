package contest05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StrungOut {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    List<String> sequences = new ArrayList<>();
    while (true) {
      String sequence = input.nextLine();
      if (sequence.equals("ZZZ")) {
        break;
      }
      sequences.add(sequence);
    }

    for (int i = 0; i < sequences.size(); ++i) {
      if (i != 0) {
        System.out.println();
      }
      String sequence = sequences.get(i);
      System.out.println("INPUT: " + sequence);

      String[] words = sequence.split(" ");
      System.out.println("THE OCCURS " + countWord(words, "THE") + " TIME(S)");
      System.out.println("NUMBER OF VOWELS: " + countVowels(sequence));
      System.out.println("AVERAGE WORD LENGTH IS " + getAverageWordLength(words));
    }
  }

  public static int countWord(String[] words, String word) {
    int count = 0;
    for (String currentWord : words) {
      if (currentWord.equals(word)) {
        ++count;
      }
    }
    return count;
  }

  public static double getAverageWordLength(String[] words) {
    int totalLength = 0;
    for (String word : words) {
      totalLength += word.length();
    }
    return (double) totalLength / words.length;
  }

  public static int countVowels(String sequence) {
    int count = 0;
    for (char c : sequence.toCharArray()) {
      if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
        ++count;
      }
    }
    return count;
  }

}
