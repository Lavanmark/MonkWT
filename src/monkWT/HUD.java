package monkWT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import monkWT.system.Item;
import resources.ResourceLoader;

public class HUD {

	//classes
	private ResourceLoader rl = new ResourceLoader();
	
	//inventory
	public Item[] inventory = new Item[29]; 
	
	/* Blow Gun = 010
	 * Dagger = 020
	 * Axe = 030
	 * Pick-Axe = 031
	 * Fishing Rod = 032
	 * Spoon = 001
	 * Board = 002 
	 */
	//items
	public Item empty;
	public Item dagger;
	public Item spoon;
	
	//inventory quick items
	public Item inMouse;
	public Item TEMP_HOLD;
	
	public double cash = 1000;
	//city 1 = Zebule;
	public String cityName;
	public String questName,questInfo;
	
	//inventory display
	public boolean dispInv = false;
	
	//sign display
	public boolean dispSignText = false;
	private int boxLocX, boxLocY;
	
	//center message variables
	private String mesg, mesg1, mesg2;
	
	//Sprinting
	public int sprint = 1;
	public int sprintTime = 500;
	// cool down time is 250
	public int sprintCoolDown = 0;
	
	//Stealth
	public int stealthLvl = 1;
	public int stealthAmt = 500;
	
	//Save display
	public boolean goSave = false;
	private int saveTime = 50;
	
	
	
	//images
	private Image savedIcon;
	
	
	public void HUDInitialize(){
		try{
			savedIcon = rl.getIcon("savedGrey.png");
		}catch(Exception e){
			System.out.println("cant get images in HUD");
		}
		try{
			empty = new Item(000,0,0,rl.getIcon("blankIcon.png"),"",0,0,0,0);
			dagger = new Item(020,25,100,rl.getIcon("daggerIcon.png"),"dagger",0,0,10,5);
			spoon =  new Item(001,5,100,rl.getIcon("spoonIcon.png"),"spoon",0,0,10,4);
		}catch(IOException e){
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
		for(int i = 0; i < inventory.length; i++){
			inventory[i] = empty;
		}
		//TODO take out when ready.
		inventory[0] = dagger;
		inMouse = empty;
	}
	public void setCityName(int cc){
		if(cc == 1){cityName = "Zebule";}
	}
	public void setQuest(int qNum){
		if(qNum == 0){
			questName = "No Quest";
			questInfo = "You haven't recieved a quest yet.";
		}
		if(qNum == 1){
			questName = "My First Steal";
			questInfo = "Steal poison from Professor Lump.";
		}
	}
	public void centerMessage(String msg, int escNum, int x, int y){
		dispSignText = true;
		//string length
		int sl = 13;
		mesg = msg;
		mesg1 = " ";
		mesg2 = " ";
		boxLocX = x;
		boxLocY = y;
		if(mesg.length() > sl){
			if(mesg.charAt(sl) == ' '){
				mesg1 = mesg.substring(sl+1);
				mesg = mesg.substring(0, sl+1);
			}else{
				int n = sl;
				try{
					while(mesg.charAt(n) != ' '){
						n--;
						if(mesg.charAt(n) == ' '){
							mesg1 = mesg.substring(n+1);
						}
					}
					mesg = mesg.substring(0, n);
				}catch(StringIndexOutOfBoundsException e){
					e.printStackTrace();
				}
			}
			if(mesg1.length() > sl){
				if(mesg1.charAt(sl) == ' '){
					mesg2 = mesg1.substring(sl+1);
					mesg1 = mesg1.substring(0, sl+1);
				}else{
					int n = sl;
					try{
						while(mesg1.charAt(n) != ' '){
							n--;
							if(mesg1.charAt(n) == ' '){
								mesg2 = mesg1.substring(n+1);
							}
						}
						mesg1 = mesg1.substring(0, n);
					}catch(StringIndexOutOfBoundsException e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	public void dragLoc(int mx, int my){
		boxLocX = mx;
		boxLocY = my;
	}
	
	public void draw(Graphics g){	
		//TODO add displaying cash beneath stamina and stealth
		//Stamina
		g.setColor(Color.GREEN);
		g.drawRect(675, 15, sprintTime/5, 10);
		g.fillRect(675, 15, sprintTime/5, 10);
		//stealth
		g.setColor(Color.BLUE);
		g.drawRect(675, 30, stealthAmt/5, 10);
		g.fillRect(675, 30, stealthAmt/5, 10);
		//Cash
		g.setColor(Color.WHITE);
		int xd = 4;
		for(int i = 0; Math.pow(i, 10) < cash*2; i++){
			if(cash > Math.pow(i, 10) && cash < Math.pow(i+1, 10)){
				xd = i+4;
			}
			if(cash == Math.pow(i, 10)){
				xd = i+4;
			}
			if(cash == 0){
				xd = 3; 
			}
		}
		g.drawString("$"+cash, 768-(xd*7), 55);
		//draw inventory
		if(dispInv){
			//size of circles
			int width = 50, height = 50;
			//draws boxes
			g.setColor(Color.WHITE);
			g.fillRect(100, 100, 600, 400);
			g.setColor(Color.BLACK);
			g.drawRect(100, 100, 600, 400);
			//draws inventory icon images
			int a=150,b=125;
			for(int i = 1; i < inventory.length;i++){
				if(a> 600){
					b+=75;
					a=150;
				}
				g.drawImage(inventory[i].getIcon(), a, b, null);
				a+=75;
			}
			g.drawImage(inventory[0].getIcon(), 565, 425, null);
			//draws circles over images
			int c=150,d=125;
			for(int i = 1; i < inventory.length;i++){
				if(c> 600){
					d+=75;
					c=150;
				}
				g.drawOval(c, d, width, height);
				c+=75;
			}
			g.drawOval(565, 425, width, height);
			//draws text
			g.drawString("In Hand", 568, 420);
			g.drawString("Cash: " + cash, 125, 425);
			g.drawString("Current City: " + cityName, 125, 440);
			g.drawString("Quest: " + questName, 125, 455);
			g.drawString("Objective: " + questInfo, 125, 470);
			if(inMouse.getItemNum() != empty.getItemNum()){
				g.drawImage(inMouse.getIcon(), boxLocX-25, boxLocY-25, null);
			}
		}
		if(goSave){
			saveTime--;
			//TODO adjust this until you like it.
			g.drawImage(savedIcon, 350, 275, null);
			if(saveTime < 0){
				saveTime = 50;
				goSave=false;
			}
		}
		//draw what a sign says
		if(dispSignText){
			//background
			g.setColor(Color.WHITE);
			g.fillRect(boxLocX, boxLocY, 100, 50);
			//outline
			Color brown = new Color(98,48,0);
			g.setColor(brown);
			g.drawRect(boxLocX, boxLocY, 100, 50);
			//draw text
			g.setColor(Color.BLACK);
			g.drawString(mesg, boxLocX+5, boxLocY+15);
			g.drawString(mesg1, boxLocX+5, boxLocY+30);
			g.drawString(mesg2, boxLocX+5, boxLocY+45);
		}
	}
}
