package monkWT.model;

import java.io.IOException;

import resources.ResourceLoader;

import monkWT.controller.SaveLoad;
import monkWT.model.levels.Levels;







public class Model {

	private Levels lvl;
	private Player player;
	private ResourceLoader rl = new ResourceLoader();
	
	
	public enum State {
		INITIALIZED, MENU, LOAD, STARTNEW, PLAYING, INVENTORY, PAUSED, GAMEOVER, DESTROYED
	}
	private State state;
	
	 private int currentLvl = 1;
	 private double bankCash = 0;
	 private double handCash = 0;
	 private int currentCity = 1;
	 private int currentQuest = 0;
	
	
	
	public Model(){
		lvl = new Levels();
		player = new Player(lvl);
		setState(State.INITIALIZED);
	}
	
	public void update(){
		switch(getState()){
		case LOAD:
			loadGame();
		  	setState(State.PLAYING);
		  	break;
		case PLAYING:
			gameUpdate();
			break;
		default:
			break;
		}
	}
	
	 public void gameUpdate() { 
		 
		 //Update the player
		 player.update();
		 //Double check that the city your in is the same as it says across the system
		 //does not actually do anything yet. 
		 //probably take it out until a second city is there.
		 if(player.currentCity != lvl.getCityIn()){
			 lvl.setCityIn(player.currentCity);
			 player.HUD.setCityName(player.currentCity);
		 }
		 //sync the HUD cash and hand cash
		 setHandCash(player.HUD.cash);
		 //check for lives
		 if(player.lives < 0){
			 setState(State.GAMEOVER);
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 /*
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  * 
	  */
	 
	
	
	private SaveLoad contrlSaveLoad;
	public void prepareLoad(SaveLoad sl){
		contrlSaveLoad = sl;
	}
	private void loadGame(){
		try{
			contrlSaveLoad.load();
	  	}catch(Exception e){
	  		e.printStackTrace();
	  	}
	  
	  	  //load main
		currentCity = contrlSaveLoad.getCity();
		currentLvl = contrlSaveLoad.getSec();
		setHandCash(contrlSaveLoad.getCash());
		setBankCash(contrlSaveLoad.getBankCash());
		setCurrentQuest(contrlSaveLoad.getQuest());
		//load levels
		lvl.cityDeaths = contrlSaveLoad.getDeaths();
		lvl.loadCity(currentCity);
		lvl.setSecIn(currentLvl);
		lvl.setBuildingEnt(contrlSaveLoad.getBuilding());
		lvl.ownerHouse = contrlSaveLoad.getOwners();
		//load player
		player.playerInitialize(currentCity);
		player.currentCity = currentCity;
		player.buildingIn = contrlSaveLoad.getBuilding();
		player.entBuilding(contrlSaveLoad.getBuilding(),0,0);
		player.loadPosition(contrlSaveLoad.getXLoc(), contrlSaveLoad.getYLoc());
		player.setDeathsForColl(contrlSaveLoad.getDeaths());
		//load HUD
		player.HUD.HUDInitialize();
		player.HUD.setCityName(lvl.getCityIn());
		player.HUD.setQuest(getCurrentQuest());
		player.HUD.cash = getHandCash();
		int[] tempSlist = contrlSaveLoad.getInv();
		//Reset all items
		for(int i = 0; i < tempSlist.length; i++){
			//TODO finish adding all items. How to implement damage and strength??
			if(tempSlist[i] == 000){
				try {
					player.HUD.inventory[i] = new Item(tempSlist[i],0,0,rl.getIcon("blankIcon.png"),"",0,0,0,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(tempSlist[i] == 001){
				try {
					player.HUD.inventory[i] = new Item(001,5,100,rl.getIcon("spoonIcon.png"),"spoon",0,0,10,4);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(tempSlist[i] == 020){
				try{
					player.HUD.inventory[i] = new Item(020,25,100,rl.getIcon("daggerIcon.png"),"dagger",0,0,10,5);
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}

	
	

	public Player getPlayer(){
		return player;
	}
	public Levels getLevel(){
		return lvl;
	}
	
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}


	public double getHandCash() {
		return handCash;
	}


	public void setHandCash(double handCash) {
		this.handCash = handCash;
	}


	public int getCurrentQuest() {
		return currentQuest;
	}


	public void setCurrentQuest(int currentQuest) {
		this.currentQuest = currentQuest;
	}


	public double getBankCash() {
		return bankCash;
	}


	public void setBankCash(double bankCash) {
		this.bankCash = bankCash;
	}
	
	
}
