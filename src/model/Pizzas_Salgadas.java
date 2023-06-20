package model;

import Interface.Pizzas;
import Interface.TipoDePizza;

import java.util.ArrayList;
import java.util.List;

public class Pizzas_Salgadas {
    public static class Strogonoff_Frango extends Pizzas {
        public Strogonoff_Frango() {
            super("Strogonoff de frango", 46.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Frango desfiado", "Catupiry", "Molho", "Batata palha", "Orégano"};
        }
    }

    public static class Strogonoff_Carne extends Pizzas {
        public Strogonoff_Carne() {
            super("Strogonoff de carne", 46.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Carne", "Molho", "Batata palha", "Orégano"};
        }
    }

    public static class Calabresa extends Pizzas {
        public Calabresa() {
            super("Calabresa", 36.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Calabresa", "Muçarela"};
        }
    }

    public static class Calabresa_Cheedar extends Pizzas {
        public Calabresa_Cheedar() {
            super("Calabresa Cheedar", 36.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Calabresa", "Muçarela", "Cheddar"};
        }
    }

    public static class Marguerita extends Pizzas {
        public Marguerita() {
            super("Marguerita", 36.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Manjericão", "Tomate"};
        }
    }

    public static class Frango_Catupiry extends Pizzas {
        public Frango_Catupiry() {
            super("Frango com catupiry", 36.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Frango", "Muçarela", "Catupiry"};
        }
    }

    public static class Frango_Cheddar extends Pizzas {
        public Frango_Cheddar() {
            super("Frango com cheddar", 36.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Frango", "Muçarela", "Cheddar"};
        }
    }

    public static class Champignon extends Pizzas {
        public Champignon() {
            super("Champignon", 36.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Champignon"};
        }
    }

    public static class Quatro_Queijos extends Pizzas {
        public Quatro_Queijos() {
            super("4 Queijos", 36.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Parmesão", "Provolone", "Catupiry"};
        }
    }

    public static class Bacon extends Pizzas {
        public Bacon() {
            super("Bacon", 36.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Bacon", "Orégano"};
        }
    }

    public static class Milho_Bacon extends Pizzas {
        public Milho_Bacon() {
            super("Milho com bacon", 36.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Bacon", "Milho"};
        }
    }

    public static class Milho extends Pizzas {
        public Milho() {
            super("Milho", 36.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Milho"};
        }
    }

    public static class Portuguesa extends Pizzas {
        public Portuguesa() {
            super("Portuguesa", 36.00);
            this.tipoDePizza = TipoDePizza.Salgada;
            this.ingredientes = new String[]{"Muçarela", "Presunto", "Cebola", "Ovos", "Azeitona"};
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