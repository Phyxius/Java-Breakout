package breakout.managers;

import breakout.gameobjects.Object2D;

import java.awt.*;

/**
 * Created by Shea on 11/11/2014.
 */
public abstract class EventManager {
    protected final GameManager manager;

    public EventManager(GameManager manager) {
        this.manager = manager;
    }

    public boolean isDebugEnabled() {
        return manager.isDebugMode();
    }

    public int getGameAreaWidth() {
        return manager.getGameArea().getWidth();
    }

    public int getGameAreaHeight() {
        return manager.getGameArea().getHeight();
    }

    public Rectangle getGameAreaRectangle() {
        return manager.getGameArea().getAsRectangle();
    }

    public boolean isOutOfBounds(Object2D obj) {
        return manager.isOutOfBounds(obj);
    }

    public int getScore() {
        return manager.getScore();
    }

    public int getLives() {
        return manager.getLives();
    }
}