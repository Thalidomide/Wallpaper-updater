package olj.wallpaperupdater.gui;

import olj.wallpaperupdater.engine.ImageSaverLoader;
import olj.wallpaperupdater.engine.modes.util.ImageUtil;
import olj.wallpaperupdater.entities.ImageFile;
import olj.wallpaperupdater.gui.components.Panel;
import olj.wallpaperupdater.util.Constants;
import olj.wallpaperupdater.util.Manager;
import olj.wallpaperupdater.util.Util;
import olj.wallpaperupdater.work.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * @author Olav Jensen
 * @since 08.apr.2010
 */
public class MainFrame extends JFrame implements ButtonPanelListener, StatusListener, MessageListener, SettingsListener,
		ImageTransferHandlerListener {

	private StatusPanel statusPanel;
	private ImageUnitsResultPanel resultPanel;
	private ButtonPanel buttonPanel;

	private Panel content;

	private ViewMode viewMode;
	private File[] selectedFilesOrDirectory;

	public MainFrame() throws HeadlessException {
		setSize(Constants.SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(0, 0);
        setTitle("Image merger (v " + Constants.VERSION + ")");

		setTransferHandler(new ImageTransferHandler(this));

		setupGui();

		Manager.get().setMessageListener(this);
		Manager.get().setStatusHandler(new WorkHandler(this));
		Manager.get().setImageObserver(this);

		setVisible(true);
	}

	private void setupGui() {
		Panel mainContent = new Panel(new BorderLayout());

		statusPanel = new StatusPanel();
		resultPanel = new ImageUnitsResultPanel();
		buttonPanel = new ButtonPanel(this);
		SettingsPanel settingsContent = new SettingsPanel(this);

		mainContent.add(buttonPanel, BorderLayout.NORTH);
		mainContent.add(resultPanel, BorderLayout.CENTER);
		mainContent.add(statusPanel, BorderLayout.SOUTH);

		content = new Panel(new CardLayout());
		getContentPane().add(content);

		content.add(mainContent, ViewMode.main.name());
		content.add(settingsContent, ViewMode.settings.name());

		showMainView();
	}

	@Override
	public void openFolder() {
		final JFileChooser fileChooser = Util.getImageFileChooser();

		int action = fileChooser.showDialog(MainFrame.this, "Open");

		if (action == JFileChooser.APPROVE_OPTION) {
			openFiles(fileChooser.getSelectedFiles());
		}
	}

	@Override
	public void openFiles(final File[] files) {
		Work work = new Work() {

			@Override
			public void executeWork() {
				selectedFilesOrDirectory = files;
				loadImagesFromDirectory();
			}
		};

		Manager.get().getWorkHandler().doWork(new WorkPackage("Scan folder for images", work));
	}

	@Override
	public void saveNewImage() {
		if (selectedFilesOrDirectory == null || selectedFilesOrDirectory.length == 0) {
			throw new IllegalStateException("There are not any files selected!");
		}

		File file = selectedFilesOrDirectory[0];
		String currentPath = isSelectedDirectory() ? file.getAbsolutePath() : file.getParent();

		String savePath = currentPath + "/" + "Wallpapers";

        System.out.println("Lagre fila til: " + savePath);


		ImageSaverLoader.saveImage(resultPanel.getRandomImageUnit(), savePath);
	}

	@Override
	public void addMessage(String message) {
		statusPanel.addMessage(message);
	}

	@Override
	public void statusChanged(StatusType statusType, String description) {
		statusPanel.updateStatus(statusType, description);
		buttonPanel.setButtonsEnabled(statusType.isGuiEnabled());
	}

	@Override
	public void showSettings() {
		viewMode = ViewMode.settings;
		updateView();
	}

	@Override
	public void saveSettings() {
		if (selectedFilesOrDirectory != null) {
			loadImagesFromDirectory();
		}
		showMainView();
	}

	@Override
	public void cancelSettings() {
		showMainView();
	}

	private void loadImagesFromDirectory() {
		File[] files = isSelectedDirectory() ? selectedFilesOrDirectory[0].listFiles() : selectedFilesOrDirectory;
		List<ImageFile> imageFiles = ImageUtil.getImageFiles(files);

		resultPanel.setImageFiles(imageFiles);
		buttonPanel.setSaveButtonEnabled(!imageFiles.isEmpty());
	}

	private boolean isSelectedDirectory() {
		return selectedFilesOrDirectory.length == 1 && selectedFilesOrDirectory[0].isDirectory();
	}

	private void showMainView() {
		viewMode = ViewMode.main;
		updateView();
	}

	private void updateView() {
		CardLayout cardLayout = (CardLayout) content.getLayout();
		cardLayout.show(content, viewMode.name());
	}
}
