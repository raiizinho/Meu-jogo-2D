package main;

import javax.swing.*;
import java.awt.*;

public class GamePannel extends JPanel implements Runnable{
    final int tamanhoOriginalTile = 16; // 16x16
    final int escala = 3;

    final int tamanhoTile = tamanhoOriginalTile * escala;

    final int colunaMaxHorizontal = 16;
    final int colunaMaxVertical = 12;

    final int alturaTela = tamanhoTile * colunaMaxVertical;
    final int larguraTela = tamanhoTile * colunaMaxHorizontal;


    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
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
        if (keyHandler.up) {
            playerY -= playerSpeed;
        }
        if (keyHandler.down) {
            playerY += playerSpeed;
        }
        if (keyHandler.right) {
            playerX += playerSpeed;
        }
        if (keyHandler.left) {
            playerX -= playerSpeed;
        }
    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tamanhoTile, tamanhoTile);
        g2.dispose();
    }
}
