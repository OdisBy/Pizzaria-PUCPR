package util;

public enum ClassePizza {
    PromocaoTradicional(21.00),
    PromocaoDoce(12.00),
    Tradicional(36.00),
    Premium(45.00);

    private double valorPadrao;
    private static final long serialVersionUID = 1L;
    ClassePizza(double valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public double getValor() {
        return valorPadrao;
    }

    public double getValorPadrao() {
        return valorPadrao;
    }
}
