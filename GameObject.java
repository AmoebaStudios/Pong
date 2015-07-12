package Entities;

import org.newdawn.slick.opengl.Texture;

import Shapes.Rectangle;

public class GameObject {
	public Rectangle r;
	Texture t;
	public float x; 
	public float y;
	public GameObject(float x, float y, Texture tex) {
		if(tex != null) {
			t = tex;
			r = new Rectangle(x, y, tex.getWidth(),tex.getHeight());
		} else {
			r = new Rectangle(x,y,0,0);
		}
		this.x = x;
		this.y = y;
		
		
	}
	public Rectangle getRect() {
		return r;
	}
	public float getX() {
		return r.getX();
	}
	public float getY(){
		return r.getY();
	}
	public float getWidth() {
		return r.getWidth();
	}
	public float getHeight() {
		return r.getHeight();
	}
	public Texture getTexture() {
		return t;
	}
	
}
