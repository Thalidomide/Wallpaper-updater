package olj.wallpaperupdater.engine;

import olj.wallpaperupdater.engine.modes.WallpaperGenerator;
import olj.wallpaperupdater.entities.ImageFile;
import olj.wallpaperupdater.entities.WallpaperScreen;
import olj.wallpaperupdater.util.Manager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Olav Jensen
 * @since 05.apr.2010
 */
public class ImageSaverLoader {

    public static void saveImage(ImageFile imageFile, String rootPath) {
        WallpaperGenerator generator = new WallpaperGenerator();
        BufferedImage srcImage = imageFile.getImage();
        List<WallpaperScreen> screens = Manager.get().getEngineSettings().getScreens();

        int index = 1;

        for (WallpaperScreen screen : screens) {
            BufferedImage image = generator.getCalculatedImage(srcImage, screen);

            //String imagePath = rootPath + "/" + index + "/" + index + "-" + imageFile.getName();
            String imagePath = rootPath + "/" + index + "/" + index + ".png";

            saveImage(image, imagePath);

            index++;
        }
    }

	private static void saveImage(BufferedImage image, String filePath) {
		try {
    		File outputfile = new File(filePath);
			outputfile.mkdirs();
			if (!outputfile.exists()) {
				outputfile.createNewFile();
			}
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
