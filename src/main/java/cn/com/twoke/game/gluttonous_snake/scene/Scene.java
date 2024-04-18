package cn.com.twoke.game.gluttonous_snake.scene;

import java.awt.*;
import java.awt.event.KeyListener;

public interface Scene extends KeyListener {

    void render(Graphics g);

    void update(float delta);
}
