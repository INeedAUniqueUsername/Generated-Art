package generation;
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
import luminous.LuminousGate;
import luminous.LuminousRing;
import radiant.Radiant;
import radiant.RadiantGate;
import radiant.RadiantRing;

public class GrayGate {
	public static void main(String[] args) {
		Gate();
	}
	public static void Gate() {
		try {
			GifSequenceWriter capture = Help.createGifWriter("./GrayGate");
			
			int width = 384; //256
			int height = 384; //256
			LuminousGate luminous = new LuminousGate(width, height);
			RadiantGate radiant = new RadiantGate(width, height);
			
			int i = 0;
			while(luminous.moreFramesLeft() || radiant.moreFramesLeft()) {
				i++;
				luminous.update();
				radiant.update();
				
				BufferedImage img = Help.createImage(width, height);
				Graphics2D g = img.createGraphics();
				luminous.paint(g);
				radiant.paint(g);
				ImageIO.write(img, "png", new File("./GrayGate/Color/" + i + ".png"));
				
				ImageIO.write(Help.createMask(img), "bmp", new File("./GrayGate/Mask/" + i + ".bmp"));
				
				capture.writeToSequence(img);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}