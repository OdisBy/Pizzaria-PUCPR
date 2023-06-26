package Interface;

import util.TipoDeBebida;

public class Bebidas extends CardapioItem {
    private TipoDeBebida tipoDeBebida;

    public Bebidas(int id, String nome, double valor, TipoDeBebida tipoDeBebida) {
        super(id, nome, valor);
        this.tipoDeBebida = tipoDeBebida;
    }

    public TipoDeBebida getTipoDeBebida() {
        return tipoDeBebida;
    }

    public void setTipoDeBebida(TipoDeBebida tipoDeBebida) {
        this.tipoDeBebida = tipoDeBebida;
    }
}

