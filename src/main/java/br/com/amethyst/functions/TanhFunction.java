package br.com.amethyst.functions;

/**
 * TanhFunction é a classe que implementa
 * a função tangente hiperbolica.
 *
 * @author Matheus Sena
 */
public class TanhFunction extends Function {
    @Override
    public double func(double value) {
        return Math.tanh(value);
    }

    @Override
    public double funcDerivate(double value) {
        return 1 - Math.pow(this.func(value), 2);
    }
}
