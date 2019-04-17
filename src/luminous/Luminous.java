package luminous;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import generation.Effect;

public class Luminous {

}

class Number implements Effect {
	Point pos;
	int opacity;
	int size;
	char c = '0';
	public Number(Point pos, int opacity, int size) {
		this.pos = pos;
		this.opacity = opacity;
		this.size = size;
	}
	@Override
	public void update() {
		if((int) (Math.random() * 4) == 0) {
			opacity = Math.max(0, opacity - 12);
		}
		c = (char) ('0' + ((int) (Math.random()*10)));
	}

	@Override
	public void paint(Graphics2D g) {
		g.setFont(new Font("Inconsolata", Font.PLAIN, size));
		g.setColor(new Color(0, 255, 0, opacity));
		g.drawString("" + c, pos.x, pos.y);
	}
	
}

class ShrinkSquare implements Effect {
	private Point pos;
	private int opacity;
	private int size;
	
	public Point getPos() {
		return pos;
	}
	public int getOpacity() {
		return opacity;
	}
	public int getSize() {
		return size;
	}
	
	public ShrinkSquare(Point pos, int opacity, int size) {
		this.pos = pos;
		this.opacity = opacity;
		this.size = size;
	}
	public void update() {
		int random = (int) (Math.random() * 2);
		if(random == 0) {
			size *= 0.9;
		}
	}
	public void paint(Graphics2D g) {
		//g.setColor(new Color(0x89, 0xd1, 0x92, opacity));
		g.setColor(new Color(0, 204, 0, opacity));
		g.fillRect(pos.x - size/2, pos.y - size/2, size, size);
	}
}

class GrowShrinkSquare implements Effect {
	ShrinkSquare shrinkSquare;
	Point pos;
	private int opacity, opacityInc;
	private int size, sizeInc;
	boolean shrinking;
	public GrowShrinkSquare(ShrinkSquare shrinkSquare, int opacityInc, int sizeInc) {
		this.shrinkSquare = shrinkSquare;
		shrinking = false;
		pos = shrinkSquare.getPos();
		opacity = 0;
		size = 0;
		this.opacityInc = opacityInc;
		this.sizeInc = sizeInc;
	}
	public void update() {
		if(!shrinking) {
			opacity += opacityInc;
			size += sizeInc;
			if(opacity > shrinkSquare.getOpacity() && size > shrinkSquare.getSize()) {
				shrinking = true;
			}
		} else {
			shrinkSquare.update();
		}
	}
	@Override
	public void paint(Graphics2D g) {
		if(!shrinking) {
			//g.setColor(new Color(0x89, 0xd1, 0x92, opacity));
			g.setColor(new Color(0, 204, 0, opacity));
			g.fillRect(pos.x - size/2, pos.y - size/2, size, size);
		} else {
			shrinkSquare.paint(g);
		}
	}
}
