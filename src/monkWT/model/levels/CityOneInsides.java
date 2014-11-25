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
	private int buildingEnt = 0;
	
	public Tile[][] city1Inside = new Tile[24][1200];
	public Rectangle[] city1InsideBlocks = new Rectangle[1200];
	
	private TileLoader tl;
	
	//Doors
		//map<(int) Map section, set of doors>
	public HashMap<Integer, Set<Door>> doorsInsideOne = new HashMap<Integer, Set<Door>>();
	public HashMap<Integer, Set<Chair>> chairsInsideOne = new HashMap<Integer, Set<Chair>>();

	
	
	public void setBuildingEnt(int buildingNum){
		buildingEnt = buildingNum;
	}
	
	
	
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
		/*
		//loadLargeHouseOrange();
		//loadSmallHouseOrange();
		//changeSmallOrangeHouse();
		//loadBusStation();
		//loadHotelF1();
		//loadClub();
		
		try {
			//rl.saveMap(city1Inside[11], "city1iLargeOrangeHouse");
			//rl.saveMap(city1Inside[13], "city1iSmallOrangeHouseDwn");
			//changeSmallOrangeHouse();
			//rl.saveMap(city1Inside[15], "city1iSmallOrangeHouseUp");
			//rl.saveMap(city1Inside[17], "city1iBusStation");
			//rl.saveMap(city1Inside[18], "city1iHotelF1");
			//rl.saveMap(city1Inside[19], "city1iHotelF2");
			//rl.saveMap(city1Inside[21], "city1iClub");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
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
		//Booth top middle right
		chairs.add(new Chair(1012, 3, 1, 2, 240, 500));
		chairs.add(new Chair(1014, 2, 1, 2, 284, 500));
		chairs.add(new Chair(972, 3, 1, 0, 240, 484));
		chairs.add(new Chair(974, 2, 1, 0, 284, 484));
		//Booth top right
		chairs.add(new Chair(1015, 3, 1, 2, 300, 500));
		chairs.add(new Chair(1017, 2, 1, 2, 344, 500));
		chairs.add(new Chair(975, 3, 1, 0, 300, 484));
		chairs.add(new Chair(977, 2, 1, 0, 344, 484));
		//Bar stools
		chairs.add(new Chair(219, 2, 0, 0, 300, 100));
		
		chairsInsideOne.put(22, chairs);
		chairs = new HashSet<Chair>();
		//TODO finish booths here and then bar stools
		//TODO add monestary chairs.
	}
	
	public int getBuildingInArray(){
		return (buildingEnt-1);
	}
	public int getBuildingIn(){
		return buildingEnt;
	}
	/*
	private void loadLargeHouseOrange(){
		int arrayNum = 1200;
		int s = 11;
		int x = 0, y = 0;
		for(int i = 0; i<arrayNum; i++){
			if(x>=800){
				x = 0;
				y+=20;
			}
			//blackness
			if(i>=0 && i < arrayNum){
				city1Inside[s][i] = tl.TILE_BLANK;
			}
			//wall
			if(i > 375 && i < 384 || i > 415 && i < 424 || i > 455 && i < 464 || i > 495 && i < 504 || i > 535 && i < 544 || i > 575 && i < 584){
				city1Inside[s][i] = tl.TILE_OUT_HOUSE_ORANGE;
			}
			//carpet
			if(i > 416 && i < 423 || i > 456 && i < 463 || i > 496 && i < 503 || i > 536 && i < 543){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP;
			}
			//door
			if(i == 579||i == 580){
				city1Inside[s][i] = tl.TILE_OUT_HOUSE_ORANGE_DOOR_DOWN;
			}

			//bed
			if(i == 457){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_BED_TOP;
			}
			if(i == 497){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_BED_BTM;
			}
			//chair
			if(i == 420){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_CHAIR_DOWN;
			}
			//table
			if(i == 421){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TTL;
			}
			if(i == 422){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TTR;
			}
			if(i == 461){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TBL;
			}
			if(i == 462){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TBR;
			}
			x+=20;
		}
	}
	
	private void loadSmallHouseOrange(){
		int arrayNum = 1200;
		int s = 13;
		int x = 0, y = 0;
		for(int i = 0; i<arrayNum; i++){
			if(x>=800){
				x = 0;
				y+=20;
			}
			//blackness
			if(i>=0 && i < arrayNum){
				city1Inside[s][i] = tl.TILE_BLANK;
			}
			//wall
			if(i > 376 && i < 384 || i > 416 && i < 424 || i > 456 && i < 464 || i > 496 && i < 504 || i > 536 && i < 544 || i > 576 && i < 584){
				city1Inside[s][i] = tl.TILE_OUT_HOUSE_ORANGE;
			}
			//carpet
			if(i > 417 && i < 423 || i > 457 && i < 463 || i > 497 && i < 503 || i > 537 && i < 543){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP;
			}
			//door
			if(i == 580){
				city1Inside[s][i] = tl.TILE_OUT_HOUSE_ORANGE_DOOR_DOWN;
			}
			//bed
			if(i == 458){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_BED_TOP;
			}	
			if(i == 498){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_BED_BTM;
			}
			//chair
			if(i == 420){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_CHAIR_DOWN;
			}
			//table
			if(i == 421){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TTL;
			}
			if(i == 422){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TTR;
			}
			if(i == 461){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TBL;
			}
			if(i == 462){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TBR;
			}
			
		x+=20;	
		}
	}
	private void changeSmallOrangeHouse(){
		int s = 15;
		city1Inside[s][380] = tl.TILE_OUT_HOUSE_ORANGE_DOOR_UP;
		city1Inside[s][580] = tl.TILE_OUT_HOUSE_ORANGE;
		city1Inside[s][420] = tl.TILE_IN_GREEN_CARP;
		city1Inside[s][540] = tl.TILE_IN_GREEN_CARP_CHAIR_UP;
		city1Inside[s][421] = tl.TILE_IN_GREEN_CARP;
		city1Inside[s][422] = tl.TILE_IN_GREEN_CARP;
		city1Inside[s][461] = tl.TILE_IN_GREEN_CARP;
		city1Inside[s][462] = tl.TILE_IN_GREEN_CARP;
		city1Inside[s][501] = tl.TILE_IN_GREEN_CARP_TTL;
		city1Inside[s][502] = tl.TILE_IN_GREEN_CARP_TTR;
		city1Inside[s][541] = tl.TILE_IN_GREEN_CARP_TBL;
		city1Inside[s][542] = tl.TILE_IN_GREEN_CARP_TBR;
		
	}
	private void loadBusStation(){
		int arrayNum = 1200;		
		int s = 17;
		int x = 0, y = 0;
		for(int i = 0; i<arrayNum; i++){
			if(x>=800){
				x = 0;
				y+=20;
			}
			//blackness
			if(i>=0 && i < arrayNum){
				city1Inside[s][i] = tl.TILE_BLANK;
				
			}
			//grass outside
			if((i/20)%2 == 0){
				city1Inside[s][i] = tl.TILE_GRASS_BASIC;
			}
			//bus sides
			if(i > 459 && i < 468 || i > 499 && i < 508 || i > 539 && i < 548 || i > 579 && i < 588 || i > 619 && i < 628
					|| i > 659 && i < 668 || i > 699 && i < 708 || i > 739 && i < 748 || i > 779 && i < 788 || i > 819 && i < 828
					|| i > 859 && i < 868){
				city1Inside[s][i] = tl.TILE_OUT_BUS;
			}
			//floor of bus station
			if(i > 500 && i < 507 || i > 540 && i < 547 || i > 580 && i < 587 || i > 619 && i < 627
					|| i > 659 && i < 667 || i > 699 && i < 707 || i > 740 && i < 747 || i > 780 && i < 787 || i > 820 && i < 827){
				city1Inside[s][i] = tl.TILE_IN_YELLOW_PAT_CREME;
			}
			//door and floor mat
			if(i == 627 || i == 667 || i == 707){
				city1Inside[s][i] = tl.TILE_OUT_DOOR_BUS;
				city1Inside[s][i-1] = tl.TILE_IN_YELLOW_PAT_CREME_FMAT;
			}
			//desk
			if(i > 540 && i < 545){
				city1Inside[s][i] = tl.TILE_IN_YELLOW_PAT_CREME_DESK;
			}
			//benches inside
			if(i == 821 || i == 824){
				city1Inside[s][i] = tl.TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_UP;
			}
			if(i == 822 || i == 825){
				city1Inside[s][i] = tl.TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_UP;
			}
			if(i == 741 || i == 744){
				city1Inside[s][i] = tl.TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_DOWN;
			}
			if(i == 742 || i == 745){
				city1Inside[s][i] = tl.TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_DOWN;
			}
			//fence
			if(i == 20 || i == 60 || i == 100 || i == 140 || i == 180 || i == 220 || i == 260 || i == 300 || i == 340
					|| i == 380 || i == 420 || i == 900 || i == 940 || i == 980
					|| i == 1020 || i == 1060 || i == 1100 || i == 1140 || i == 1180){
				city1Inside[s][i] = tl.TILE_GRASS_FENCE_VERT;
			}
			//street
			if(i > 2 && i < 11 || i > 42 && i < 51 || i > 82 && i < 91 || i > 122 && i < 131 || i > 162 && i < 171
					|| i > 202 && i < 211 || i > 242 && i < 251 || i > 282 && i < 291 || i > 322 && i < 331 || i > 362 && i < 371
					|| i > 402 && i < 411 || i > 442 && i < 451 || i > 482 && i < 491 || i > 522 && i < 531 || i > 562 && i < 571
					|| i > 602 && i < 611 || i > 642 && i < 651 || i > 682 && i < 691 || i > 722 && i < 731 || i > 762 && i < 771
					|| i > 802 && i < 811 || i > 842 && i < 851 || i > 882 && i < 891 || i > 922 && i < 931 || i > 962 && i < 971
					|| i > 1002 && i < 1011 || i > 1042 && i < 1051 || i > 1082 && i < 1091 || i > 1122 && i < 1131 || i > 1162 && i < 1171){
				city1Inside[s][i] = tl.TILE_STREET_BASIC;
			}
			//street with white line on left
			if(i == 3 || i == 43 || i == 83 || i == 123 || i == 163 || i == 203 || i == 243 || i == 283 || i == 323 || i == 363 
					|| i == 403 || i == 443 || i == 483 || i == 523 || i == 563 || i == 603 || i == 643 || i == 683 || i == 723
					|| i == 763 || i == 803 || i == 843 || i == 883 || i == 923 || i == 963 || i == 1003 || i == 1043 || i == 1083
					|| i == 1123 || i == 1163){
				city1Inside[s][i] = tl.TILE_STREET_LEFT;
			}
			//street with center lines
			if(i == 7 || i == 47 || i == 87 || i == 127 || i == 167 || i == 207 || i == 247 || i == 287 || i == 327 || i == 367 
					|| i == 407 || i == 447 || i == 487 || i == 527 || i == 567 || i == 607 || i == 647 || i == 687 || i == 727
					|| i == 767 || i == 807 || i == 847 || i == 887 || i == 927 || i == 967 || i == 1007 || i == 1047 || i == 1087
					|| i == 1127 || i == 1167){
				city1Inside[s][i] = tl.TILE_STREET_CENT;
			}
			//street with white line on right
			if(i == 11 || i == 51 || i == 91 || i == 131 || i == 171 || i == 211 || i == 251 || i == 291 || i == 331 || i == 371 
					|| i == 411 || i == 451 || i == 491 || i == 531 || i == 571 || i == 611 || i == 651 || i == 691 || i == 731
					|| i == 771 || i == 811 || i == 851 || i == 891 || i == 931 || i == 971 || i == 1011 || i == 1051 || i == 1091
					|| i == 1131 || i == 1171){
				city1Inside[s][i] = tl.TILE_STREET_RIGHT;
			}
			//path to road
			if(i == 532 || i == 572 || i > 611 && i < 620 || i > 651 && i < 660 || i > 691 && i < 700 || i == 732 || i == 772){
				city1Inside[s][i] = tl.TILE_PATH_GREY_PAT_RED;
			}
			//benches on road
			if(i == 733 || i == 533){
				city1Inside[s][i] = tl.TILE_GRASS_BENCH_LEFT_TOP;
			}
			if(i == 773 || i == 573){
				city1Inside[s][i] = tl.TILE_GRASS_BENCH_LEFT_BTM;
			}
			
			
			x+=20;
		}
	}
	private void loadHotelF1(){
		int arrayNum = 1200;
		int s = 18;
		int x=0,y=0;
		for(int i = 0; i < arrayNum; i++){
			if(x >= 800){
				y+=20;
				x = 0;
			}
			//blackness
			if(i >= 0 && i < arrayNum){
				city1Inside[s][i] = tl.TILE_BLANK;
			}
			//Hotel walls
			if(i > 559 && i < arrayNum){
				city1Inside[s][i] = tl.TILE_OUT_HOTEL;
			}
			//hotel door
			if(i > 577 && i < 583){
				city1Inside[s][i] = tl.TILE_OUT_HOTEL_DOOR;
			}
			//hotel floor
			if(i > 600 && i < 639 || i > 640 && i < 679 || i > 680 && i < 719 || i > 720 && i < 759 || i > 760 && i < 799 || i > 800 && i < 839
					|| i > 851 && i < 869 || i > 880 && i < 919 || i > 920 && i < 959 || i > 1000 && i < 1039
					|| i > 1040 && i < 1079 || i > 1080 && i < 1119 || i > 1120 && i < 1159
					|| i == 963 || i == 968 || i == 973 || i == 978 || i == 983 || i == 988 || i == 993 || i == 997 || i == 998
					|| i == 844 || i == 875){
				city1Inside[s][i] = tl.TILE_IN_WHITE_PAT_PINK_MARB;
 			}
			//Hotel walls in certain places
			if(i == 811 || i == 771 || i == 731 || i == 691 || i == 651 || i == 611
					|| i == 829 || i == 789 || i == 749 || i == 709 || i == 669 || i == 629
					|| i == 1125 || i == 1085 || i == 1045 || i == 1005
					|| i == 1130 || i == 1090 || i == 1050 || i == 1010
					|| i == 1135 || i == 1095 || i == 1055 || i == 1015
					|| i == 1140 || i == 1100 || i == 1060 || i == 1020
					|| i == 1145 || i == 1105 || i == 1065 || i == 1025
					|| i == 1150 || i == 1110 || i == 1070 || i == 1030
					|| i == 1155 || i == 1115 || i == 1075 || i == 1035){
				city1Inside[s][i] = tl.TILE_OUT_HOTEL;
 			}
			//Lobby desk
			if(i == 814 || i == 774 || i == 734 || i == 694 || i == 654 || i == 614){
				city1Inside[s][i] = tl.TILE_IN_WHITE_PAT_PINK_MARB_DESK;
 			}
			//couches
			if(i == 668 || i == 788 || i == 1044 || i == 1049 || i == 1054 || i == 1059 || i == 1064 || i == 1069 || i == 1074){
				city1Inside[s][i] = tl.TILE_IN_WHITE_PAT_PINK_MARB_COUCH_TOP;
 			}
			if(i == 708 || i == 828 || i == 1084 || i == 1089 || i == 1094 || i == 1099 || i == 1104 || i == 1109 || i == 1114){
				city1Inside[s][i] = tl.TILE_IN_WHITE_PAT_PINK_MARB_COUCH_BTM;
 			}
			//beds
			if(i == 1041 || i == 1046 || i == 1051 || i == 1056 || i == 1061 || i == 1066 || i == 1071){
				city1Inside[s][i] = tl.TILE_IN_WHITE_PAT_PINK_MARB_BED_TOP;
 			}
			if(i == 1081 || i == 1086 || i == 1091 || i == 1096 || i == 1101 || i == 1106 || i == 1111){
				city1Inside[s][i] = tl.TILE_IN_WHITE_PAT_PINK_MARB_BED_BTM;
 			}
			//stairs
			if(i == 1157 || i == 1158 || i == 1117 || i == 1118 ){
				city1Inside[s][1117] = tl.TILE_IN_BLACK_STAIR_TL_U;
				city1Inside[s][1118] = tl.TILE_IN_BLACK_STAIR_TR_U;
				city1Inside[s][1157] = tl.TILE_IN_BLACK_STAIR_BL_U;
				city1Inside[s][1158] = tl.TILE_IN_BLACK_STAIR_BR_U;
			}
			
			
			x+=20;
		}
		loadHotelF2();
	}
	private void loadHotelF2(){
		int arrayNum = 1200;
		int s = 19;
		int x=0,y=0;
		for(int i = 0; i < arrayNum; i++){
			if(x >= 800){
				y+=20;
				x = 0;
			}
			//blackness
			if(i >= 0 && i < arrayNum){
				city1Inside[s][i] = tl.TILE_BLANK;
			}
			//Hotel walls
			if(i > 559 && i < arrayNum){
				city1Inside[s][i] = tl.TILE_OUT_HOTEL;
			}
			//hotel floor
			if(i > 600 && i < 639 || i > 640 && i < 679 || i > 680 && i < 719 || i > 720 && i < 759 || i > 760 && i < 799 || i > 800 && i < 839
					|| i > 880 && i < 919 || i > 920 && i < 959 || i > 1000 && i < 1039
					|| i > 1040 && i < 1079 || i > 1080 && i < 1119 || i > 1120 && i < 1159
					|| i == 963 || i == 968 || i == 973 || i == 978 || i == 983 || i == 988 || i == 993 || i == 997 || i == 998
					|| i == 844 || i == 855 || i == 864 || i == 873){
				city1Inside[s][i] = tl.TILE_IN_WHITE_PAT_PINK_MARB;
 			}
			//Hotel walls in certain places
			if(i == 811 || i == 771 || i == 731 || i == 691 || i == 651 || i == 611
					|| i == 829 || i == 789 || i == 749 || i == 709 || i == 669 || i == 629
					|| i == 820 || i == 780 || i == 740 || i == 700 || i == 660 || i == 620
					
					|| i == 1125 || i == 1085 || i == 1045 || i == 1005
					|| i == 1130 || i == 1090 || i == 1050 || i == 1010
					|| i == 1135 || i == 1095 || i == 1055 || i == 1015
					|| i == 1140 || i == 1100 || i == 1060 || i == 1020
					|| i == 1145 || i == 1105 || i == 1065 || i == 1025
					|| i == 1150 || i == 1110 || i == 1070 || i == 1030
					|| i == 1155 || i == 1115 || i == 1075 || i == 1035){
				city1Inside[s][i] = tl.TILE_OUT_HOTEL;
 			}
			//couches
			if( i == 650 || i == 770 || i == 659 || i == 779 || i == 668 || i == 788 || i == 678 || i == 798
					|| i == 1044 || i == 1049 || i == 1054 || i == 1059 || i == 1064 || i == 1069 || i == 1074){
				city1Inside[s][i] = tl.TILE_IN_WHITE_PAT_PINK_MARB_COUCH_TOP;
 			}
			if(i == 690 || i == 810 || i == 699 || i == 819 ||i == 708 || i == 828 || i == 718 || i == 838
					|| i == 1084 || i == 1089 || i == 1094 || i == 1099 || i == 1104 || i == 1109 || i == 1114){
				city1Inside[s][i] = tl.TILE_IN_WHITE_PAT_PINK_MARB_COUCH_BTM;
 			}
			//beds
			//TODO Two beds in the big room and add a table and carpet and chair
			if(i == 641 || i == 761 || i == 652 || i == 772 || i == 661 || i == 781 || i == 670 || i == 790
					|| i == 1041 || i == 1046 || i == 1051 || i == 1056 || i == 1061 || i == 1066 || i == 1071){
				city1Inside[s][i] = tl.TILE_IN_WHITE_PAT_PINK_MARB_BED_TOP;
 			}
			if(i == 681 || i == 801 || i == 692 || i == 812 || i == 701 || i == 821 || i == 710 || i == 830
					|| i == 1081 || i == 1086 || i == 1091 || i == 1096 || i == 1101 || i == 1106 || i == 1111){
				city1Inside[s][i] = tl.TILE_IN_WHITE_PAT_PINK_MARB_BED_BTM;
 			}
			//carpet
			if(i > 643 && i < 648 || i > 683 && i < 688 || i > 723 && i < 728 || i > 763 && i < 768
					|| i > 653 && i < 658 || i > 693 && i < 698 || i > 733 && i < 738 || i > 773 && i < 778
					|| i > 662 && i < 667 || i > 702 && i < 707 || i > 742 && i < 747 || i > 782 && i < 787
					|| i > 672 && i < 677 || i > 712 && i < 717 || i > 752 && i < 757 || i > 792 && i < 797){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP;
			}
			//tables
			if(i == 685 || i == 695 || i == 704 || i == 714){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TTL;
 			}
			if(i == 686 || i == 696 || i == 705 || i == 715){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TTR;
 			}
			if(i == 725 || i == 735 || i == 744 || i == 754){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TBL;
 			}
			if(i == 726 || i == 736 || i == 745 || i == 755){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_TBR;
 			}
			//chair
			if(i == 645 || i == 646
					|| i == 655 
					|| i == 664 || i == 665
					|| i == 674 || i == 675){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_CHAIR_DOWN;
 			}
			if(i == 765 || i == 766
					|| i == 775 || i == 776
					|| i == 784 || i == 785
					|| i == 794 || i == 795){
				city1Inside[s][i] = tl.TILE_IN_GREEN_CARP_CHAIR_UP;
 			}
			//stairs
			if(i == 1157 || i == 1158 || i == 1117 || i == 1118 ){
				city1Inside[s][1157] = tl.TILE_IN_BLACK_STAIR_TL_D;
				city1Inside[s][1158] = tl.TILE_IN_BLACK_STAIR_TR_D;
				city1Inside[s][1117] = tl.TILE_IN_BLACK_STAIR_BL_D;
				city1Inside[s][1118] = tl.TILE_IN_BLACK_STAIR_BR_D;
			}
			
			
			x+=20;
		}
	}
	private void loadClub(){
		int arrayNum = 1200;
		int s = 21;
		int x=0,y=0;
		for(int i = 0; i < arrayNum; i++){
			if(x >= 800){
				y +=20;
				x = 0;
			}
			//blackness
			if(i >= 0 && i < arrayNum){
				city1Inside[s][i] = tl.TILE_BLANK;
			}
			//walls of building
			if(i > 124 && i < 155 || i > 164 && i < 195 || i > 204 && i < 235 || i > 244 && i < 275 || i > 284 && i < 315
					|| i > 324 && i < 355 || i > 364 && i < 395 || i > 404 && i < 435 || i > 444 && i < 475 || i > 484 && i < 515
					|| i > 524 && i < 555 || i > 564 && i < 595 || i > 604 && i < 635 || i > 644 && i < 675 || i > 684 && i < 715
					|| i > 724 && i < 755 || i > 764 && i < 795 || i > 804 && i < 835 || i > 844 && i < 875 || i > 884 && i < 915
					|| i > 924 && i < 955 || i > 964 && i < 995 || i > 1004 && i < 1035 || i > 1044 && i < 1075 ){
				city1Inside[s][i] = tl.TILE_OUT_CLUB;
			}
			//door
			if(i == 525|| i == 565 || i == 605 ){
				city1Inside[s][i] = tl.TILE_OUT_CLUB_DOOR;
			}
			//floor
			if(i > 165 && i < 194 || i > 205 && i < 234 || i > 245 && i < 274 || i > 285 && i < 314
					|| i > 325 && i < 354 || i > 365 && i < 394 || i > 405 && i < 434 || i > 445 && i < 474 || i > 485 && i < 514
					|| i > 525 && i < 554 || i > 565 && i < 594 || i > 605 && i < 634 || i > 645 && i < 674 || i > 685 && i < 714
					|| i > 725 && i < 754 || i > 765 && i < 794 || i > 805 && i < 834 || i > 845 && i < 874 || i > 885 && i < 914
					|| i > 925 && i < 954 || i > 965 && i < 994 || i > 1005 && i < 1034){
				city1Inside[s][i] = tl.TILE_IN_WOOD_RED;
 			}
			//walls in specific places
			if(i == 184 || i == 224 || i == 264 || i == 304 || i == 344 || i == 384 || i == 424 || i == 464 || i == 504 || i == 544 
					|| i == 584 || i == 624 || i == 664 || i == 704 || i == 744 || i == 784 || i == 824 || i == 864 || i == 904 || i == 944){
				city1Inside[s][i] = tl.TILE_OUT_CLUB;
 			}
			//bar
			if(i == 300 || i == 340 || i == 380 || i == 420 || i == 460 || i == 500 || i == 540 || i == 580 || i == 620 || i == 660 || i == 700 || i == 740 || i == 780 || i == 820 || i == 860){
				city1Inside[s][i] = tl.TILE_IN_WOOD_RED_BAR;
 			}
			//bar stool
			if(i == 339 || i == 419 || i == 499 || i == 579 || i == 659 || i == 739 || i == 819){
				city1Inside[s][i] = tl.TILE_IN_WOOD_RED_STOOL;
 			}
			//booths
			if(i == 166 || i == 169 || i == 172 || i == 175
					|| i == 966 || i == 969 || i == 972 || i == 975){
				city1Inside[s][i] = tl.TILE_IN_WOOD_RED_BTL;
 			}
			if(i == 167 || i == 170 || i == 173 || i == 176
					|| i == 967 || i == 970 || i == 973 || i == 976){
				city1Inside[s][i] = tl.TILE_IN_WOOD_RED_TT;
 			}
			if(i == 168 || i == 171 || i == 174 || i == 177
					|| i == 968 || i == 971 || i == 974 || i == 977){
				city1Inside[s][i] = tl.TILE_IN_WOOD_RED_BTR;
 			}
			if(i == 206 || i == 209 || i == 212 || i == 215
					|| i == 1006 || i == 1009 || i == 1012 || i == 1015){
				city1Inside[s][i] = tl.TILE_IN_WOOD_RED_BBL;
 			}
			if(i == 207 || i == 210 || i == 213 || i == 216
					|| i == 1007 || i == 1010 || i == 1013 || i == 1016){
				city1Inside[s][i] = tl.TILE_IN_WOOD_RED_TB;
 			}
			if(i == 208 || i == 211 || i == 214 || i == 217
					|| i == 1008 || i == 1011 || i == 1014 || i == 1017){
				city1Inside[s][i] = tl.TILE_IN_WOOD_RED_BBR;
 			}
			//stage
			if(i > 409 && i < 417 || i > 449 && i < 457 || i > 489 && i < 497||i > 529 && i < 537 || i > 569 && i < 577 
					|| i > 609 && i < 617 || i > 649 && i < 657 || i > 689 && i < 697 || i > 729 && i < 737){
				city1Inside[s][i] = tl.TILE_IN_MAGENTA_STAGE;
 			}
			//stripper pole
			if(i == 491 || i == 495 || i == 651 || i == 655){
				city1Inside[s][i] = tl.TILE_IN_MAGENTA_STAGE_POLE;
			}
			//TODO add back room stuff and basement?
			
			
			x+=20;
		}
			
	}*/

	
	
	
	public void draw(Graphics g){
		int s = getBuildingInArray();
		for(int i = 0; i < 1200; i++){
			g.drawImage(city1Inside[s][i].getImage(), city1InsideBlocks[i].x, city1InsideBlocks[i].y, null);
		}
	}
}
