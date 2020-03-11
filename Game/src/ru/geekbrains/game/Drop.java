package ru.geekbrains.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Drop extends JFrame {

    private static Drop window;
    private static Image background;
    private static Image drop;
    private static Image game_over;
    private static long last_frame_time;
    private static float drop_left = 200;
    private static float drop_top = -100;
    private static float drop_v = 200;
    private static int score;

    public static void main(String[] args) throws IOException {
        background = ImageIO.read(Drop.class.getResourceAsStream("background.png"));
        drop = ImageIO.read(Drop.class.getResourceAsStream("drop.png"));
        game_over = ImageIO.read(Drop.class.getResourceAsStream("game_over.png"));
        last_frame_time = System.nanoTime();
        window = new Drop();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocation(300,200);
        window.setSize(906,478);
        window.setResizable(false);
        GameField game_field = new GameField();
        game_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                float drop_right = drop_left + drop.getWidth(null);
                float drop_bottom = drop_top + drop.getHeight(null);
                boolean is_drop = x >= drop_left && x <= drop_right && y >= drop_top && y <= drop_bottom;
                if (is_drop) {
                    drop_top = -100;
                    drop_left = (int) (Math.random()*(game_field.getWidth() - drop.getWidth(null)));
                    drop_v += 20;
                    score ++;
                    window.setTitle("Score: " + score);
                }
            }
        });
        window.add(game_field);
        window.setVisible(true);
    }

    private static void onRepaint (Graphics g) {
        long current_time = System.nanoTime();
        float delta_time = (current_time - last_frame_time) * 0.000000001f;
        last_frame_time = current_time;
        drop_top = drop_top + drop_v * delta_time;
        g.drawImage(background, 0,0,null);
        g.drawImage(drop, (int) drop_left, (int) drop_top,null);
        if (drop_top > window.getHeight()) g.drawImage(game_over, 280, 120, null);
    }

    private static class GameField extends JPanel {
        @Override
        protected void paintComponent (Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }

}
