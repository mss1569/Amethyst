package br.com.amethyst.functions;

/**
 * Function é a classe abstrata utilizada
 * como base para implementação de todas as funções
 * dentro do contexto deste projeto.
 *
 * @author Matheus Sena
 */
public abstract class Function {
    /**
     * func é o metodo responsavel pela realização do calculo
     * da função implementada.
     *
     * @param value Valor a ser calculado.
     * @return Resultado do calculo da função sobre
     * o valor informado.
     * @author Matheus Sena
     */
    public abstract double func(double value);

    /**
     * funcDerivate é o metodo responsavel pela realização
     * do calculo da derivada da função implementada.
     *
     * @param value Valor a ser calculado.
     * @return Resultado do calculo da função sobre
     * o valor informado.
     * @author Matheus Sena
     */
    public abstract double funcDerivate(double value);
}
