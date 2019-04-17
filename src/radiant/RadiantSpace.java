package radiant;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import generation.Generator;
import generation.Help;
import generation.Main;

public class RadiantSpace implements Generator {
	public static void main(String[] args) {
		BufferedImage image = new BufferedImage(2048, 2048, BufferedImage.TYPE_INT_ARGB);
		Main.fill(image, new Color(0));
		new RadiantCarapace().paint(image);
		Help.writeImage("./RadiantSpace", image);
	}
	public void paint(BufferedImage image) {
		int width = image.getWidth(), height = image.getHeight();
		Graphics2D g = image.createGraphics();
		//Color orange = new Color(0xf4, 0xb9, 0x00, 51);
		g.setColor(Radiant.ORANGE_51);
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(Math.random() < 0.1 / 5) {
					g.drawRect(x, y, (int) (Math.random()*8) + 8, (int) (Math.random()*8) + 8);
				}
			}
		}
	}
}