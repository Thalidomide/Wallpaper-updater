package olj.wallpaperupdater.gui;

import olj.wallpaperupdater.entities.ImageFile;
import olj.wallpaperupdater.gui.components.Label;
import olj.wallpaperupdater.gui.components.Panel;
import olj.wallpaperupdater.gui.util.GuiUtil;
import olj.wallpaperupdater.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Olav Jensen
 * @since 09.apr.2010
 */
public class ImageUnitsResultPanel extends Panel {

	private Panel resultPanel;
	private Label resultLabel;
	private List<ImageUnitPanel> unitPanels = new ArrayList<ImageUnitPanel>();

	public ImageUnitsResultPanel() {
		super(new BorderLayout());

		resultLabel = new Label("No images loaded.");
		resultPanel = new Panel();
		resultPanel.setLayout(new GridBagLayout());

        Panel wrapper = new Panel();
        wrapper.add(resultPanel);

		JScrollPane scroll = new JScrollPane(wrapper);
		scroll.setAutoscrolls(true);

		Panel resultLabelPanel = new Panel();
		resultLabelPanel.add(resultLabel);

		add(resultLabelPanel, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}

	public synchronized void setImageFiles(List<ImageFile> files) {
		unitPanels.clear();

		int index = 0;
		for (ImageFile file : files) {
			Color color = index % 2 == 0 ? Constants.BACKGROUND_LIGHT : Constants.BACKGROUND_LIGHTER;
			ImageUnitPanel unitPanel = new ImageUnitPanel(file, color, index);
			unitPanels.add(unitPanel);
			index++;
		}

		updateResultLabel(files);

		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		resultPanel.removeAll();

        GridBagConstraints constraints = GuiUtil.getDefaultGridBagConstraints();
        constraints.gridwidth = 2;
        int gridY = 0;

        for (ImageUnitPanel unitPanel : unitPanels) {
            constraints.gridy = gridY++;
            constraints.gridx = 0;
            constraints.weightx = 0;
            resultPanel.add(unitPanel.getUseImage(), constraints);
            constraints.gridx = 1;
            constraints.weightx = 1;
            resultPanel.add(unitPanel.getContent(), constraints);
		}
		resultPanel.repaint();
		validate();
	}

	public ImageFile getRandomImageUnit() {
		List<ImageFile> activeImages = new ArrayList<ImageFile>();

		for (ImageUnitPanel unitPanel : unitPanels) {
			unitPanel.addImageIfActive(activeImages);
		}

        if (activeImages.isEmpty()) {
            return null;
        }

        int randomIndex = (int) (Math.random() * activeImages.size());

		return activeImages.get(randomIndex);
	}

	private void updateResultLabel(List<ImageFile> files) {
		String text;

		if (files.isEmpty()) {
			text = "No images found.";
		} else {
			text = "Images found: " + files.size() + ".";
		}

		resultLabel.setText(text);
	}
}

