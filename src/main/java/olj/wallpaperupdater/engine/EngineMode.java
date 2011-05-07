package olj.wallpaperupdater.engine;

import olj.wallpaperupdater.engine.modes.ImageEngine;
import olj.wallpaperupdater.engine.modes.ImageManipulator;
import olj.wallpaperupdater.engine.modes.ImageMerger;
import olj.wallpaperupdater.engine.modes.ImageRestructure;

/**
 * @author Olav Jensen
 * @since 11.apr.2010
 */
public enum EngineMode {

	manipulate("Manipulate", ImageManipulator.class), composite("Merge", ImageMerger.class), restructure("Restructure", ImageRestructure.class);

	private final String text;
	private final Class<? extends ImageEngine> engineClass;

	EngineMode(String text, Class<? extends ImageEngine> engineClass) {
		this.text = text;
		this.engineClass = engineClass;
	}

	public String getText() {
		return text;
	}

	public Class<? extends ImageEngine> getEngineClass() {
		return engineClass;
	}
}
