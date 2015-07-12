package Entities;

import Shapes.Rectangle;

public class Paddle extends GameObject {
	public Paddle(float x, float y) {
		super(x,y,null);
		this.r = new Rectangle(x,y,15,75);
	}
	
}
