package cn.com.twoke.game.gluttonous_snake.framework;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class GamePanel extends JPanel {

    private Consumer<Graphics> render;


    public GamePanel(Consumer<Graphics> render) {
        this.render = render;
        Dimension dimension = new Dimension(800, 600);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        render.accept(g);
    }
}
