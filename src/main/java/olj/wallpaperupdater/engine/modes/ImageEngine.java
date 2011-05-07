package olj.wallpaperupdater.engine.modes;

import java.awt.image.BufferedImage;

import olj.wallpaperupdater.entities.WallpaperScreen;

/**
 * @author Olav Jensen
 * @since Apr 12, 2010
 */
public interface ImageEngine {

	BufferedImage getCalculatedImage(BufferedImage srcImage, WallpaperScreen screen);

}
