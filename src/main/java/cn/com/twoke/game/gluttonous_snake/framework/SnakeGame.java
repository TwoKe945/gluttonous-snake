package cn.com.twoke.game.gluttonous_snake.framework;

import cn.com.twoke.game.gluttonous_snake.scene.Scene;
import cn.com.twoke.game.gluttonous_snake.scene.SceneManage;

import java.awt.*;
import java.util.Optional;

public class SnakeGame implements Runnable {
    private final GamePanel panel;
    private final Scene currentScene;



    public SnakeGame() {
        this.panel = new GamePanel(this::render);
        new GameWindow("Gluttonous Snake", window -> panel);
        this.currentScene = SceneManage.getDefaultScene();
        this.panel.addKeyListener(this.currentScene);
        Thread renderThread = new Thread(this);
        renderThread.start();
    }


    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / 120;
        double timePerUpdate = 1_000_000_000.0 / 5;
        long lastCheck = System.currentTimeMillis();;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;

        double deltaU = 0;
        double deltaF = 0;
        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                panel.requestFocus();
                update((float) (timePerUpdate / 1E9f));
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                panel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000 ) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }



    public void render(Graphics g) {
        Optional.ofNullable(currentScene).ifPresent(scene -> scene.render(g));
    }


    public void update(float delta) {
        Optional.ofNullable(currentScene).ifPresent(scene -> scene.update(delta));
    }
}
