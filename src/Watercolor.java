import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

public class Watercolor {
	public static void main(String[] args) {
		try {
			//BufferedImage image = new Collage(new File(".")).generate();
			BufferedImage image = new Watercolor(1920, 1080).getImage();
			ImageIO.write(image, "png", new File("./" + new Date().getTime() + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static final Color[] colors = {
			Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW
	};
	private int width, height;
	public Watercolor(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public BufferedImage getImage() {
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = result.createGraphics();
		g.setBackground(new Color(0, 0, 0, 0));
		g.clearRect(0, 0, width, height);
		for(int i = 0; i < 2000; i++) {
			Color c = colors[(int) (Math.random() * colors.length)];
			g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 26));
			//g.fillRect((int) (Math.random() * width * 2) - width, (int) (Math.random() * height * 2) - height, (int) (Math.random() * width), (int) (Math.random() * height));
			g.fillRect((int) (Math.random() * width * 2) - width, (int) (Math.random() * height * 2) - height, (int) (Math.random() * width/4), (int) (Math.random() * height/4));
		}
		return result;
	}
}
