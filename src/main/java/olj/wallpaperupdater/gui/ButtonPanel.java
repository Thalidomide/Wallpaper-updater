package olj.wallpaperupdater.gui;

import olj.wallpaperupdater.gui.components.Button;
import olj.wallpaperupdater.gui.components.Label;
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
	private Button openSourceFolder;
    private Button openTargetFolder;
	private Button toggleGenerateImages;
	private Button settings;

    private Label targetFolder;

	private boolean saveButtonEnabled;
    private boolean generatingWallpapers;

	public ButtonPanel(ButtonPanelListener listener) {
		this.listener = listener;
		setBackground(Constants.BACKGROUND);

		//TODO Add label displaying mode..

		add(getOpenSourceFolder());
        add(getOpenTargetFolder());
        add(getTargetFolder());
		add(getToggleGenerateImages());
		add(getSettings());
	}

    public void setButtonsEnabled(boolean enabled) {
		openSourceFolder.setEnabled(enabled);
		toggleGenerateImages.setEnabled(enabled && saveButtonEnabled);
        openTargetFolder.setEnabled(enabled);
		settings.setEnabled(enabled);
	}

	public void setSaveButtonEnabled(boolean enabled) {
		saveButtonEnabled = enabled;
		toggleGenerateImages.setEnabled(enabled);
	}

	private JButton getOpenSourceFolder() {
		openSourceFolder = new Button("Open files/folder");
		openSourceFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				listener.openFolder();
			}
		});
		return openSourceFolder;
	}

    private Button getOpenTargetFolder() {
        openTargetFolder = new Button("Select destination folder");
        openTargetFolder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                listener.selectTargetFolder();
            }
        });

        return openTargetFolder;
    }

    private Label getTargetFolder() {
        targetFolder = new Label("Target folder not selected");
        return targetFolder;
    }

	private JButton getToggleGenerateImages() {
		toggleGenerateImages = new Button("Start wallpaper generation");
		toggleGenerateImages.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                toggleGeneratingWallpapers();
            }
		});
		toggleGenerateImages.setEnabled(false);

		return toggleGenerateImages;
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

        toggleGenerateImages.setText(buttonText);
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

    public void showTargetFolder(String path) {
        targetFolder.setText(path);
    }
}
