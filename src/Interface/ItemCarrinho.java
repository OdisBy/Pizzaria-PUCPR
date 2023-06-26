package Interface;

import data.DataBebidas;
import data.DataPizzas;
import util.ClassePizza;
import util.TamanhoPizza;
import util.TipoDePizza;

import java.io.Serializable;

public class ItemCarrinho implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private TamanhoPizza tamanho;

    public ItemCarrinho(String pizza, TamanhoPizza tamanho) {
        this.nome = pizza;
        this.tamanho = tamanho;
    }

    public ItemCarrinho(String bebida) {
        this.nome = bebida;
    }

    public String getNome() {
        return nome;
    }

    public TamanhoPizza getTamanho() {
        return tamanho;
    }

    public void setTamanho(TamanhoPizza tamanhoSelecionado) {
        tamanho = tamanhoSelecionado;
    }
    public double getValor() {
        Double valorSoma = 0.0;
        if(tamanho != null) {
            Object[][] cardapio = DataPizzas.criarCardapio();
            valorSoma = getDefaultValue(cardapio, nome);
            valorSoma = (double) (valorSoma + (10 * (tamanho.ordinal() - 2)));
        } else {
            Object[][] cardapioBebidas = DataBebidas.criarCardapio();
            valorSoma = getDefaultDrinkValue(cardapioBebidas, nome);
        }
        return valorSoma;
    }

    private double getDefaultValue(Object[][] cardapio, String name) {
        ClassePizza classePizzaEncontrada = null;
        TipoDePizza tipoDePizzaEncontrada = null;

        for (Object[] pizza : cardapio) {
            String nomePizza = (String) pizza[1];
            ClassePizza classePizza = (ClassePizza) pizza[3];
            TipoDePizza tipoDePizza = (TipoDePizza) pizza[2];

            if (nomePizza.equals(name)) {
                classePizzaEncontrada = classePizza;
                tipoDePizzaEncontrada = tipoDePizza;
                break;
            }
        }
        if(tipoDePizzaEncontrada == TipoDePizza.Doce){
            return classePizzaEncontrada.getValor() + 10;
        }
        return classePizzaEncontrada.getValor();
    }
    private Double getDefaultDrinkValue(Object[][] cardapioBebidas, String nome) {
        Double valor = null;
        for (Object[] bebida : cardapioBebidas) {
            String nomeBebida = (String) bebida[1];
            valor = (Double) bebida[2];
            if (nomeBebida.equals(nome)) {
                return valor;
            }
        }
        return null;
    }
}
