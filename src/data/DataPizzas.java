package data;

import Interface.Pizzas;
import util.ClassePizza;
import util.TamanhoPizza;
import util.TipoDePizza;

import java.util.ArrayList;
import java.util.List;

public class DataPizzas {
    public static Object[][] criarCardapio() {
        Object[][] cardapio = new Object[][]{

                {1, "Strogonoff de frango", TipoDePizza.Salgada, ClassePizza.Tradicional, new String[]{"Frango desfiado", "Catupiry", "Molho", "Batata palha", "Orégano"}},
                {2, "Strogonoff de carne", TipoDePizza.Salgada, ClassePizza.Tradicional, new String[]{"Carne", "Molho", "Batata palha", "Orégano"}},
                {3, "Calabresa", TipoDePizza.Salgada, ClassePizza.Tradicional, new String[]{"Calabresa", "Muçarela"}},
                {4, "Calabresa Cheedar", TipoDePizza.Salgada, ClassePizza.Tradicional, new String[]{"Calabresa", "Muçarela", "Cheddar"}},
                {5, "Marguerita", TipoDePizza.Salgada, ClassePizza.Tradicional, new String[]{"Muçarela", "Manjericão", "Tomate"}},
                {6, "Frango com catupiry", TipoDePizza.Salgada, ClassePizza.Tradicional, new String[]{"Frango", "Muçarela", "Catupiry"}},
                {7, "Frango com cheddar", TipoDePizza.Salgada, ClassePizza.Tradicional, new String[]{"Frango", "Muçarela", "Cheddar"}},
                {8, "Champignon", TipoDePizza.Salgada, ClassePizza.Premium, new String[]{"Muçarela", "Champignon"}},
                {9, "Quatro Queijos", TipoDePizza.Salgada, ClassePizza.Premium, new String[]{"Muçarela", "Parmesão", "Provolone", "Catupiry"}},
                {10, "Bacon", TipoDePizza.Salgada, ClassePizza.Tradicional, new String[]{"Muçarela", "Bacon", "Orégano"}},
                {11, "Milho com bacon", TipoDePizza.Salgada, ClassePizza.Tradicional, new String[]{"Muçarela", "Bacon", "Milho"}},
                {12, "Milho", TipoDePizza.Salgada, ClassePizza.Tradicional, new String[]{"Muçarela", "Milho"}},
                {13, "Portuguesa", TipoDePizza.Salgada, ClassePizza.Tradicional, new String[]{"Muçarela", "Presunto", "Cebola", "Ovos", "Azeitona"}},

                {14, "Chocolate", TipoDePizza.Doce, ClassePizza.Tradicional, new String[]{"Chocolate Preto", "Chocolate Granulado", "Leite Condensado"}},
                {15, "Beijinho", TipoDePizza.Doce, ClassePizza.Tradicional, new String[]{"Chocolate Branco", "Côco Ralado", "Leite Condensado"}},
                {16, "Califórnia", TipoDePizza.Doce, ClassePizza.Tradicional, new String[]{"Abacaxi", "Figo", "Pêssego", "Cereja"}},
                {17, "Dois Amores", TipoDePizza.Doce, ClassePizza.Tradicional, new String[]{"Chocolate Preto", "Chocolate Branco", "Leite Condensado"}},
                {18, "Prestígio", TipoDePizza.Doce, ClassePizza.Tradicional, new String[]{"Chocolate Preto", "Côco Ralado", "Leite Condensado"}},
                {19, "Banana Nevada", TipoDePizza.Doce, ClassePizza.Tradicional, new String[]{"Banana fatiada", "Chocolate branco"}},
                {20, "Chocolate Com Morango", TipoDePizza.Doce, ClassePizza.Tradicional, new String[]{"Chocolate Preto", "Morango", "Chocolate Granulado"}},
                {21, "MMs", TipoDePizza.Doce, ClassePizza.Tradicional, new String[]{"Chocolate Preto", "Confetes de MMs"}}
        };

        return cardapio;
    }

    public static Object[][] cardapioParaTable() {
        Object[][] cardapio = criarCardapio();
        Object[][] data = new Object[cardapio.length][4];


        for(int i = 0; i < cardapio.length; i++) {
            ClassePizza classePizza = (ClassePizza) cardapio[i][3];
            String[] ingredientes = (String[]) cardapio[i][4];
            String ingredientesString = String.join(", ", ingredientes);

            data[i][0] = cardapio[i][1];
            data[i][1] = ingredientesString;
            data[i][2] = classePizza.getValorPadrao();
            data[i][3] = 0;
        }
        return data;
    }
}
