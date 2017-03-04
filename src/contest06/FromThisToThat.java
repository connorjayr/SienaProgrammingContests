package contest06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FromThisToThat {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int dictionarySize = input.nextInt();
    input.nextLine();

    List<String> dictionary = new ArrayList<>();
    for (int i = 0; i < dictionarySize; ++i) {
      dictionary.add(input.nextLine());
    }
    String start = input.nextLine();
    String end = input.nextLine();
    input.close();

    List<String> ladder = new ArrayList<>();
    ladder.add(start);

    boolean ladderFound = false;
    while (!ladder.get(ladder.size() - 1).equals(end)) {
      String currentWord = ladder.get(ladder.size() - 1);

      ladderFound = false;
      for (String word : dictionary) {
        if (!ladder.contains(word) && getDeviation(currentWord, word) == 1) {
          ladder.add(word);
          ladderFound = true;
        }
      }

      if (!ladderFound) {
        break;
      }
    }

    if (ladderFound) {
      for (String ladderWord : ladder) {
        System.out.println(ladderWord);
      }
    } else {
      System.out.println("***");
    }
  }

  public static int getDeviation(String str1, String str2) {
    int deviation = 0;
    for (int i = 0; i < str1.length(); ++i) {
      if (str1.charAt(i) != str2.charAt(i)) {
        ++deviation;
      }
    }
    return deviation;
  }

}
