package ru.stqa.pft.sandbox;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void testDistance() {
        Point point1 = new Point(3, 5);
        Point point2 = new Point(1, 7);

        Assert.assertEquals(point1.distance(point1, point2), 6.32, 0.01);
    }
}
