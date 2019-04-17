package generation;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Splitter {
	//Sprite sheet to individual images
	//Right then down
	public static void main(String[] args) {
		try {
			BufferedImage sheet = ImageIO.read(new File(JOptionPane.showInputDialog("Input")));
			int frameWidth = Integer.parseInt(JOptionPane.showInputDialog("Frame width"));
			int frameHeight = Integer.parseInt(JOptionPane.showInputDialog("Frame height"));
			String output = JOptionPane.showInputDialog("Output");
			/*
			int i = 0;
			for(int x = 0; x < sheet.getWidth(); x += frameWidth) {
				for(int y = 0; y < sheet.getHeight(); y += frameHeight) {
					ImageIO.write(sheet.getSubimage(x, y, frameWidth, frameHeight), "png", new File(String.format("%s%04d", output, i)));
				}
			}
			*/
			ArrayList<BufferedImage> result = process(sheet, frameWidth, frameHeight);
			for(int i = 0; i < result.size(); i++) {
				ImageIO.write(result.get(i), "png", new File(String.format("%s%04d", output, i)));
			}
		} catch (HeadlessException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ArrayList<BufferedImage> process(BufferedImage sheet, int frameWidth, int frameHeight) {
		ArrayList<BufferedImage> result = new ArrayList<>();
		for(int x = 0; x < sheet.getWidth(); x += frameWidth) {
			for(int y = 0; y < sheet.getHeight(); y += frameHeight) {
				result.add(sheet.getSubimage(x, y, frameWidth, frameHeight));
			}
		}
		return result;
	}
}
