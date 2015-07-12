package Shapes;

public class Rectangle extends Shape {
	public float width;
	public float height;
	public Rectangle(float x, float y, float w, float h) {
		super(x,y);
		this.width = w;
		this.height = h;
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	
}
