package br.com.amethyst.layers;

import br.com.amethyst.neurons.Neuron;
import br.com.amethyst.functions.Function;

/**
 * HiddenLayer é a classe utilizada
 * para representar a(s) camada(s) escondidas
 * de uma rede neural.
 *
 * @author Matheus Sena
 */
public class HiddenLayer extends Layer {
    public Neuron[] neurons;
    public Layer lastLayer;

    /**
     * Construtor HiddenLayer.
     *
     * @param size      Numero de entradas da layer.
     * @param function  Função de ativação dos neuronios.
     * @param lastLayer Layer anterior ao atual.
     * @author Matheus Sena
     */
    public HiddenLayer(int size, Function function, Layer lastLayer) {
        this.size = size;
        this.lastLayer = lastLayer;
        this.neurons = new Neuron[this.size];
        for (int i = 0; i < this.size; i++) {
            this.neurons[i] = new Neuron(this.lastLayer.size, function);
        }
    }

    @Override
    public double getOutput(int i) {
        double[] inputs = new double[lastLayer.size];
        for (int j = 0; j < lastLayer.size; j++) {
            inputs[j] = lastLayer.getOutput(j);
        }
        return this.neurons[i].getOutput(inputs);
    }
}
