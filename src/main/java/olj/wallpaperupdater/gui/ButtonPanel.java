package olj.wallpaperupdater.gui;

import olj.wallpaperupdater.gui.components.Button;
import olj.wallpaperupdater.gui.components.Panel;
import olj.wallpaperupdater.util.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Olav Jensen
 * @since 09.apr.2010
 */
public class ButtonPanel extends Panel {

	private final ButtonPanelListener listener;
	private Button openFolder;
	private Button saveImages;
	private Button settings;

	private boolean saveButtonEnabled;
    private boolean generatingWallpapers;

	public ButtonPanel(ButtonPanelListener listener) {
		this.listener = listener;
		setBackground(Constants.BACKGROUND);

		//TODO Add label displaying mode..

		add(getOpenFolder());
		add(getSaveImages());
		add(getSettings());
	}

	public void setButtonsEnabled(boolean enabled) {
		openFolder.setEnabled(enabled);
		saveImages.setEnabled(enabled && saveButtonEnabled);
		settings.setEnabled(enabled);
	}

	public void setSaveButtonEnabled(boolean enabled) {
		saveButtonEnabled = enabled;
		saveImages.setEnabled(enabled);
	}

	private JButton getOpenFolder() {
		openFolder = new Button("Open files/folder");
		openFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				listener.openFolder();
			}
		});
		return openFolder;
	}

	private JButton getSaveImages() {
		saveImages = new Button("Start wallpaper generation");
		saveImages.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                toggleGeneratingWallpapers();
            }
		});
		saveImages.setEnabled(false);

		return saveImages;
	}

    private void toggleGeneratingWallpapers() {
        generatingWallpapers = !generatingWallpapers;
        String buttonText;

        if (generatingWallpapers) {
            listener.startGeneratingWallpapers();
            buttonText = "Stop wallpaper generation";
        } else {
            listener.stopGeneratingWallpapers();
            buttonText = "Start wallpaper generation";
        }

        saveImages.setText(buttonText);
    }

    private JButton getSettings() {
		settings = new Button("Settings");
		settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				listener.showSettings();
			}
		});

		return settings;
	}
}
