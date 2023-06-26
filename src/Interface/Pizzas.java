package Interface;

import util.ClassePizza;
import util.TipoDePizza;

public class Pizzas extends CardapioItem {
    private static final long serialVersionUID = 1L;
    private TipoDePizza tipoDePizza;
    private ClassePizza classePizza;
    private String[] ingredientes;

    public Pizzas(int id, String nome, TipoDePizza tipoDePizza, ClassePizza classePizza, String[] ingredientes) {
        super(id, nome, 36);
        this.tipoDePizza = tipoDePizza;
        this.classePizza = classePizza;
        this.ingredientes = ingredientes;
    }

    public TipoDePizza getTipoDePizza() {
        return tipoDePizza;
    }

    public void setTipoDePizza(TipoDePizza tipoDePizza) {
        this.tipoDePizza = tipoDePizza;
    }

    public ClassePizza getClassePizza() {
        return classePizza;
    }

    public void setClassePizza(ClassePizza classePizza) {
        this.classePizza = classePizza;
    }

    public String[] getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String[] ingredientes) {
        this.ingredientes = ingredientes;
    }
}

