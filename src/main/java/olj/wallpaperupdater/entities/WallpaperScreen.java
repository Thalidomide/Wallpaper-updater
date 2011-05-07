package olj.wallpaperupdater.entities;

/**
 * @author aestonpro17
 * @since 07.05.11
 */
public class WallpaperScreen {

    private int x;
    private int y;

    private int width = 1920;
    private int height = 1200;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
