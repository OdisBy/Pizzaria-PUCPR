package Interface;

import model.Pizzas_Salgadas;
import util.ClassePizza;
import util.TipoDePizza;

import java.util.Arrays;
import java.util.List;

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

    public static Object[][] getPizzasSalgadas() {
        List<Pizzas> pizzasSalgadas = Pizzas_Salgadas.PizzasFactory.criarCardapio();
        Object[][] data = new Object[pizzasSalgadas.size()][4];

        for (int i = 0; i < pizzasSalgadas.size(); i++) {
            Pizzas pizza = pizzasSalgadas.get(i);
            data[i][0] = pizza.getNome();
            data[i][1] = pizza.getIngredientes();
            data[i][2] = pizza.getValor();
            data[i][3] = 0;
        }

        return data;
    }
}

