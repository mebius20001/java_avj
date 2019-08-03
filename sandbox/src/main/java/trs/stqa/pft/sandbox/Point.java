package trs.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point next) {
    return Math.sqrt((next.x - this.x) * (next.x - this.x) + (next.y - this.y) * (next.y - this.y));

  }

}
