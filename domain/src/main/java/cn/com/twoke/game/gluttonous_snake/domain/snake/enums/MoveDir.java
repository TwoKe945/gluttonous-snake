package cn.com.twoke.game.gluttonous_snake.domain.snake.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MoveDir {
    LEFT(-1,0),
    RIGHT(1,0),
    UP(0,-1),
    DOWN(0,1);


    public final int xFlag;
    public final int yFlag;
}
