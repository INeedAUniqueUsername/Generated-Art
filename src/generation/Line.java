package generation;

import java.awt.Graphics;
import java.awt.Point;

class Line {
	public final Point start, end;
	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	public void paint(Graphics g) {
		g.drawLine(start.x, start.y, end.x, end.y);
	}
	/*
	public void shake(Graphics g, int times, double maxRadius) {
		for(int i = 0; i < times; i++) {
			double radius = Math.random()*maxRadius;
			double angle = Math.random()*Math.PI*2;
			Point startOffset = new Point(start.x + (int) (radius*Math.cos(angle)), start.y + (int) (radius*Math.sin(angle)));
			Point endOffset = new Point(end.x + (int) (radius*Math.cos(angle)), end.y + (int) (radius*Math.sin(angle)));
			g.drawLine(startOffset.x, startOffset.y, endOffset.x, endOffset.y);
		}
	}
	*/
}