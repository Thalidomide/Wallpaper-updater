package olj.wallpaperupdater.gui;

import olj.wallpaperupdater.entities.ImageFile;
import olj.wallpaperupdater.entities.WallpaperScreen;
import olj.wallpaperupdater.gui.components.CheckBox;
import olj.wallpaperupdater.gui.components.Label;
import olj.wallpaperupdater.gui.components.Panel;
import olj.wallpaperupdater.util.Constants;
import olj.wallpaperupdater.util.Manager;
import olj.wallpaperupdater.work.Work;
import olj.wallpaperupdater.work.WorkPackage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Olav Jensen
 * @since 09.apr.2010
 */
public class ImageUnitPanel {

	private final ImageFile file;
	private final Color background;
	private CheckBox useImage;
    private Panel contentCheckBox;
    private Panel content;
	private List<Label> labels = new ArrayList<Label>();

	public ImageUnitPanel(final ImageFile file, Color background, int index) {
		super();
		this.file = file;
		this.background = background;

        contentCheckBox = getPanel();
        content = getPanel();

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		addTitle(index);
        addUseImage();
		addImages();
	}

    public void addImageIfActive(List<ImageFile> files) {
		if (useImage.isSelected()) {
			files.add(file);
		}
	}

	private void addTitle(int index) {
		Label titleLabel = getLabel((index + 1) + ". image file: " + file.getName());
		titleLabel.setFont(Constants.HEADER_2);
		titleLabel.addMouseListener(getMouseListener("Generating preview for " + file.getName(), new Work() {

			@Override
			public void executeWork() {
                BufferedImage previewImage = file.getImage();
                int index = 1;

                for (WallpaperScreen screen : Manager.get().getEngineSettings().getScreens()) {
                    int x = screen.getX();
                    int y = screen.getY();
                    int x2 = x + screen.getWidth();
                    int y2 = y + screen.getHeight();

                    int xMid = x + screen.getWidth() / 2;
                    int yMid = y + screen.getHeight() / 2;

                    int backgroundSize = 40;

                    Graphics2D g2 = (Graphics2D) previewImage.getGraphics();

                    g2.setColor(new Color(0, 0, 0, 100));
                    g2.fillOval(xMid - backgroundSize, yMid - backgroundSize, backgroundSize * 2, backgroundSize * 2);

                    g2.setStroke(new BasicStroke(5));
                    g2.setColor(Color.black);

                    g2.drawRect(x, y, x2, y2);
                    g2.setFont(new Font("Verdana", Font.PLAIN, 45));
                    g2.drawString(index + "", xMid - 15, yMid + 15);

                    index++;
                }

				Manager.get().showPreview(previewImage, "Showing preview of " + file.getName());
			}
		}));

		content.add(titleLabel);
	}

    private void addUseImage() {
        useImage = new CheckBox();
		useImage.setBackground(background);
		useImage.setSelected(true);
		useImage.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateLabels();
			}
		});
        contentCheckBox.add(useImage);
    }

	private void addImages() {
        final String fileName = file.getName();
        Label label = getLabel(fileName);
        label.addMouseListener(getMouseListener(null, new Work() {

            @Override
            public void executeWork() {
                Manager.get().showPreview(file.getImage(), fileName);
            }
        }));
        addWrapped(label);
	}

    public Panel getUseImage() {
        return contentCheckBox;
    }

    public Panel getContent() {
        return content;
    }

    private void addWrapped(Component component) {
		Panel panel = getPanel();
		panel.add(component);
		content.add(panel);
	}

	private Panel getPanel() {
		Panel panel = new Panel();
		panel.setBackground(background);

		return panel;
	}

	private Label getLabel(String text) {
		Label label = new Label(text);
		labels.add(label);
		return label;
	}

	private void updateLabels() {
		for (Label label : labels) {
			label.setActive(useImage.isSelected());
		}
	}

	private MouseAdapter getMouseListener(final String description, final Work work) {
		return new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Manager.get().getWorkHandler().doWork(new WorkPackage(description, work));
			}
		};
	}
}
