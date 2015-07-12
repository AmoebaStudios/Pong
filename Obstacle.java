package Entities;

import org.newdawn.slick.opengl.Texture;

public class Obstacle extends GameObject {
	int type;
	//0 is hard
	//1 is pass through bottom but not top
	//2 is transparent
	public Obstacle(float x, float y, Texture tex, int t) {
		super(x,y,tex);
		type = t;
	}
	
}
