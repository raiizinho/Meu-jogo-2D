package main;

import javax.swing.*;

public class Main {
    static void main() {
        JFrame janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setTitle("Aventura em um mundo de duas dimensões");

        GamePannel gamePannel = new GamePannel();
        janela.add(gamePannel);
        janela.pack();

        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        gamePannel.startGameThread();

    }
}
