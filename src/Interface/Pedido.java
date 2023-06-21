package Interface;

import util.TamanhoPizza;
import util.ClassePizza;

public class Pedido {
    private Pizzas pizza;
    private TamanhoPizza tamanho;
    private double valor;

    public Pedido(Pizzas pizza, TamanhoPizza tamanho) {
        this.pizza = pizza;
        this.tamanho = tamanho;
    }

    public Pizzas getPizza() {
        return pizza;
    }

    public TamanhoPizza getTamanho() {
        return tamanho;
    }

    public Double getValor() {
        double valorPizza = pizza.getValor() + (9 * (tamanho.ordinal() - 2));
        return valorPizza;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "pizza=" + pizza.getNome() +
                ", tamanho=" + tamanho +
                '}';
    }
}
