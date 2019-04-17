package generation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JOptionPane;

public class Marquee {
	public static void main(String[] args) {
		try {
			String inputPath = JOptionPane.showInputDialog("Input");
			BufferedImage input = ImageIO.read(new File(inputPath));
			
			String outputPath = JOptionPane.showInputDialog("Output");
			int space = Integer.parseInt("0" + JOptionPane.showInputDialog("Interval"));
			
			
			ImageOutputStream output = new FileImageOutputStream(new File(outputPath.isEmpty() ? inputPath.substring(0, inputPath.lastIndexOf('.')) + ".gif" : outputPath));
			GifSequenceWriter capture = new GifSequenceWriter(output, BufferedImage.TYPE_INT_ARGB, 1000/30, true);
			
			int time = 0;
			while(time < input.getWidth() + space) {
				final int x = -time;
				capture.writeToSequence(new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB) {{
					Graphics2D g = createGraphics();
					g.setBackground(new Color(0, 0, 0, 0));
					g.clearRect(0, 0, getWidth(), getHeight());
					g.drawImage(input, x, 0, null);
					g.drawImage(input, getWidth() + x + space, 0, null);
				}});
				time++;
			}
			
			capture.close();
			output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
		}
	}
}
