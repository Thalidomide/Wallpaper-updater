package olj.wallpaperupdater.gui;

/**
 * @author Olav Jensen
 * @since 09.apr.2010
 */
public interface ButtonPanelListener {

	void openFolder();

    void selectTargetFolder();

	void startGeneratingWallpapers();

	void showSettings();

    void stopGeneratingWallpapers();
}
