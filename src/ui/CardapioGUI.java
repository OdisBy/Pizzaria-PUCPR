package ui;

import Interface.Pizzas;
import model.Pizzas_Salgadas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class CardapioGUI extends JFrame {

    public CardapioGUI() {
        initComponents();
        setupGUI();
    }
    private ArrayList<String> carrinho = new ArrayList<>();
    private JTextArea pedidosTextArea;

    private Pizzas pizzas;

    private void setupGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 576);
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
            JOptionPane.showMessageDialog(this, "Clicou no botão Home");
        });
        navigationPanel.add(homeButton);


        ImageIcon cardapioIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/ic_cardapio.png")));
        JButton cardapioButton = new JButton("Cardápio");
        cardapioButton.setPreferredSize(new Dimension(100, 100));
        cardapioButton.setBorderPainted(false);
        cardapioButton.setContentAreaFilled(false);
        cardapioButton.setText("");
        cardapioButton.setIcon(cardapioIcon);

        cardapioButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Clicou no botão Cardápio");
        });
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
            JOptionPane.showMessageDialog(this, "Clicou no botão Pedidos");
        });
        navigationPanel.add(pedidosButton);

        JPanel menuPanel = new JPanel(new BorderLayout());
        contentPane.add(menuPanel, BorderLayout.CENTER);

        JLabel menuLabel = new JLabel("Cardápio", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 24));
        menuPanel.add(menuLabel, BorderLayout.NORTH);

        // dados
        Object[][] data = Pizzas.getPizzasSalgadas();


        MenuTableModel model = new MenuTableModel(data);
        JTable menuTable = new JTable(model);

        TableColumn columnName = menuTable.getColumnModel().getColumn(0);
        columnName.setPreferredWidth(100);

        TableColumn columnIngredientes = menuTable.getColumnModel().getColumn(1);
        columnIngredientes.setPreferredWidth(450);

        TableColumn columnValor = menuTable.getColumnModel().getColumn(2);
        columnValor.setPreferredWidth(5);

        TableColumn columnQuantity = menuTable.getColumnModel().getColumn(3);
        columnQuantity.setPreferredWidth(5);

//        menuTable.getColumnModel().getColumn(1).setCellRenderer(new WrapTextCellRenderer());
        menuTable.setRowHeight(menuTable.getRowHeight() * 4);

        JScrollPane scrollPane = new JScrollPane(menuTable);
        scrollPane.setBorder(new EmptyBorder(30, 10, 30, 10));

        menuPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Adicionar Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = menuTable.getSelectedRow();
                if (selectedRow != -1) {
                    model.incrementQuantity(selectedRow);
                    menuTable.repaint();
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
                int selectedRow = menuTable.getSelectedRow();
                if (selectedRow != -1) {
                    model.decrementQuantity(selectedRow);
                    menuTable.repaint();
                }
            }
        });
        controlPanel.add(removeButton);

        JButton finalizarButton = new JButton("Finalizar Pedido");
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarPedido(model);
//                JOptionPane.showMessageDialog(CardapioGUI.this, "Clicou no botão Finalizar Pedido");
            }
        });
        controlPanel.add(finalizarButton);

        menuPanel.add(controlPanel, BorderLayout.SOUTH);
    }

    private void finalizarPedido(MenuTableModel model) {
        carrinho.clear();

        // Iterar sobre os dados do modelo
        for (int row = 0; row < model.getRowCount(); row++) {
            int quantidade = (int) model.getValueAt(row, 3); // Obtém a quantidade da coluna "Quantidade"
            if (quantidade > 0) {
                String pizza = (String) model.getValueAt(row, 0); // Obtém o nome da pizza da coluna "Pizza"
                carrinho.add(pizza); // Adiciona a pizza ao array de pizzas selecionadas
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
            }
        });
        botoesPanel.add(pagarButton);

        pedidosFrame.add(botoesPanel, BorderLayout.SOUTH);
        StringBuilder pedidosText = new StringBuilder();
        for (String pizza : carrinho) {
            pedidosText.append("- ").append(pizza).append("\n");
        }
        pedidosTextArea.setText(pedidosText.toString());

        pedidosFrame.setVisible(true);
    }

    private void RedirectPedidos() {
        JFrame acompanharFrame = new JFrame("Acompanhar");
        acompanharFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        acompanharFrame.setSize(400, 300);
        acompanharFrame.setLocationRelativeTo(this);

        acompanharFrame.setVisible(true);
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

    private class MenuTableModel extends AbstractTableModel {

        private String[] columnNames = {"Sabor", "Ingredientes", "Valor", "Quantidade"};

        private Object[][] data;

        public MenuTableModel(Object[][] data){
            this.data = data;
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

        public void incrementQuantity(int row) {
            int quantity = (int) data[row][3];
            data[row][3] = quantity + 1;
        }

        public void decrementQuantity(int row) {
            int quantity = (int) data[row][3];
            if (quantity > 0) {
                data[row][3] = quantity - 1;
            }
        }
    }

//    public class WrapTextCellRenderer extends DefaultTableCellRenderer {
//        private JTextArea textArea;
//
//        public WrapTextCellRenderer() {
//            textArea = new JTextArea();
//            textArea.setLineWrap(true);
//            textArea.setWrapStyleWord(true);
//            textArea.setBorder(new EmptyBorder(5, 5, 5, 5));
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            textArea.setText((String) value);
//            return textArea;
//        }
//    }
}
