package contest07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoingForASpin {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    List<Integer> sequence1 = new ArrayList<>();
    String[] sequence1Str = input.nextLine().split(" ");
    for (int i = 0; i < sequence1Str.length - 1; ++i) {
      sequence1.add(Integer.parseInt(sequence1Str[i]));
    }

    List<Integer> sequence2 = new ArrayList<>();
    String[] sequence2Str = input.nextLine().split(" ");
    for (int i = 0; i < sequence2Str.length - 1; ++i) {
      sequence2.add(Integer.parseInt(sequence2Str[i]));
    }

    boolean cycle = false;
    for (int i = 0; i < sequence1.size(); ++i) {
      int num = sequence1.remove(0);
      sequence1.add(num);
      if (sequence1.equals(sequence2)) {
        cycle = true;
        break;
      }
    }

    System.out.println(cycle ? "CYCLE" : "NO CYCLE");
  }

}
