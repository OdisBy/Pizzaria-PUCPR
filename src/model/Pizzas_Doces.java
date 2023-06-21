package model;

import Interface.Pizzas;
import util.ClassePizza;
import util.TipoDePizza;

public class Pizzas_Doces {

    public static class Chocolate extends Pizzas {
        public Chocolate() {
            super("Chocolate");
            this.tipoDePizza = TipoDePizza.Doce;
            this.ingredientes = new String[]{"Chocolate Preto", "Chocolate Granulado", "Leite Condensado"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Beijinho extends Pizzas {
        public Beijinho() {
            super("Beijinho");
            this.tipoDePizza = TipoDePizza.Doce;
            this.ingredientes = new String[]{"Chocolate Branco", "Côco Ralado", "Leite Condensado"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class California extends Pizzas {
        public California() {
            super("Califórnia");
            this.tipoDePizza = TipoDePizza.Doce;
            this.ingredientes = new String[]{"Abacaxi", "Figo", "Pêssego", "Cereja"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class DoisAmores extends Pizzas {
        public DoisAmores() {
            super("Dois Amores");
            this.tipoDePizza = TipoDePizza.Doce;
            this.ingredientes = new String[]{"Chocolate Preto", "Chocolate Branco", "Leite Condensado"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class Prestigio extends Pizzas {
        public Prestigio() {
            super("Prestígio");
            this.tipoDePizza = TipoDePizza.Doce;
            this.ingredientes = new String[]{"Chocolate Preto", "Côco Ralado", "Leite Condensado"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class BananaNevada extends Pizzas {
        public BananaNevada() {
            super("Banana Nevada");
            this.tipoDePizza = TipoDePizza.Doce;
            this.ingredientes = new String[]{"Banana fatiada", "Chocolate branco"};
            this.classePizza = ClassePizza.Tradicional;
        }
    }

    public static class ChocolateComMorango extends Pizzas {
        public ChocolateComMorango() {
            super("Chocolate Com Morango");
            this.tipoDePizza = TipoDePizza.Doce;
            this.ingredientes = new String[]{"Chocolate Preto", "Morango", "Chocolate Granulado"};
            this.classePizza = ClassePizza.Premium;
        }
    }

    public static class MMs extends Pizzas {
        public MMs() {
            super("MMs");
            this.tipoDePizza = TipoDePizza.Doce;
            this.ingredientes = new String[]{"Chocolate Preto", "Confete de MMs"};
            this.classePizza = ClassePizza.Premium;
        }
    }

}