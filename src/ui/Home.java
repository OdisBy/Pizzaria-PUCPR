package ui;

import javax.swing.*;

public class Home extends JFrame {
    private JPanel panelMain;
    private JPanel drawer_nav;
    private JButton cardapioBtn;
    private JButton acompanharBtn;
    private JButton homeBtn;
    private JLabel pucName;
    private JLabel pucText;

    public void homeUiShow() {
        Home homeUi = new Home();
        homeUi.setContentPane(homeUi.panelMain);
        homeUi.setTitle("Pizzaria da Puc");
        homeUi.setSize(1024, 576);
        homeUi.setVisible(true);
        homeUi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}