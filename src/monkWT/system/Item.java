package monkWT.system;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import resources.ResourceLoader;

//class for items
public class Item implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;

	private ResourceLoader rl = new ResourceLoader();
	
	//item number
	private int itemNum = 0;
	//strength is how much damage an item does
	private int strength = 0;
	//how much damage is taken. when it gets to zero destroy the item
	private int damage = 100;
	//icon image for inventory
	private Image icon;
	//images for the item in the game
	private BufferedImage objPicUp,objPicDown,objPicLeft,objPicRight;
	//location maybe for when you drop it or hit someone?
	//no use yet
	public int x,y;
	//height and with of object. should be 10 and 5 for most all
	public int height,width;
	//no use yet. if things are different sized use them for adding them to the location at runtime
	public int pup,pdwn,plt,prt;
	
	public Item(int num, int streng, int damag, Image pic, String picName, int locX, int locY, int h, int w) throws IOException{
		itemNum = num;
		strength = streng;
		damage = damag;
		icon = pic;
		//enter the name of the first part of file Ex. daggerUP.png, then enter dagger
		if(picName != ""){
			objPicUp = rl.getSprite(picName+"Up.png");
			objPicDown = rl.getSprite(picName+"Down.png");
			objPicLeft = rl.getSprite(picName+"Left.png");
			objPicRight = rl.getSprite(picName+"Right.png");
		}
		x = locX;
		y = locY;
		height = h;
		width = w;
	}
	
	public int getItemNum(){
		return itemNum;
	}
	public void setItemNum(int num){
		itemNum = num;
	}
	public int getItemStrength(){
		return strength;
	}
	public void setItemStregnth(int stren){
		itemNum = stren;
	}
	public int getItemDamage(){
		return damage;
	}
	public void setItemDamage(int damag){
		itemNum = damag;
	}
	public Image getIcon(){
		return icon;
	}
	public BufferedImage getPicUp(){
		return objPicUp;
	}
	public BufferedImage getPicDown(){
		return objPicDown;
	}
	public BufferedImage getPicLeft(){
		return objPicLeft;
	}
	public BufferedImage getPicRight(){
		return objPicRight;
	}
	
	
}
