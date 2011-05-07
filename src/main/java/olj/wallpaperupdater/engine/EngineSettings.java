package olj.wallpaperupdater.engine;

import olj.wallpaperupdater.entities.WallpaperScreen;
import olj.wallpaperupdater.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olav Jensen
 * @since Apr 12, 2010
 */
public class EngineSettings {

	private List<WallpaperScreen> screens = new ArrayList<WallpaperScreen>();

    public void addScreen(WallpaperScreen screen) {
        screens.add(screen);
    }

    public void removeScreen(WallpaperScreen screen) {
        screens.remove(screen);
    }

    public List<WallpaperScreen> getScreens() {
        return screens;
    }
}
