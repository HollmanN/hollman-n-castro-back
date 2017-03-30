/**
 * @author Hollman N
 *
 * Created on 3/30/17.
 *
 * Fenwick tree structure, based on papers "A New Algorithm for Updating and Querying
 * Sub-arrays of Multidimensional Arrays" (Pushkar Mishra, 2016) and
 * "A New Data Structure for Cumulative Frequency Tables" (Peter M. Fenwick, 1994)
 *
 * @see "https://arxiv.org/abs/1311.6093v6"
 */

public class FenwickTree {
    private long[][][] tree;
    private int size;

    public FenwickTree(int size) {
        /*Fenwick tree size is (N + 1), to accomplishment numeration from 1 to N without change the original algorithm.*/
        this.tree = new long[size + 1][size + 1][size + 1];
        this.size = size;
    }

    /**
     * Point-update operation on the Fenwick tree
     *
     * @param point : Point to update
     * @param value : Value to update
     */
    public void update(Point3D point, long value) {

        int x = point.getX();

        while (x <= size) {
            int y = point.getY();

            while (y <= size) {
                int z = point.getZ();

                while (z <= size) {
                    tree[x][y][z] += value;
                    z += z & (-z);
                }
                y += y & (-y);
            }
            x += x & (-x);
        }
    }

    /**
     * Accumulative sum from (1,1,1) to (i,j,k)
     * @param i : coordinate x of the point
     * @param j : coordinate y of the point
     * @param k : coordinate z of the point
     * @return accumulative sum
     */
    private long sum(int i, int j, int k) {

        long result = 0;

        int x = i;
        while (x > 0) {
            int y = j;

            while (y > 0) {
                int z = k;

                while (z > 0) {
                    result += tree[x][y][z];
                    z -= z & (-z);
                }
                y -= y & (-y);
            }
            x -= x & (-x);
        }

        return result;
    }

    /**
     * Method to calculate the sum of the numbers of a sub-cube, based on the inclusion-exclusion principle
     * @param pointX1 First point of the sub-cube
     * @param pointX2 Second point of the sub-cube
     * @return
     */
    public long query(Point3D pointX1, Point3D pointX2) {

        int x1 = pointX1.getX();
        int y1 = pointX1.getY();
        int z1 = pointX1.getZ();
        int x2 = pointX2.getX();
        int y2 = pointX2.getY();
        int z2 = pointX2.getZ();

        long value1 = sum(x2, y2, z2) - sum(x1 - 1, y2, z2) - sum(x2, y1 - 1, z2) +
                sum(x1 - 1, y1 - 1, z2);

        long value2 = sum(x2, y2, z1 - 1) - sum(x1 - 1, y2, z1 - 1) -
                sum(x2, y1 - 1, z1 - 1) + sum(x1 - 1, y1 - 1, z1 - 1);

        return value1 - value2;
    }
}
