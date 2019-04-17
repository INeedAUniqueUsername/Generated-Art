import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

public class Static {

	public static void main(String[] args) {
		BufferedImage result = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = result.createGraphics();
		for(int i = 0; i < 1920; i++) {
			for(int j = 0; j < 1080; j++) {
				g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
				g.fillRect(i, j, 1, 1);
			}
		}
		try {
			ImageIO.write(result, "png", new File("./" + new Date().getTime() + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
