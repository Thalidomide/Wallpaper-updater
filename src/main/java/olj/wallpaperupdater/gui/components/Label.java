package olj.wallpaperupdater.gui.components;

import javax.swing.JLabel;

import olj.wallpaperupdater.util.Constants;

/**
 * @author Olav Jensen
 * @since Apr 27, 2010
 */
public class Label extends JLabel {

	public Label(String text) {
		super(text);
		setActive(true);
		setFont(Constants.NORMAL);
	}

	public void setActive(boolean active) {
		setForeground(active ? Constants.FONT : Constants.FONT_DISABLED);
	}
}
