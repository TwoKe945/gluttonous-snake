package cn.com.twoke.game.gluttonous_snake.scene;

import cn.com.twoke.game.gluttonous_snake.domain.snake.aggregate.Snake;
import cn.com.twoke.game.gluttonous_snake.domain.snake.aggregate.SnakeImpl;
import cn.com.twoke.game.gluttonous_snake.domain.snake.command.MoveCommand;
import cn.com.twoke.game.gluttonous_snake.domain.snake.enums.MoveDir;
import cn.com.twoke.game.gluttonous_snake.domain.snake.event.DomainEvent;
import cn.com.twoke.game.gluttonous_snake.domain.snake.event.SnakeAteFoodEvent;
import cn.com.twoke.game.gluttonous_snake.domain.snake.event.SnakeDeadEvent;
import cn.com.twoke.game.gluttonous_snake.domain.snake.external.PlaygroundService;
import cn.com.twoke.game.gluttonous_snake.utils.GraphicsHelper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PlayingScene implements Scene, PlaygroundService {

    private final Snake snake;
    private final Point food;
    private final Random rand = new Random();
    private boolean isDead = false;
    private Font font = new Font("微软雅黑", Font.BOLD, 50);
    private MoveDir currentMoveDir = MoveDir.UP;

    public PlayingScene() {
        snake = new SnakeImpl(this);
        food = new Point(rand.nextInt(40), rand.nextInt(30));
        snake.addListener(this::onEvent);
    }

    public void onEvent(DomainEvent e) {
        if (e instanceof SnakeDeadEvent deadEvent && !isDead) {
            isDead = true;
        } else if (e instanceof SnakeAteFoodEvent ateFoodEvent && !isDead) {
            food.x = rand.nextInt(40);
            food.y = rand.nextInt(30);
        }

    }


    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0, 800, 600);
        GraphicsHelper.eliminateShapeSawtooth(g);
        final List<Point> body = snake.getBody();
        for (int i = 0; i < body.size(); i++) {
            Point point = body.get(i);
            if (i == 0) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.black);
            }
            g.drawRect(point.x * 20, point.y * 20, 20, 20);
        }

        g.setColor(Color.BLUE);
        g.fillRect(food.x * 20, food.y * 20, 20, 20);

        if (isDead) {
            g.setColor(new Color(0,0,0, 100));
            g.fillRect(0,0,800,600);
            g.setColor(Color.red);

            g.setFont(font);
            FontMetrics fm;
            if (Objects.nonNull(font)) {
                g.setFont(font);
                fm = g.getFontMetrics(font);
            } else {
                fm = g.getFontMetrics();
            }
            String text = "Game Over";
            int x = (800-(int)fm.getStringBounds(text, g).getWidth())/2;
            int y =  (600 - fm.getHeight()) / 2 + fm.getAscent();
            g.drawString(text, x, y);
        }

    }

    @Override
    public void update(float delta) {
        snake.handle(new MoveCommand(currentMoveDir));
    }

    @Override
    public boolean isOutBound(Point head) {
        return head.x < 0 || head.x >= 40 || head.y < 0 || head.y >= 30;
    }

    @Override
    public boolean isAteFood(Point nextHead) {
        return nextHead.x == food.x && nextHead.y == food.y;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                currentMoveDir = MoveDir.UP;
                break;
            case KeyEvent.VK_DOWN:
                currentMoveDir = MoveDir.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                currentMoveDir = MoveDir.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                currentMoveDir = MoveDir.RIGHT;
                break;
            case KeyEvent.VK_SPACE:
                reset();
                break;
        }
    }

    private void reset() {
        snake.reset();
        food.x = rand.nextInt(40);
        food.y = rand.nextInt(30);
        isDead = false;
    }
}
