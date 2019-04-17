package generation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImagesToSheet {

	public static void main(String[] args) {
		try {
			String directory = JOptionPane.showInputDialog("File directory");
			String prefix = JOptionPane.showInputDialog("File name prefix");
			String fileOutput = JOptionPane.showInputDialog("File output path");
			int animationFrames = Integer.parseInt(JOptionPane.showInputDialog("Animation frames"));
			
			File dir = new File(directory);
			
			List<File> files = new ArrayList<File>();
			for(File f : dir.listFiles()) {
				if(f.getName().startsWith(prefix)) {
					try {
						files.add(f);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			/*
			files.sort(new Comparator<File>() {
				public int compare(File f1, File f2) {
					return f1.getName().compareTo(f2.getName());
				}
				
			});
			*/
			files.sort(new Comparator<File>() {
				Alphanumerator a = new Alphanumerator();
				public int compare(File f1, File f2) {
					return a.compare(f1.getName(), f2.getName());
				}
				
			});
			int count = files.size();
			int facings = count/animationFrames;
			
			int rows = facings;
			int columns = animationFrames;
			
			BufferedImage first = ImageIO.read(files.get(0));
			int width = first.getWidth();
			int height = first.getHeight();
			System.out.println("Count: " + count);
			System.out.println("Rows: " + rows);
			System.out.println("Columns: " + columns);
			System.out.println("Width: " + width);
			System.out.println("Height: " + height);
			BufferedImage result = new BufferedImage(columns * width, rows * height, BufferedImage.TYPE_INT_ARGB);
			Graphics g = result.getGraphics();
			
			for(int facing = 0; facing < rows; facing++) {
				for(int frame = 0; frame < columns; frame++) {
					BufferedImage i = ImageIO.read(files.get(facing + frame*facings));
					g.drawImage(i, frame*width, facing*height, null);
				}
				System.out.println("Facing " + facing + " done");
			}
			
			ImageIO.write(result, fileOutput.substring(fileOutput.lastIndexOf('.')+1), new File(fileOutput));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
