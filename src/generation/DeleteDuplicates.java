package generation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class DeleteDuplicates {

	public static void main(String[] args) {
		try {
			String directory = JOptionPane.showInputDialog("File directory");
			int interval = Integer.parseInt(JOptionPane.showInputDialog("Facings count"));
			
			File dir = new File(directory);
			
			List<File> files = new ArrayList<File>();
			for(File f : dir.listFiles()) {
				if(f.isFile()) {
					files.add(f);
				}
			}
			files.sort(new Comparator<File>() {
				Alphanumerator a = new Alphanumerator();
				public int compare(File f1, File f2) {
					return a.compare(f1.getName(), f2.getName());
				}
				
			});
			for(int i = interval; i < files.size(); i += interval) {
				File f = files.get(i);
				f.delete();
				//f.renameTo(new File(f.getParent() + File.separator + "Deleted " + f.getName()));
				files.remove(i);
			}
			/*
			Delete: for(int i = 0; i+interval < files.size(); i += interval) {
				BufferedImage b1 = ImageIO.read(files.get(i));
				File f2 = files.get(i+interval);
				BufferedImage b2 = ImageIO.read(f2);
				for (int y = 0; y < b1.getHeight(); y++) {
					for(int x = 0; x < b1.getWidth(); x++) {
						if(b1.getRGB(x, y) != b2.getRGB(x, y)) {
							continue Delete;
						}
					}
				}
				System.out.println(f2.getName() + " deleted");
				f2.renameTo(new File(f2.getParent() + File.separator + "Deleted " + f2.getName()));
				count++;
				i++;
			}
			*/
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
