package olj.wallpaperupdater.engine.modes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import olj.wallpaperupdater.engine.EngineMode;
import olj.wallpaperupdater.entities.ImageUnit;

/**
 * @author Olav Jensen
 * @since Apr 12, 2010
 */
public interface ImageEngine {

	EngineMode getEngineMode();

	BufferedImage getCalculatedImage(ImageUnit imageUnit);

	List<ImageUnit> getImageUnits(File[] files);

}