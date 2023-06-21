package model;

import Interface.Pizzas;
import util.ClassePizza;
import util.TipoDeBebida;
import util.TipoDePizza;

public class Bebidas {
    public static class CocaCola extends Interface.Bebidas {
        public CocaCola() {
            super("Coca-Cola", 9.99);
            this.tipoDeBebida = TipoDeBebida.Lata_350_ml;
        }
    }}
