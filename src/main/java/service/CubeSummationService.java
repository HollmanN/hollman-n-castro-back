package service;

import domain.FenwickTree;
import domain.Point3D;
import domain.TestCase;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Hollman_Castro on 4/3/17.
 */
public class CubeSummationService {

    public String helloService() {
        return "Hello world";
    }

    public String run(TestCase testCase){
        ArrayList<Long> results = new ArrayList<>();

        FenwickTree tree = new FenwickTree(testCase.getN());
        for (String operation : testCase.getOperations()) {

            String[] arguments = operation.split(" ");

            String action = arguments[0];

            switch (action) {
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
                    results.add(result);

                    break;
                }
            }
        }

        return results.stream().map(Object::toString).
                collect(Collectors.joining("\n"));
    }
}
