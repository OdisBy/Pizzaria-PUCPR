package data;

import Interface.Bebidas;
import util.TipoDeBebida;

import java.util.ArrayList;
import java.util.List;

public class DataBebidas {
    public static Object[][] criarCardapio() {
        Object[][] cardapio = new Object[][]{
                {1, "Água 500ml", 2.99, TipoDeBebida.Garrafa_500_ml},
                {2, "Chá Mate Gelado 1L", 6.99, TipoDeBebida.Garrafa_1L},
                {3, "Coca-Cola 2L", 12.99, TipoDeBebida.Garrafa_2L},
                {4, "Fanta Uva 2L", 12.99, TipoDeBebida.Garrafa_2L},
                {5, "Fanta Laranja 2L", 12.99, TipoDeBebida.Garrafa_2L},
                {6, "Fanta Guaraná 2L", 12.99, TipoDeBebida.Garrafa_2L},
                {7, "Fanta Maracujá 2L", 12.99, TipoDeBebida.Garrafa_2L},
                {8, "Guaraná 2L", 12.99, TipoDeBebida.Garrafa_2L},
                {9, "Sukita 2L", 12.99, TipoDeBebida.Garrafa_2L},
                {10, "Sprite 2L", 12.99, TipoDeBebida.Garrafa_2L},
                {11, "Pepsi 2L", 12.99, TipoDeBebida.Garrafa_2L},
                {12, "Sprite 2L", 12.99, TipoDeBebida.Garrafa_2L},
                {13, "Coca-Cola Lata", 6.99, TipoDeBebida.Lata_350_ml}
        };
        return cardapio;
    }

    public static Object[][] cardapioParaTable() {
        Object[][] cardapio = criarCardapio();
        Object[][] data = new Object[cardapio.length][3];

        for(int i = 0; i < cardapio.length; i++) {
            data[i][0] = cardapio[i][1];
            data[i][1] = cardapio[i][2];
            data[i][2] = 0;
        }
        return data;
    }
}
