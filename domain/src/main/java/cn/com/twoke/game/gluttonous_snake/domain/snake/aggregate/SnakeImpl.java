package cn.com.twoke.game.gluttonous_snake.domain.snake.aggregate;


import cn.com.twoke.game.gluttonous_snake.domain.snake.command.MoveCommand;
import cn.com.twoke.game.gluttonous_snake.domain.snake.enums.DeadReason;
import cn.com.twoke.game.gluttonous_snake.domain.snake.enums.MoveDir;
import cn.com.twoke.game.gluttonous_snake.domain.snake.event.*;
import cn.com.twoke.game.gluttonous_snake.domain.snake.event.manage.EventManage;
import cn.com.twoke.game.gluttonous_snake.domain.snake.external.PlaygroundService;
import lombok.Data;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@Data
public class SnakeImpl implements Snake {
    private final PlaygroundService playgroundService;
    private final EventManage eventManage;
    private boolean isDead = false;
    private List<Point> body;

    @Override
    public List<Point> getBody() {
        return body;
    }

    public SnakeImpl(PlaygroundService playgroundService) {
        this.playgroundService = playgroundService;
        this.eventManage = EventManage.getInstance();
        this.body = new LinkedList<>() {{
            add(new Point(20, 15));
            add(new Point(20, 16));
            add(new Point(20, 17));
        }};
    }

    public void addListener(Consumer<? super DomainEvent> listener) {
        eventManage.addListener(listener);
    }

    @Override
    public void reset() {
        this.body = new LinkedList<>() {{
            add(new Point(20, 15));
            add(new Point(20, 16));
            add(new Point(20, 17));
        }};
        this.isDead = false;
    }

    /**
     * 处理移动
     * @param command
     */
    public void handle(MoveCommand command) {
        int offsetX = command.dir().xFlag;
        int offsetY = command.dir().yFlag;
        int bodySize = body.size();
        // 获取头节点
        Point head = body.get(0);
        Point nextHead = new Point(head.x + offsetX, head.y + offsetY);

        if (playgroundService.isOutBound(nextHead)) { //1、超出边界 游戏结束
            eventManage.publish(new SnakeDeadEvent(DeadReason.OUT_OF_BOUNDS));
            return;
        }

        if (isCollisionSelf(nextHead)) { // 2、碰撞到自身 游戏结束
            eventManage.publish(new SnakeDeadEvent(DeadReason.COLLISION_SELF));
            return;
        }
        boolean isAteFood = playgroundService.isAteFood(nextHead);
        if (isAteFood) { // 3、吃到食物
            eventManage.publish(new SnakeAteFoodEvent());  // 发布吃到食物事件
            Point point = body.get(bodySize - 1);
            body.add(new Point(point.x, point.y));
            eventManage.publish(new SnakeGrowsEvent());  // 发布蛇变长的事件
        } else {
            body.remove(bodySize - 1);
        }
        body.add(0, nextHead);
        eventManage.publish(new SnakeMovedEvent());
    }

    private boolean isCollisionSelf(Point nextHead) {
        return this.body.stream().anyMatch(point -> point.x == nextHead.x && point.y == nextHead.y);
    }


}
