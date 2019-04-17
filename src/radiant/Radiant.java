package radiant;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import generation.Effect;

public class Radiant {
	public static final Color ORANGE_51 = orange(51);
	public static final Color ORANGE_102 = orange(102);
	public static final Color orange(int alpha) {
		return new Color(0xff, 0xb9, 0x00, alpha);
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
		g.setColor(Radiant.orange(opacity));
		g.drawRect(pos.x - size/2, pos.y - size/2, size, size);
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
			g.setColor(Radiant.orange(opacity));
			g.drawRect(pos.x - size/2, pos.y - size/2, size, size);
		} else {
			shrinkSquare.paint(g);
		}
	}
}
