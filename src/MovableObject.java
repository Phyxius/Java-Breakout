import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Phyxius on 11/14/2014.
 */
public interface MovableObject {
    int getXSpeed();
    int getYSpeed();
    void setXSpeed(int xSpeed);
    void setYSpeed(int ySpeed);
    default Point2D.Double getAsPoint() {
        return new Point2D.Double(getXSpeed(), getYSpeed());
    }
    default void setSpeed(Point vector) {
        setXSpeed((int)vector.getX());
        setYSpeed((int)vector.getY());
    }
}
