package cn.com.twoke.game.gluttonous_snake.domain.snake.event.manage;

import cn.com.twoke.game.gluttonous_snake.domain.snake.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class DefaultEventManage implements EventManage{

    private final List<Consumer<? super DomainEvent>> listeners = new ArrayList<>();

    @Override
    public void publish(DomainEvent event) {
        listeners.forEach(l -> l.accept(event));
    }

    @Override
    public void addListener(Consumer<? super DomainEvent> handler) {
        Optional.ofNullable(handler).ifPresent(listeners::add);
    }

}
