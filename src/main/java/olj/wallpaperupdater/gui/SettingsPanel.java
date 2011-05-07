package olj.wallpaperupdater.gui;

import olj.wallpaperupdater.engine.EngineSettings;
import olj.wallpaperupdater.gui.components.Button;
import olj.wallpaperupdater.gui.components.Panel;
import olj.wallpaperupdater.util.Constants;
import olj.wallpaperupdater.util.Manager;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Olav Jensen
 * @since Apr 11, 2010
 */
public class SettingsPanel extends Panel {

	private final SettingsListener listener;
	private Panel subPanel;
	private EngineSettings engineSettings;

    private List<WallpaperScreenSettings> settingsPanels = new ArrayList<WallpaperScreenSettings>();

	private final Color subEngineBackground = Constants.BACKGROUND_INPUT;

	public SettingsPanel(SettingsListener listener) {
		super(new BorderLayout());

		this.listener = listener;
		engineSettings = Manager.get().getEngineSettings();

		addSettingsComponents();
		addButtons();
	}

	private void addSettingsComponents() {
		Panel settingsContent = new Panel(new BorderLayout());

        JButton btnAdd = new JButton();
        btnAdd.setText("Legg til skjerm");
        settingsContent.add(btnAdd);

		Panel engineCommonPanel = new Panel();
		subPanel = new Panel();
		subPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

		settingsContent.add(engineCommonPanel, BorderLayout.NORTH);
		settingsContent.add(subPanel, BorderLayout.CENTER);

		restoreGui();

		add(settingsContent, BorderLayout.CENTER);
	}

	private void addButtons() {
		Panel buttonPanel = new Panel();
		Button save = new Button("Save");
		Button cancel = new Button("Cancel");

		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (isValidInput()) {
					save();
					listener.saveSettings();
				}
			}
		});

		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				restoreGui();
				listener.cancelSettings();
			}
		});

		buttonPanel.add(save);
		buttonPanel.add(cancel);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	private boolean isValidInput() {
		return true;//TODO Any case where this is not true?
	}

	private void save() {
		//TODO Nothinbg to do here?
	}

	private void restoreGui() {
		Manager manager = Manager.get();
	}

}
