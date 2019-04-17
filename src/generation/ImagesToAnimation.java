package generation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JOptionPane;

public class ImagesToAnimation {

	public static void main(String[] args) {
		try {
			String directory = JOptionPane.showInputDialog("File directory");
			String prefix = JOptionPane.showInputDialog("File name prefix");
			String fileOutput = JOptionPane.showInputDialog("File output path");
			
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
			//ImageOutputStream output = new FileImageOutputStream(new File("./LuminousRing/LuminousRing.gif"));
			ImageOutputStream output = new FileImageOutputStream(new File(fileOutput));
			GifSequenceWriter capture = new GifSequenceWriter(output, BufferedImage.TYPE_INT_ARGB, 1000/30, true);
			
			for(File f : files) {
				try {
					BufferedImage i = ImageIO.read(f);
					
					for (int y = 0; y < i.getHeight(); y++) {
					    for (int x = 0; x < i.getWidth(); x++) {
					          Color c = new Color(i.getRGB(x, y));
					          if(c.getRGB() == Color.WHITE.getRGB()) {
					        	  i.setRGB(x, y, Color.BLACK.getRGB());
					          }
					    }
					}
					
					capture.writeToSequence(i);
					System.out.println(f.getName());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			capture.close();
			output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
		}
	}
}