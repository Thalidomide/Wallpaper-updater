package olj.wallpaperupdater.gui;

import olj.wallpaperupdater.engine.EngineSettings;
import olj.wallpaperupdater.entities.WallpaperScreen;
import olj.wallpaperupdater.gui.components.Button;
import olj.wallpaperupdater.gui.components.Panel;
import olj.wallpaperupdater.util.Constants;
import olj.wallpaperupdater.util.Manager;
import sun.awt.OrientableFlowLayout;

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
	private EngineSettings settings;

    private List<WallpaperScreenSettings> settingsPanels = new ArrayList<WallpaperScreenSettings>();

	private final Color subEngineBackground = Constants.BACKGROUND_INPUT;

	public SettingsPanel(SettingsListener listener) {
		super(new BorderLayout());

		this.listener = listener;
		settings = Manager.get().getEngineSettings();

		addSettingsComponents();
		addButtons();
	}

	private void addSettingsComponents() {
        OrientableFlowLayout l = new OrientableFlowLayout(OrientableFlowLayout.VERTICAL);
        l.setAlignOnBaseline(true);
        l.setAlignment(OrientableFlowLayout.TOP);

		subPanel = new Panel(l);
		subPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

		restoreGui();

		add(subPanel, BorderLayout.CENTER);
	}

	private void addButtons() {
		Panel buttonPanel = new Panel();
		Button save = new Button("Save");
		Button cancel = new Button("Cancel");

        Button addScreen = new Button("Add screen");

        addScreen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                addScreen();
            }
        });

		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
                boolean valid = saveAndValidate();
				if (valid) {
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

		buttonPanel.add(addScreen);
		buttonPanel.add(save);
		buttonPanel.add(cancel);

		add(buttonPanel, BorderLayout.NORTH);
	}

    private void addScreen() {
        WallpaperScreen screen = new WallpaperScreen();
        WallpaperScreenSettings screenSettings = new WallpaperScreenSettings(screen);

        settings.addScreen(screen);
        settingsPanels.add(screenSettings);
        subPanel.add(screenSettings);

        subPanel.validate();
    }

	private boolean saveAndValidate() {
        boolean valid = true;

        for (WallpaperScreenSettings settingsPanel : settingsPanels) {
            valid &= settingsPanel.saveAndValidate();
        }

		return valid;
	}

	private void restoreGui() {
		Manager manager = Manager.get();
	}

}
