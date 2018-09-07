package br.com.amethyst;


import br.com.amethyst.functions.TanhFunction;
import br.com.amethyst.model.Mlp;
import java.util.Arrays;

public class App {

    public static void main(String[] args) {

        //Modelo com numero de neuronios por camada
        int[] layers = new int[]{2,2,1};

        //Dados
        double[][] inputs = new double[][]{{0.0, 0.0}, {1.0, 0.0}, {0.0, 1.0}, {1.0, 1.0}};
        double[][] output = new double[][]{{0.0}, {1.0}, {1.0}, {0.0}};

        //Função de ativação
        TanhFunction f = new TanhFunction();

        //Taxa de aprendizagem
        double rateLearning = 0.01;

        //Intanciação
        Mlp mlp = new Mlp(rateLearning, layers, f);

        //Treinamento
        mlp.train(inputs, output,100000);

        //Testes
        System.out.println(Arrays.toString(mlp.run(new double[]{0.0, 0.0})));
        System.out.println(Arrays.toString(mlp.run(new double[]{1.0, 1.0})));
        System.out.println(Arrays.toString(mlp.run(new double[]{1.0, 0.0})));
        System.out.println(Arrays.toString(mlp.run(new double[]{0.0, 1.0})));

    }


}

