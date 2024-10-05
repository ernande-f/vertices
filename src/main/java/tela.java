import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class tela {
    public tela(){
        JFrame tela = new JFrame("Vertices");

        tela.setSize(800, 600);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null);

        JTextField input1 = new JTextField();
        input1.setBounds(50, 50, 100, 30);

        JLabel letraa = new JLabel("A da função");
        letraa.setBounds(50, 25, 100, 30);

        JTextField input2 = new JTextField();
        input2.setBounds(50, 100, 100, 30);

        JLabel letrab = new JLabel("B da função");
        letrab.setBounds(50, 75, 100, 30);

        JTextField input3 = new JTextField();
        input3.setBounds(50, 150, 100, 30);

        JLabel letrac = new JLabel("C da função");
        letrac.setBounds(50, 125, 100, 30);

        JLabel resultadoLabel = new JLabel("O vértice fica em: ");
        resultadoLabel.setBounds(50, 200, 200, 30);

        JButton calcularButton = new JButton("Calcular");
        calcularButton.setBounds(200, 100, 120, 30);

        JPanel graficoPanel = new JPanel();
        graficoPanel.setBounds(350, 50, 400, 400);

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // calcula os vértices usando o método que ta em calculo.java
                double num1 = Double.parseDouble(input1.getText());
                double num2 = Double.parseDouble(input2.getText());
                double num3 = Double.parseDouble(input3.getText());
                ArrayList<Double> resultado = calculo.calculatorvertices(num1, num2, num3);

                // muda o texto e coloca o resultado
                resultadoLabel.setText("O vértice fica em: " + resultado);


                // cria a série com o vertice
                XYSeries series = new XYSeries("Vértice da parábola");
                // adiciona as coordenadas no vertice
                series.add(resultado.get(0), resultado.get(1));
                System.out.printf("vértice: [%s, %s]", resultado.getFirst(), resultado.getLast()); // debug

                // cria uma coleção com os valores da função e o vértice
                XYSeriesCollection dataset        = new XYSeriesCollection();
                XYSeries           functionSeries = new XYSeries("f(x) = ax^2 + bx + c");

                for (double x = -10; x <= 10; x += 0.1) {
                    double y = num1 * x * x + num2 * x + num3; // Calcula f(x)
                    functionSeries.add(x, y);
                }

                // usa o jfreechart pra criar um plano de grafico utilizando o dataset
                JFreeChart xyChart = ChartFactory.createXYLineChart(
                        "Gráfico com os vértices",
                        "X",
                        "Y",
                        dataset);



                // adiciona as series da função e do vertice no dataset
                dataset.addSeries(functionSeries);
                dataset.addSeries(series);
                // faz o render do ponto vértice e dos pontos reais da função
                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
                renderer.setSeriesShapesVisible(0, true); // Tornar visíveis os pontos
                renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-2, -1, 2, 2));
                renderer.setSeriesPaint(0, Color.RED);

                renderer.setSeriesPaint(0, Color.BLUE);
                renderer.setSeriesStroke(0, new BasicStroke(1.0f));

                xyChart.getXYPlot().setRenderer(renderer);

                // cria em si o painel
                ChartPanel chartPanel = new ChartPanel(xyChart);
                chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
                graficoPanel.removeAll(); //
                graficoPanel.add(chartPanel);
                graficoPanel.validate();
                graficoPanel.repaint();

            }
        });


        // adiciona os conteúdos pra tela
        tela.add(input1);
        tela.add(letraa);
        tela.add(input2);
        tela.add(letrab);
        tela.add(input3);
        tela.add(letrac);
        tela.add(resultadoLabel);
        tela.add(calcularButton);
        tela.add(graficoPanel);

        tela.setVisible(true); // faz com que eles sejam visiveis
    }
}

