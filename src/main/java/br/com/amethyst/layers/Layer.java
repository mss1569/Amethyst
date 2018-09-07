package br.com.amethyst.layers;

/**
 * Layer é a classe abstrata utilizada
 * como base para implementação de todas as layers
 * dentro do contexto deste projeto.
 *
 * @author Matheus Sena
 */
public abstract class Layer {

    public int size;

    /**
     * getOutput é o metodo responsavel pela realização
     * do calculo de saida do neuronio dentro dentro
     * da layer implementada.
     *
     * @param i Posição do neuronio dentro da layer.
     * @return Saida do neuronio informado.
     * @author Matheus Sena
     */
    public abstract double getOutput(int i);

}
