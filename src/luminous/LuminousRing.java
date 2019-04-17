package luminous;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import generation.Effect;
import generation.GifSequenceWriter;
import generation.Help;

public class LuminousRing {
	public static void main(String[] args) {
		Ring();
	}
	public static void Ring() {
		try {
			GifSequenceWriter capture = Help.createGifWriter("./LuminousRing");
			
			int width = 1024; //256
			int height = 1024; //256
			LuminousRing effect = new LuminousRing(width, height);
			int i = 0;
			while(effect.moreFramesLeft()) {
				i++;
				effect.update();
				
				BufferedImage img = Help.createImage(width, height);
				Graphics2D g = img.createGraphics();
				effect.paint(g);
				ImageIO.write(img, "png", new File("./LuminousRing/Color/" + i + ".png"));
				
				ImageIO.write(Help.createMask(img), "bmp", new File("./LuminousRing/Mask/" + i + ".bmp"));
				
				capture.writeToSequence(img);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int width, height;
	private int tick = 0;
	private List<Effect> particles = new ArrayList<>();
	public LuminousRing(int width, int height) {
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
		
		//tick/2
		if(tick < 36) {
			addSquare(tick*12, 16 + tick, 153 + (int) (51*Math.random()), 8*tick/9 + 16, 3);
				
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
	void addSquare(int radius, int count, int opacity, int size, int layers) {
		int centerX = width/2;
		int centerY = height/2 + size/2;
		
		int width = this.width;
		int height = this.height;
		
		double angleInc = 2*Math.PI / count;
		for(int i = 0; i < count; i++) {
			double angle = angleInc*i;
			Point pos = new Point(centerX + (int) (radius*Math.cos(angle)), centerY + (int) (radius*Math.sin(angle)));
			
			for(double j = 0; j < size; j += size/layers) {
				ShrinkSquare shrinkSquare = new ShrinkSquare(pos, (int) (j/size * opacity), (int) j);
				particles.add(new GrowShrinkSquare(shrinkSquare, shrinkSquare.getOpacity()/layers, shrinkSquare.getSize()/layers));
			}
		}
	}
}