package cn.com.twoke.game.gluttonous_snake;

import cn.com.twoke.game.gluttonous_snake.domain.snake.aggregate.Snake;
import cn.com.twoke.game.gluttonous_snake.domain.snake.aggregate.SnakeImpl;
import cn.com.twoke.game.gluttonous_snake.domain.snake.command.MoveCommand;
import cn.com.twoke.game.gluttonous_snake.domain.snake.enums.MoveDir;
import cn.com.twoke.game.gluttonous_snake.domain.snake.event.SnakeAteFoodEvent;
import cn.com.twoke.game.gluttonous_snake.domain.snake.event.SnakeDeadEvent;
import cn.com.twoke.game.gluttonous_snake.domain.snake.event.SnakeGrowsEvent;
import cn.com.twoke.game.gluttonous_snake.domain.snake.event.SnakeMovedEvent;
import cn.com.twoke.game.gluttonous_snake.domain.snake.external.PlaygroundService;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class SnakeDomainTest {

    @Test
    public void test01() {
        Snake snake = new SnakeImpl(new PlaygroundService() {
            @Override
            public boolean isOutBound(Point head) {
                return false;
            }

            @Override
            public boolean isAteFood(Point nextHead) {
                return true;
            }
        });
        snake.addListener(event -> {
            if (event instanceof SnakeDeadEvent snakeDeadEvent) {
                System.out.println("蛇死了: " + snakeDeadEvent.reason());
            }
            if (event instanceof SnakeAteFoodEvent) {
                System.out.println("吃到食物");
            }
            if (event instanceof SnakeGrowsEvent) {
                System.out.println("蛇长长了");
            }
            if (event instanceof SnakeMovedEvent) {
                System.out.println("蛇在移动");
            }
        });
        System.out.println(snake.getBody());
        snake.handle(new MoveCommand(MoveDir.LEFT));
        System.out.println(snake.getBody());
        snake.handle(new MoveCommand(MoveDir.LEFT));
        System.out.println(snake.getBody());
        snake.handle(new MoveCommand(MoveDir.LEFT));
        System.out.println(snake.getBody());
    }

}
