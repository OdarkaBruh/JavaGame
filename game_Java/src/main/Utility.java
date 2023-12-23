package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Utility {
	public BufferedImage scaleImage(BufferedImage orig, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, orig.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(orig, 0, 0, width, height, null);
		g2.dispose();
		return scaledImage;
	}
}
