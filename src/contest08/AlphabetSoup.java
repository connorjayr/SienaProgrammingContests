package contest08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AlphabetSoup {

  public static final char[] alphabet = {
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
      'X', 'Y', 'Z'
  };
  private static List<Character> modifiedAlphabet = new ArrayList<>();

  public static void main(String[] args) {
    String[] input = new Scanner(System.in).nextLine().split(" ");
    String modifying = input[0];

    List<String> words = new ArrayList<>();
    words.add(modifying);
    for (int i = 2; i < input.length; ++i) {
      words.add(input[i]);
    }

    for (char modChar : modifying.toCharArray()) {
      if (!modifiedAlphabet.contains(modChar)) {
        modifiedAlphabet.add(modChar);
      }
    }
    for (char letter : alphabet) {
      if (!modifiedAlphabet.contains(letter)) {
        modifiedAlphabet.add(letter);
      }
    }

    words.sort((word1, word2) -> {
      int minLength = word1.length() < word2.length() ? word1.length() : word2.length();
      for (int i = 0; i < minLength; ++i) {
        if (modifiedAlphabet.indexOf(word1.charAt(i)) > modifiedAlphabet.indexOf(word2.charAt(i))) {
          return 1;
        } else if (modifiedAlphabet.indexOf(word1.charAt(i)) < modifiedAlphabet.indexOf(word2.charAt(i))) {
          return -1;
        }
      }
      return word1.length() < word2.length() ? -1 : 1;
    });

    for (String word : words) {
      System.out.println(word);
    }
  }

}
