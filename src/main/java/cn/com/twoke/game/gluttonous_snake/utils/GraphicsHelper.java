package cn.com.twoke.game.gluttonous_snake.utils;

import java.awt.*;
import java.util.function.Consumer;

public class GraphicsHelper {

    public static void draw2D(Graphics g, Consumer<Graphics2D> renderer) {
        renderer.accept((Graphics2D) g);
    }

    public static void eliminateTextSawtooth(Graphics g) {
        draw2D(g, g2d -> g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
    }

    public static void eliminateShapeSawtooth(Graphics g) {
        draw2D(g, g2d -> g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
    }

}
