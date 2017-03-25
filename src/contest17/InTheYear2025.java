package contest17;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InTheYear2025 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int a = input.nextInt();
    int b = input.nextInt();

    for (int value = a; value <= b; ++value) {
      List<Integer> cubes = new ArrayList<>();

      // Perfect scubey years
      int nextCube = 1;
      while (getCubeSum(cubes) < value) {
        cubes.add(nextCube++);
      }

      if (getCubeSum(cubes) == value) {
        System.out.println(value + " Perfect\t1 " + (nextCube - 1));
      }

      //Normal scubey years
      List<Integer> normalCubes = new ArrayList<>(cubes);
      int nextNormalCube = nextCube;
      for (int m = 2; m <= nextNormalCube; ++m) {
        List<Integer> currentNormalCubes = new ArrayList<>(normalCubes.subList(m - 1, normalCubes.size()));

        while (value - getCubeSum(currentNormalCubes) >= Math.pow(nextNormalCube, 3)) {
          currentNormalCubes.add(nextNormalCube);
          normalCubes.add(nextNormalCube++);
        }
        if (getCubeSum(currentNormalCubes) == value) {
          System.out.println(value + " Normal\t" + m + " " + (nextNormalCube - 1));
        }
      }

      //Near scubey years
      normalCubes = new ArrayList<>(cubes);
      nextNormalCube = nextCube;
      for (int m = 1; m < nextNormalCube; ++m) {
        List<Integer> currentNormalCubes = new ArrayList<>(normalCubes.subList(m - 1, normalCubes.size()));

        while (value - getCubeSum(currentNormalCubes) >= Math.pow(nextNormalCube, 3)) {
          currentNormalCubes.add(nextNormalCube);
          normalCubes.add(nextNormalCube++);
        }

        List<Integer> nearCubes = new ArrayList<>(currentNormalCubes);
        int nextNearCube = nextCube;
        for (int k = m + 1; k < nextNearCube; ++k) {
          List<Integer> currentNearCubes = new ArrayList<>(nearCubes);
          currentNearCubes.remove(k - m);

          while (value - getCubeSum(currentNearCubes) >= Math.pow(nextNearCube, 3)) {
            currentNearCubes.add(nextNearCube);
            nearCubes.add(nextNearCube++);
          }
          if (getCubeSum(currentNearCubes) == value) {
            System.out.println(value + " Near\t\t" + m + " " + (nextNearCube - 1) + " " + k);
          }
        }
      }

    }
  }

  public static int getCubeSum(List<Integer> cubes) {
    int sum = 0;
    for (int value : cubes) {
      sum += Math.pow(value, 3);
    }
    return sum;
  }

}
