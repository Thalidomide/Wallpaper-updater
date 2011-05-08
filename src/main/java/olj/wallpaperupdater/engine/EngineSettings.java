package olj.wallpaperupdater.engine;

import olj.wallpaperupdater.entities.WallpaperScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olav Jensen
 * @since Apr 12, 2010
 */
public class EngineSettings {

	private List<WallpaperScreen> screens = new ArrayList<WallpaperScreen>();
    private int secondsBetweenUpdate = 5;

    public void addScreen(WallpaperScreen screen) {
        screens.add(screen);
    }

    public void removeScreen(WallpaperScreen screen) {
        screens.remove(screen);
    }

    public List<WallpaperScreen> getScreens() {
        return screens;
    }

    public int getSecondsBetweenUpdate() {
        return secondsBetweenUpdate;
    }

    public void setSecondsBetweenUpdate(int secondsBetweenUpdate) {
        this.secondsBetweenUpdate = secondsBetweenUpdate;
    }
}
