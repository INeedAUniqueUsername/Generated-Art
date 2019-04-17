package generation;
import java.awt.Graphics2D;

public interface Effect {
	public void update();
	public void paint(Graphics2D g);
}