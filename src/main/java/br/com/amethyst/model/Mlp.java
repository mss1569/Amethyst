package br.com.amethyst.model;

import br.com.amethyst.functions.Function;
import br.com.amethyst.layers.HiddenLayer;
import br.com.amethyst.layers.InputLayer;
import br.com.amethyst.layers.OutputLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Mlp é a classe utilizada
 * para representar uma rede neural
 * de multiplas camadas.
 *
 * @author Matheus Sena
 */
public class Mlp {
    private double rateLearn;
    private HiddenLayer[] hiddenLayers;
    private InputLayer inputLayer;
    private OutputLayer outputLayer;

    /**
     * Construtor Mlp.
     *
     * @param rateLearn Taxa de aprendizagem.
     * @param layers    Vetor com a representação do
     *                  numero de layers e aquantidade de neuronios em cada.
     * @param f         Função de ativação da rede.
     * @author Matheus Sena
     */
    public Mlp(double rateLearn, int[] layers, Function f) {
        this.rateLearn = rateLearn;
        this.hiddenLayers = new HiddenLayer[layers.length - 2];

        this.inputLayer = new InputLayer(layers[0]);

        for (int i = 1; i < layers.length - 1; i++) {
            if (i == 1) {
                this.hiddenLayers[i - 1] = new HiddenLayer(layers[i], f, inputLayer);
            } else {
                this.hiddenLayers[i - 1] = new HiddenLayer(layers[i], f, this.hiddenLayers[i - 2]);
            }
        }

        this.outputLayer = new OutputLayer(layers[layers.length - 1], f, this.hiddenLayers[this.hiddenLayers.length - 1]);
    }

    /**
     * run é o metodo responsavel execução
     * do calculo de saida da rede.
     *
     * @param input Vetor de entrada(s).
     * @return Saida da rede.
     * @author Matheus Sena
     */
    public double[] run(double[] input) {
        double[] output = new double[this.outputLayer.size];

        this.inputLayer.inputs = input;

        for (HiddenLayer hiddenLayer : this.hiddenLayers) {
            for (int j = 0; j < hiddenLayer.size; j++) {
                hiddenLayer.getOutput(j);
            }
        }

        for (int i = 0; i < output.length; i++) {
            double out = this.outputLayer.getOutput(i);
            output[i] = out;
        }
        return output;
    }

    /**
     * train é um metodo que implementa o algoritimo
     * responsavel pela realização
     * do treinamento da rede.
     *
     * @param inputs Matriz contendo os vetore(s)
     *               de entrada(s).
     * @param output Matriz contendo os vetore(s)
     *               de saida(s) esperada(s).
     * @param epochs Numero de epocas.
     * @author Matheus Sena
     */
    public void train(double[][] inputs, double[][] output, int epochs) {
        System.out.println("\tInicio do treinamento:");
        System.out.println("Numero de exemplos: " + inputs.length);
        System.out.println("Numero de epocas: " + epochs);
        System.out.println("\n");

        for (int epo = 0; epo < epochs; epo++) {
            double erro_medio = backProp(inputs, output);
            System.out.println("-------------------------------------");
            System.out.println("Época: " + (epo + 1) + " ==> Erro: " + erro_medio);
        }
        System.out.println("-------------------------------------");
    }

