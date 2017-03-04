package contest00;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DoYouDropTheLowestGrade {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    List<Integer> grades = new ArrayList<>();
    for (int i = 0; i < 13; ++i) {
      grades.add(input.nextInt());
    }

    Collections.sort(grades);
    for (int i = 0; i < 3; ++i) {
      grades.remove(0);
    }

    int sum = 0;
    for (int grade : grades) {
      sum += grade;
    }
    System.out.println("Quiz average is " + (int) Math.round((double) sum / grades.size()) + ".");
  }

}
