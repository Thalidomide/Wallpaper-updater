package olj.wallpaperupdater.engine.modes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.List;

import olj.wallpaperupdater.engine.modes.util.ImageUtil;
import olj.wallpaperupdater.entities.ImageFile;
import olj.wallpaperupdater.entities.WallpaperScreen;
import olj.wallpaperupdater.util.Manager;

/**
 * @author Olav Jensen
 * @since Apr 12, 2010
 */
public class WallpaperGenerator implements ImageEngine {

	@Override
	public BufferedImage getCalculatedImage(BufferedImage srcImage, WallpaperScreen screen) {
		//ImageObserver imgObserver = Manager.get().getImageObserver();

		//int width = srcImage.getWidth();
		//int heigth = srcImage.getHeight();

		BufferedImage result = srcImage.getSubimage(screen.getX(), screen.getY(), screen.getWidth(), screen.getHeight());

        /*
		Graphics graphics = result.getGraphics();
		int xStart, xEnd;
		for (int i = 0; i < partsCount; i++) {
			xStart = (int) (((double) i / partsCount) * width);
			xEnd = (int) (((double) (i + 1) / partsCount) * width);
			int widthPart = getWidth(xStart, xEnd);

			BufferedImage part = srcImage.getSubimage(xStart, 0, widthPart, heigth);
			graphics.drawImage(part, width - widthPart - xStart, 0, imgObserver);
		}
		*/

		return result;
	}
}
