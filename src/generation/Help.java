package generation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

public class Help {
	public static void writeImage(String directory, BufferedImage image) {
	    try {
	    	int n = 0;
	    	File dir = new File(directory);
	    	if(!dir.exists())
	    		dir.mkdirs();
	    	File result;
	    	do {
	    		result = new File(directory + File.separator + n + ".png");
	    		n++;
	    	} while(result.exists());
	    	ImageIO.write(image, "png", result);
	    } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    }
	}
	public static void writeGif(String directory, Iterable<BufferedImage> images) {
		try {
			int n = 0;
	    	File result;
	    	do {
	    		result = new File(directory + File.separator + n + ".png");
	    		n++;
	    	} while(result.exists());
	    	
			ImageOutputStream output = new FileImageOutputStream(result);
			//ImageOutputStream output = new FileImageOutputStream(new File("./LuminousRing/LuminousRing.gif"));
			GifSequenceWriter capture = new GifSequenceWriter(output, BufferedImage.TYPE_INT_ARGB, 1000/30, true);
			for(BufferedImage image : images) {
				capture.writeToSequence(image);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static BufferedImage createImage(int width, int height) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, width, height);
		return img;
	}
	public static BufferedImage createMask(BufferedImage i) {
		int width = i.getWidth(), height = i.getHeight();
		BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = mask.createGraphics();
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, width, height);
		g.drawImage(i, 0, 0, null);
		for(int y = 0; y < height; y++) {
		    for(int x = 0; x < width; x++) {
		    	Color pixel = new Color(mask.getRGB(x, y));
		    	if(pixel.getRed() + pixel.getGreen() + pixel.getBlue() > 0) {
		    		mask.setRGB(x, y, new Color(255, 255, 255, 255).getRGB());
		    	}
		    }
		}
		return mask;
	}
	public static GifSequenceWriter createGifWriter(String directory) {
		System.out.println(new File(directory).getAbsolutePath());
		try {
	    	int n = 0;
	    	File dir = new File(directory);
	    	if(!dir.exists())
	    		dir.mkdirs();
	    	File result;
	    	do {
	    		result = new File(directory + File.separator + n + ".gif");
	    		n++;
	    	} while(result.exists());
	    	
	    	ImageOutputStream output = new FileImageOutputStream(result);
			GifSequenceWriter capture = new GifSequenceWriter(output, BufferedImage.TYPE_INT_ARGB, 1000/30, true);
			return capture;
	    } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    }
		return null;
	}
}
