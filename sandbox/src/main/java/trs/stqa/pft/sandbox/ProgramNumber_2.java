package trs.stqa.pft.sandbox;

public class ProgramNumber_2 {

  public static void main(String[] args) {

    Point first = new Point(1, 2);
    Point second = new Point(5, 9);

    System.out.println("1. Calculated with function. Distance between X (" + first.x + "," + first.y + ") and Y ("
            + second.x + "," + second.y + ") = " + distance(first, second));

    System.out.println("2. Calculated with class method. Distance between X (" + first.x + "," + first.y + ") and Y ("
            + second.x + "," + second.y + ") = " + first.distance(second));
  }


  public static double distance(Point p1, Point p2) {

    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));

  }


}