import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by Phyxius on 11/23/2014.
 */
public class FileSystemBasicFileLevel extends BasicFileLevel {
    public FileSystemBasicFileLevel(String filePath) throws IOException {
        super(Files.readAllLines(Paths.get(filePath)).stream().collect(
                Collectors.joining("\n")));
    }
}
