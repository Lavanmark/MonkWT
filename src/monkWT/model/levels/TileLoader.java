package monkWT.model.levels;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TreeMap;

import monkWT.controller.SheetGrabber;
import resources.ResourceLoader;

public class TileLoader {
	//Tile Image Names
	public Tile TILE_GRASS_BASIC, TILE_GRASS_FENCE_VERT, TILE_GRASS_FENCE_HORZ, TILE_GRASS_FENCE_TLC, TILE_GRASS_FENCE_TRC, 
				TILE_GRASS_FENCE_BLC, TILE_GRASS_FENCE_BRC, TILE_GRASS_SIGN, TILE_GRASS_FENCE_VERT_BLACK, TILE_GRASS_FENCE_HORZ_BLACK,
				TILE_GRASS_FENCE_TLC_BLACK, TILE_GRASS_FENCE_TRC_BLACK, TILE_GRASS_FENCE_BLC_BLACK, TILE_GRASS_FENCE_BRC_BLACK,
				TILE_GRASS_TOMB_JB, TILE_GRASS_TOMB_TY, TILE_GRASS_TOMB_FR, TILE_GRASS_TOMB_SQ, TILE_GRASS_BENCH_LEFT_DOWN, TILE_GRASS_BENCH_RIGHT_DOWN,
				TILE_GRASS_BENCH_LEFT_UP, TILE_GRASS_BENCH_RIGHT_UP,
				TILE_HOUSE_PURPLE, TILE_DOOR_HOUSE_PURPLE, TILE_HOUSE_ORANGE, TILE_DOOR_HOUSE_ORANGE, TILE_HOUSE_RED, TILE_DOOR_HOUSE_RED, 
				TILE_HOUSE_BLUE, TILE_DOOR_HOUSE_BLUE, TILE_HOUSE_WHITE, TILE_DOOR_HOUSE_WHITE, TILE_HOUSE_RED_PAT_GREY, TILE_DOOR_HOUSE_RED_PAT_GREY,
				TILE_HOUSE_BLACK_PAT_GREY, TILE_DOOR_HOUSE_BLACK_PAT_GREY,TILE_HOUSE_BROWN_PAT_MUST, TILE_DOOR_HOUSE_BROWN_PAT_MUST, TILE_HOUSE_SAND,
				TILE_DOOR_HOUSE_SAND_UP, TILE_DOOR_HOUSE_SAND_LEFT, TILE_HOUSE_LBLUE, TILE_DOOR_HOUSE_LBLUE_DOWN, TILE_DOOR_HOUSE_LBLUE_LEFT, 
				TILE_HOUSE_GOLD, TILE_DOOR_HOUSE_GOLD, TILE_HOUSE_GREY_PAT_BLUE, TILE_DOOR_HOUSE_GREY_PAT_BLUE, TILE_HOUSE_BLUE_PAT_PINK,
				TILE_DOOR_HOUSE_BLUE_PAT_PINK, TILE_HOUSE_WHITE_PAT_BLUE, TILE_DOOR_HOUSE_WHITE_PAT_BLUE_LEFT, TILE_DOOR_HOUSE_WHITE_PAT_BLUE_RIGHT, 
				TILE_HOUSE_WHITE_PAT_ORANGE, TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_UP, TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_DOWN, TILE_PATH_GREYRED, TILE_PATH_DIRT,
				TILE_BLANK, TILE_TREE_LEFT_CENT, TILE_TREE_LEFT_TOP, TILE_TREE_CENT_TOP, TILE_TREE_RIGHT_TOP, TILE_TREE_RIGHT_CENT, TILE_TREE_RIGHT_BTM,
				TILE_TREE_CENT_BTM, TILE_TREE_LEFT_BTM, TILE_TREE_CENT_CENT, TILE_HOUSE_BLACK_PAT_BROWN, TILE_DOOR_HOUSE_BLACK_PAT_BROWN, 
				TILE_DOOR_HOUSE_MINE, TILE_WATER_BASIC;
			
