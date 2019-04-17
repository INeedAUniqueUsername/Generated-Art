package radiant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import generation.Generator;
import generation.Help;
import generation.Main;
import generation.Shake;
public class RadiantPoints implements Generator {
	public static void main(String[] args) {
		BufferedImage image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
		new RadiantPoints().paint(image);
		Help.writeImage("./Points", image);
		
		BufferedImage image2 = new BufferedImage(512*2, 512*2, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image2.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.drawImage(image, 512, 0, null);
		g.drawImage(image, 0, 512, null);
		g.drawImage(image, 512, 512, null);
		Help.writeImage("./Points/Repeating", image2);
	}
	public RadiantPoints() {
		
	}
	@Override
	public void paint(BufferedImage image) {
		Main.fill(image, new Color(0));
		int width = image.getWidth(), height = image.getHeight();
		int min_distance = 64;
		List<Point> points = new ArrayList<Point>();
		int tries = 50;
		AddPoint: while(tries > 0) {
			Point next = new Point((int) (Math.random() * width), (int) (Math.random() * height));
			for(Point other : points) {
				double distance = next.distance(other);
				if(distance < min_distance) {
					tries--;
					continue AddPoint;
				}
			}
			tries = 50;
			points.add(next);
		}
		System.out.println(points.size());
		Graphics g = image.getGraphics();
		//Color orange = new Color(0xff, 0xb9, 0x00, 102);
		g.setColor(Radiant.ORANGE_102);
		ConnectPoints: for(int i = 0; i < points.size(); i++) {
			Point start = points.get(i);
			for(int j = i+1; j < points.size(); j++) {
				Point p = points.get(j);
				for(Point pWrap : new Point[] {
						/*new Point(p.x - width, p.y - height),*/ new Point(p.x, p.y - height), /*new Point(p.x + width, p.y - height),*/
						new Point(p.x - width, p.y), new Point(p.x, p.y), new Point(p.x + width, p.y),
						/*new Point(p.x - width, p.y + height),*/ new Point(p.x, p.y + height), /*new Point(p.x + width, p.y + height),*/
				}) {
					double distance = pWrap.distance(start);
					if(distance < 128) {
						int times = 32;
						double maxRadius = 16;
						long seed = System.currentTimeMillis();
						
						//Draw the line from the other ends to get rid of the seam
						if(!pWrap.equals(p)) {
							
							//Halve the alpha
							//orange = new Color(orange.getRed(), orange.getGreen(), orange.getBlue(), orange.getAlpha()/2);
							
							Point offset = new Point(p.x - pWrap.x, p.y - pWrap.y);
							/*
							if(offset.x != 0) {
								Point startWrap = new Point(start.x + offset.x, start.y);
								new Shake(startWrap, p, 64, 16, new Random(seed)).paint(g);
							}
							if(offset.y != 0) {
								Point startWrap = new Point(start.x, start.y + offset.y);
								new Shake(startWrap, p, 64, 16, new Random(seed)).paint(g);
							}
							if(offset.x != 0 && offset.y != 0) {
								Point startWrap = new Point(start.x + offset.x, start.y + offset.y);
								new Shake(startWrap, p, 64, 16, new Random(seed)).paint(g);
							}
							*/
							
							Point startWrap = new Point(start.x + offset.x, start.y + offset.y);
							//g.drawLine(startWrap.x, startWrap.y, p.x, p.y);
							new Shake(startWrap, p, times, maxRadius, new Random(seed)).paint(g);
							
						}
						
						//g.drawLine(start.x, start.y, pWrap.x, pWrap.y);
						new Shake(start, pWrap, times, maxRadius, new Random(seed)).paint(g);
						
						continue ConnectPoints;
					}
				}
			}
		}
	}
}