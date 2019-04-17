package generation;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Shake extends Line{
	int times;
	double maxRadius;
	Random random;
	public Shake(Point start, Point end, int times, double maxRadius, Random random) {
		super(start, end);
		this.times = times;
		this.maxRadius = maxRadius;
		this.random = random;
	}
	public void paint(Graphics g) {
		for(int i = 0; i < times; i++) {
			double radius = random.nextDouble()*maxRadius;
			double angle = random.nextDouble()*Math.PI*2;
			Point startOffset = new Point(start.x + (int) (radius*Math.cos(angle)), start.y + (int) (radius*Math.sin(angle)));
			Point endOffset = new Point(end.x + (int) (radius*Math.cos(angle)), end.y + (int) (radius*Math.sin(angle)));
			g.drawLine(startOffset.x, startOffset.y, endOffset.x, endOffset.y);
		}
	}
}
/*
class RadiantCarapace2 implements Generator {
	public void paint(BufferedImage image) {
		Main.fill(image, new Color(0));
		int width = image.getWidth(), height = image.getHeight();
		Graphics2D g = image.createGraphics();
		//Color orange = new Color(0xf4, 0xb9, 0x00, 51);
		Color orange = new Color(0xff, 0xb9, 0x00, 51);
		g.setColor(orange);
		
		
		int[][] grid = new int[width][height];
		for(int i = (int) (Math.random() * 4); i < 10; i++) {
			int[][] inc = new int[width][height];
			for(int x = 0; x < width; x++) {
				for(int y = 0; y < height; y++) {
					for(Point adjacent : new Point[] { new Point(x-1, y-1), new Point(x-1, y), new Point(x-1, y+1), new Point(x, y-1), new Point(x, y+1), new Point(x+1, y-1), new Point(x+1, y), new Point(x+1, y+1)}) {
						
					}
				}
			}
		}
		
	}
}
*/