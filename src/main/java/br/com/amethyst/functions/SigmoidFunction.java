package br.com.amethyst.functions;

/**
 * SigmoidFunction é a classe que implementa
 * a função sigmoide.
 *
 * @author Matheus Sena
 */
public class SigmoidFunction extends Function {
    @Override
    public double func(double value) {
        return 1 / (1 + Math.pow(Math.E, value));
    }

    @Override
    public double funcDerivate(double value) {
        return 1 / (1 + Math.pow(Math.E, value)) * (1 - (1 / (1 + Math.pow(Math.E, value))));
    }
}
