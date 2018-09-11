package br.com.amethyst.neurons;

import br.com.amethyst.functions.Function;

/**
 * Neuron é a classe utilizada
 * para representar um neuronio dentro
 * de uma rede neural.
 *
 * @author Matheus Sena
 */
public class Neuron {
    public Function function;
    public double delta;
    public double bias;
    public double[] weigths;
    public double v;
    public double y;

    /**
     * Construtor Neuron.
     *
     * @param sizeLastLayer Numero de entradas.
     * @param f             Função de ativação do neuronio.
     * @author Matheus Sena
     */
    public Neuron(int sizeLastLayer, Function f) {
        this.weigths = new double[sizeLastLayer];
        this.function = f;
        this.bias = 1;
        for (int i = 0; i < sizeLastLayer; i++) {
            this.weigths[i] = Math.random() / 10;
        }
    }

    /**
     * getOutput é o metodo responsavel pela realização do calculo
     * da saida do neuronio apartir de suas entradas.
     *
     * @param inputs Vetor de entradas.
     * @return Saida do neuronio.
     * @author Matheus Sena
     */
    public double getOutput(double[] inputs) {
        this.v = 0.0;
        for (int i = 0; i < inputs.length; i++) {
            this.v += inputs[i] * this.weigths[i];
        }
        this.v += this.bias;
        this.y = this.function.func(this.v);
        return this.y;
    }
}
