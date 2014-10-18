package monkWT.system;

import java.awt.image.BufferedImage;

public class SheetGrabber {

public BufferedImage sheet;
	
	public SheetGrabber(BufferedImage ss){
		this.sheet = ss;
	}
	
	public BufferedImage grabSprite(int x, int y, int width, int height){
		BufferedImage sprite = sheet.getSubimage(x, y, width, height);
		return sprite;
	}
}
