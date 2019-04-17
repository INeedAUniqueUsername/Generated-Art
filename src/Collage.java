import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Collage {
	public static void main(String[] args) {
		try {
			//BufferedImage image = new Collage(new File(".")).generate();
			BufferedImage image = new Collage(new File("./Collage")).generate();
			ImageIO.write(image, "png", new File("./" + new Date().getTime() + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public final File source;
	public Collage(File source) {
		this.source = source;
	}
	public BufferedImage generate() {
		int width = 1920;
		int height = 1080;
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = result.createGraphics();
		
		AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
		g.setComposite(alpha);
		
		for(File f : source.listFiles()) {
			String name = f.getName().toLowerCase();
			if(name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg")) {
				try {
					BufferedImage image = ImageIO.read(f);
					System.out.println("Including image: " + f.getName());
				    image = rotateImage(image);
				    g.drawImage(image, (int) (Math.random() * width) - image.getWidth()/2, (int) (Math.random() * height) - result.getHeight()/2, null);
				    
				    //g.drawImage(image, new RescaleOp(new float[] {1, 1, 1, 0.6f}, new float[] {0, 0, 0, 0}, null), 0, 0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		for(int i = 0; i < 10; i++) {
			BufferedImage image = rotateImage(result);
			g.drawImage(image, (int) (Math.random() * width) - image.getWidth()/2, (int) (Math.random() * height) - result.getHeight()/2, null);
		}
		
		return result;
	}
	public static BufferedImage rotateImage(BufferedImage image) {
		//https://stackoverflow.com/questions/20952076/rotating-and-drawing-an-image-at-a-point-in-java
		AffineTransform rotation = new AffineTransform();
	    rotation.rotate(Math.toRadians(Math.random() * 360), image.getWidth() / 2, image.getHeight() / 2);
	    AffineTransformOp rotator = new AffineTransformOp(rotation, AffineTransformOp.TYPE_BILINEAR);
	    return rotator.filter(image, null);
	}
}
