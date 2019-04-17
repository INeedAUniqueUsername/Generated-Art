package generation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import radiant.RadiantCarapace;
import radiant.RadiantSpace;

public class Main {
	public static void main(String[] args) {
		GraySpace();
	}
	public static void fill(BufferedImage i, Color c) {
		Graphics g = i.getGraphics();
		g.setColor(c);
		g.fillRect(0, 0, i.getWidth(), i.getHeight());
	}
	public static void RadiantCore() {
		int width = 512, height = 512;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		fill(image, new Color(0));
		Color orange = new Color(0xff, 0xb9, 0x00, 51);
		g.setColor(orange);
		for(int x = -16; x < width; x++) {
			for(int y = -16; y < height; y++) {
				if((int) (Math.random()*25) == 0) {
					g.fillRect(x, y, 16, 16);
				}
			}
		}
		Help.writeImage("./RadiantCore", image);
	}
	public static void RadiantRays() {
		int width = 512, height = 512;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		Color orange = new Color(0xff, 0xb9, 0x00, 51);
		g.setColor(orange);
		
		/*
		new Shake(new Point(width/2, 0), new Point(width/2, height), 16, 4, new Random()).paint(g);
		new Shake(new Point(width/3, 0), new Point(width/3, height), 16, 4, new Random()).paint(g);
		*/
		fill(image, new Color(0));
		int centerX = width/2;
		for(int x = 0; x < width; x++) {
			/*
			if((int) (Math.abs(centerX - x) * Math.random()/4) == 0) {
				new Shake(new Point(x, 0), new Point(x, height), 16, 8, new Random()).paint(g);
			}
			*/
			new Shake(new Point(x, 0), new Point(x, height), (int) (Math.random() * (12 - Math.abs(centerX - x)/16)), 8, new Random()).paint(g);
		}
		
		Help.writeImage("./RadiantRays", image);
	}
	
	public static void GraySpace() {
		BufferedImage image = new BufferedImage(2048, 2048, BufferedImage.TYPE_INT_ARGB);
		Main.fill(image, new Color(0));
		new RadiantSpace().paint(image);
		new LuminousSpace().paint(image);
		Help.writeImage("./Grayspace", image);
	}
	public static void line(BufferedImage i, Point pos, double direction) {
		int magnitude = (i.getWidth() + i.getHeight());
		i.getGraphics().drawLine(pos.x, pos.y, pos.x + (int) (magnitude * Math.cos(direction)), pos.y + (int) (magnitude * Math.sin(direction)));
	}
}
class LuminousSpace {
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