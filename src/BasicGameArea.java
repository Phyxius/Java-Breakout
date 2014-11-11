/**
 * Created by Shea on 11/11/2014.
 */
public class BasicGameArea implements GameArea {
    private final int width, height;

    public BasicGameArea(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
