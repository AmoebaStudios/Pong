package Entities;

public class Ball extends GameObject {
	public float dx = 1.5f;
	public float dy = 1.5f;
	int w;
	int h;
	public Ball(float x, float y, int wid, int he) {
		super(x, y, null);
		w = wid;
		h = he;
	}
	public void move() {
		
		this.x +=dx;
		this.y +=dy;
		if((y >= 480)||(y <= 0)) {
			y = y-dy;
			dy *= -1;
		}
		
	}
}
