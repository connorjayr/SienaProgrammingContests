package contest05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class IFoundAWinner {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double bestScore = input.nextDouble();
    List<Double> scores = new ArrayList<>();
    for (int i = 0; i < 3; ++i) {
      scores.add(input.nextDouble());
    }

    Collections.sort(scores);
    scores.remove(2);
    scores.remove(0);

    double score = scores.get(0);
    StringBuilder scoreVal = new StringBuilder();
    if (Math.round(score) == score) {
      scoreVal.append((int) score);
    } else {
      scoreVal.append(score);
    }
    System.out.println(scoreVal + ": " + (score >= bestScore ? "WINNER!" : "DID NOT WIN"));

  }

}
