package cn.com.twoke.game.gluttonous_snake.scene;

public class SceneManage {
    static final Scene PLAYING_SCENE;

    static {
        PLAYING_SCENE = new PlayingScene();
    }



    public static Scene getDefaultScene() {
        return PLAYING_SCENE;
    }
}
