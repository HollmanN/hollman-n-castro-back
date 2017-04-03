/**
 * Created by Hollman_Castro on 3/30/17.
 *
 * Test cases based on HackerRank page
 *
 * @see https://www.hackerrank.com/challenges/cube-summation
 */

import domain.FenwickTree;
import domain.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;

public class FenwickTreeTest {

    @Test
    public void testFirstCaseHackerRank(){
        long result = 0;

        FenwickTree tree = new FenwickTree(4);

        tree.update(new Point3D(2, 2, 2), 4);
        result = tree.query(new Point3D(1, 1, 1), new Point3D(3, 3, 3));

        assertEquals(4, result);

        tree.update(new Point3D(1, 1, 1), 23);

        result = tree.query(new Point3D(2, 2, 2), new Point3D(4, 4, 4));
        assertEquals(4, result);

        result = tree.query(new Point3D(1, 1, 1), new Point3D(3, 3, 3));
        assertEquals(27, result);
    }

    @Test
    public void testSecondCaseHackerRank(){
        long result = 0;

        FenwickTree tree = new FenwickTree(4);

        tree.update(new Point3D(2, 2, 2), 1);
        result = tree.query(new Point3D(1, 1, 1), new Point3D(1, 1, 1));

        assertEquals(0, result);

        result = tree.query(new Point3D(1, 1, 1), new Point3D(2, 2, 2));
        assertEquals(1, result);

        result = tree.query(new Point3D(2, 2, 2), new Point3D(2, 2, 2));
        assertEquals(1, result);
    }

}
