package generation;

public class Point {
	private double x, y;
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Point(Point...points) {
		x = 0;
		y = 0;
		for(Point p : points) {
			x += p.x;
			y += p.y;
		}
	}
	public double x() { return x; }
	public int xFloor() { return (int) x; }
	public double y() { return y; }
	public int yFloor() { return (int) y; }
	public Point sum(Point p) { return new Point(x + p.x, y + p.y); }
	public Point difference(Point p) { return sum(p.scale(-1)); }
	public Point scale(double scale) { return new Point(x * scale, y * scale); }
	public Point midpoint(Point p) { return sum(p).scale(0.5); }
	public Point bottomLeft(Point p) { return new Point(Math.min(x, p.x), Math.min(y, p.y)); }
	public Point bottomRight(Point p) { return new Point(Math.max(x, p.x), Math.min(y, p.y)); }
	public Point topLeft(Point p) { return new Point(Math.min(x, p.x), Math.max(y, p.y)); }
	public Point topRight(Point p) { return new Point(Math.max(x, p.x), Math.max(y, p.y)); }
	public Point rotate(Point p, double theta) {
		double distance = distance(p);
		double angle = difference(p).angle();
		angle += theta;
		return polar(angle, distance);
	};
	public Point flip(Point p) {
		return rotate(p, Math.PI);
	}
	public double angle() { return Math.atan2(y, x); }
	public double magnitude() { return Math.sqrt(x * x + y * y); }
	public double distance(Point p) {
		return difference(p).magnitude();
	}
	public Point polar(double angle, double distance) {
		return new Point(distance * Math.cos(angle), distance * Math.sin(angle));
	}
	public Point clone() { return new Point(x, y); }
}
