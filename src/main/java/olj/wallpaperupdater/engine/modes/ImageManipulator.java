package olj.wallpaperupdater.engine.modes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import olj.wallpaperupdater.engine.EngineMode;
import olj.wallpaperupdater.engine.modes.util.ImageUtil;
import olj.wallpaperupdater.entities.ImageUnit;

/**
 * Simple engine for applying the common options without merging or restructuring the image.
 *
 * @author olav
 * @since 12.okt.2010
 */
public class ImageManipulator implements ImageEngine {

    @Override
    public EngineMode getEngineMode() {
        return EngineMode.manipulate;
    }

    @Override
    public BufferedImage getCalculatedImage(ImageUnit imageUnit) {
        if (imageUnit.getComponents().size() != 1) {
            throw new IllegalStateException("Invalid number of components: " + imageUnit.getComponents().size());

        }

        return imageUnit.getComponents().get(0).getImage();
    }

    @Override
    public List<ImageUnit> getImageUnits(File[] files) {
        return ImageUtil.getSingleImageUnitsFromFiles(files);
    }
}
