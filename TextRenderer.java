package Text;

import java.awt.Font;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class TextRenderer {
	Font f;
	TrueTypeFont f2;
	TrueTypeFont ttf = new TrueTypeFont(new Font("Times New Roman",Font.BOLD,32),false);
	public TextRenderer() {
		f = new Font("Times New Roman", Font.BOLD, 24);
		f2 = new TrueTypeFont(f, false);
	}
	public void drawString(float x, float y, String t) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		f2.drawString(x, y, t,Color.white);
	}
	public void drawString(float x, float y, int type, int size, String t) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		ttf.drawString(x,y,t);
	}
}
