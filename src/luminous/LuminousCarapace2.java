package luminous;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

import generation.Generator;
import generation.Help;
import generation.Main;
import generation.Shake;

public class LuminousCarapace2 implements Generator {
	public static void main(String[] args) {
		LuminousCarapace2();
	}
	public static void LuminousCarapace2() {
		BufferedImage image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
		Main.fill(image, new Color(0));
		new LuminousCarapace2().paint(image);
		Help.writeImage("./LuminousCarapace2", image);
	}
	public void paint(BufferedImage image) {
		int width = image.getWidth(), height = image.getHeight();
		Graphics2D g = image.createGraphics();
		//Color orange = new Color(0xf4, 0xb9, 0x00, 51);
		g.setColor(new Color(0, 0xff, 0, 34));
		
		int inc = 24;
		Point displacement = new Point(inc + (int) (Math.random()*inc), inc + (int) (Math.random()*inc));
		for(int x = -inc; x < width; x += inc + Math.random()*inc) {
			for(int y = -inc; y < height; y += inc + Math.random()*inc) {
				//int xInc = 0, yInc = 0;
				int xInc = (int) (Math.random()*inc), yInc = (int) (Math.random()*inc);
				for(Point p : new Point[] {
						new Point(-width, -height), new Point(0, -height), new Point(width, -height),
						new Point(-width, 0), new Point(0, 0), new Point(width, 0),
						new Point(-width, height), new Point(0, height), new Point(width, height)
						}) {
					new Shake(new Point(xInc + x + p.x, yInc + y + p.y), new Point(xInc + x + displacement.x + p.x, yInc + y + displacement.y + p.y), 40, 16, new Random()).paint(g);
				}
				
			}
		}
	}
}