package util;

public enum ClassePizza {
    Promocao(0.00),
    Tradicional(2.00),
    Premium(10.00);

    private double valorPadrao;

    ClassePizza(double valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public double getValorPadrao() {
        return valorPadrao;
    }
}