	public Tile  TILE_IN_YELLOW_PAT_CREME, TILE_IN_YELLOW_PAT_CREME_FMAT, TILE_IN_YELLOW_PAT_CREME_DESK, TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_UP,
			TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_UP, TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_DOWN, TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_DOWN,
			TILE_IN_GREEN_CARP, TILE_IN_GREEN_CARP_BED_TOP, TILE_IN_GREEN_CARP_BED_BTM, TILE_IN_GREEN_CARP_CHAIR_DOWN, TILE_IN_GREEN_CARP_CHAIR_UP, TILE_IN_GREEN_CARP_TTL, TILE_IN_GREEN_CARP_TTR,
			TILE_IN_GREEN_CARP_TBL, TILE_IN_GREEN_CARP_TBR,
			TILE_IN_WHITE_PAT_PINK_MARB, TILE_IN_WHITE_PAT_PINK_MARB_BED_TOP, TILE_IN_WHITE_PAT_PINK_MARB_BED_BTM, TILE_IN_WHITE_PAT_PINK_MARB_DESK, TILE_IN_WHITE_PAT_PINK_MARB_COUCH_TOP,
			TILE_IN_WHITE_PAT_PINK_MARB_COUCH_BTM, TILE_IN_BLACK_STAIR_TL_U, TILE_IN_BLACK_STAIR_TR_U, TILE_IN_BLACK_STAIR_BL_U, TILE_IN_BLACK_STAIR_BR_U,
			TILE_IN_BLACK_STAIR_TL_D, TILE_IN_BLACK_STAIR_TR_D, TILE_IN_BLACK_STAIR_BL_D, TILE_IN_BLACK_STAIR_BR_D,
			TILE_IN_WOOD_RED, TILE_IN_WOOD_RED_BAR, TILE_IN_WOOD_RED_STOOL, TILE_IN_WOOD_RED_BTL, TILE_IN_WOOD_RED_TT, TILE_IN_WOOD_RED_BTR, TILE_IN_WOOD_RED_BBL, 
			TILE_IN_WOOD_RED_TB, TILE_IN_WOOD_RED_BBR, TILE_IN_MAGENTA_STAGE, TILE_IN_MAGENTA_STAGE_POLE,
			TILE_OUT_BUS, TILE_OUT_DOOR_BUS, TILE_OUT_HOUSE_ORANGE, TILE_OUT_HOUSE_ORANGE_DOOR_UP, TILE_OUT_HOUSE_ORANGE_DOOR_DOWN,
			TILE_OUT_HOTEL, TILE_OUT_HOTEL_DOOR,
			TILE_OUT_CLUB, TILE_OUT_CLUB_DOOR,
			TILE_GRASS_BENCH_LEFT_TOP, TILE_GRASS_BENCH_LEFT_BTM,
			TILE_STREET_BASIC, TILE_STREET_LEFT, TILE_STREET_RIGHT, TILE_STREET_CENT, TILE_PATH_GREY_PAT_RED;
			
			
			
