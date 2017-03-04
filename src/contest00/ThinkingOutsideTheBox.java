package contest00;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ThinkingOutsideTheBox {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    List<Integer> x = new ArrayList<>(), y = new ArrayList<>();
    for (int i = 0; i < 4; ++i) {
      x.add(input.nextInt());
      y.add(input.nextInt());
    }
    Collections.sort(x);
    Collections.sort(y);

    Rectanle rectanle = new Rectanle(x.get(0), y.get(0), x.get(x.size() - 1), y.get(y.size() - 1));

    int inX = input.nextInt(), inY = input.nextInt();
    if (rectanle.isInside(inX, inY)) {
      System.out.println(inX + ", " + inY + " IS IN THE BOX.");
    } else if (rectanle.isOnPerimeter(inX, inY)) {
      System.out.println(inX + ", " + inY + " IS PART OF THE PERIMETER OF THE BOX.");
    } else {
      System.out.println(inX + ", " + inY + " IS OUT OF THE BOX.");
    }
  }

  static class Rectanle {
    int minX;
    int minY;
    int maxX;
    int maxY;

    public Rectanle(int minX, int minY, int maxX, int maxY) {
      this.minX = minX;
      this.minY = minY;
      this.maxX = maxX;
      this.maxY = maxY;
    }

    public boolean isOnPerimeter(int x, int y) {
      if ((y == minY || y == maxY) && minX <= x && x <= maxX) {
        return true;
      } else if ((x == minX || x == maxX) && minY <= y && y <= maxY) {
        return true;
      } else {
        return false;
      }
    }

    public boolean isInside(int x, int y) {
      return minX < x && x < maxX && minY < y && y < maxY;
    }

  }

}
