package cn.com.twoke.game.gluttonous_snake.domain.snake.aggregate;

import cn.com.twoke.game.gluttonous_snake.domain.snake.command.MoveCommand;
import cn.com.twoke.game.gluttonous_snake.domain.snake.event.DomainEvent;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public interface Snake {

    void handle(MoveCommand command);

    List<Point> getBody();

    void addListener(Consumer<? super DomainEvent> listener);

    void reset();

}
