package MainEngine;

import java.awt.Font;
import java.util.Random;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import Entities.Ball;
import Entities.Paddle;
import Input.Input;
import Rendering.DisplayManager;
import Shapes.ShapeCreator;
import Text.TextRenderer;

public class MainComponent {
	DisplayManager man = new DisplayManager();
	ShapeCreator shape = new ShapeCreator();
	TextRenderer text;
	
	boolean running = false;
	boolean paused  = true;
	boolean aiMode = true;
	int del = 0;
	int won = 0;
	int width = 640;
	int height = 480;
	int s1 = 0;
	int s2 = 0;
	Paddle p1 = new Paddle(0,(float) ((height/2)-37.5));
	Paddle p2 = new Paddle(width-15,(float) ((height/2)-37.5));
	Ball b = new Ball((width/2)-10,(height/2)-10,width,height);
	long beforeTime;
	long currentTime;
	long delta;
	public void start() {
		beforeTime = getTime();
		man.glInit();
		texInit();
		width = man.getWIDTH();
		height = man.getHEIGHT();
		running = true;
		run();
	}
	public void texInit() {
		text = new TextRenderer();
		
	}
	public void run() {
		
		synchronized(this) {
		while((!Display.isCloseRequested())&&running) {
			if(del == 100) {
				delta = getDelta()*10;
				del = 0;
			} else {
				del++;
			}
			
			if(!paused) {
				update();
			} else {
				Input.pollInput();
				if(Input.getKey(Keyboard.KEY_SPACE)) {
					paused = false;
				}
			}
			
			render();
			man.render();
			try {
				wait(5);
			} catch (InterruptedException e) {

			}
		}
		}
		stop();
	}
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glDisable(GL11.GL_BLEND);
		//draw paddles
		shape.createRectangle(p1.r, (Color) Color.WHITE);
		shape.createRectangle(p2.r, (Color) Color.WHITE);
		//draw line in the middle
		shape.createRectangle(3,height,(float)((width/2)-1.5), 0f, (Color)Color.WHITE);
		//draw ball
		shape.createSquare(10, b.x, b.y, (Color)Color.WHITE);
		text.drawString(280, 0, Font.BOLD, 32, Integer.toString(s1));
		text.drawString(340, 0, Font.BOLD, 32, Integer.toString(s2));
		text.drawString(0, 0, Long.toString(delta));
		if(won != 0) {
			if(won == 1) {
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
				text.drawString(50, 50, Font.BOLD, 50, "Player 1 Wins!");
				text.drawString(100, 100, Font.PLAIN, 24, "Press Escape to Exit");
			}
			if(won == 2) {
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
				text.drawString(50, 50, Font.BOLD, 50, "Player 2 Wins!");
				text.drawString(100, 100, Font.PLAIN, 24, "Press Escape to Exit");
			}
		}
	}
	public int getDelta() {
		long cur = getTime();
		int delt = (int)(cur-beforeTime);
		beforeTime = cur;
		
		return delt;
	}
	public long getTime() {
		return (Sys.getTime() * 1000 / Sys.getTimerResolution());
	}
	public void update() {
		Input.pollInput();
		if(Input.getKey(Keyboard.KEY_ESCAPE)) {
			stop();
		}
		if(Input.getKey(Keyboard.KEY_SPACE)) {
			paused = true;
		}
		if(Input.getKey(Keyboard.KEY_Q)) {
			p1.y -= 5;
			if(p1.y <= 0) {
				p1.y += 5;
			}
			p1.r.setX(p1.x);
			p1.r.setY(p1.y);
		}
		
		if(Input.getKey(Keyboard.KEY_A)) {
			p1.y += 5;
			if(p1.y >= (height-75)) {
				p1.y -= 5;
			}
			p1.r.setX(p1.x);
			p1.r.setY(p1.y);
		}
		if(Input.getKey(Keyboard.KEY_P)) {
			if(aiMode = true) {
				aiMode = false;
			}
			p2.y -= 5;
			if(p2.y <= 0) {
				p2.y +=5;
			}
			p2.r.setX(p2.x);
			p2.r.setY(p2.y);
		}
		if(Input.getKey(Keyboard.KEY_L)) {
			if(aiMode = true) {
				aiMode = false;
			}
			p2.y += 5;
			if(p2.y >= (height-75)) {
				p2.y -= 5;
			}
			p2.r.setX(p2.x);
			p2.r.setY(p2.y);
		}
		aiMove();
		b.move();
		//check if the ball hit the paddles
		if((b.x <= (p1.x+15)) && ((b.y >= p1.y)&&(b.y<=(p1.y+75)))) {
			b.dx *= -1;
			
		}
		if((b.x >= (p2.x)) && ((b.y >= p2.y)&&(b.y<=(p2.y+75)))) {
			b.dx *= -1;
			
		}
		if(b.x >= 640) {
			//ball went off on the right
			s1 += 1;
			checkWin();
			resetBall(2);
		}
		if(b.x <= 0) {
			//ball went off on the left
			s2 += 1;
			checkWin();
			resetBall(1);
		}
		
	}
	public void aiMove() {
		if((b.x > (width/2)) && aiMode) {
		if(b.y < p2.y) {
			//ball is above the paddle, move up
			p2.y -=1.5;
			if(p2.y <= 0) {
				p2.y += 1.5;
			}
			p2.r.setX(p2.x);
			p2.r.setY(p2.y);
		} else if(b.y > p2.y) {
			//ball is below the paddle, move down
			p2.y += 1.5;
			if(p2.y >= height-75) {
				p2.y -= 1.5;
			}
			p2.r.setX(p2.x);
			p2.r.setY(p2.y);
		}
		}
	}
	public void checkWin() {
		if(s1 == 11) {
			win(1);
		} else if(s2 == 11) {
			win(2);
		}
	}
	public void win(int i) {
		won = i;
	}
	public void resetBall(int who) {
		Random r =  new Random();
		float rand = r.nextFloat()+1;
		b = new Ball((width/2)-10,(height/2)-10,width,height);
		if(who == 1) {
			b.dx = 1.5f*rand;
			b.dy = -1.5f*rand;
		} else if(who == 2) {
			
			b.dx = -1.5f*rand;
			b.dy = 1.5f*rand;
		}
		paused = true;
	}
	public void stop() {
		man.cleanUp();
		System.exit(0);
	}
	public static void main(String[] args) {
		MainComponent m = new MainComponent();
		m.start();
	}

}
