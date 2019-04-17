package radiant;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JOptionPane;

import generation.Effect;
import generation.GifSequenceWriter;
import generation.Help;

public class RadiantGate {
	public static void main(String[] args) {
		Gate();
	}
	public static void Gate() {
		try {
			GifSequenceWriter capture = Help.createGifWriter("./RadiantGate");
			
			int width = 384; //256
			int height = 384; //256
			RadiantGate effect = new RadiantGate(width, height);
			int i = 0;
			while(effect.moreFramesLeft()) {
				i++;
				effect.update();
				
				BufferedImage img = Help.createImage(width, height);
				Graphics2D g = img.createGraphics();
				effect.paint(g);
				ImageIO.write(img, "png", new File("./RadiantGate/Colors/" + i + ".png"));
				
				ImageIO.write(Help.createMask(img), "bmp", new File("./RadiantGate/Mask/" + i + ".bmp"));
				
				capture.writeToSequence(img);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private int width, height;
	private int tick = 0;
	private List<Effect> particles = new ArrayList<>();
	public RadiantGate(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public boolean moreFramesLeft() {
		return tick < 100;
	}
	public void update() {
		tick++;
		/*
		if(tick == 2)
			for(int i = 0; i < 10; i++)
				addNumber(255, 16);
		*/
		
		if(tick < 45) {
			for(int i = 0; i < 25; i++)
				addSquare(153 + (int) (Math.random()*51), 8*tick/9 + 16, 3);
		}
		for(Effect p : particles) {
			p.update();
		}
	}
	public void paint(Graphics2D g) {
		for(Effect p : particles) {
			p.paint(g);
		}
	}
	void addSquare(int opacity, int size, int layers) {
		
		int centerX = width/2;
		int centerY = height/2;
		
		int width = this.width;
		int height = this.height;
		
		Point pos = new Point((int) (Math.random()*width*2) - width, (int) (Math.random()*height*2) - height);
		
		while(Math.pow(pos.x - centerX, 2) + Math.pow(pos.y - centerY, 2) > 128*128) {
			pos = new Point((int) (Math.random()*width*2) - width, (int) (Math.random()*height*2) - height);
		}

		for(double i = 0; i < size; i += size/layers) {
			ShrinkSquare shrinkSquare = new ShrinkSquare(pos, (int) (i/size * opacity), (int) i);
			particles.add(new GrowShrinkSquare(shrinkSquare, shrinkSquare.getOpacity()/5, shrinkSquare.getSize()/5));
		}
	}
}