import java.util.*;

/**
 * Driver for testing out breakout objects. 
 */
public class TestDriver {

    public static void main(String[] args) {

        // Make a dummy game area
        // In an actual game, this could be a specialized JPanel
        GameArea gameArea = new GameArea() {
            public int getWidth() { return 300; }
            public int getHeight() { return 200; }
        };

        // Make some game objects and test out their methods.
        List<Object2D> objects = new ArrayList<>();

        // Make a ball
        Ball ball = new Ball(gameArea, 25, 140, 30);
        objects.add(ball);

        // Make a paddle
        Paddle paddle = new Paddle(200, 155, 50, 40);
        objects.add(paddle);

        // Make a bunch of bricks
        for(int x = -10; x < 300; x+= 65) {
            for(int y = -5; y < 200; y += 30) {
                Brick brick = new Brick(x, y, 55, 25);
                objects.add(brick);
            }
        }

        // Print out a bunch of stuff about the objects
        for(Object2D object : objects) {

            // Make sure toString was properly overridden.
            System.out.println(object);

            // Check Object2D methods
            System.out.format("  Vals = %d %d %d %d%n",
                    object.getX(), object.getY(),
                    object.getWidth(), object.getHeight());
            System.out.println("  Bounds = " + object.getBoundingRectangle());
            System.out.println("  Intersects ball? " + object.intersects(ball) +
                    ", Ball intersects? " + ball.intersects(object));
            System.out.println("  Intersects paddle? " + object.intersects(paddle) +
                    ", Paddle intersects? " + paddle.intersects(object));
            System.out.println("  Out of bounds? " + object.isOutOfBounds());

            // This better be true for all
            System.out.println("  Is GameObject? " + (object instanceof GameObject));
        }
    }
}