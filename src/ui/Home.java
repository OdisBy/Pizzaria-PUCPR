package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener  {
    private JPanel panelMain;
    private JPanel drawer_nav;
    private JButton cardapioBtn;
    private JButton acompanharBtn;
    private JButton homeBtn;
    private JLabel pucName;
    private JLabel pucText;

    private CardapioGUI cardapioGUI;


    public void homeUiSetup(){
        buttonProperties();
        homeUiShow();
    }

    public void homeUiShow() {
        setContentPane(panelMain);
        setTitle("Pizzaria da Puc");
        setSize(1024, 576);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
    }

    public void buttonProperties(){
        cardapioBtn.addActionListener(this);
        acompanharBtn.addActionListener(this);
        homeBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cardapioBtn) {
            System.out.println("Cardapio btn clicked");
            cardapioGUI = new CardapioGUI();
            setVisible(false);
            cardapioGUI.setVisible(true);
        } else if (e.getSource() == acompanharBtn) {
            System.out.println("acompanharBtn clicked");
        } else if (e.getSource() == homeBtn) {
            System.out.println("homeBtn clicked");
        }
    }
}