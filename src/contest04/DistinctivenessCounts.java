package contest04;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DistinctivenessCounts {

  public static void main(String[] args) {
    int n = new Scanner(System.in).nextInt();

    Set<List<Integer>> triangles = new HashSet<>();

    //TODO: Optimize run time for a high number of vertices (>100)
    for (int vertex1 = 3; vertex1 <= n; ++vertex1) {
      for (int vertex2 = 2; vertex2 < vertex1; ++vertex2) {
        for (int vertex3 = 1; vertex3 < vertex2; ++vertex3) {
          List<Integer> sides = Arrays.asList(
              getDistance(vertex1, vertex2, n),
              getDistance(vertex2, vertex3, n),
              getDistance(vertex3, vertex1, n)
          );
          Collections.sort(sides);
          triangles.add(sides);
        }
      }
      if (vertex1 % 100 == 0) {
        System.out.println(vertex1);
      }
    }

    System.out.println(triangles.size() + " " + triangles);
  }

  public static int getDistance(int vertex1, int vertex2, int n) {
    int vertex1Prime = vertex1;
    while (vertex1Prime > n / 2) {
      vertex1Prime -= n;
    }
    int vertex2Prime = vertex2;
    while (vertex2Prime > n / 2) {
      vertex2Prime -= n;
    }
    return Math.min(
        Math.max(vertex1, vertex2) - Math.min(vertex1, vertex2),
        Math.max(vertex1Prime, vertex2Prime) - Math.min(vertex1Prime, vertex2Prime)
    );
  }

}
