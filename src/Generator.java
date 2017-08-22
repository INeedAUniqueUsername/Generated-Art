import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import static java.lang.Math.*;
public class Generator extends JPanel {
	public static void main(String[] args) {
		Generator g = new Generator(1920, 1080);
		g.save();
	}
	public Generator(int width, int height) {
		setSize(width, height);
	}
	public void save()
	{
	    BufferedImage bImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
	    Graphics2D cg = bImg.createGraphics();
	    paint(cg);
	    try {
	    	ImageIO.write(bImg, "png", new File("./" + JOptionPane.showInputDialog("Name") + ".png"));
	    } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    }
	    System.exit(0);
	}
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		slashes(g);
	}
	public void slashes(Graphics g) {
		System.out.println("Slashes");
		int interval = 10;
		
		g.setColor(Color.black);
		for(int i = 0; i < getWidth(); i += interval) {
			for(int j = 0; j < getHeight(); j += interval) {
				System.out.println("Slash");
				int length = (int) (50 * random());
				double angle = random() * PI * 2;
				int w = (int) (length * cos(angle));
				int h = (int) (length * sin(angle));
				g.drawLine(
						i - w,
						j - h,
						i + w,
						j + h
						);
			}
		}
	}
	public void squareStatic(Graphics g) {
		squareStatic(g, 100, 10);
	}
	public void squareStatic2(Graphics g) {
		squareStatic(g, 200, 100);
	}
	public void squareStatic3(Graphics g) {
		squareStatic(g, 1000, 100);
	}
	public void squareStatic(Graphics g, int size, int interval) {
		double chance = Math.random();
		double modifier = 2;
		for(int i = 0; i < getWidth(); i += interval) {
			for(int j = 0; j < getHeight(); j += interval) {
				g.setColor(Math.random() < chance ? Color.BLACK : Color.WHITE);
				g.drawRect(i, j, (int) (Math.random() * size), (int) (Math.random() * size));
				if(Math.random() * modifier < chance + modifier - 1) {
					chance = Math.random();
				}
			}
		}
	}
}
