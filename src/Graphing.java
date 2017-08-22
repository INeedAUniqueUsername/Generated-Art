import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Graphing extends JPanel {
	private final static int WIDTH = 1920; //1720;	//255
	private final static int HEIGHT = 1080; //1080;	//255
	public static void main(String[] args) {
		Graphing g = new Graphing();
		g.save();
	}
	public Graphing() {
		setSize(WIDTH, HEIGHT);
	}
	/*
	private void createPixels(Color base, int row, int column) {
		if(row > 0 && column > 0 && row < pixels.length && column < pixels[row].length && pixels[row][column] == null) {
			Color c = new Color(generator.getRed(row, column), generator.getGreen(row, column), generator.getBlue(row, column), generator.getAlpha(row, column));
			pixels[row][column] = c;
			createPixels(c, row, column+1);
			createPixels(c, row, column-1);
			createPixels(c, row+1, column);
			createPixels(c, row-1, column);
		}
	}
	*/
	/*
	public void paint(Graphics g) {
		System.out.println("Paint");
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for(int i = 0; i < WIDTH; i++) {
			for(int j = 0; j < HEIGHT; j++) {
				g.setColor(new Color(getRed(i, j), getGreen(i, j), getBlue(i, j), getAlpha(i, j)));
				g.drawRect(i, j, 1, 1);
			}
		}
		/*
		for(int i = 0; i < pixels.length; i++) {
			for(int j = 0; j < pixels[i].length; j++) {
				g.setColor(pixels[i][j]);
				g.drawRect(i, j, 1, 1);
			}
		}
		*//*
		
		System.out.println("Painted");
	}
	*/
	
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Grid pattern
		double chance = Math.random();
		double modifier = 2;
		int w = 100;
		int h = 100;
		int interval = 10;
		for(int i = 0; i < WIDTH; i += interval) {
			for(int j = 0; j < HEIGHT; j += interval) {
				g.setColor(Math.random() < chance ? Color.BLACK : Color.WHITE);
				g.drawRect(i, j, (int) (Math.random() * w), (int) (Math.random() * h));
				if(Math.random() * modifier < chance + modifier - 1) {
					chance = Math.random();
				}
			}
		}
	}
	public static int mod(int x, int m) {
		while(x>m) {
			x -= m;
		}
		while(x<0) {
			x += m;
		}
		return x;
	}
	public static int getRed(int x, int y) {
		return x%255;
		//return mod(x*2-y, 255);
	}
	public static int getGreen(int x, int y) {
		return y%255;
	}
	public static int getBlue(int x, int y) {
		return (x+y)%255;
	}
	public static int getAlpha(int x, int y) {
		return random(x+y)%255;
	}
	public static int random(int max) {
		return (int) (Math.random()*max);
	}
	public void save()
	{
		System.out.println("Saving");
	    BufferedImage bImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
	    Graphics2D cg = bImg.createGraphics();
	    paint(cg);
	    try {
	    	ImageIO.write(bImg, "png", new File("./" + JOptionPane.showInputDialog("Name") + ".png"));
	    } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    }
	    System.out.println("Saved");
	}
}
