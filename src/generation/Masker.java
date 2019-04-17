package generation;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Masker {
	public static void main(String[] args) {
		try {
			ImageIO.write(process(
					ImageIO.read(new File(JOptionPane.showInputDialog("Color"))),
					ImageIO.read(new File(JOptionPane.showInputDialog("Mask")))
					), "png", new File(JOptionPane.showInputDialog("Output")));
		} catch(HeadlessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static BufferedImage process(BufferedImage color, BufferedImage mask) {
		int width = mask.getWidth();
		int height = mask.getHeight();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Color m = new Color(mask.getRGB(x, y));
				int alpha = (m.getRed() + m.getGreen() + m.getBlue())/3;
				Color c = new Color(color.getRGB(x, y));
				//result.setRGB(x, y, compositeColor(new Color(255, 255, 255, 255 - alpha), new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha)));
			}
		}
		return result;
	}
	
	
	// a2 over a1
	private static int compositeAlpha(int a1, int a2) {
	    return 255 - ((255 - a2) * (255 - a1)) / 255;
	}


	// For a single R/G/B component. a = precomputed compositeAlpha(a1, a2)
	private static int compositeColorComponent(int c1, int a1, int c2, int a2, int a) {
	    // Handle the singular case of both layers fully transparent.
	    if (a == 0) {
	        return 0x00;
	    }
	    return (((255 * c2 * a2) + (c1 * a1 * (255 - a2))) / a) / 255;
	}

	// argb2 over argb1. No range checking.
	public static int compositeColor(Color argb1, Color argb2) {
	    final int a1 = argb1.getAlpha();
	    final int a2 = argb2.getAlpha();

	    final int a = compositeAlpha(a1, a2);

	    final int r = compositeColorComponent(argb1.getRed(), a1,   
	    		argb2.getRed(), a2, a);
	    final int g = compositeColorComponent(argb1.getGreen(), a1, 
	    		argb2.getGreen(), a2, a);
	    final int b = compositeColorComponent(argb1.getBlue(), a1, 
	    		argb2.getBlue(), a2, a);

	    return new Color(r, g, b, a).getRGB();
	}
}