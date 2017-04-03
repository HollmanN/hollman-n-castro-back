package domain;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Hollman_Castro on 4/3/17.
 */
public class TestCase {
    private int n;
    private ArrayList<String> operations;

    public TestCase() {
    }

    public TestCase(int n, ArrayList<String> operations) {
        this.n = n;
        this.operations = operations;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public ArrayList<String> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<String> operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "TestCase {" +
                "n=" + n +
                ", operations=" + operations.stream().map(Object::toString).
                collect(Collectors.joining(", "))
                + '}';
    }
}
