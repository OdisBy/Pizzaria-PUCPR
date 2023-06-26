import ui.Pedidos;

public class App {
    public static void main(String[] args) {
//        List<Pizzas> cardapio = Pizzas_Salgadas.PizzasFactory.criarCardapio();
//        for (Pizzas pizza : cardapio) {
//            System.out.println(pizza);
//        }
//
//        Pedido pedido = new Pedido(new Pizzas_Salgadas.Quatro_Queijos(), TamanhoPizza.Big);
//
//        System.out.println(pedido.getValor());
//
//        Home homeUi = new Home();
//        homeUi.homeUiSetup();
//        CardapioGUI cardapioGUI = new CardapioGUI();
//        cardapioGUI.setVisible(true);

        Pedidos pedidos = new Pedidos();
        pedidos.setVisible(true);
    }
}
