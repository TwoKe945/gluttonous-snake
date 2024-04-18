package cn.com.twoke.game.gluttonous_snake.domain.snake.event.manage;

import cn.com.twoke.game.gluttonous_snake.domain.snake.event.DomainEvent;

import java.util.function.Consumer;

public interface EventManage {

    void publish(DomainEvent event);

    void addListener(Consumer<? super DomainEvent> handler);

    static EventManage getInstance() {
        return new DefaultEventManage();
    }
}
