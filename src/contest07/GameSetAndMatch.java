package contest07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GameSetAndMatch {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    List<String> cards = new ArrayList<>();
    for (int i = 0; i < 3; ++i) {
      cards.add(input.nextLine());
    }

    boolean set = true;
    for (int i = 0; i < cards.get(0).length(); ++i) {
      Set<Character> attributes = new HashSet<>();
      for (String card : cards) {
        attributes.add(card.charAt(i));
      }
      int attributeCount = attributes.size();
      if (attributeCount != 1 && attributeCount != cards.size()) {
        set = false;
        break;
      }
    }
    System.out.println(set ? "SET" : "NO SET");
  }

}
