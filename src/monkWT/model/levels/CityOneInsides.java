package monkWT.model.levels;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import monkWT.model.Chair;
import monkWT.model.Door;
import resources.ResourceLoader;

public class CityOneInsides {
	
	/*0 = none 
	 *  1  = 
	 *  2  = 
	 *  12 = Large Orange House Left
	 *  13 = Large Orange House Right
	 *  14 = Small Orange House Left Top
	 *  15 = Small Orange House Right Top
	 *  16 = Small Orange House Left Bottom
	 *  17 = Small Orange House Right Bottom
	 *  18 = Bus Station
	 *  19 = Hotel F1
	 *  20 = Hotel F2
	 *  21 = Bargain Palace
	 *  22 = Club Sizzle 
	 *  23 = Town Hall
	 *  24 = Monestary
	*/
	
	public Tile[][] city1Inside = new Tile[24][1200];
	public Rectangle[] city1InsideBlocks = new Rectangle[1200];
	
	private TileLoader tl;
	
	//Doors
		//map<(int) Map section, set of doors>
	public HashMap<Integer, Set<Door>> doorsInsideOne = new HashMap<Integer, Set<Door>>();
	public HashMap<Integer, Set<Chair>> chairsInsideOne = new HashMap<Integer, Set<Chair>>();

	
	
	
	
	
	
	public void cityOneInsidesInit(TileLoader tlin){
		tl = tlin;
		int arrayNum = 1200;
 		int x = 0, y = 0;	
		
		for(int i=0; i<arrayNum; i++){
			if(x>=800){
				x = 0;
				y+=20;
			}
			city1InsideBlocks[i] = new Rectangle(x, y, 20, 20);
			x+=20;
		}
		ResourceLoader rl = new ResourceLoader();
		tl.setInsideSquares();
		try{
			city1Inside[11] = setMapSecs(rl.getMap("city1", true, "city1iLargeOrangeHouse"));
			city1Inside[12] = setMapSecs(rl.getMap("city1", true, "city1iLargeOrangeHouse"));
			city1Inside[13] = setMapSecs(rl.getMap("city1", true, "city1iSmallOrangeHouseDwn"));
			city1Inside[14] = setMapSecs(rl.getMap("city1", true, "city1iSmallOrangeHouseDwn"));
			city1Inside[15] = setMapSecs(rl.getMap("city1", true, "city1iSmallOrangeHouseUp"));
			city1Inside[16] = setMapSecs(rl.getMap("city1", true, "city1iSmallOrangeHouseUp"));
			city1Inside[17] = setMapSecs(rl.getMap("city1", true, "city1iBusStation"));
			city1Inside[18] = setMapSecs(rl.getMap("city1", true, "city1iHotelF1"));
			city1Inside[19] = setMapSecs(rl.getMap("city1", true, "city1iHotelF2"));
			city1Inside[21] = setMapSecs(rl.getMap("city1", true, "city1iClub"));
			city1Inside[23] = setMapSecs(rl.getMap("city1", true, "city1iMonestary"));
		}catch(IOException e){
			e.printStackTrace();
		}
		addInsideDoors();
		addInsideChairs();
	}
	public Tile[] setMapSecs(int[] mapIn){
		Tile[] m = new Tile[1200];
		
		for(int i = 0; i < 1200; i++){
			m[i] = tl.tileArray[mapIn[i]];
		}
		return m;
	}
	
	
	public void addInsideDoors(){
		Set<Door> doors = new HashSet<Door>();
		
		doors.add(new Door(true, 579, 0, -280, -160));
		doors.add(new Door(true, 580, 0, -280, -160));
		
		doorsInsideOne.put(12, doors);
		doors = new HashSet<Door>();
		
		doors.add(new Door(true, 579, 0, -120, -160));
		doors.add(new Door(true, 580, 0, -120, -160));
		
		doorsInsideOne.put(13, doors);
		doors = new HashSet<Door>();
		
		doors.add(new Door(true, 580, 0, 120, -160));
		
		doorsInsideOne.put(14, doors);
		doors = new HashSet<Door>();
		
		doors.add(new Door(true, 580, 0, 300, -160));
		
		doorsInsideOne.put(15, doors);
		doors = new HashSet<Door>();
		
		doors.add(new Door(true, 380, 0, 120,-40));
		
		doorsInsideOne.put(16, doors);
		doors = new HashSet<Door>();
		
		doors.add(new Door(true, 380, 0, 300, -120));
		
		doorsInsideOne.put(17, doors);
		doors = new HashSet<Door>();
		
		doors.add(new Door(true, 627, 0, -400, 0));
		doors.add(new Door(true, 667, 0, -400, 0));
		doors.add(new Door(true, 707, 0, -400, 0));
		
		doorsInsideOne.put(18, doors);
		doors = new HashSet<Door>();
		
		doors.add(new Door(true, 577, 0, 0, 100));
		doors.add(new Door(true, 578, 0, 0, 100));
		doors.add(new Door(true, 579, 0, 0, 100));
		doors.add(new Door(true, 580, 0, 0, 100));
		doors.add(new Door(true, 581, 0, 0, 100));
		doors.add(new Door(true, 582, 0, 0, 100));
		doors.add(new Door(true, 583, 0, 0, 100));
		//stairs only need one because they are at same location
		doors.add(new Door(true, 1157, 20, 0, -5));
		doors.add(new Door(true, 1158, 20, 0, -5));

		doorsInsideOne.put(19, doors);
		doors = new HashSet<Door>();
		
		doors.add(new Door(true, 1157, 19, 0, -5));
		doors.add(new Door(true, 1158, 19, 0, -5));

		doorsInsideOne.put(20, doors);
		doors = new HashSet<Door>();
		
		doors.add(new Door(true, 525, 0, 340, -140));
		doors.add(new Door(true, 565, 0, 340, -140));
		doors.add(new Door(true, 605, 0, 340, -140));

		doorsInsideOne.put(22, doors);
	}
	
