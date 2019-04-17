package generation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import luminous.LuminousRing;
import radiant.RadiantRing;

public class GrayRing {

	public static void main(String[] args) {
		Ring();
	}
	public static void Ring() {
		try {
			GifSequenceWriter capture = Help.createGifWriter("./GrayRing");
			
			int width = 1024; //256
			int height = 1024; //256
			LuminousRing luminous = new LuminousRing(width, height);
			RadiantRing radiant = new RadiantRing(width, height);
			
			int i = 0;
			while(luminous.moreFramesLeft() || radiant.moreFramesLeft()) {
				i++;
				luminous.update();
				radiant.update();
				
				BufferedImage img = Help.createImage(width, height);
				Graphics2D g = img.createGraphics();
				luminous.paint(g);
				radiant.paint(g);
				ImageIO.write(img, "png", new File("./GrayRing/Color/" + i + ".png"));
				
				ImageIO.write(Help.createMask(img), "bmp", new File("./GrayRing/Mask/" + i + ".bmp"));
				
				capture.writeToSequence(img);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
