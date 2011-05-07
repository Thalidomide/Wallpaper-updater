package olj.wallpaperupdater.gui;

import olj.wallpaperupdater.entities.WallpaperScreen;

import javax.swing.*;

/**
 * @author aestonpro17
 * @since 07.05.11
 */
public class WallpaperScreenSettings extends JPanel {

    private WallpaperScreen screen;

    public WallpaperScreenSettings(WallpaperScreen screen) {
        this.screen = screen;
    }

    public WallpaperScreen getScreen() {
        return screen;
    }
}
