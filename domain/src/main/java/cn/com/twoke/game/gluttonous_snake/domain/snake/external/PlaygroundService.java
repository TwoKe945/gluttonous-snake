package cn.com.twoke.game.gluttonous_snake.domain.snake.external;

import java.awt.*;

public interface PlaygroundService {

    /**
     * 判断是否超出边界
     * @param head
     * @return
     */
    boolean isOutBound(Point head);

    /**
     * 判断是否吃到食物
     * @param nextHead
     * @return
     */
    boolean isAteFood(Point nextHead);

}
