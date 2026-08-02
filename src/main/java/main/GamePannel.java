package main;

import main.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePannel extends JPanel implements Runnable{
    final int tamanhoOriginalTile = 16; // 16x16
    final int escala = 3;

    public final int tamanhoTile = tamanhoOriginalTile * escala;

    final int colunaMaxHorizontal = 16;
    final int colunaMaxVertical = 12;

    final int alturaTela = tamanhoTile * colunaMaxVertical;
    final int larguraTela = tamanhoTile * colunaMaxHorizontal;


    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);
    int FPS = 60;

    public GamePannel() {
        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    public void startGameThread () {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 / (float) FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >=1 ) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update() {
        player.update();
    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}
