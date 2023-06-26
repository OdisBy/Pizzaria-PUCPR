package ui;

import Interface.Pedido;
import data.DataPizzas;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Objects;

public class PedidosGUI extends JFrame {
    private CardapioGUI cardapioGUI;
    private HomeGUI homeGUI;

    public PedidosGUI() {
        super("Pizzaria da PUCPR");
        initComponents();
        setupGUI();
    }

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
        homeButton.setEnabled(true);
        homeButton.addActionListener(e -> {
            homeGUI = new HomeGUI();
            this.setVisible(false);
            homeGUI.setVisible(true);
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
            cardapioGUI = new CardapioGUI();
            this.setVisible(false);
            cardapioGUI.setVisible(true);
        });
        cardapioButton.setEnabled(true);
        navigationPanel.add(cardapioButton);

        ImageIcon pedidosIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/ic_pedido.png")));
        JButton pedidosButton = new JButton("Pedidos");
        pedidosButton.setPreferredSize(new Dimension(100, 100));
        pedidosButton.setBorderPainted(false);
        pedidosButton.setContentAreaFilled(false);
        pedidosButton.setText("");
        pedidosButton.setIcon(pedidosIcon);
        pedidosButton.setEnabled(false);
        navigationPanel.add(pedidosButton);

        JPanel menuPanel = new JPanel(new BorderLayout());
        contentPane.add(menuPanel, BorderLayout.CENTER);

        JLabel menuLabel = new JLabel("Pedidos", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 24));
        menuPanel.add(menuLabel, BorderLayout.NORTH);

        Object[][] pedidosData = Pedido.loadAllData();
        String[] pedidosColumnNames = {"ID", "Pedido", "Valor", "Criado", "Status"};
        MenuTableModel pedidosModel = new MenuTableModel(pedidosData, pedidosColumnNames);
        JTable pedidosTable = new JTable(pedidosModel);


        TableColumn columnId = pedidosTable.getColumnModel().getColumn(0);
        columnId.setPreferredWidth(5);

        TableColumn columnPedido = pedidosTable.getColumnModel().getColumn(1);
        columnPedido.setPreferredWidth(450);


        TableColumn columnValor = pedidosTable.getColumnModel().getColumn(2);
        columnValor.setPreferredWidth(5);

        TableColumn columnCreated = pedidosTable.getColumnModel().getColumn(3);
        columnCreated.setPreferredWidth(10);

        TableColumn columnStatus = pedidosTable.getColumnModel().getColumn(4);
        columnStatus.setPreferredWidth(50);

        pedidosTable.setRowHeight(pedidosTable.getRowHeight() * 4);

        menuPanel.add(pedidosTable);
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
            java.util.logging.Logger.getLogger(PedidosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeGUI().setVisible(true);
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

    }
}
