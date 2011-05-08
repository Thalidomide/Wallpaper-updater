package olj.wallpaperupdater.gui;

import olj.wallpaperupdater.entities.WallpaperScreen;
import olj.wallpaperupdater.gui.components.IntegerTextField;
import olj.wallpaperupdater.gui.components.Label;
import olj.wallpaperupdater.gui.components.Panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @author aestonpro17
 * @since 07.05.11
 */
public class WallpaperScreenSettings extends Panel {

    private final WallpaperScreen screen;
    private final WallpaperScreenSettingsListener listener;

    private IntegerTextField width;
    private IntegerTextField height;
    private IntegerTextField x;
    private IntegerTextField y;
    private Button remove;


    public WallpaperScreenSettings(WallpaperScreen screen, WallpaperScreenSettingsListener listener) {
        super(new GridLayout(1, 9));

        this.screen = screen;
        this.listener = listener;

        width = new IntegerTextField(screen.getWidth());
        height = new IntegerTextField(screen.getHeight());
        x = new IntegerTextField(screen.getX());
        y = new IntegerTextField(screen.getY());
        remove = new Button("Remove");

        width.setOptional(false);
        height.setOptional(false);
        x.setOptional(false);
        y.setOptional(false);

        remove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                WallpaperScreenSettings.this.listener.remove(WallpaperScreenSettings.this);
            }
        });

        add(new Label("Bredde:"));
        add(width);
        add(new Label("Høyde:"));
        add(height);
        add(new Label("X:"));
        add(x);
        add(new Label("Y:"));
        add(y);
        add(remove);
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