    /**
     * backProp é a implementação
     * do algoritmo de retro-propagação
     *
     * @param inputs Matriz contendo os vetore(s)
     *               de entrada(s).
     * @param output Matriz contendo os vetore(s)
     *               de saida(s) esperada(s).
     * @author Matheus Sena
     */
    private double backProp(double[][] inputs, double[][] output) {
        double erro_medio = 0.0;
        for (int h = 0; h < inputs.length; h++) {
            run(inputs[h]);

            double erro = 0.0;
            for (int j = 0; j < this.outputLayer.size; j++) {
                erro += Math.pow((this.outputLayer.neurons[j].y - output[h][j]), 2);
            }
            erro_medio += (erro / 2);

            for (int j = 0; j < this.outputLayer.size; j++) {
                double e = output[h][j] - this.outputLayer.neurons[j].y;
                this.outputLayer.neurons[j].delta =
                        this.outputLayer.neurons[j].function.funcDerivate(this.outputLayer.neurons[j].v) *
                                e;
                for (int i = 0; i < this.outputLayer.lastLayer.size; i++) {
                    this.outputLayer.neurons[j].weigths[i] += this.rateLearn *
                            this.outputLayer.lastLayer.getOutput(i) *
                            this.outputLayer.neurons[j].delta;
                }
            }
            for (int l = this.hiddenLayers.length - 1; l >= 0; l--) {
                for (int j = 0; j < this.hiddenLayers[l].size; j++) {
                    double s = 0;
                    if (l == this.hiddenLayers.length - 1) {
                        for (int k = 0; k < this.outputLayer.size; k++) {
                            s += this.outputLayer.neurons[k].delta * this.outputLayer.neurons[k].weigths[j];
                        }
                    } else {
                        for (int k = 0; k < this.hiddenLayers[l + 1].size; k++) {
                            s += this.hiddenLayers[l + 1].neurons[k].delta * this.hiddenLayers[l + 1].neurons[k].weigths[j];
                        }
                    }
                    this.hiddenLayers[l].neurons[j].delta =
                            this.hiddenLayers[l].neurons[j].function.funcDerivate(this.hiddenLayers[l].neurons[j].v) *
                                    s;
                    for (int i = 0; i < this.hiddenLayers[l].lastLayer.size; i++) {
                        this.hiddenLayers[l].neurons[j].weigths[i] += this.rateLearn *
                                this.hiddenLayers[l].lastLayer.getOutput(i) *
                                this.hiddenLayers[l].neurons[j].delta;
                    }
                }
            }
        }
        return erro_medio;
    }

    /**
     * train é um metodo que implementa o algoritimo
     * responsavel pela realização
     * do treinamento da rede.
     *
     * @param inputs Matriz contendo os vetore(s)
     *               de entrada(s).
     * @param output Matriz contendo os vetore(s)
     *               de saida(s) esperada(s).
     * @author Matheus Sena
     */
    public void train(double[][] inputs, double[][] output) {
        double erro_medio = backProp(inputs, output);
        System.out.println("-------------------------------------");
        System.out.println("Numero de exemplos: " + inputs.length);
        System.out.println("Erro: " + erro_medio);
        System.out.println("-------------------------------------");
    }

    /**
     * getBackup é um metodo responsavel
     * por realizar um backup de todos os
     * pesos das sinapses dos neuroneos da rede.
     *
     * @return Backup de todos os pesos.
     * @author Matheus Sena
     */
    public List<List<double[]>> getBackup() {
        List<List<double[]>> r = new ArrayList<List<double[]>>();
        for (int l = 0; l < this.hiddenLayers.length; l++) {
            List<double[]> t = new ArrayList<double[]>();
            for (int i = 0; i < this.hiddenLayers[l].size; i++) {
                t.add(this.hiddenLayers[l].neurons[i].weigths);
            }
            r.add(t);
        }
        List<double[]> aa = new ArrayList<double[]>();
        for (int i = 0; i < this.outputLayer.size; i++) {
            aa.add(this.outputLayer.neurons[i].weigths);
        }
        r.add(aa);
        return r;
    }

    /**
     * restoreBackup é um metodo responsavel
     * por realizar a restauração
     * do backup dos pesos da rede.
     *
     * @param backup Backup de todos os pesos.
     * @author Matheus Sena
     */
    public void restoreBackup(List<List<double[]>> backup) {
        for (int l = 0; l < this.hiddenLayers.length; l++) {
            List<double[]> t = backup.get(l);
            for (int i = 0; i < this.hiddenLayers[l].size; i++) {
                this.hiddenLayers[l].neurons[i].weigths = t.get(i);
            }
        }
        List<double[]> aa = backup.get(backup.size() - 1);
        for (int i = 0; i < this.outputLayer.size; i++) {
            this.outputLayer.neurons[i].weigths = aa.get(i);
        }
    }

}
