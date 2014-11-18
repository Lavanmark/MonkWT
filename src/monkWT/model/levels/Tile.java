package monkWT.model.levels;

import java.awt.image.BufferedImage;

import monkWT.controller.SheetGrabber;

public class Tile {
	
	//TODO convert all chairs to items
	
	private BufferedImage tileImage;
	private boolean solid;
	private boolean door;
	private boolean sign;
	private int id;
	
	
	public Tile(boolean solid, boolean door, int x, int y, SheetGrabber sg, int id){
		this.tileImage = null;
		this.solid = solid;
		this.door = door;
		this.sign = false;
		this.setImage(x, y,sg);
		this.id = id;
	}
	public Tile(boolean solid, boolean door, boolean sign, int x, int y, SheetGrabber sg, int id){
		this.tileImage = null;
		this.solid = solid;
		this.door = door;
		this.sign = sign;
		this.setImage(x, y,sg);
		this.id = id;
	}
	
	public BufferedImage getImage(){
		return tileImage;
	}
	public boolean isSolid(){
		return solid;
	}
	public boolean isDoor(){
		return door;
	}
	public boolean isSign(){
		return sign;
	}
	public void setImage(int x, int y, SheetGrabber sg1){
		int width = 20, height = 20;
		tileImage = sg1.grabSprite(x, y, width, height);
	}
	public int getId(){
		return id;
	}
	
}
