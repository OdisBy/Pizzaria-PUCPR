package ui;

import Interface.ItemCarrinho;
import Interface.Pedido;
import Interface.Pizzas;
import data.DataBebidas;
import data.DataPizzas;
import util.TamanhoPizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class CardapioGUI extends JFrame {
    private HomeGUI homeUi = new HomeGUI();
    private PedidosGUI pedidosGUI;
    private Pedido pedidoAtual;


    public CardapioGUI() {
        super("Pizzaria da PUCPR");
        initComponents();
        setupGUI();
        this.pedidoAtual = null;
    }
    private ArrayList<ItemCarrinho> carrinho = new ArrayList<>();
    private ArrayList<String> carrinhoString = new ArrayList<>();
    private JTextArea pedidosTextArea;

    private Pizzas pizzas;

    private void setupGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        JPanel navigationPanel = new JPanel(new GridLayout(3, 1));
        navigationPanel.setPreferredSize(new Dimension(getWidth() / 6, getHeight()));
        navigationPanel.setBackground(new Color(12, 37, 11));
        contentPane.add(navigationPanel, BorderLayout.WEST);

        ImageIcon homeIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/ic_home.png")));

        JButton homeButton = new JButton("Home");
        homeButton.setPreferredSize(new Dimension(100, 100));
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setText("");
        homeButton.setIcon(homeIcon);
        homeButton.addActionListener(e -> {
//            JOptionPane.showMessageDialog(this, "Clicou no botão Home");
            this.setVisible(false);
            homeUi.setVisible(true);
        });
        navigationPanel.add(homeButton);


        ImageIcon cardapioIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/ic_cardapio.png")));
        JButton cardapioButton = new JButton("Cardápio");
        cardapioButton.setPreferredSize(new Dimension(100, 100));
        cardapioButton.setBorderPainted(false);
        cardapioButton.setContentAreaFilled(false);
        cardapioButton.setText("");
        cardapioButton.setIcon(cardapioIcon);
        cardapioButton.setEnabled(false);
        navigationPanel.add(cardapioButton);

        ImageIcon pedidosIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/ic_pedido.png")));
        JButton pedidosButton = new JButton("Pedidos");
        pedidosButton.setPreferredSize(new Dimension(100, 100));
        pedidosButton.setBorderPainted(false);
        pedidosButton.setContentAreaFilled(false);
        pedidosButton.setText("");
        pedidosButton.setIcon(pedidosIcon);
        pedidosButton.addActionListener(e -> {
//            Pedido pedido = Pedido.loadData(2);
            pedidosGUI = new PedidosGUI();
            this.setVisible(false);
            pedidosGUI.setVisible(true);
        });
        navigationPanel.add(pedidosButton);

        JPanel menuPanel = new JPanel(new BorderLayout());
        contentPane.add(menuPanel, BorderLayout.CENTER);

        JLabel menuLabel = new JLabel("Cardápio", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 24));
        menuPanel.add(menuLabel, BorderLayout.NORTH);


        // TABELA DE PIZZAS ------------------
        Object[][] pizzasData = DataPizzas.cardapioParaTable();
        String[] pizzaColumnNames = {"Sabor", "Ingredientes", "Valor (Média)", "Quantidade"};
        MenuTableModel pizzasModel = new MenuTableModel(pizzasData, pizzaColumnNames);
        JTable pizzasTable = new JTable(pizzasModel);


        TableColumn columnName = pizzasTable.getColumnModel().getColumn(0);
        columnName.setPreferredWidth(100);

        TableColumn columnIngredientes = pizzasTable.getColumnModel().getColumn(1);
        columnIngredientes.setPreferredWidth(450);


        TableColumn columnValor = pizzasTable.getColumnModel().getColumn(2);
        columnValor.setPreferredWidth(5);

        TableColumn columnQuantity = pizzasTable.getColumnModel().getColumn(3);
        columnQuantity.setPreferredWidth(5);

        pizzasTable.setRowHeight(pizzasTable.getRowHeight() * 4);


        // TABELA DE BEBIDAS ------------------
        Object[][] bebidasData = DataBebidas.cardapioParaTable();
        String[] bebidasColumnNames = {"Nome", "Valor", "Quantidade"};


        MenuTableModel bebidasModel = new MenuTableModel(bebidasData, bebidasColumnNames);
        JTable bebidasTable = new JTable(bebidasModel);

        bebidasTable.setRowHeight(bebidasTable.getRowHeight() * 4);


        JScrollPane bebidasScrollPane = new JScrollPane(bebidasTable);
        JScrollPane pizzasScrollPane = new JScrollPane(pizzasTable);


        bebidasScrollPane.setBorder(new EmptyBorder(30, 10, 30, 10));
        pizzasScrollPane.setBorder(new EmptyBorder(30, 10, 30, 10));


        JPanel tablesPanel = new JPanel(new BorderLayout());
        tablesPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        tablesPanel.add(pizzasScrollPane, BorderLayout.CENTER);
        tablesPanel.add(bebidasScrollPane, BorderLayout.SOUTH);

        JScrollPane tablesScrollPane = new JScrollPane(tablesPanel);

        menuPanel.add(tablesScrollPane, BorderLayout.CENTER);


        pizzasTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = pizzasTable.getSelectedRow();
                    if (selectedRow != -1) {
                        bebidasTable.clearSelection(); // Desmarcar seleção na tabela de bebidas
                    }
                }
            }
        });

        bebidasTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = bebidasTable.getSelectedRow();
                    if (selectedRow != -1) {
                        pizzasTable.clearSelection(); // Desmarcar seleção na tabela de pizzas
                    }
                }
            }
        });



        JPanel controlPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Adicionar Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowPizza = pizzasTable.getSelectedRow();
                if (selectedRowPizza != -1) {
                    pizzasModel.incrementQuantityPizza(selectedRowPizza);
                    pizzasTable.repaint();
                }

                int selectedRowDrink = bebidasTable.getSelectedRow();
                if (selectedRowDrink != -1) {
                    bebidasModel.incrementQuantityDrink(selectedRowDrink);
                    bebidasTable.repaint();
                }
            }
        });
        controlPanel.add(addButton);

        JLabel quantityLabel = new JLabel("", SwingConstants.CENTER);
        controlPanel.add(quantityLabel);

        JButton removeButton = new JButton("Remover Item");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowPizza = pizzasTable.getSelectedRow();
                if (selectedRowPizza != -1) {
                    pizzasModel.decrementQuantityPizza(selectedRowPizza);
                    pizzasTable.repaint();
                }
                int selectedRowDrink = bebidasTable.getSelectedRow();
                if (selectedRowDrink != -1) {
                    bebidasModel.decrementQuantityDrink(selectedRowDrink);
                    bebidasTable.repaint();
                }
            }
        });
        controlPanel.add(removeButton);

        JButton finalizarButton = new JButton("Finalizar Pedido");
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarPedido(pizzasModel, bebidasModel);
//                JOptionPane.showMessageDialog(CardapioGUI.this, "Clicou no botão Finalizar Pedido");
            }
        });
        controlPanel.add(finalizarButton);

        menuPanel.add(controlPanel, BorderLayout.SOUTH);
    }

    private void finalizarPedido(MenuTableModel pizzaModel, MenuTableModel bebidaModel) {
        carrinho.clear();

        for (int row = 0; row < pizzaModel.getRowCount(); row++) {
            int quantidade = (int) pizzaModel.getValueAt(row, 3);
            if (quantidade > 0) {
                String pizza = (String) pizzaModel.getValueAt(row, 0);
                TamanhoPizza tamanho = pedirTamanho(pizza);
                ItemCarrinho item = new ItemCarrinho(pizza, tamanho);
                carrinho.add(item);
            }
        }

        for (int row = 0; row < bebidaModel.getRowCount(); row++) {
            int quantidade = (int) bebidaModel.getValueAt(row, 2);
            if (quantidade > 0) {
                String bebida = (String) bebidaModel.getValueAt(row, 0);
                ItemCarrinho item = new ItemCarrinho(bebida);
                carrinho.add(item);
            }
        }

        exibirPedidos();
    }

    private void exibirPedidos() {
        JFrame pedidosFrame = new JFrame("Pedidos");
        pedidosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pedidosFrame.setSize(400, 300);
        pedidosFrame.setLocationRelativeTo(this);

        pedidosTextArea = new JTextArea();
        pedidosTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(pedidosTextArea);
        pedidosFrame.add(scrollPane, BorderLayout.CENTER);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton editarPedidoButton = new JButton("Editar Pedido");
        editarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedidosFrame.dispose();
            }
        });
        botoesPanel.add(editarPedidoButton);

        JButton pagarButton = new JButton("Pagar");
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RedirectPedidos();
                pedidosFrame.setVisible(false);
            }
        });
        botoesPanel.add(pagarButton);

        pedidosFrame.add(botoesPanel, BorderLayout.SOUTH);
        StringBuilder pedidosText = new StringBuilder();
        for (ItemCarrinho item : carrinho) {
            if (item.getTamanho() != null) { // Verifica se é uma pizza
                pedidosText.append("- ").append(item.getNome()).append(" (Tamanho: ").append(item.getTamanho()).append(")");
            } else {
                pedidosText.append("- ").append(item.getNome());
            }
            pedidosText.append("\n");
        }
        pedidosTextArea.setText(pedidosText.toString());

        pedidosFrame.setVisible(true);
    }


    private TamanhoPizza pedirTamanho(String pizza) {
        TamanhoPizza[] tamanhos = TamanhoPizza.values();

        TamanhoPizza tamanhoSelecionado = (TamanhoPizza) JOptionPane.showInputDialog(
                this,
                "Selecione o tamanho da pizza de " + pizza + ":",
                "Escolha o tamanho",
                JOptionPane.PLAIN_MESSAGE,
                null,
                tamanhos,
                tamanhos[0]);

        return tamanhoSelecionado;
    }

    private void RedirectPedidos() {
        pedidoAtual = new Pedido(carrinho);
        pedidoAtual.saveData();
        carrinho.clear();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
        pack();
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CardapioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CardapioGUI().setVisible(true);
            }
        });
    }

    public class MenuTableModel extends AbstractTableModel {

        private String[] columnNames;
        private Object[][] data;

        public MenuTableModel(Object[][] data, String[] columnNames) {
            this.data = data;
            this.columnNames = columnNames;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int row, int column) {
            return data[row][column];
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        public void incrementQuantityPizza(int row) {
            int quantity = (int) data[row][3];
            data[row][3] = quantity + 1;
        }

        public void incrementQuantityDrink(int row) {
            int quantity = (int) data[row][2];
            data[row][2] = quantity + 1;
        }

        public void decrementQuantityPizza(int row) {
            int quantity = (int) data[row][3];
            if (quantity > 0) {
                data[row][3] = quantity - 1;
            }
        }

        public void decrementQuantityDrink(int row) {
            int quantity = (int) data[row][2];
            if (quantity > 0) {
                data[row][2] = quantity - 1;
            }
        }
    }
}
