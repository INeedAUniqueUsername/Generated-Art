package generation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Flip {
	public static void main(String[] args) {
		new JFrame() {{
				setExtendedState(JFrame.MAXIMIZED_BOTH);
				setVisible(true);
				BufferedImage b = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB) {{
					Graphics2D g = createGraphics();
					g.setBackground(new Color(0, 0, 0, 0));
					g.clearRect(0, 0, getWidth(), getHeight());
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, getWidth(), getHeight());
				}};
				Flip f = new Flip(b);
				add(new JPanel() {
					public void paintComponent(Graphics g) {
						super.paintComponent(g);
						Graphics2D g2D = (Graphics2D) g;
						g2D.setBackground(new Color(255, 255, 255, 0));
						g2D.clearRect(0, 0, getWidth(), getHeight());
						g2D.setColor(Color.BLACK);
						//g2D.fillRect(0, 0, 100, 100);
						f.update();
						f.paint(g2D);
						repaint();
					}
				});
				pack();
			}
		};
	}
	
	private BufferedImage source;
	private double progress;
	public Flip(BufferedImage source) {
		this.source = source;
		progress = 0;
	}
	public void update() {
		progress += 0.001;
	}
	public void setProgress(double progress) {
		this.progress = progress;
	}
	public void setSource(BufferedImage source) {
		this.source = source;
	}
	public void paint(Graphics g) {
		double heightMultiplier = Math.cos(progress * Math.PI * 2);
		int frameHeight = (int) (source.getHeight() * Math.abs(heightMultiplier));
		int top = (int) (source.getHeight() * (1 - Math.abs(heightMultiplier)))/2;
		BufferedImage frame = new BufferedImage(source.getWidth(), Math.max(Math.abs(frameHeight), 1), BufferedImage.TYPE_INT_ARGB) {{
			Graphics2D g = createGraphics();
			g.scale(1, heightMultiplier);
			g.drawImage(source, 0, heightMultiplier > 0 ? 0 : -getHeight()/2, null);
			g.setColor(Color.RED);
		}};
		
		g.drawImage(frame, 0, top, null);
	}
}
class Triangle {
	private Point vertex1, vertex2, vertex3;
	private Triangle adjacent1, adjacent2, adjacent3;
	public Triangle(Point vertex1, Point vertex2, Point vertex3) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.vertex3 = vertex3;
	}
	private void createAdjacents() {
		Point mid12 = vertex1.midpoint(vertex2);
		Point mid23 = vertex2.midpoint(vertex3);
		Point mid13 = vertex1.midpoint(vertex3);
		adjacent1 = new Triangle(vertex1.flip(mid23), vertex2, vertex3);
		adjacent2 = new Triangle(vertex1, vertex2.flip(mid13), vertex3);
		adjacent3 = new Triangle(vertex1, vertex2, vertex3.flip(mid12));
	}
	public Point getBottomLeft() {
		return vertex1.bottomLeft(vertex2).bottomLeft(vertex3);
	}
	
}