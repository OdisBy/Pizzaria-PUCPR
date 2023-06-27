package ui;

import Interface.Pedido;
import util.PedidosStatus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Objects;

public class PedidosGUI extends JFrame {
    private CardapioGUI cardapioGUI;
    private HomeGUI homeGUI;

    private JTable pedidosTable;

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
        pedidosTable = new JTable(pedidosModel);


        TableColumn columnId = pedidosTable.getColumnModel().getColumn(0);
        columnId.setPreferredWidth(5);

        TableColumn columnItems = pedidosTable.getColumnModel().getColumn(1);
        columnItems.setPreferredWidth(450);

        TableColumn columnValor = pedidosTable.getColumnModel().getColumn(2);
        columnValor.setPreferredWidth(3);

        TableColumn columnCreated = pedidosTable.getColumnModel().getColumn(3);
        columnCreated.setPreferredWidth(30);

        TableColumn columnStatus = pedidosTable.getColumnModel().getColumn(4);
        columnStatus.setPreferredWidth(50);

        pedidosTable.setRowHeight(pedidosTable.getRowHeight() * 4);

        JScrollPane pedidosScrollPanel = new JScrollPane(pedidosTable);

        pedidosScrollPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel tablesPanel = new JPanel(new BorderLayout());
        tablesPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        tablesPanel.add(pedidosScrollPanel, BorderLayout.CENTER);

        JScrollPane tablesScrollPane = new JScrollPane(tablesPanel);

        menuPanel.add(tablesScrollPane, BorderLayout.CENTER);


        JPanel controlPanel = new JPanel(new FlowLayout());

        JButton removeButton = new JButton("Excluir Item");
        removeButton.addActionListener(e -> excluirPedido(pedidosModel));
        controlPanel.add(removeButton);

        JButton modifyButton = new JButton("Modificar item");
        modifyButton.addActionListener(e -> modifyPedido(pedidosModel));
        controlPanel.add(modifyButton);

        JButton checkButton = new JButton("Concluído");
        checkButton.addActionListener(e -> checkPedido(pedidosModel));
        controlPanel.add(checkButton);

        menuPanel.add(controlPanel, BorderLayout.SOUTH);
    }

    private void excluirPedido(MenuTableModel pedidosModel) {
        int selectedRowPedido = pedidosTable.getSelectedRow();
        if (selectedRowPedido >= 0) {
            Pedido pedido = Pedido.loadPedidoById((int) pedidosModel.getValueAt(selectedRowPedido, 0));
            if (pedido != null) {
                pedido.autoExcluir();
                updatePedidos();
            }
        }
    }


    private void checkPedido(MenuTableModel pedidosModel) {
        int selectedRowPedido = pedidosTable.getSelectedRow();
        if (selectedRowPedido >= 0) {
            Pedido pedido = Pedido.loadPedidoById((int) pedidosModel.getValueAt(selectedRowPedido, 0));
            if (pedido != null) {
                pedido.setPedidoStatus(PedidosStatus.FINALIZADO);
                updatePedidos();
            }
        }
    }

    private void modifyPedido(MenuTableModel pedidosModel) {
        int selectedRow = pedidosTable.getSelectedRow();
        if (selectedRow >= 0) {
            int pedidoId = (int) pedidosModel.getValueAt(selectedRow, 0);
            PedidosStatus[] pedidosStatusModel = PedidosStatus.values();
            PedidosStatus jaSelecionado = (PedidosStatus) pedidosModel.getValueAt(selectedRow, 4);

            PedidosStatus pedidosStatus = (PedidosStatus) JOptionPane.showInputDialog(
                    this,
                    "Selecione o status do pedido ID: " + pedidoId,
                    "Escolha o status",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    pedidosStatusModel,
                    jaSelecionado);


            Pedido pedido = Pedido.loadPedidoById(pedidoId);
            pedido.setPedidoStatus(pedidosStatus);
            updatePedidos();
        }
    }

    private void updatePedidos() {
        PedidosGUI pedidosGUI = new PedidosGUI();
        this.setVisible(false);
        pedidosGUI.setVisible(true);
    }

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

        java.awt.EventQueue.invokeLater(() -> new HomeGUI().setVisible(true));
    }

    public static class MenuTableModel extends AbstractTableModel {

        private final String[] columnNames;
        private final Object[][] data;

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
