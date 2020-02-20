package IDMA_Libraries_Alpha.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	/**
	 * Load images
	 * 
	 * @param String path
	 * @return sprite
	 * 
	 */

	public static BufferedImage loadImage(String path) {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}
}
