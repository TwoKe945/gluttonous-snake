package cn.com.twoke.game.gluttonous_snake.framework;

import lombok.Getter;

import javax.swing.*;
import java.util.function.Function;

@Getter
public class GameWindow {

    private final JFrame frame;

    public GameWindow(String title, Function<GameWindow, JPanel> callback) {
        frame = new JFrame();
        frame.setTitle(title);
//      设置默认的关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(callback.apply(this));
//		不允许变动窗口大小
        frame.setResizable(false);
        frame.pack();
//      设置显示位置到屏幕中间,必须在pack后面设置
        frame.setLocationRelativeTo(null);
//		设置窗口显示
        frame.setVisible(true);
    }

}
