package monkWT.model;

public class Door {

	/*if(lvl.isPlayerInside()){
		   //Building 12 : Large Orange House Left Side
		   if(buildingIn == 12){
			   if(playerLocalLeftBtm == 579 || playerLocalRightBtm == 579
					   || playerLocalLeftBtm == 580 || playerLocalRightBtm == 580){
				   int xDist = -280;
				   int yDist = -160;
				   lvl.setBuildingEnt(0);
				   entBuilding(0, xDist, yDist);
			   }
		   }
	}*/
	private int blockLoc;
	private int xMoveDist;
	private int yMoveDist;
	private boolean inside;
	private int buildingEnt;
	
	public Door(boolean intOrOutf, int location, int building, int xMove, int yMove){
		inside = intOrOutf;
		blockLoc = location;
		buildingEnt = building;
		xMoveDist = xMove;
		yMoveDist = yMove;
	}
	
	
	
	public int getBlockLoc() {
		return blockLoc;
	}
	public void setBlockLoc(int blockLoc) {
		this.blockLoc = blockLoc;
	}
	public int getxMoveDist() {
		return xMoveDist;
	}
	public void setxMoveDist(int xMoveDist) {
		this.xMoveDist = xMoveDist;
	}
	public int getyMoveDist() {
		return yMoveDist;
	}
	public void setyMoveDist(int yMoveDist) {
		this.yMoveDist = yMoveDist;
	}
	public boolean isInside() {
		return inside;
	}
	public void setInside(boolean inside) {
		this.inside = inside;
	}
	public int getBuildingEnt() {
		return buildingEnt;
	}
	public void setBuildingEnt(int buildingEnt) {
		this.buildingEnt = buildingEnt;
	}   
}
