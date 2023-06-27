package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HomeGUI extends JFrame {
    private CardapioGUI cardapioGUI;
    private PedidosGUI pedidosGUI;

    public HomeGUI() {
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
        homeButton.setEnabled(false);
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
        pedidosButton.addActionListener(e -> {
            pedidosGUI = new PedidosGUI();
            this.setVisible(false);
            pedidosGUI.setVisible(true);
        });
        navigationPanel.add(pedidosButton);

        JPanel menuPanel = new JPanel(new GridBagLayout());
        contentPane.add(menuPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("<html>Pizzaria da PUCPR<br>A pizza que te fara passar de ano</html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        menuPanel.add(titleLabel, gbc);
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
            java.util.logging.Logger.getLogger(CardapioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new HomeGUI().setVisible(true));
    }
}