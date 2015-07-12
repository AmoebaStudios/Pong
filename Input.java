package Input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public abstract class Input {
	private static boolean[] keys = new boolean[128];
	private static boolean mouseKeys[] = new boolean[5];
	
	public static void pollInput() {
		for(int i = 0;i<keys.length;i++) {
			keys[i] = false;
		}
		for(int i = 0;i<keys.length;i++) {
			if(isKeyDown(i)) {
				keys[i] = true;
			}
		}
		for(int i = 0;i<mouseKeys.length;i++) {
			mouseKeys[i] = false;
		}
		for(int i = 0;i<mouseKeys.length;i++) {
			if(isButtonDown(i)) {
				mouseKeys[i] = true;
			}
		}
	}
	private static boolean isKeyDown(int key) {
		return Keyboard.isKeyDown(key);
	}
	private static boolean isButtonDown(int key) {
		return Mouse.isButtonDown(key);
	}
	public static boolean getKey(int key) {
		return keys[key];
	}
	public static boolean getButton(int button) {
		return mouseKeys[button];
	}
	
	
}
