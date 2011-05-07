package olj.wallpaperupdater.gui;

import olj.wallpaperupdater.entities.WallpaperScreen;
import olj.wallpaperupdater.gui.components.IntegerTextField;
import olj.wallpaperupdater.gui.components.Label;
import olj.wallpaperupdater.gui.components.Panel;

import java.awt.*;


/**
 * @author aestonpro17
 * @since 07.05.11
 */
public class WallpaperScreenSettings extends Panel {

    private WallpaperScreen screen;

    private IntegerTextField width;
    private IntegerTextField height;
    private IntegerTextField x;
    private IntegerTextField y;


    public WallpaperScreenSettings(WallpaperScreen screen) {
        super(new GridLayout(1, 8));

        this.screen = screen;

        width = new IntegerTextField(screen.getWidth());
        height = new IntegerTextField(screen.getHeight());
        x = new IntegerTextField(screen.getX());
        y = new IntegerTextField(screen.getY());

        width.setOptional(false);
        height.setOptional(false);
        x.setOptional(false);
        y.setOptional(false);

        add(new Label("Bredde:"));
        add(width);
        add(new Label("Høyde:"));
        add(height);
        add(new Label("X:"));
        add(x);
        add(new Label("Y:"));
        add(y);
    }

    public WallpaperScreen getScreen() {
        return screen;
    }

    public boolean saveAndValidate() {
        boolean valid = width.isValidInteger() && height.isValidInteger() && x.isValidInteger() && y.isValidInteger();

        if (valid) {
            screen.setWidth(width.getInteger());
            screen.setHeight(height.getInteger());
            screen.setX(x.getInteger());
            screen.setY(y.getInteger());
        }

        return valid;
    }
}
