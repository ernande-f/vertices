import java.io.IOException;
import java.util.ArrayList;

public class calculo {
    public static void main(String[] args) throws IOException {
        new tela();
    }

    public static ArrayList<Double> calculatorvertices(double num1, double num2, double num3) {
        ArrayList<Double> vertices = new ArrayList<>();

        double delta = (Math.pow(num2, 2)) - 4 * num1 * num3;
        double yvertice = (0 - delta) / (4 * num1);
        double xvertice = (0 - num2) / (2 * num1);

        vertices.add(xvertice);
        vertices.add(yvertice);

        return vertices;

    }
}
