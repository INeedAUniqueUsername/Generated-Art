package luminous;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import generation.Help;
import generation.Main;
import radiant.RadiantCarapace;

public class LuminousSpace {
	public static void main(String[] args) {
		int width = 2048, height = 2048;
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Main.fill(image, new Color(0));
		new LuminousSpace().paint(image);
		Help.writeImage("./LuminousSpace", image);
	}
	public void paint(BufferedImage i) {
		int width = i.getWidth(), height = i.getHeight();
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Color c = new Color(i.getRGB(x, y));
				//Chance: 16
				if((int) (Math.random()*64) == 0) {
					Graphics g = i.getGraphics();
					//Alpha: 12
					g.setColor(new Color(0x89, 0xd1, 0x92, 12));
					
					//Size: 16
					g.fillRect(x, y, 8 + (int) (Math.random()*16), 8 + (int) (Math.random()*16));
				}
			}
		}
	}
}
