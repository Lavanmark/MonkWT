package monkWT.model;

public class Chair {

	private int chairLoc;
	private int chairDir;
	private int playerSitDir;
	private boolean extraHigh;
	private boolean extraLow;
	private int playerX;
	private int playerY;
	
	public Chair(int chairBlock, int playerDirection, int chairDirection, int playerXLoc, int playerYLoc){
		this.chairLoc = chairBlock;
		this.chairDir = chairDirection;
		this.playerSitDir = playerDirection;
		this.playerX = playerXLoc;
		this.playerY = playerYLoc;
		this.extraHigh = false;
		this.extraLow = false;
	}
	public Chair(int chairBlock, int playerDirection, int chairDirection, int extraSumthin, int playerXLoc, int playerYLoc){
		this.chairLoc = chairBlock;
		this.chairDir = chairDirection;
		this.playerSitDir = playerDirection;
		this.playerX = playerXLoc;
		this.playerY = playerYLoc;
		if(extraSumthin == 1){
			this.extraHigh = true;
			this.extraLow = false;
		}else if(extraSumthin == 2){
			this.extraHigh = false;
			this.extraLow = true;
		}else{
			this.extraHigh = false;
			this.extraLow = false;
		}
	}
	
	public int getChairLoc() {
		return chairLoc;
	}
	public void setChairLoc(int chairLoc) {
		this.chairLoc = chairLoc;
	}
	public int getChairDir() {
		return chairDir;
	}
	public void setChairDir(int chairDir) {
		this.chairDir = chairDir;
	}
	public int getPlayerSitDir() {
		return playerSitDir;
	}
	public void setPlayerSitDir(int playerSitDir) {
		this.playerSitDir = playerSitDir;
	}
	public boolean isHigh() {
		return extraHigh;
	}
	public void setHigh(boolean extra) {
		this.extraHigh = extra;
	}
	public boolean isLow(){
		return extraLow;
	}
	public void setLow(boolean extra){
		this.extraLow = extra;
	}
	public int getPlayerX() {
		return playerX;
	}
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}
	public int getPlayerY() {
		return playerY;
	}
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}
}
