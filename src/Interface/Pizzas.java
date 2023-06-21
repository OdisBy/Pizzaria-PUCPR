package Interface;

import util.ClassePizza;
import util.TipoDePizza;

import java.util.Arrays;

public abstract class Pizzas {

    private static final long serialVersionUID = 1L;
    private int id = 0;
    private static int nextId = 1;
    private String nome;
    private double valor;
    protected TipoDePizza tipoDePizza;
    protected ClassePizza classePizza;
    protected String[] ingredientes;

    public Pizzas(String nome){
        this.id = nextId++;
        this.nome = nome;
        this.valor = 36.00;
    }

    public String toString() {
        return "Pizzas{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + getValor() +
                ", ingredientes=" + Arrays.toString(ingredientes) +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor + classePizza.getValorPadrao();
    }

    public String[] getIngredientes() {
        return ingredientes;
    }
}

