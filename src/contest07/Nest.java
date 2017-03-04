package contest07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Nest {

  public static void main(String[] args) {
    String expression = new Scanner(System.in).nextLine();
    List<Character> opening = new ArrayList<>();
    boolean valid = true;
    for (char c : expression.toCharArray()) {
      char closing = getClosing(c);
      if (closing != ' ') {
        opening.add(c);
      } else {
        if (getClosing(opening.get(opening.size() - 1)) != c) {
          valid = false;
          break;
        } else {
          opening.remove(opening.size() - 1);
        }
      }
    }
    if (opening.size() != 0) {
      valid = false;
    }

    System.out.println(valid ? "VALID" : "NOT VALID");
  }

  public static char getClosing(char opening) {
    switch (opening) {
      case '(': return ')';
      case '[': return ']';
      case '{': return '}';
      case '<': return '>';
      default: return ' ';
    }
  }

}
