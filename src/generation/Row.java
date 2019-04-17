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

public class Row {

	public static void main(String[] args) {
		File input = new File(JOptionPane.showInputDialog("Input Directory"));
		String prefix = JOptionPane.showInputDialog("File Prefix");
		ArrayList<File> files = new ArrayList<>(Arrays.asList(input.listFiles()));
		Alphanumerator a = new Alphanumerator();
		files.sort((f1, f2) -> {
			return a.compare(f1.getName(), f2.getName());
		});
		for(File f : files) {
			if(f.getName().startsWith(prefix)) {
				try {
					BufferedImage current = ImageIO.read(f);
					ImageIO.write(new BufferedImage(current.getWidth() * 5, current.getHeight(), BufferedImage.TYPE_INT_ARGB) {{
						Graphics2D g = createGraphics();
						for(int i = 0; i < 5; i++) {
							g.drawImage(current, i * current.getWidth(), 0, null);
						}
					}}, "png", f);
					System.out.println(f.getAbsolutePath());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
