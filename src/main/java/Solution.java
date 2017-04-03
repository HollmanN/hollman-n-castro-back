/**
 * @author Hollman N
 * Created on 3/30/17.
 * <p>
 * Class Solution for Cube Summation HackerRank submit
 * @see https://www.hackerrank.com/challenges/cube-summation
 */

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);

        int testCases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < testCases; i++) {

            String testCaseFirstLine = scanner.nextLine();

            int N = Integer.valueOf(testCaseFirstLine.split(" ")[0]);
            int M = Integer.valueOf(testCaseFirstLine.split(" ")[1]);

            FenwickTree tree = new FenwickTree(N);

            for (int j = 0; j < M; j++) {

                String[] arguments = scanner.nextLine().split(" ");

                String operations = arguments[0];

                switch (operations) {
                    case "UPDATE": {
                        int x = Integer.valueOf(arguments[1]);
                        int y = Integer.valueOf(arguments[2]);
                        int z = Integer.valueOf(arguments[3]);
                        long value = Long.valueOf(arguments[4]);

                        Point3D point = new Point3D(x, y, z);
                        tree.update(point, value);
                        break;
                    }

                    case "QUERY": {
                        int x1 = Integer.valueOf(arguments[1]);
                        int y1 = Integer.valueOf(arguments[2]);
                        int z1 = Integer.valueOf(arguments[3]);
                        int x2 = Integer.valueOf(arguments[4]);
                        int y2 = Integer.valueOf(arguments[5]);
                        int z2 = Integer.valueOf(arguments[6]);

                        Point3D pointX1 = new Point3D(x1, y1, z1);
                        Point3D pointX2 = new Point3D(x2, y2, z2);

                        long result = tree.query(pointX1, pointX2);
                        System.out.println(result);

                        break;
                    }
                }
            }
        }
    }

    /**
     * @author Hollman N
     *         <p>
     *         Created on 3/30/17.
     *         <p>
     *         Fenwick tree structure, based on papers "A New Algorithm for Updating and Querying
     *         Sub-arrays of Multidimensional Arrays" (Pushkar Mishra, 2016) and
     *         "A New Data Structure for Cumulative Frequency Tables" (Peter M. Fenwick, 1994)
     * @see "https://arxiv.org/abs/1311.6093v6"
     */

    private static class FenwickTree {
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

            /**
             * The original implementation of Fenwick tree always adds a value to a position.
             * In order to make a plain update, we have to subtract the new value vs the accumulative result
             * to get the delta factor, before make the update process.
             */
            long delta = value - query(point, point);

            int x = point.getX();

            while (x <= size) {
                int y = point.getY();

                while (y <= size) {
                    int z = point.getZ();

                    while (z <= size) {
                        tree[x][y][z] += delta;
                        z += z & (-z);
                    }
                    y += y & (-y);
                }
                x += x & (-x);
            }
        }

        /**
         * Accumulative sum from (1,1,1) to (i,j,k)
         *
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
         *
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

    private static class Point3D {
        private int x;
        private int y;
        private int z;

        public Point3D(int x, int y, int z) {
            super();
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            this.z = z;
        }
    }
}