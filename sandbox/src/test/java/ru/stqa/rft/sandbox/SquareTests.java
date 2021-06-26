package ru.stqa.rft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


// public class SquareTests {
//
//  @Test
//  public void testArea() {
//    Square s = new Square(5);
//    Assert.assertEquals(s.area(), 25);
//
//  }
//}
public class SquareTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(4, 8);
    Assert.assertEquals(p1.distance(p2), 5.385164807134504);
  }

  @Test
  public void testDistance2() {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(4, 8);
    Assert.assertNotNull(p1.distance(p2));

  }

  @Test
  public void testDistance3() {
    Point p1 = new Point(0, 3);
    Point p2 = new Point(3, 0);
    Assert.assertEquals(p1.distance(p2), 4.242640687119285);
//AB = √(xb - xa)2 + (yb - ya)2
  }
}
