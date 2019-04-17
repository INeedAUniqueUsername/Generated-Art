package radiant;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.function.BiFunction;
import java.util.function.Function;

import generation.Generator;
import generation.Help;
import generation.Main;

public class RadiantCarapace implements Generator {
	public static void main(String[] args) {
		RadiantCarapace2();
	}
	public static void RadiantCarapace() {
		BufferedImage image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
		Main.fill(image, new Color(0));
		new RadiantCarapace().paint(image);
		Help.writeImage("./RadiantCarapace", image);
	}
	public static void RadiantCarapace2() {
		BufferedImage image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
		Main.fill(image, new Color(0));
		new RadiantCarapace2().paint(image);
		Help.writeImage("./RadiantCarapace2", image);
	}
	public static void RadiantSpace() {
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
		
		int stroke = 2;
		for(int y = 0; y < height; y++) {
			if(Math.random()*10 < 2) {
				g.fillRect(0, y, width, stroke);
			}
		}
		for(int x = 0; x < width; x++) {
			if(Math.random()*10 < 2) {
				g.fillRect(x, 0, stroke, height);
			}
		}
		g.setColor(Radiant.orange(34));
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(Math.random() * 100 < 4) {
					g.fillRect(x, y, 4, 4);
				}
			}
		}
	}
}
class RadiantCarapace2 implements Generator {
	public void paint(BufferedImage image) {
		int width = image.getWidth(), height = image.getHeight();
		int centerX = width/2, centerY = height/2;
		Graphics2D g = image.createGraphics();
		//Color orange = new Color(0xf4, 0xb9, 0x00, 51);
		g.setColor(Radiant.ORANGE_51);
		
		for(int y = 0; y < height; y++) {
			int difference = Math.abs(y - centerY);
			if(difference/16 + Math.random() * 100 < 20) {
				int stroke = difference/48 + 1;
				g.fillRect(0, y, width, stroke);
			}
		}
		for(int x = 0; x < width; x++) {
			int difference = Math.abs(x - centerX);
			if(difference/16 + Math.random() * 100 < 20) {
				int stroke = difference/48 + 1;
				g.fillRect(x, 0, stroke, height);
			}
		}
		g.setColor(Radiant.orange(34));
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				//int difference = (Math.abs(x-centerX) + Math.abs(y-centerY)/2);
				int difference = (int) Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
				if((difference/16 + Math.random() * 100) < 20) {
					g.fillRect(x, y, difference/48 + 1, difference/48 + 1);
				}
			}
		}
	}
}