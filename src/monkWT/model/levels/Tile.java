package monkWT.model.levels;

import java.awt.image.BufferedImage;

import monkWT.controller.SheetGrabber;

@SuppressWarnings("rawtypes")
public class Tile implements Comparable{
	
	//TODO convert all chairs to items
	

	private BufferedImage tileImage;
	private boolean solid;
	private boolean door;
	private boolean sign;
	private int id;
	private int imgX, imgY;
	
	
	public Tile(boolean solid, boolean door, int x, int y, SheetGrabber sg, int id){
		this.tileImage = null;
		this.solid = solid;
		this.door = door;
		this.sign = false;
		this.setImage(x, y,sg);
		this.id = id;
		this.imgX = x;
		this.imgY = y;
	}
	public Tile(boolean solid, boolean door, boolean sign, int x, int y, SheetGrabber sg, int id){
		this.tileImage = null;
		this.solid = solid;
		this.door = door;
		this.sign = sign;
		this.setImage(x, y,sg);
		this.id = id;
		this.imgX = x;
		this.imgY = y;
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
	
	public int getImgX() {
		return imgX;
	}
	public void setImgX(int imgX) {
		this.imgX = imgX;
	}
	public int getImgY() {
		return imgY;
	}
	public void setImgY(int imgY) {
		this.imgY = imgY;
	}
	
	public void loadImage(SheetGrabber sg1){
		if(sg1 != null){
			int width = 20, height = 20;
			tileImage = sg1.grabSprite(imgX, imgY, width, height);
		}
	}
	
	public void setImage(int x, int y, SheetGrabber sg1){
		int width = 20, height = 20;
		tileImage = sg1.grabSprite(x, y, width, height);
	}
	public int getId(){
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (door ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + (sign ? 1231 : 1237);
		result = prime * result + (solid ? 1231 : 1237);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (door != other.door)
			return false;
		if (id != other.id)
			return false;
		if (sign != other.sign)
			return false;
		if (solid != other.solid)
			return false;
		return true;
	}
	@Override
	public int compareTo(Object obj) {
		if (this == obj)
			return 0;
		Tile other = (Tile) obj;
		if(other.id == this.id)
			return 0;
		else if(other.id < this.id)
			return 1;
		else
			return -1;
	}
}
