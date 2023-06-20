package Interface;

import java.util.Arrays;

public abstract class Pizzas {

    private static final long serialVersionUID = 1L;
    private int id = 0;
    private static int nextId = 1;
    private String nome;
    private double valor;
    protected TipoDePizza tipoDePizza;

    protected String[] ingredientes;

    public Pizzas(String nome, double valor_base_media){
        this.id = nextId++;
        this.nome = nome;
        this.valor = valor_base_media;
    }

    public String toString() {
        return "Pizzas{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", ingredientes=" + Arrays.toString(ingredientes) +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public String[] getIngredientes() {
        return ingredientes;
    }
}

