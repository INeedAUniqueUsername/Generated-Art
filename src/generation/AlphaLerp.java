package generation;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class AlphaLerp {

	public static void main(String[] args) {
		File input = new File(JOptionPane.showInputDialog("Input Directory"));
		String prefix = JOptionPane.showInputDialog("File Prefix");
		Queue<BufferedImage> images = new LinkedList<>();
		ArrayList<File> files = new ArrayList<>(Arrays.asList(input.listFiles()));
		
		Alphanumerator a = new Alphanumerator();
		files.sort((f1, f2) -> {
			return a.compare(f1.getName(), f2.getName());
		});
		for(File f : files) {
			if(f.getName().startsWith(prefix)) {
				try {
					images.add(ImageIO.read(f));
					System.out.println(f.getAbsolutePath());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		String output = JOptionPane.showInputDialog("Output Path");
		int n = 0;
		BufferedImage previous = images.remove();
		while(!images.isEmpty()) {
			BufferedImage current = images.remove();
			for(int i = 0; i < 5; i++) {
				n++;
				float currentAlpha = i * 0.2f;
				float previousAlpha = 1 - currentAlpha;
				
				final BufferedImage previous2 = previous;
				BufferedImage frame = new BufferedImage(current.getWidth(), current.getHeight(), BufferedImage.TYPE_INT_ARGB) {{
					Graphics2D g = createGraphics();
					g.drawImage(previous2, new RescaleOp(new float[] {1f, 1f, 1f, previousAlpha}, new float[] {0, 0, 0, 0}, null), 0, 0);
					g.drawImage(current, new RescaleOp(new float[] {1f, 1f, 1f, currentAlpha}, new float[] {0, 0, 0, 0}, null), 0, 0);
				}};
				try {
					ImageIO.write(frame, "png", new File(output + " " + n + ".png"));
					System.out.println(new File(output + " " + n + ".png").getAbsolutePath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			previous = current;
		}
	}

}
