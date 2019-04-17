package generation;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JOptionPane;

public class SheetToAnimation {
	public static void main(String[] args) {
		try {
			String color = JOptionPane.showInputDialog("Color");
			BufferedImage sheet = ImageIO.read(new File(color));
			String mask = JOptionPane.showInputDialog("Mask");
			if(!mask.isEmpty()) {
				sheet = Masker.process(sheet, ImageIO.read(new File(mask)));
			}
			String s;
			int frameWidth;
			if(!(s = JOptionPane.showInputDialog("Frame width")).isEmpty()) {
				frameWidth = Integer.parseInt(s);
			} else if(sheet.getWidth() < sheet.getHeight()) {
				frameWidth = sheet.getWidth()-1;
			} else {
				frameWidth = sheet.getHeight()-1;
			}
			int frameHeight = (s = JOptionPane.showInputDialog("Frame height")).isEmpty()
					? frameWidth : Integer.parseInt(s);
			FileImageOutputStream output = new FileImageOutputStream(new File(
					(s = JOptionPane.showInputDialog("Output")).isEmpty()
							? color.substring(0, color.lastIndexOf('.')) + ".gif" : s));
			int interval;
			if(!(s = JOptionPane.showInputDialog("Frames per second")).isEmpty()) {
				interval = 1000 / Integer.parseInt(s);
			} else if(!(s = JOptionPane.showInputDialog("Facing count")).isEmpty()) {
				interval = 4000 / Integer.parseInt(s);
			} else {
				interval = 1000 / 15;
			}
			GifSequenceWriter capture = new GifSequenceWriter(output, BufferedImage.TYPE_INT_RGB, interval, true);
			
			ArrayList<BufferedImage> result = Splitter.process(sheet, frameWidth, frameHeight);
			for(int i = 0; i < result.size(); i++) {
				capture.writeToSequence(result.get(i));
			}
			
			capture.close();
			output.close();
		} catch (HeadlessException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
