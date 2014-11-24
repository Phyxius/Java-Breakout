import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Phyxius on 11/22/2014.
 */
public class BasicFileLevel implements Level {
    private static final int BRICK_WIDTH = 40;
    private static final int BRICK_HEIGHT = 20;
    private static final int BRICK_HSPACING = 20;
    private static final int BRICK_VSPACING = 20;
    private static final int GAME_WIDTH = 800;
    private final int[][] brickHitsArray;

    public BasicFileLevel(String fileText) {
        Scanner scanner = new Scanner(fileText);
        String[] header = scanner.nextLine().split("\\s*,\\s*", 3);
        brickHitsArray = new int[Integer.parseInt(header[0])][
                Integer.parseInt(header[1])];
        for(int i = 0; i < brickHitsArray.length && scanner.hasNextLine(); i++){
            String[] line = scanner.nextLine().split("\\s*,\\s*",
                    brickHitsArray[0].length + 1);
            for(int j = 0; j < line.length && j < brickHitsArray[0].length; j++) {
                brickHitsArray[i][j] = line[j].equals("") ? 0 :
                        Integer.parseInt(line[j]);
            }
        }
    }

    @Override
    public Iterable<GameObject> createObjects() {
        ArrayList<GameObject> objs = new ArrayList<>();
        int startx = (GAME_WIDTH - (brickHitsArray[0].length *
                (BRICK_WIDTH + BRICK_HSPACING))) / 2;
        int starty = 20;
        for (int i = 0; i < brickHitsArray.length; i++) {
            for (int j = 0; j < brickHitsArray[i].length; j++) {
                if (brickHitsArray[i][j] == 0) {
                    continue;
                }
                objs.add(new Brick(startx + j * (BRICK_WIDTH + BRICK_HSPACING),
                        starty + i * (BRICK_HEIGHT + BRICK_VSPACING),
                        BRICK_WIDTH, BRICK_HEIGHT, brickHitsArray[i][j]));
            }
        }
        return objs;
    }
}
