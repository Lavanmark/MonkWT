package monkWT.model;

public class Chair {

	private int chairLoc;
	private int chairDir;
	private int playerSitDir;
	private boolean extra;
	private int playerX;
	private int playerY;
	
	public Chair(int chairBlock, int playerDirection, int chairDirection, int playerXLoc, int playerYLoc){
		this.chairLoc = chairBlock;
		this.chairDir = chairDirection;
		this.playerSitDir = playerDirection;
		this.playerX = playerXLoc;
		this.playerY = playerYLoc;
		this.extra = false;
	}
	public Chair(int chairBlock, int playerDirection, int chairDirection, boolean extraSumthin, int playerXLoc, int playerYLoc){
		this.chairLoc = chairBlock;
		this.chairDir = chairDirection;
		this.playerSitDir = playerDirection;
		this.playerX = playerXLoc;
		this.playerY = playerYLoc;
		this.extra = extraSumthin;
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
	public boolean isExtra() {
		return extra;
	}
	public void setExtra(boolean extra) {
		this.extra = extra;
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