	private void addInsideChairs(){
		Set<Chair> chairs = new HashSet<Chair>();
		
		chairs.add(new Chair(420, 0, 0, 0, 402, 204));
		
		chairsInsideOne.put(12, chairs);
		chairs = new HashSet<Chair>();
		
		chairs.add(new Chair(420, 0, 0, 0, 402, 204));
		
		chairsInsideOne.put(13, chairs);
		chairs = new HashSet<Chair>();
		
		chairs.add(new Chair(420, 0, 0, 0, 402, 204));
		
		chairsInsideOne.put(14, chairs);
		chairs = new HashSet<Chair>();

		chairs.add(new Chair(420, 0, 0, 0, 402, 204));
		
		chairsInsideOne.put(15, chairs);
		chairs = new HashSet<Chair>();

		chairs.add(new Chair(540, 1, 1, 0, 402, 264));
		
		chairsInsideOne.put(16, chairs);
		chairs = new HashSet<Chair>();

		chairs.add(new Chair(540, 1, 1, 0, 402, 264));
		
		chairsInsideOne.put(17, chairs);
		chairs = new HashSet<Chair>();

		chairs.add(new Chair(821, 1, 1, 0, 424, 400));
		chairs.add(new Chair(822, 1, 1, 0, 440, 400));
		chairs.add(new Chair(824, 1, 1, 0, 484, 400));
		chairs.add(new Chair(825, 1, 1, 0, 500, 400));
		chairs.add(new Chair(741, 0, 0, 0, 424, 362));
		chairs.add(new Chair(742, 0, 0, 0, 440, 362));
		chairs.add(new Chair(744, 0, 0, 0, 484, 362));
		chairs.add(new Chair(745, 0, 0, 0, 500, 362));
		//TODO add when you sit on a bench it takes you to where ever you have a ticket to.
		chairs.add(new Chair(533, 2, 2, 0, 262, 264));
		chairs.add(new Chair(573, 2, 2, 0, 262, 280));
		chairs.add(new Chair(733, 0, 0, 0, 262, 364));
		chairs.add(new Chair(773, 0, 0, 0, 262, 380));

		chairsInsideOne.put(18, chairs);
		chairs = new HashSet<Chair>();
		
		//Lobby Couches
		chairs.add(new Chair(668, 2, 2, 0, 562, 324));
		chairs.add(new Chair(708, 2, 2, 0, 562, 340));
		chairs.add(new Chair(788, 2, 2, 0, 562, 384));
		chairs.add(new Chair(828, 2, 2, 0, 562, 400));
		//Room 101
		chairs.add(new Chair(1044, 2, 2, 0, 82, 524));
		chairs.add(new Chair(1084, 2, 2, 0, 82, 540));
		//Room 103
		chairs.add(new Chair(1049, 2, 2, 0, 182, 524));
		chairs.add(new Chair(1089, 2, 2, 0, 182, 540));
		//Room 105
		chairs.add(new Chair(1054, 2, 2, 0, 282, 524));
		chairs.add(new Chair(1094, 2, 2, 0, 282, 540));
		//Room 107
		chairs.add(new Chair(1059, 2, 2, 0, 382, 524));
		chairs.add(new Chair(1099, 2, 2, 0, 382, 540));
		//Room 109
		chairs.add(new Chair(1064, 2, 2, 0, 482, 524));
		chairs.add(new Chair(1104, 2, 2, 0, 482, 540));
		//Room 111
		chairs.add(new Chair(1069, 2, 2, 0, 582, 524));
		chairs.add(new Chair(1109, 2, 2, 0, 582, 540));
		//Room 113
		chairs.add(new Chair(1074, 2, 2, 0, 682, 524));
		chairs.add(new Chair(1114, 2, 2, 0, 682, 540));
		
		chairsInsideOne.put(19, chairs);
		chairs = new HashSet<Chair>();
		
		//Room 202
			//couch
		chairs.add(new Chair(650, 2, 2, 0, 202, 324));
		chairs.add(new Chair(690, 2, 2, 0, 202, 340));
		chairs.add(new Chair(770, 2, 2, 0, 202, 384));
		chairs.add(new Chair(810, 2, 2, 0, 202, 400));
			//table chairs
		chairs.add(new Chair(645, 0, 2, 0, 102, 324));
		chairs.add(new Chair(646, 0, 3, 0, 122, 324));
		chairs.add(new Chair(765, 1, 2, 0, 102, 380));
		chairs.add(new Chair(766, 1, 3, 0, 122, 380));
		//Room 206
			//couch
		chairs.add(new Chair(659, 2, 2, 0, 382, 324));
		chairs.add(new Chair(699, 2, 2, 0, 382, 340));
		chairs.add(new Chair(779, 2, 2, 0, 382, 384));
		chairs.add(new Chair(819, 2, 2, 0, 382, 400));
			//table chairs
		chairs.add(new Chair(655, 0, 2, 0, 302, 324));
		chairs.add(new Chair(775, 1, 2, 0, 302, 380));
		chairs.add(new Chair(776, 1, 3, 0, 322, 380));
		//Room 210
			//couch
		chairs.add(new Chair(668, 2, 2, 0, 562, 324));
		chairs.add(new Chair(708, 2, 2, 0, 562, 340));
		chairs.add(new Chair(788, 2, 2, 0, 562, 384));
		chairs.add(new Chair(828, 2, 2, 0, 562, 400));
			//table chairs
		chairs.add(new Chair(664, 0, 2, 0, 482, 324));
		chairs.add(new Chair(665, 0, 3, 0, 502, 324));
		chairs.add(new Chair(784, 1, 2, 0, 482, 380));
		chairs.add(new Chair(785, 1, 3, 0, 502, 380));
		//Room 214
			//couch
		chairs.add(new Chair(678, 2, 2, 0, 762, 324));
		chairs.add(new Chair(718, 2, 2, 0, 762, 340));
		chairs.add(new Chair(798, 2, 2, 0, 762, 384));
		chairs.add(new Chair(838, 2, 2, 0, 762, 400));
			//table chairs
		chairs.add(new Chair(674, 0, 2, 0, 682, 324));
		chairs.add(new Chair(675, 0, 3, 0, 702, 324));
		chairs.add(new Chair(794, 1, 2, 0, 682, 380));
		chairs.add(new Chair(795, 1, 3, 0, 702, 380));
		//Room 201
		chairs.add(new Chair(1044, 2, 2, 0, 82, 524));
		chairs.add(new Chair(1084, 2, 2, 0, 82, 540));
		//Room 203
		chairs.add(new Chair(1049, 2, 2, 0, 182, 524));
		chairs.add(new Chair(1089, 2, 2, 0, 182, 540));
		//Room 205
		chairs.add(new Chair(1054, 2, 2, 0, 282, 524));
		chairs.add(new Chair(1094, 2, 2, 0, 282, 540));
		//Room 207
		chairs.add(new Chair(1059, 2, 2, 0, 382, 524));
		chairs.add(new Chair(1099, 2, 2, 0, 382, 540));
		//Room 209
		chairs.add(new Chair(1064, 2, 2, 0, 482, 524));
		chairs.add(new Chair(1104, 2, 2, 0, 482, 540));
		//Room 211
		chairs.add(new Chair(1069, 2, 2, 0, 582, 524));
		chairs.add(new Chair(1109, 2, 2, 0, 582, 540));
		//Room 213
		chairs.add(new Chair(1074, 2, 2, 0, 682, 524));
		chairs.add(new Chair(1114, 2, 2, 0, 682, 540));
		
		chairsInsideOne.put(20, chairs);
		chairs = new HashSet<Chair>();
		
		//Booth top left
		chairs.add(new Chair(206, 3, 0, 0, 120, 100));
		chairs.add(new Chair(208, 2, 0, 0, 164, 100));
		chairs.add(new Chair(166, 3, 0, 1, 120, 84));
		chairs.add(new Chair(168, 2, 0, 1, 164, 84));
		//Booth top middle left
		chairs.add(new Chair(209, 3, 0, 0, 180, 100));
		chairs.add(new Chair(211, 2, 0, 0, 224, 100));
		chairs.add(new Chair(169, 3, 0, 1, 180, 84));
		chairs.add(new Chair(171, 2, 0, 1, 224, 84));
		//Booth top middle right
		chairs.add(new Chair(212, 3, 0, 0, 240, 100));
		chairs.add(new Chair(214, 2, 0, 0, 284, 100));
		chairs.add(new Chair(172, 3, 0, 1, 240, 84));
		chairs.add(new Chair(174, 2, 0, 1, 284, 84));
		//Booth top right
		chairs.add(new Chair(215, 3, 0, 0, 300, 100));
		chairs.add(new Chair(217, 2, 0, 0, 344, 100));
		chairs.add(new Chair(175, 3, 0, 1, 300, 84));
		chairs.add(new Chair(177, 2, 0, 1, 344, 84));
		//Booth bottom left
		chairs.add(new Chair(1006, 3, 1, 2, 120, 500));
		chairs.add(new Chair(1008, 2, 1, 2, 164, 500));
		chairs.add(new Chair(966, 3, 1, 0, 120, 484));
		chairs.add(new Chair(968, 2, 1, 0, 164, 484));
		//Booth bottom middle left
		chairs.add(new Chair(1009, 3, 1, 2, 180, 500));
		chairs.add(new Chair(1011, 2, 1, 2, 224, 500));
		chairs.add(new Chair(969, 3, 1, 0, 180, 484));
		chairs.add(new Chair(971, 2, 1, 0, 224, 484));
		//Booth bottom middle right
		chairs.add(new Chair(1012, 3, 1, 2, 240, 500));
		chairs.add(new Chair(1014, 2, 1, 2, 284, 500));
		chairs.add(new Chair(972, 3, 1, 0, 240, 484));
		chairs.add(new Chair(974, 2, 1, 0, 284, 484));
		//Booth bottom right
		chairs.add(new Chair(1015, 3, 1, 2, 300, 500));
		chairs.add(new Chair(1017, 2, 1, 2, 344, 500));
		chairs.add(new Chair(975, 3, 1, 0, 300, 484));
		chairs.add(new Chair(977, 2, 1, 0, 344, 484));
		//Bar stools
		chairs.add(new Chair(339, 3, 2, 0, 382, 160));
		chairs.add(new Chair(419, 3, 2, 0, 382, 200));
		chairs.add(new Chair(499, 3, 2, 0, 382, 240));
		chairs.add(new Chair(579, 3, 2, 0, 382, 280));
		chairs.add(new Chair(659, 3, 2, 0, 382, 320));
		chairs.add(new Chair(739, 3, 2, 0, 382, 360));
		chairs.add(new Chair(819, 3, 2, 0, 382, 400));

		chairsInsideOne.put(22, chairs);
		chairs = new HashSet<Chair>();
		//TODO add monestary chairs.
	}
	
	public void draw(Graphics g, int buildIn){
		int s = buildIn-1;
		for(int i = 0; i < 1200; i++){
			g.drawImage(city1Inside[s][i].getImage(), city1InsideBlocks[i].x, city1InsideBlocks[i].y, null);
		}
	}
}
