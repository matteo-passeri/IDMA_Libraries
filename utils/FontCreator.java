package IDMA_Libraries_Alpha.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class FontCreator {
	/**
	 * ////////////////////////////////////////////////////////////////////////////
	 * - Ability to load in specific fonts at a specific size. Pass in the String
	 *   and the size, and it will be returned.
	 * ////////////////////////////////////////////////////////////////////////////
	 */

	/**
	 * Load the font
	 * 
	 * @param String path, float size
	 * @return Font
	 */
	public static Font initFont(String path, float size) {
		Font font = null;

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path)));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			return null;
		}
		return font;
	}
}
