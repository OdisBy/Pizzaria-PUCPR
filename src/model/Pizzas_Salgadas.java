package model;

import Interface.Pizzas;
import util.ClassePizza;
import util.TipoDePizza;

import java.util.ArrayList;
import java.util.List;

public class Pizzas_Salgadas {
    public static class Strogonoff_Frango extends Pizzas {
        public Strogonoff_Frango() {
            super("Strogonoff de frango");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Frango desfiado", "Catupiry", "Molho", "Batata palha", "Orégano"};
            this.classePizza = ClassePizza.Premium;
        }
    }

    public static class Strogonoff_Carne extends Pizzas {
        public Strogonoff_Carne() {
            super("Strogonoff de carne");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Carne", "Molho", "Batata palha", "Orégano"};
            this.classePizza = ClassePizza.Premium;
        }
    }

    public static class Calabresa extends Pizzas {
        public Calabresa() {
            super("Calabresa");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Calabresa", "Muçarela"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Calabresa_Cheedar extends Pizzas {
        public Calabresa_Cheedar() {
            super("Calabresa Cheedar");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Calabresa", "Muçarela", "Cheddar"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Marguerita extends Pizzas {
        public Marguerita() {
            super("Marguerita");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Manjericão", "Tomate"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Frango_Catupiry extends Pizzas {
        public Frango_Catupiry() {
            super("Frango com catupiry");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Frango", "Muçarela", "Catupiry"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Frango_Cheddar extends Pizzas {
        public Frango_Cheddar() {
            super("Frango com cheddar");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Frango", "Muçarela", "Cheddar"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Champignon extends Pizzas {
        public Champignon() {
            super("Champignon");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Champignon"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Quatro_Queijos extends Pizzas {
        public Quatro_Queijos() {
            super("4 Queijos");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Parmesão", "Provolone", "Catupiry"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Bacon extends Pizzas {
        public Bacon() {
            super("Bacon");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Bacon", "Orégano"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Milho_Bacon extends Pizzas {
        public Milho_Bacon() {
            super("Milho com bacon");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Bacon", "Milho"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Milho extends Pizzas {
        public Milho() {
            super("Milho");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Milho"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Portuguesa extends Pizzas {
        public Portuguesa() {
            super("Portuguesa");
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Presunto", "Cebola", "Ovos", "Azeitona"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class PizzasFactory {
        public static List<Pizzas> criarCardapio() {
            List<Pizzas> cardapio = new ArrayList<>();

            cardapio.add(new Strogonoff_Frango());
            cardapio.add(new Strogonoff_Carne());
            cardapio.add(new Calabresa());
            cardapio.add(new Calabresa_Cheedar());
            cardapio.add(new Marguerita());
            cardapio.add(new Frango_Catupiry());
            cardapio.add(new Frango_Cheddar());
            cardapio.add(new Champignon());
            cardapio.add(new Quatro_Queijos());
            cardapio.add(new Bacon());
            cardapio.add(new Milho_Bacon());
            cardapio.add(new Milho());
            cardapio.add(new Portuguesa());

            return cardapio;
        }
    }
}