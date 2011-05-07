package olj.wallpaperupdater.entities;

import java.awt.image.BufferedImage;
import java.io.File;

import olj.wallpaperupdater.util.Util;

/**
 * @author Olav Jensen
 * @since 08.apr.2010
 */
public class ImageFile {

	private final File imageFile;
	private final String name;

    public ImageFile(File imageFile, String name) {
        this.imageFile = imageFile;
        this.name = name;
    }

    public BufferedImage getImage() {
		return Util.loadImage(imageFile);
	}

    public String getName() {
        return imageFile.getName();
    }
}
