package br.com.amethyst.layers;

/**
 * InputLayer é a classe utilizada
 * para representar a camada de entrada
 * de uma rede neural.
 *
 * @author Matheus Sena
 */
public class InputLayer extends Layer {

    public double[] inputs;

    /**
     * Construtor InputLayer.
     *
     * @param size Numero de entradas da layer.
     * @author Matheus Sena
     */
    public InputLayer(int size) {
        this.inputs = new double[size];
        this.size = size;
    }

    @Override
    public double getOutput(int x) {
        return this.inputs[x];
    }

}

