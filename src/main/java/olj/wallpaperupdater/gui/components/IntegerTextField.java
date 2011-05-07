package olj.wallpaperupdater.gui.components;

import olj.wallpaperupdater.util.Constants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Olav Jensen
 * @since 07.mai.2010
 */
public class IntegerTextField extends TextField {

	private boolean optional = true;
	private Integer min;
	private Integer max;

	public IntegerTextField(Integer value) {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateValidStyle();
			}
		});

        setInteger(value);

		updateValidStyle();
	}

	public Integer getInteger() {
		String text = getText();
		if ("".equals(text)) {
			return null;
		}

		return Integer.parseInt(text);
	}

	public void setInteger(Integer value) {
		super.setText(value != null ? value.toString() : "");
		updateValidStyle();
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * @deprecated use {@link #setInteger(Integer)} instead
	 */
	@Override
	@Deprecated
	public void setText(String text) {
		super.setText(text);
	}

	public boolean isValidInteger() {
		Integer value;
		try {
			value = getInteger();
		} catch (NumberFormatException e) {
			return false;
		}

		if (value != null) {
			if (min != null && min > value || max != null && max < value) {
				return false;
			}
		}

		return value != null || optional;
	}

	private void updateValidStyle() {
		setBackground(isValidInteger() ? Constants.BACKGROUND_INPUT : Constants.BACKGROUND_INPUT_ERROR);
	}
}
