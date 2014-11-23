import java.util.ArrayList;

/**
 * Created by Phyxius on 11/22/2014.
 */
public class BasicLevel implements Level {
    private final int rows, columns, vSpacing, hSpacing, width, height,
            startX, startY;
    private final int numHits;

    public BasicLevel(int rows, int columns, int vSpacing, int hSpacing,
                      int width, int height, int startX, int startY,
                      int numHits) {
        this.rows = rows;
        this.columns = columns;
        this.vSpacing = vSpacing;
        this.hSpacing = hSpacing;
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.startY = startY;
        this.numHits = numHits;
    }

    @Override
    public Iterable<GameObject> createObjects() {
        ArrayList<GameObject> ret = new ArrayList<>(rows * columns);
        for(int i = 0; i < columns; i++) {
            for(int j = 0; j < rows; j++) {
                ret.add(new Brick(startX + i * (width + hSpacing),
                        startY + j * (height + vSpacing),
                        width, height, numHits));
            }
        }

        return ret;
    }
}
