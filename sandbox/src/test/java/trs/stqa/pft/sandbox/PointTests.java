package trs.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testArea() {

    Point p1 = new Point(1, 2);
    Point p2 = new Point(5, 10);

    Point p3 = new Point(3, 5);
    Point p4 = new Point(7, 10);

    Assert.assertEquals(p1.distance(p2), 8.94427190999916);
    Assert.assertEquals(p3.distance(p4), 6.4031242374328485);

  }


}
