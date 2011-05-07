package olj.wallpaperupdater.engine.modes.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import olj.wallpaperupdater.entities.ImageFile;
import olj.wallpaperupdater.util.Util;

/**
 * @author Olav Jensen
 * @since Apr 12, 2010
 */
public class ImageUtil {

	public static BufferedImage createNewImage(int width, int heigth) {
		return new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
	}

    public static List<ImageFile> getImageFiles(File[] files) {
		List<ImageFile> imageComponents = new ArrayList<ImageFile>();

		for (File file : files) {
			String fileNameWithEnding = file.getName();
			if (Util.isValidImageFile(fileNameWithEnding)) {
				String nameWithoutEnding = Util.getFileNameWithoutEnding(fileNameWithEnding);
				imageComponents.add(new ImageFile(file, nameWithoutEnding));
			}
		}

		return imageComponents;
	}
}
