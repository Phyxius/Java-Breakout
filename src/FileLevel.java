import java.util.Scanner;

/**
 * Created by Phyxius on 11/22/2014.
 */
public class FileLevel implements Level {
    //private final int[][] brickHitsArray;

    public FileLevel(String fileText) {
        Scanner scanner = new Scanner(fileText);

    }

    @Override
    public Iterable<GameObject> createObjects() {
        return null;
    }
}
