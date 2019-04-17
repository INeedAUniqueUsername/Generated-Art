package generation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class TranscendencePNG {
	public static void main(String[] args) {
		try {
			for(File f : new File(JOptionPane.showInputDialog("Directory")).listFiles()) {
				//System.out.println("Checking " + f.getName());
				String fullName = f.getName();
				if(f.isFile() && fullName.toLowerCase().endsWith(".jpg")) {
					String name = fullName.substring(0, fullName.lastIndexOf('.'));
					File maskFile = new File(f.getParent() + File.separator + name + "Mask.bmp");
					//System.out.println("Looking For " + maskFile.getName());
					if(maskFile.exists()) {
						//System.out.println("Demasking");
						BufferedImage color = ImageIO.read(f);
						BufferedImage mask = ImageIO.read(maskFile);
						BufferedImage result = Masker.process(color, mask);
						ImageIO.write(result, "png", new File(f.getParent() + File.separator + name + ".png"));
						f.delete();
						maskFile.delete();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
