package util;

public enum ClassePizza {
    PromocaoTradicional(21.00),
    PromocaoDoce(12.00),
    Tradicional(36.00),
    Premium(45.00);

    private double valorPadrao;

    ClassePizza(double valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public double getValorPadrao() {
        return valorPadrao;
    }
}
