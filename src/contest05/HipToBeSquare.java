package contest05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HipToBeSquare {

  public static void main(String[] args) {
    int n = new Scanner(System.in).nextInt();
    List<Integer> squares = new ArrayList<>();
    for (int i = 1; i <= getMaxSquare(n); ++i) {
      squares.add(i);
    }

    List<Integer> found = null;
    for (int size = 1; size <= getMaxFill(n); ++size) {
      for (List<Integer> permutation : getSquarePermutations(new ArrayList<>(), 0, size, squares)) {
        if (getSquareSum(permutation) == n) {
          found = permutation;
          break;
        }
      }
      if (found != null) break;
    }

    if (found != null) {
      StringBuilder squaresVal = new StringBuilder();
      for (int i = 0; i < found.size(); ++i) {
        if (i != 0) {
          squaresVal.append(" ");
        }
        squaresVal.append(found.get(i));
      }
      System.out.println(squaresVal);
    } else {
      System.out.println("Cool!");
    }
  }

  public static int getMaxFill(int n) {
    int current = 1;
    int i = 2;
    while (current <= n) {
      current += Math.pow(i++, 2);
    }
    return --i;
  }

  public static int getMaxSquare(int n) {
    return (int) Math.sqrt(n);
  }

  public static List<List<Integer>> getSquarePermutations(List<Integer> current, int next, int size, List<Integer> squares) {
    if (current.size() == size) {
      return Collections.singletonList(new ArrayList<>(current));
    }
    List<List<Integer>> permutations = new ArrayList<>();
    for (int i = next; i < squares.size(); ++i) {
      List<Integer> nextPermutation = new ArrayList<>(current);
      nextPermutation.add(squares.get(i));
      permutations.addAll(getSquarePermutations(nextPermutation, i + 1, size, squares));
    }
    return permutations;
  }

  public static int getSquareSum(List<Integer> squares) {
    int sum = 0;
    for (int square : squares) {
      sum += Math.pow(square, 2);
    }
    return sum;
  }

}
