package cn.com.twoke.game.gluttonous_snake.domain.snake.event;

import cn.com.twoke.game.gluttonous_snake.domain.snake.enums.DeadReason;

public record SnakeDeadEvent(
    DeadReason reason
) implements DomainEvent {
}
