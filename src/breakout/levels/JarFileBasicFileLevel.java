package breakout.levels;

import breakout.Util;

/**
 * Created by Phyxius on 11/23/2014.
 */
public class JarFileBasicFileLevel extends BasicFileLevel {
    public JarFileBasicFileLevel(String filePath) {
        super(Util.getStringFromJar(filePath));
    }
}
