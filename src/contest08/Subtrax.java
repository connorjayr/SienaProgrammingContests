package contest08;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Subtrax {

  public static final Path[] paths = {
      new Path(1, 4, 8),
      new Path(2, 5, 10), new Path(2, 4, 6),
      new Path(3, 4, 5), new Path(3, 6, 11),
      new Path(4, 5, 7), new Path(4, 8, 13), new Path(4, 6, 9),
      new Path(5, 10, 15), new Path(5, 8, 11),
      new Path(6, 8, 10), new Path(6, 11, 16),
      new Path(7, 10, 13),
      new Path(8, 10, 12), new Path(8, 11, 14),
      new Path(9, 11, 13),
      new Path(10, 13, 16),
      new Path(11, 13, 15)
  };

  public static void main(String[] args) {
    String[] input = new Scanner(System.in).nextLine().split(" ");

    List<Integer> pegs = new ArrayList<>();
    for (int i = 1; i < input.length; ++i) {
      pegs.add(Integer.parseInt(input[i]));
    }

    Solution finalSolution = null;

    List<Solution> currentSolutions = new ArrayList<>();
    List<Solution> nextSolutions = null;
    currentSolutions.add(new Solution(new ArrayList<>(), pegs));
    while (currentSolutions.size() != 0) {
      nextSolutions = new ArrayList<>();
      for (Solution solution : currentSolutions) {
        nextSolutions.addAll(solution.getNextSolutions());
      }
      for (Solution solution : nextSolutions) {
        if (solution.getPegCount() == 1) {
          finalSolution = solution;
        }
      }
      currentSolutions = nextSolutions;
    }

    if (finalSolution == null) {
      System.out.println("NONE");
    } else {
      for (Path path : finalSolution.getPaths()) {
        System.out.println(path.getFrom() + "-" + path.getTo());
      }
    }
  }

  public static List<Path> getPathsInvolvingPeg(int peg) {
    List<Path> paths = new ArrayList<>();
    for (Path path : Subtrax.paths) {
      if (path.getFrom() == peg || path.getTo() == peg) {
        paths.add(path);
      }
    }
    return paths;
  }

  static class Solution {
    private final List<Path> paths;
    private final List<Integer> pegs;

    public Solution(List<Path> paths, List<Integer> pegs) {
      this.paths = paths;
      this.pegs = pegs;
    }

    public List<Path> getPaths() {
      return paths;
    }

    public int getPegCount() {
      return pegs.size();
    }

    public List<Solution> getNextSolutions() {
      List<Solution> solutions = new ArrayList<>();

      for (int peg : pegs) {
        List<Path> paths = getPathsInvolvingPeg(peg);
        for (Path path : paths) {
          if (pegs.contains(path.getOver()) && !pegs.contains(path.getOtherPeg(peg))) {
            List<Path> newPaths = new ArrayList<>(this.paths);
            newPaths.add(new Path(peg, path.getOver(), path.getOtherPeg(peg)));

            List<Integer> newPegs = new ArrayList<>(pegs);
            newPegs.remove(Integer.valueOf(peg));
            newPegs.remove(Integer.valueOf(path.getOver()));
            newPegs.add(path.getOtherPeg(peg));

            solutions.add(new Solution(newPaths, newPegs));
          }
        }
      }

      return solutions;
    }
  }

  static class Path {
    int from;
    int over;
    int to;

    public Path(int from, int over, int to) {
      this.from = from;
      this.over = over;
      this.to = to;
    }

    public int getFrom() {
      return from;
    }

    public int getOver() {
      return over;
    }

    public int getTo() {
      return to;
    }

    public int getOtherPeg(int peg) {
      if (from == peg) {
        return to;
      } else {
        return from;
      }
    }
  }

}
