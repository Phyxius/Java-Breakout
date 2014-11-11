import java.awt.*;

public interface GameArea {

    /** @return Width of the playing area */
    int getWidth();

    /** @return Height of the playing area */
    int getHeight();
    
    default Rectangle getAsRectangle() {
        return new Rectangle(0, 0, getWidth(), getHeight());
    }
}