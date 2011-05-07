package olj.wallpaperupdater.engine.modes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.List;

import olj.wallpaperupdater.engine.EngineMode;
import olj.wallpaperupdater.engine.modes.util.ImageUtil;
import olj.wallpaperupdater.entities.ImageUnit;
import olj.wallpaperupdater.util.Manager;

/**
 * @author Olav Jensen
 * @since Apr 12, 2010
 */
public class ImageRestructure implements ImageEngine {

	@Override
	public EngineMode getEngineMode() {
		return EngineMode.restructure;
	}

	@Override
	public BufferedImage getCalculatedImage(ImageUnit imageUnit) {
		ImageObserver imgObserver = Manager.get().getImageObserver();
		final int partsCount = Manager.get().getEngineSettings().getImageParts();

		BufferedImage srcImage = imageUnit.getImageComponent(0).getImage();

		int width = srcImage.getWidth();
		int heigth = srcImage.getHeight();

		BufferedImage result = ImageUtil.createNewImage(width, heigth);

		Graphics graphics = result.getGraphics();
		int xStart, xEnd;
		for (int i = 0; i < partsCount; i++) {
			xStart = (int) (((double) i / partsCount) * width);
			xEnd = (int) (((double) (i + 1) / partsCount) * width);
			int widthPart = getWidth(xStart, xEnd);

			BufferedImage part = srcImage.getSubimage(xStart, 0, widthPart, heigth);
			graphics.drawImage(part, width - widthPart - xStart, 0, imgObserver);
		}

		return result;
	}

	private int getWidth(int xStart, int xEnd) {
		int width = xEnd - xStart;
		if (width == 0) {
			width ++;
		}
		return width;
	}

	@Override
	public List<ImageUnit> getImageUnits(File[] files) {
		return ImageUtil.getSingleImageUnitsFromFiles(files);
	}
}