			//public Tile[] tileArray = new Tile[1100];
			public TreeMap<TileXY,Tile> tileMap = new TreeMap<TileXY,Tile>(); 
			
			
			//gets all images from tiles sheet and stores them into variable
			public void setSquares(){
				BufferedImage tiles = null;
				ResourceLoader rl =  new ResourceLoader();
				try{
					tiles = rl.getSquares("squareSheet.png");
				}catch(IOException ex){
					
				}
				SheetGrabber sg1 = new SheetGrabber(tiles);
				int id = 0;
				tileMap.put(new TileXY(0,0), TILE_GRASS_BASIC = new Tile(false, false, 0, 0, sg1,id++));
				tileMap.put(new TileXY(20,0), TILE_GRASS_FENCE_VERT = new Tile(true, false, 20,0, sg1,id++));
				tileMap.put(new TileXY(40,0), TILE_GRASS_FENCE_HORZ = new Tile(true, false, 40,0, sg1,id++)); 
				tileMap.put(new TileXY(60,0), TILE_GRASS_FENCE_TLC = new Tile(true, false, 60, 0, sg1,id++));
				tileMap.put(new TileXY(80,0), TILE_GRASS_FENCE_TRC = new Tile(true, false, 80, 0, sg1,id++));
				tileMap.put(new TileXY(100,0), TILE_GRASS_FENCE_BLC = new Tile(true, false,100, 0, sg1,id++));
				tileMap.put(new TileXY(120,0), TILE_GRASS_FENCE_BRC = new Tile(true, false, 120, 0, sg1,id++));
				tileMap.put(new TileXY(140,0), TILE_GRASS_SIGN = new Tile(true, false, true, 140, 0, sg1,id++));
				tileMap.put(new TileXY(160,0), TILE_GRASS_FENCE_VERT_BLACK = new Tile(true, false, 160, 0, sg1,id++));
				tileMap.put(new TileXY(180,0), TILE_GRASS_FENCE_HORZ_BLACK = new Tile(true, false,180, 0, sg1,id++)); 
				tileMap.put(new TileXY(200,0), TILE_GRASS_FENCE_TLC_BLACK = new Tile(true, false, 200, 0, sg1,id++));
				tileMap.put(new TileXY(220,0), TILE_GRASS_FENCE_TRC_BLACK = new Tile(true, false, 220, 0, sg1,id++));
				tileMap.put(new TileXY(240,0), TILE_GRASS_FENCE_BLC_BLACK = new Tile(true, false, 240, 0, sg1,id++));
				tileMap.put(new TileXY(260,0), TILE_GRASS_FENCE_BRC_BLACK = new Tile(true, false,260, 0, sg1,id++));
				tileMap.put(new TileXY(280,0), TILE_GRASS_TOMB_JB = new Tile(true, false, 280, 0, sg1,id++));
				tileMap.put(new TileXY(300,0), TILE_GRASS_TOMB_TY = new Tile(true, false, 300, 0, sg1,id++));
				tileMap.put(new TileXY(320,0), TILE_GRASS_TOMB_FR = new Tile(true, false, 320, 0, sg1,id++));
				tileMap.put(new TileXY(340,0), TILE_GRASS_TOMB_SQ = new Tile(true, false, 340, 0, sg1,id++));
				tileMap.put(new TileXY(360,0), TILE_GRASS_BENCH_LEFT_DOWN = new Tile(true, false, 360, 0, sg1,id++));
				tileMap.put(new TileXY(380,0), TILE_GRASS_BENCH_RIGHT_DOWN = new Tile(true, false, 380, 0, sg1,id++));
				tileMap.put(new TileXY(400,0), TILE_GRASS_BENCH_LEFT_UP = new Tile(true, false, 400, 0, sg1,id++));
				tileMap.put(new TileXY(420,0), TILE_GRASS_BENCH_RIGHT_UP = new Tile(true, false, 420, 0, sg1,id++));
				
				tileMap.put(new TileXY(0,20), TILE_HOUSE_PURPLE = new Tile(true, false,0, 20, sg1,id++));
				tileMap.put(new TileXY(20,20), TILE_DOOR_HOUSE_PURPLE = new Tile(true, true, 20, 20, sg1,id++));
				tileMap.put(new TileXY(40,20), TILE_HOUSE_ORANGE = new Tile(true, false, 40, 20, sg1,id++));
				tileMap.put(new TileXY(60,20), TILE_DOOR_HOUSE_ORANGE = new Tile(true, true, 60, 20, sg1,id++));
				tileMap.put(new TileXY(80,20), TILE_HOUSE_RED = new Tile(true, false,80, 20, sg1,id++));
				tileMap.put(new TileXY(100,20), TILE_DOOR_HOUSE_RED = new Tile(true, true, 100, 20, sg1,id++));
				tileMap.put(new TileXY(120,20), TILE_HOUSE_BLUE = new Tile(true, false, 120, 20, sg1,id++));
				tileMap.put(new TileXY(140,20), TILE_DOOR_HOUSE_BLUE = new Tile(true, true, 140, 20, sg1,id++));
				tileMap.put(new TileXY(160,20), TILE_HOUSE_WHITE = new Tile(true, false, 160, 20, sg1,id++));
				tileMap.put(new TileXY(180,20), TILE_DOOR_HOUSE_WHITE = new Tile(true, true, 180, 20, sg1,id++));
				tileMap.put(new TileXY(200,20), TILE_HOUSE_RED_PAT_GREY = new Tile(true, false, 200, 20, sg1,id++));
				tileMap.put(new TileXY(220,20), TILE_DOOR_HOUSE_RED_PAT_GREY = new Tile(true, true, 220, 20, sg1,id++));
				tileMap.put(new TileXY(240,20), TILE_HOUSE_BLACK_PAT_GREY = new Tile(true, false, 240, 20, sg1,id++));
				tileMap.put(new TileXY(260,20), TILE_DOOR_HOUSE_BLACK_PAT_GREY = new Tile(true, true, 260, 20, sg1,id++));
				tileMap.put(new TileXY(280,20), TILE_HOUSE_BROWN_PAT_MUST = new Tile(true, false, 280, 20, sg1,id++));
				tileMap.put(new TileXY(300,20), TILE_DOOR_HOUSE_BROWN_PAT_MUST = new Tile(true, true, 300, 20, sg1,id++));
				tileMap.put(new TileXY(320,20), TILE_HOUSE_SAND = new Tile(true, false, 320, 20, sg1,id++));
				tileMap.put(new TileXY(340,20), TILE_DOOR_HOUSE_SAND_UP = new Tile(true, true, 340, 20, sg1,id++));
				tileMap.put(new TileXY(360,20), TILE_DOOR_HOUSE_SAND_LEFT = new Tile(true, true, 360, 20, sg1,id++));
				tileMap.put(new TileXY(380,20), TILE_HOUSE_LBLUE = new Tile(true, false, 380, 20, sg1,id++));
				tileMap.put(new TileXY(400,20), TILE_DOOR_HOUSE_LBLUE_DOWN = new Tile(true, true, 400, 20, sg1,id++));
				tileMap.put(new TileXY(420,20), TILE_DOOR_HOUSE_LBLUE_LEFT = new Tile(true, true, 420, 20, sg1,id++));
				tileMap.put(new TileXY(440,20), TILE_HOUSE_GOLD = new Tile(true, false, 440, 20, sg1,id++));
				tileMap.put(new TileXY(460,20), TILE_DOOR_HOUSE_GOLD = new Tile(true, true, 460, 20, sg1,id++));
				tileMap.put(new TileXY(480,20), TILE_HOUSE_GREY_PAT_BLUE = new Tile(true, false, 480, 20, sg1,id++));
				tileMap.put(new TileXY(500,20), TILE_DOOR_HOUSE_GREY_PAT_BLUE = new Tile(true, true, 500, 20, sg1,id++));
				tileMap.put(new TileXY(520,20), TILE_HOUSE_BLUE_PAT_PINK = new Tile(true, false, 520, 20, sg1,id++));
				tileMap.put(new TileXY(540,20), TILE_DOOR_HOUSE_BLUE_PAT_PINK = new Tile(true, true, 540, 20, sg1,id++));
				tileMap.put(new TileXY(560,20), TILE_HOUSE_WHITE_PAT_BLUE = new Tile(true, false, 560, 20, sg1,id++));
				tileMap.put(new TileXY(580,20), TILE_DOOR_HOUSE_WHITE_PAT_BLUE_LEFT = new Tile(true, true, 580, 20, sg1,id++));
				tileMap.put(new TileXY(600,20), TILE_DOOR_HOUSE_WHITE_PAT_BLUE_RIGHT = new Tile(true, true, 600, 20, sg1,id++));
				tileMap.put(new TileXY(620,20), TILE_HOUSE_WHITE_PAT_ORANGE = new Tile(true, false, 620, 20, sg1,id++));
				tileMap.put(new TileXY(640,20), TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_UP = new Tile(true, true, 640, 20, sg1,id++));
				tileMap.put(new TileXY(660,20), TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_DOWN = new Tile(true, true, 660, 20, sg1,id++));
				
				tileMap.put(new TileXY(0,40), TILE_PATH_GREYRED = new Tile(false, false, 0, 40, sg1,id++));
				tileMap.put(new TileXY(20,40), TILE_PATH_DIRT = new Tile(false, false, 20, 40, sg1,id++));
				
				tileMap.put(new TileXY(0,60), TILE_BLANK = new Tile(false, false, 0, 60, sg1,id++));
				tileMap.put(new TileXY(20,60), TILE_TREE_LEFT_CENT = new Tile(false, false, 20, 60, sg1,id++));
				tileMap.put(new TileXY(40,60), TILE_TREE_LEFT_TOP = new Tile(false, false, 40, 60, sg1,id++));
				tileMap.put(new TileXY(60,60), TILE_TREE_CENT_TOP = new Tile(false, false, 60, 60, sg1,id++));
				tileMap.put(new TileXY(80,60), TILE_TREE_RIGHT_TOP = new Tile(false, false, 80, 60, sg1,id++));
				tileMap.put(new TileXY(100,60), TILE_TREE_RIGHT_CENT = new Tile(false, false, 100, 60, sg1,id++));
				tileMap.put(new TileXY(120,60), TILE_TREE_RIGHT_BTM = new Tile(false, false, 120, 60, sg1,id++));
				tileMap.put(new TileXY(140,60), TILE_TREE_CENT_BTM = new Tile(false, false, 140, 60, sg1,id++));
				tileMap.put(new TileXY(160,60), TILE_TREE_LEFT_BTM = new Tile(false, false, 160, 60, sg1,id++));
				tileMap.put(new TileXY(180,60), TILE_TREE_CENT_CENT =new Tile(true, false, 180, 60, sg1,id++));
				tileMap.put(new TileXY(200,60), TILE_HOUSE_BLACK_PAT_BROWN = new Tile(true, false, 200, 60, sg1,id++));
				tileMap.put(new TileXY(220,60), TILE_DOOR_HOUSE_BLACK_PAT_BROWN = new Tile(true, true, 220, 60, sg1,id++));
				tileMap.put(new TileXY(240,60), TILE_DOOR_HOUSE_MINE = new Tile(false, false, 240, 60, sg1,id++));
				tileMap.put(new TileXY(260,60), TILE_WATER_BASIC = new Tile(true, false, 260, 60, sg1,id++));
				
			}
			
			
			public void setInsideSquares(){	
				BufferedImage tiles = null;
				ResourceLoader rl =  new ResourceLoader();
				try{
					tiles = rl.getSquares("squareSheet.png");
				}catch(IOException ex){

				}
				SheetGrabber sg1 = new SheetGrabber(tiles);
				int loc = 1000;
				tileMap.put(new TileXY(440,0), TILE_GRASS_BENCH_LEFT_TOP = new Tile(true, false, 440,0, sg1,loc++));
				tileMap.put(new TileXY(460,0), TILE_GRASS_BENCH_LEFT_BTM = new Tile(true, false, 460,0, sg1,loc++));
				
				tileMap.put(new TileXY(200,20), TILE_OUT_BUS = new Tile(true, false, 200,20, sg1,loc++));
				tileMap.put(new TileXY(220,20), TILE_OUT_DOOR_BUS = new Tile(true, true, 220,20, sg1,loc++));
				tileMap.put(new TileXY(520,20), TILE_OUT_HOTEL = new Tile(true, false, 520,20, sg1,loc++));
				tileMap.put(new TileXY(540,20), TILE_OUT_HOTEL_DOOR = new Tile(true, true, 540,20, sg1,loc++));
				tileMap.put(new TileXY(620,20), TILE_OUT_HOUSE_ORANGE = new Tile(true, false, 620,20, sg1,loc++));
				tileMap.put(new TileXY(640,20), TILE_OUT_HOUSE_ORANGE_DOOR_UP = new Tile(true, true, 640,20, sg1,loc++));
				tileMap.put(new TileXY(660,20), TILE_OUT_HOUSE_ORANGE_DOOR_DOWN = new Tile(true, true, 660,20, sg1,loc++));
				tileMap.put(new TileXY(240,20), TILE_OUT_CLUB = new Tile(true, false, 240,20, sg1,loc++));
				tileMap.put(new TileXY(260,20), TILE_OUT_CLUB_DOOR = new Tile(true, true, 260,20, sg1,loc++));
				
				tileMap.put(new TileXY(0,40), TILE_PATH_GREY_PAT_RED = new Tile(false, false, 0,40, sg1,loc++));
				tileMap.put(new TileXY(40,40), TILE_STREET_BASIC = new Tile(false, false, 40,40, sg1,loc++));
				tileMap.put(new TileXY(60,40), TILE_STREET_LEFT = new Tile(false, false, 60,40, sg1,loc++));
				tileMap.put(new TileXY(80,40), TILE_STREET_CENT = new Tile(false, false, 80,40, sg1,loc++));
				tileMap.put(new TileXY(100,40), TILE_STREET_RIGHT = new Tile(false, false, 100,40, sg1,loc++));
				
				tileMap.put(new TileXY(0,80), TILE_IN_YELLOW_PAT_CREME = new Tile(false, false, 0,80, sg1,loc++));
				tileMap.put(new TileXY(20,80), TILE_IN_YELLOW_PAT_CREME_FMAT = new Tile(false, false, 20,80, sg1,loc++));
				tileMap.put(new TileXY(40,80), TILE_IN_YELLOW_PAT_CREME_DESK = new Tile(true, false, 40,80, sg1,loc++));
				tileMap.put(new TileXY(60,80), TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_UP = new Tile(true, false, 60,80, sg1,loc++));
				tileMap.put(new TileXY(80,80), TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_UP = new Tile(true, false, 80,80, sg1,loc++));
				tileMap.put(new TileXY(100,80), TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_DOWN = new Tile(true, false, 100,80, sg1,loc++));
				tileMap.put(new TileXY(120,80), TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_DOWN = new Tile(true, false, 120,80, sg1,loc++));
				tileMap.put(new TileXY(140,80), TILE_IN_GREEN_CARP = new Tile(false, false, 140,80, sg1,loc++));
				tileMap.put(new TileXY(160,80), TILE_IN_GREEN_CARP_BED_TOP = new Tile(true, false, 160,80, sg1,loc++));
				tileMap.put(new TileXY(180,80), TILE_IN_GREEN_CARP_BED_BTM = new Tile(true, false, 180,80, sg1,loc++));
				tileMap.put(new TileXY(200,80), TILE_IN_GREEN_CARP_CHAIR_DOWN = new Tile(true, false, 200,80, sg1,loc++));
				tileMap.put(new TileXY(220,80), TILE_IN_GREEN_CARP_TTL = new Tile(true, false, 220,80, sg1,loc++));
				tileMap.put(new TileXY(240,80), TILE_IN_GREEN_CARP_TTR = new Tile(true, false, 240,80, sg1,loc++));
				tileMap.put(new TileXY(260,80), TILE_IN_GREEN_CARP_TBL = new Tile(true, false, 260,80, sg1,loc++));
				tileMap.put(new TileXY(280,80), TILE_IN_GREEN_CARP_TBR = new Tile(true, false, 280,80, sg1,loc++));
				tileMap.put(new TileXY(300,80), TILE_IN_GREEN_CARP_CHAIR_UP = new Tile(true, false, 300,80, sg1,loc++));
				tileMap.put(new TileXY(320,80), TILE_IN_WHITE_PAT_PINK_MARB = new Tile(false, false, 320,80, sg1,loc++));
				tileMap.put(new TileXY(340,80), TILE_IN_WHITE_PAT_PINK_MARB_BED_TOP = new Tile(true, false, 340,80, sg1,loc++));
				tileMap.put(new TileXY(360,80), TILE_IN_WHITE_PAT_PINK_MARB_BED_BTM = new Tile(true, false, 360,80, sg1,loc++));
				tileMap.put(new TileXY(380,80), TILE_IN_WHITE_PAT_PINK_MARB_DESK = new Tile(true, false, 380,80, sg1,loc++));
				tileMap.put(new TileXY(400,80), TILE_IN_WHITE_PAT_PINK_MARB_COUCH_TOP = new Tile(true, false, 400,80, sg1,loc++));
				tileMap.put(new TileXY(420,80), TILE_IN_WHITE_PAT_PINK_MARB_COUCH_BTM = new Tile(true, false, 420,80, sg1,loc++));
				tileMap.put(new TileXY(440,80), TILE_IN_BLACK_STAIR_TL_U = new Tile(false, true, 440,80, sg1,loc++));
				tileMap.put(new TileXY(460,80), TILE_IN_BLACK_STAIR_TR_U = new Tile(false, true, 460,80, sg1,loc++));
				tileMap.put(new TileXY(480,80), TILE_IN_BLACK_STAIR_BL_U = new Tile(false, true, 480,80, sg1,loc++));
				tileMap.put(new TileXY(500,80), TILE_IN_BLACK_STAIR_BR_U = new Tile(false, true, 500,80, sg1,loc++));
				tileMap.put(new TileXY(520,80), TILE_IN_BLACK_STAIR_TL_D = new Tile(false, true, 520,80, sg1,loc++));
				tileMap.put(new TileXY(540,80), TILE_IN_BLACK_STAIR_TR_D = new Tile(false, true, 540,80, sg1,loc++));
				tileMap.put(new TileXY(560,80), TILE_IN_BLACK_STAIR_BL_D = new Tile(false, true, 560,80, sg1,loc++));
				tileMap.put(new TileXY(580,80), TILE_IN_BLACK_STAIR_BR_D = new Tile(false, true, 580,80, sg1,loc++));
				tileMap.put(new TileXY(600,80), TILE_IN_WOOD_RED = new Tile(false, false, 600,80, sg1,loc++));
				tileMap.put(new TileXY(620,80), TILE_IN_WOOD_RED_BAR = new Tile(true, false, 620,80, sg1,loc++));
				tileMap.put(new TileXY(640,80), TILE_IN_WOOD_RED_STOOL = new Tile(true, false, 640,80, sg1,loc++));
				tileMap.put(new TileXY(660,80), TILE_IN_WOOD_RED_BTL = new Tile(true, false, 660,80, sg1,loc++));
				tileMap.put(new TileXY(680,80), TILE_IN_WOOD_RED_TT = new Tile(true, false, 680,80, sg1,loc++));
				tileMap.put(new TileXY(700,80), TILE_IN_WOOD_RED_BTR = new Tile(true, false, 700,80, sg1,loc++));
				tileMap.put(new TileXY(720,80), TILE_IN_WOOD_RED_BBL = new Tile(true, false, 720,80, sg1,loc++));
				tileMap.put(new TileXY(740,80), TILE_IN_WOOD_RED_TB = new Tile(true, false, 740,80, sg1,loc++));
				tileMap.put(new TileXY(760,80), TILE_IN_WOOD_RED_BBR = new Tile(true, false, 760,80, sg1,loc++));
				tileMap.put(new TileXY(780,80), TILE_IN_MAGENTA_STAGE = new Tile(true, false, 780,80, sg1,loc++));
				tileMap.put(new TileXY(800,80), TILE_IN_MAGENTA_STAGE_POLE = new Tile(true, false, 800,80, sg1,loc++));
			}
}
