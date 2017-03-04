package contest00;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATwistedTriangleTeaser {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    double[] point1 = new double[2];
    point1[0] = input.nextInt();
    point1[1] = input.nextInt();

    double[] point2 = new double[2];
    point2[0] = input.nextInt();
    point2[1] = input.nextInt();

    double[] point3 = new double[2];
    point3[0] = input.nextInt();
    point3[1] = input.nextInt();

    int x = input.nextInt(), y = input.nextInt();

    Triangle triangle = new Triangle(new Line(point1, point2), new Line(point2, point3), new Line(point1, point3));
    Location location = triangle.getLocation(new double[]{x, y});
    if (location.equals(Location.IN)) {
      System.out.println(x + ", " + y + " IS IN THE TRIANGLE.");
    } else if (location.equals(Location.OUT)) {
      System.out.println(x + ", " + y + " IS OUT OF THE TRIANGLE.");
    } else if (location.equals(Location.AT)) {
      System.out.println(x + ", " + y + " IS PART OF THE PERIMETER OF THE TRIANGLE.");
    }
  }

  static class Triangle {
    private Map<Line, Location> edges = new HashMap<>();

    public Triangle(Line edge1, Line edge2, Line edge3) {
      edges.put(edge1, edge1.getLocation(edge2.getIntersection(edge3)));
      edges.put(edge2, edge2.getLocation(edge1.getIntersection(edge3)));
      edges.put(edge3, edge3.getLocation(edge1.getIntersection(edge2)));
    }

    public Location getLocation(double[] point) {
      boolean perimeter = false;
      for (Line edge : edges.keySet()) {
        Location location = edge.getLocation(point);
        if (location.equals(Location.AT)) {
          perimeter = true;
        } else if (!location.equals(edges.get(edge))) {
          return Location.OUT;
        }
      }
      if (perimeter) {
        return Location.AT;
      } else {
        return Location.IN;
      }
    }
  }

  static class Line {
    private double slope;
    private double intercept;
    
    public Line(double[] point1, double[] point2) {
      slope = (point2[1] - point1[1]) / (point2[0] - point1[0]);
      intercept = point1[1] - slope * point1[0];
    }

    public double getSlope() {
      return slope;
    }

    public double getIntercept() {
      return intercept;
    }

    public Location getLocation(double[] point) {
      double lineY = slope * point[0] + intercept;
      if (point[1] > lineY) {
        return Location.ABOVE;
      } else if (point[1] < lineY) {
        return Location.BELOW;
      } else {
        return Location.AT;
      }
    }

    public double[] getIntersection(Line line) {
      double x = (line.getIntercept() - intercept) / (slope - line.getSlope());
      return new double[]{x, slope * x + intercept};
    }

  }

  enum Location {
    ABOVE,
    AT,
    BELOW,
    IN,
    OUT;

    static Location invert(Location location) {
      if (location.equals(ABOVE)) {
        return BELOW;
      } else if (location.equals(BELOW)) {
        return ABOVE;
      } else {
        return AT;
      }
    }
  }

}
