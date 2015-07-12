package Entities;

import org.lwjgl.util.Color;

public class Floor extends Obstacle {
	Color c;
	public Floor(float x, float y, Color c) {
		super(x, y, null, 0);	
		this.c = c;
	}
	public Color getColor() {
		return c;
	}
}
