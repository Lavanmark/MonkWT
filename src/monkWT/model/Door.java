package monkWT.model;

public class Door {
	
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
