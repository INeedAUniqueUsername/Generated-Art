package generation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Spriter {

	public static void main(String[] args) {
		try {
			String directory = JOptionPane.showInputDialog("File directory");
			String prefix = JOptionPane.showInputDialog("File name prefix");
			String fileOutput = JOptionPane.showInputDialog("File output path");
			
			File dir = new File(directory);
			
			List<File> files = new ArrayList<File>();
			for(File f : dir.listFiles()) {
				if(f.getName().startsWith(prefix) && f.isFile()) {
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
			int rows = count, columns = 1;
			//This code tries to adjust the rows and columns to be as square as possible
			/*
			int difference = Math.abs(rows - columns);
			for(int i_rows = 2; i_rows < count; i_rows++) {
				if(count%i_rows == 0) {
					int i_columns = count/i_rows;
					int i_difference = Math.abs(i_rows - i_columns);
					if(i_difference < difference) {
						difference = i_difference;
						rows = i_rows;
						columns = i_columns;
					}
				}
			}
			*/
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
			for(int x = 0; x < columns; x++) {
				for(int y = 0; y < rows; y++) {
					try {
						File f = files.get(x*rows + y);
						BufferedImage i = ImageIO.read(f);
						g.drawImage(i, width*x, height*y, null);
						System.out.println(f.getName());
					} catch(Exception e) {
						e.printStackTrace();
					}
					
				}
			}
			ImageIO.write(result, fileOutput.substring(fileOutput.lastIndexOf('.')+1), new File(fileOutput));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
