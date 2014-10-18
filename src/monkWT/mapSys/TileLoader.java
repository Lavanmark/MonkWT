package monkWT.mapSys;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import monkWT.Main;
import monkWT.system.SheetGrabber;
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
			
			
			
			public Tile[] tileArray = new Tile[1100];
			
			
			//gets all images from tiles sheet and stores them into variable
			public void setSquares(){
				BufferedImage tiles = null;
				ResourceLoader rl =  new ResourceLoader();
				try{
					tiles = rl.getSquares("squareSheet.png");
				}catch(IOException ex){
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
				}
				SheetGrabber sg1 = new SheetGrabber(tiles);
				tileArray[0] = TILE_GRASS_BASIC = new Tile(false, false, 0, 0, sg1,0);
				tileArray[1] = TILE_GRASS_FENCE_VERT = new Tile(true, false, 20,0, sg1,1);
				tileArray[2] = TILE_GRASS_FENCE_HORZ = new Tile(true, false, 40,0, sg1,2); 
				tileArray[3] = TILE_GRASS_FENCE_TLC = new Tile(true, false, 60, 0, sg1,3);
				tileArray[4] = TILE_GRASS_FENCE_TRC = new Tile(true, false, 80, 0, sg1,4);
				tileArray[5] = TILE_GRASS_FENCE_BLC = new Tile(true, false,100, 0, sg1,5);
				tileArray[6] = TILE_GRASS_FENCE_BRC = new Tile(true, false, 120, 0, sg1,6);
				tileArray[7] = TILE_GRASS_SIGN = new Tile(true, false, true, 140, 0, sg1,7);
				tileArray[8] = TILE_GRASS_FENCE_VERT_BLACK = new Tile(true, false, 160, 0, sg1,8);
				tileArray[9] = TILE_GRASS_FENCE_HORZ_BLACK = new Tile(true, false,180, 0, sg1,9); 
				tileArray[10] = TILE_GRASS_FENCE_TLC_BLACK = new Tile(true, false, 200, 0, sg1,10);
				tileArray[11] = TILE_GRASS_FENCE_TRC_BLACK = new Tile(true, false, 220, 0, sg1,11);
				tileArray[12] = TILE_GRASS_FENCE_BLC_BLACK = new Tile(true, false, 240, 0, sg1,12);
				tileArray[13] = TILE_GRASS_FENCE_BRC_BLACK = new Tile(true, false,260, 0, sg1,13);
				tileArray[14] = TILE_GRASS_TOMB_JB = new Tile(true, false, 280, 0, sg1,14);
				tileArray[15] = TILE_GRASS_TOMB_TY = new Tile(true, false, 300, 0, sg1,15);
				tileArray[16] = TILE_GRASS_TOMB_FR = new Tile(true, false, 320, 0, sg1,16);
				tileArray[17] = TILE_GRASS_TOMB_SQ = new Tile(true, false, 340, 0, sg1,17);
				tileArray[18] = TILE_GRASS_BENCH_LEFT_DOWN = new Tile(true, false, 360, 0, sg1,18);
				tileArray[19] = TILE_GRASS_BENCH_RIGHT_DOWN = new Tile(true, false, 380, 0, sg1,19);
				tileArray[20] =	TILE_GRASS_BENCH_LEFT_UP = new Tile(true, false, 400, 0, sg1,20);
				tileArray[21] = TILE_GRASS_BENCH_RIGHT_UP = new Tile(true, false, 420, 0, sg1,21);
				tileArray[22] = TILE_HOUSE_PURPLE = new Tile(true, false,0, 20, sg1,22);
				tileArray[23] = TILE_DOOR_HOUSE_PURPLE = new Tile(true, true, 20, 20, sg1,23);
				tileArray[24] = TILE_HOUSE_ORANGE = new Tile(true, false, 40, 20, sg1,24);
				tileArray[25] = TILE_DOOR_HOUSE_ORANGE = new Tile(true, true, 60, 20, sg1,25);
				tileArray[26] = TILE_HOUSE_RED = new Tile(true, false,80, 20, sg1,26);
				tileArray[27] = TILE_DOOR_HOUSE_RED = new Tile(true, true, 100, 20, sg1,27);
				tileArray[28] = TILE_HOUSE_BLUE = new Tile(true, false, 120, 20, sg1,28);
				tileArray[29] = TILE_DOOR_HOUSE_BLUE = new Tile(true, true, 140, 20, sg1,29);
				tileArray[30] = TILE_HOUSE_WHITE = new Tile(true, false, 160, 20, sg1,30);
				tileArray[31] = TILE_DOOR_HOUSE_WHITE = new Tile(true, true, 180, 20, sg1,31);
				tileArray[32] = TILE_HOUSE_RED_PAT_GREY = new Tile(true, false, 200, 20, sg1,32);
				tileArray[33] = TILE_DOOR_HOUSE_RED_PAT_GREY = new Tile(true, true, 220, 20, sg1,33);
				tileArray[34] = TILE_HOUSE_BLACK_PAT_GREY = new Tile(true, false, 240, 20, sg1,34);
				tileArray[35] = TILE_DOOR_HOUSE_BLACK_PAT_GREY = new Tile(true, true, 260, 20, sg1,35);
				tileArray[36] = TILE_HOUSE_BROWN_PAT_MUST = new Tile(true, false, 280, 20, sg1,36);
				tileArray[37] =	TILE_DOOR_HOUSE_BROWN_PAT_MUST = new Tile(true, true, 300, 20, sg1,37);
				tileArray[38] = TILE_HOUSE_SAND = new Tile(true, false, 320, 20, sg1,38);
				tileArray[39] = TILE_DOOR_HOUSE_SAND_UP = new Tile(true, true, 340, 20, sg1,39);
				tileArray[40] = TILE_DOOR_HOUSE_SAND_LEFT = new Tile(true, true, 360, 20, sg1,40);
				tileArray[41] = TILE_HOUSE_LBLUE = new Tile(true, false, 380, 20, sg1,41);
				tileArray[42] = TILE_DOOR_HOUSE_LBLUE_DOWN = new Tile(true, true, 400, 20, sg1,42);
				tileArray[43] = TILE_DOOR_HOUSE_LBLUE_LEFT = new Tile(true, true, 420, 20, sg1,43);
				tileArray[44] = TILE_HOUSE_GOLD = new Tile(true, false, 440, 20, sg1,44);
				tileArray[45] = TILE_DOOR_HOUSE_GOLD = new Tile(true, true, 460, 20, sg1,45);
				tileArray[46] = TILE_HOUSE_GREY_PAT_BLUE = new Tile(true, false, 480, 20, sg1,46);
				tileArray[47] = TILE_DOOR_HOUSE_GREY_PAT_BLUE = new Tile(true, true, 500, 20, sg1,47);
				tileArray[48] = TILE_HOUSE_BLUE_PAT_PINK = new Tile(true, false, 520, 20, sg1,48);
				tileArray[49] = TILE_DOOR_HOUSE_BLUE_PAT_PINK = new Tile(true, true, 540, 20, sg1,49);
				tileArray[50] = TILE_HOUSE_WHITE_PAT_BLUE = new Tile(true, false, 560, 20, sg1,50);
				tileArray[51] = TILE_DOOR_HOUSE_WHITE_PAT_BLUE_LEFT = new Tile(true, true, 580, 20, sg1,51);
				tileArray[52] = TILE_DOOR_HOUSE_WHITE_PAT_BLUE_RIGHT = new Tile(true, true, 600, 20, sg1,52);
				tileArray[53] = TILE_HOUSE_WHITE_PAT_ORANGE = new Tile(true, false, 620, 20, sg1,53);
				tileArray[54] = TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_UP = new Tile(true, true, 640, 20, sg1,54);
				tileArray[55] = TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_DOWN = new Tile(true, true, 660, 20, sg1,55);
				tileArray[56] = TILE_PATH_GREYRED = new Tile(false, false, 0, 40, sg1,56);
				tileArray[57] = TILE_PATH_DIRT = new Tile(false, false, 20, 40, sg1,57);
				tileArray[58] = TILE_BLANK = new Tile(false, false, 0, 60, sg1,58);
				tileArray[59] = TILE_TREE_LEFT_CENT = new Tile(false, false, 20, 60, sg1,59);
				tileArray[60] = TILE_TREE_LEFT_TOP = new Tile(false, false, 40, 60, sg1,60);
				tileArray[61] = TILE_TREE_CENT_TOP = new Tile(false, false, 60, 60, sg1,61);
				tileArray[62] = TILE_TREE_RIGHT_TOP = new Tile(false, false, 80, 60, sg1,62);
				tileArray[63] = TILE_TREE_RIGHT_CENT = new Tile(false, false, 100, 60, sg1,63);
				tileArray[64] = TILE_TREE_RIGHT_BTM = new Tile(false, false, 120, 60, sg1,64);
				tileArray[65] = TILE_TREE_CENT_BTM = new Tile(false, false, 140, 60, sg1,65);
				tileArray[66] = TILE_TREE_LEFT_BTM = new Tile(false, false, 160, 60, sg1,66);
				tileArray[67] = TILE_TREE_CENT_CENT =new Tile(true, false, 180, 60, sg1,67);
				tileArray[68] = TILE_HOUSE_BLACK_PAT_BROWN = new Tile(true, false, 200, 60, sg1,68);
				tileArray[69] = TILE_DOOR_HOUSE_BLACK_PAT_BROWN = new Tile(true, true, 220, 60, sg1,69);
				tileArray[70] = TILE_DOOR_HOUSE_MINE = new Tile(true, true, 240, 60, sg1,70);
				tileArray[71] = TILE_WATER_BASIC = new Tile(true, false, 260, 60, sg1,71);
				
			}
			
			
			public void setInsideSquares(){	
				BufferedImage tiles = null;
				ResourceLoader rl =  new ResourceLoader();
				try{
					tiles = rl.getSquares("squareSheet.png");
				}catch(IOException ex){
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
				}
				SheetGrabber sg1 = new SheetGrabber(tiles);
				int loc = 1000;
				tileArray[loc++] = TILE_GRASS_BENCH_LEFT_TOP = new Tile(true, false, 440,0, sg1,1000);
				tileArray[loc++] = TILE_GRASS_BENCH_LEFT_BTM = new Tile(true, false, 460,0, sg1,1001);
				tileArray[loc++] = TILE_OUT_BUS = new Tile(true, false, 200,20, sg1,1002);
				tileArray[loc++] = TILE_OUT_DOOR_BUS = new Tile(true, true, 220,20, sg1,1003);
				tileArray[loc++] = TILE_OUT_HOTEL = new Tile(true, false, 520,20, sg1,1004);
				tileArray[loc++] = TILE_OUT_HOTEL_DOOR = new Tile(true, true, 540,20, sg1,1005);
				tileArray[loc++] = TILE_OUT_HOUSE_ORANGE = new Tile(true, false, 620,20, sg1,1006);
				tileArray[loc++] = TILE_OUT_HOUSE_ORANGE_DOOR_UP = new Tile(true, true, 640,20, sg1,1007);
				tileArray[loc++] = TILE_OUT_HOUSE_ORANGE_DOOR_DOWN = new Tile(true, true, 660,20, sg1,1008);
				tileArray[loc++] = TILE_OUT_CLUB = new Tile(true, false, 240,20, sg1,1009);
				tileArray[loc++] = TILE_OUT_CLUB_DOOR = new Tile(true, true, 260,20, sg1,1010);
				tileArray[loc++] = TILE_PATH_GREY_PAT_RED = new Tile(false, false, 0,40, sg1,1011);
				tileArray[loc++] = TILE_STREET_BASIC = new Tile(false, false, 40,40, sg1,1012);
				tileArray[loc++] = TILE_STREET_LEFT = new Tile(false, false, 60,40, sg1,1013);
				tileArray[loc++] = TILE_STREET_CENT = new Tile(false, false, 80,40, sg1,1014);
				tileArray[loc++] = TILE_STREET_RIGHT = new Tile(false, false, 100,40, sg1,1015);
				tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME = new Tile(false, false, 0,80, sg1,1016);
				tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_FMAT = new Tile(false, false, 20,80, sg1,1017);
				tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_DESK = new Tile(true, false, 40,80, sg1,1018);
				tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_UP = new Tile(true, false, 60,80, sg1,1019);
				tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_UP = new Tile(true, false, 80,80, sg1,1020);
				tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_DOWN = new Tile(true, false, 100,80, sg1,1021);
				tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_DOWN = new Tile(true, false, 120,80, sg1,1022);
				tileArray[loc++] = TILE_IN_GREEN_CARP = new Tile(false, false, 140,80, sg1,1023);
				tileArray[loc++] = TILE_IN_GREEN_CARP_BED_TOP = new Tile(true, false, 160,80, sg1,1024);
				tileArray[loc++] = TILE_IN_GREEN_CARP_BED_BTM = new Tile(true, false, 180,80, sg1,1025);
				tileArray[loc++] = TILE_IN_GREEN_CARP_CHAIR_DOWN = new Tile(true, false, 200,80, sg1,1026);
				tileArray[loc++] = TILE_IN_GREEN_CARP_TTL = new Tile(true, false, 220,80, sg1,1027);
				tileArray[loc++] = TILE_IN_GREEN_CARP_TTR = new Tile(true, false, 240,80, sg1,1028);
				tileArray[loc++] = TILE_IN_GREEN_CARP_TBL = new Tile(true, false, 260,80, sg1,1029);
				tileArray[loc++] = TILE_IN_GREEN_CARP_TBR = new Tile(true, false, 280,80, sg1,1030);
				tileArray[loc++] = TILE_IN_GREEN_CARP_CHAIR_UP = new Tile(true, false, 300,80, sg1,1031);
				tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB = new Tile(false, false, 320,80, sg1,1032);
				tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB_BED_TOP = new Tile(true, false, 340,80, sg1,1033);
				tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB_BED_BTM = new Tile(true, false, 360,80, sg1,1034);
				tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB_DESK = new Tile(true, false, 380,80, sg1,1035);
				tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB_COUCH_TOP = new Tile(true, false, 400,80, sg1,1036);
				tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB_COUCH_BTM = new Tile(true, false, 420,80, sg1,1037);
				tileArray[loc++] = TILE_IN_BLACK_STAIR_TL_U = new Tile(false, false, 440,80, sg1,1038);
				tileArray[loc++] = TILE_IN_BLACK_STAIR_TR_U = new Tile(false, false, 460,80, sg1,1039);
				tileArray[loc++] = TILE_IN_BLACK_STAIR_BL_U = new Tile(false, false, 480,80, sg1,1040);
				tileArray[loc++] = TILE_IN_BLACK_STAIR_BR_U = new Tile(false, false, 500,80, sg1,1041);
				tileArray[loc++] = TILE_IN_BLACK_STAIR_TL_D = new Tile(false, false, 520,80, sg1,1042);
				tileArray[loc++] = TILE_IN_BLACK_STAIR_TR_D = new Tile(false, false, 540,80, sg1,1043);
				tileArray[loc++] = TILE_IN_BLACK_STAIR_BL_D = new Tile(false, false, 560,80, sg1,1044);
				tileArray[loc++] = TILE_IN_BLACK_STAIR_BR_D = new Tile(false, false, 580,80, sg1,1045);
				tileArray[loc++] = TILE_IN_WOOD_RED = new Tile(false, false, 600,80, sg1,1046);
				tileArray[loc++] = TILE_IN_WOOD_RED_BAR = new Tile(true, false, 620,80, sg1,1047);
				tileArray[loc++] = TILE_IN_WOOD_RED_STOOL = new Tile(true, false, 640,80, sg1,1048);
				tileArray[loc++] = TILE_IN_WOOD_RED_BTL = new Tile(true, false, 660,80, sg1,1049);
				tileArray[loc++] = TILE_IN_WOOD_RED_TT = new Tile(true, false, 680,80, sg1,1050);
				tileArray[loc++] = TILE_IN_WOOD_RED_BTR = new Tile(true, false, 700,80, sg1,1051);
				tileArray[loc++] = TILE_IN_WOOD_RED_BBL = new Tile(true, false, 720,80, sg1,1052);
				tileArray[loc++] = TILE_IN_WOOD_RED_TB = new Tile(true, false, 740,80, sg1,1053);
				tileArray[loc++] = TILE_IN_WOOD_RED_BBR = new Tile(true, false, 760,80, sg1,1054);
				tileArray[loc++] = TILE_IN_MAGENTA_STAGE = new Tile(true, false, 780,80, sg1,1055);
				tileArray[loc++] = TILE_IN_MAGENTA_STAGE_POLE = new Tile(true, false, 800,80, sg1,1056);
			}
}
