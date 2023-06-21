package Interface;

import util.ClassePizza;
import util.TipoDeBebida;
import util.TipoDePizza;
import java.util.Arrays;

public abstract class Bebidas {

    private static final long serialVersionUID = 1L;
    private int id = 0;
    private static int nextId = 1;
    private String nome;
    private double valor;
    protected TipoDeBebida tipoDeBebida;

    public Bebidas(String nome, double valorPadrao){
        this.id = nextId++;
        this.nome = nome;
        this.valor = valorPadrao;
    }

    public String toString() {
        return "Pizzas{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + getValor() +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

}

