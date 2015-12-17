package monkWT.model.levels;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import monkWT.model.Chair;
import monkWT.model.Door;
import monkWT.model.Player;
import resources.ResourceLoader;




public class Levels {

		
	
		//Level boxes
		public Tile[][] city1Tree = new Tile[9][1200];
		public Tile[][] city1 = new Tile[9][1200];
		public Rectangle[] city1Blocks = new Rectangle[1200];
		
		
		//city info
		public int cityDeaths;
		public String[] ownerHouse = new String[10];
		
		//Level Spawns
		public Rectangle spawnRect;
		public Rectangle spawnLeft;
		public Rectangle spawnRight;
		public Rectangle spawnTop;
		public Rectangle spawnFeet;
		
		//Doors
			//map<(int) Map section, set of doors>
		public HashMap<Integer, Set<Door>> doorsLvlOne = new HashMap<Integer, Set<Door>>();
		//Chairs
			//map<(int) Map section, set of chairs>
		public HashMap<Integer, Set<Chair>> chairsLvlOne = new HashMap<Integer, Set<Chair>>();
		
		
		//class declarations
		//city one insides
		public CityOneInsides c1i = new CityOneInsides();
		private ResourceLoader rl = new ResourceLoader();
		private TileLoader tl = new TileLoader();
		
		/*public void setBuildingEnt(int buildingNum){
			c1i.setBuildingEnt(buildingNum);
			if(buildingNum == 0){
				setPlayerInside(false);
			}else{
				setPlayerInside(true);
			}
		}*/
		public void loadCity(int whichCity){
			tl.setSquares();
			if(whichCity == 1){	
				//loadCity16();
				try{
					//rl.saveMap(city1[5], "city1s6");
					city1[0] = setMapSecs(rl.getMap("city1",false,"city1s1"));
					city1[1] = setMapSecs(rl.getMap("city1",false,"city1s2"));
					city1[2] = setMapSecs(rl.getMap("city1",false,"city1s3"));
					city1[3] = setMapSecs(rl.getMap("city1",false,"city1s4"));
					city1[4] = setMapSecs(rl.getMap("city1",false,"city1s5"));
					city1[5] = setMapSecs(rl.getMap("city1",false,"city1s6"));
					city1[6] = setMapSecs(rl.getMap("city1",false,"city1s7"));
					city1Tree[6] = setMapSecs(rl.getMap("city1",false,"city1s7tree"));
					city1[7] = setMapSecs(rl.getMap("city1",false,"city1s8"));
					city1Tree[7] = setMapSecs(rl.getMap("city1",false,"city1s8tree"));
					city1[8] = setMapSecs(rl.getMap("city1",false,"city1s9"));
					city1Tree[8] = setMapSecs(rl.getMap("city1",false,"city1s9tree"));
				}catch(IOException e){
					e.printStackTrace();
				}
				addDoorsLvlOne();
				addChairsLvlOne();
				
				spawnRect = new Rectangle(400, 243, 15, 15);
		        spawnLeft = new Rectangle(400, 245, 1, 13);
		        spawnRight = new Rectangle(415, 245, 1, 13);
		        spawnTop = new Rectangle(401, 243, 13, 1);
		 		spawnFeet = new Rectangle(401, 258, 13, 1);
				int arrayNum = 1200;
		 		int x = 0, y = 0;
				for(int i=0; i<arrayNum; i++){
					if(x>=800){
						x = 0;
						y+=20;
					}
					city1Blocks[i] = new Rectangle(x, y, 20, 20);
					x+=20;
				}
				c1i.cityOneInsidesInit(tl);
			}
			
			if(whichCity == 2){
				//add second city here
			}
		}
		
		public Tile[] setMapSecs(TileXY[] mapIn){
			Tile[] m = new Tile[1200];
			
			for(int i = 0; i < 1200; i++){
				m[i] = tl.tileMap.get(mapIn[i]);
			}
			return m;
		}
		
		public void setHouseOwner(int house, String name){
			ownerHouse[house] = name;
		}
		
		public void addDoorsLvlOne(){
			Set<Door> doors = new HashSet<Door>();
			
			doors.add(new Door(false, 165, 12, 280, 160));
			doors.add(new Door(false, 166, 12, 280, 160));
			doors.add(new Door(false, 173, 13, 120, 160));
			doors.add(new Door(false, 174, 13, 120, 160));
			doors.add(new Door(false, 186, 14, -120, 160));
			doors.add(new Door(false, 195, 15, -300, 160));
			doors.add(new Door(false, 386, 16, -120, 40));
			doors.add(new Door(false, 395, 17, -300, 40));
			doors.add(new Door(false, 605, 18, 400, 0));
			doors.add(new Door(false, 645, 18, 400, 0));
			doors.add(new Door(false, 685, 18, 400, 0));
			doors.add(new Door(false, 857, 19, 0, -100));
			doors.add(new Door(false, 858, 19, 0, -100));
			doors.add(new Door(false, 859, 19, 0, -100));
			doors.add(new Door(false, 860, 19, 0, -100));
			doors.add(new Door(false, 861, 19, 0, -100));
			doors.add(new Door(false, 862, 19, 0, -100));
			doors.add(new Door(false, 863, 19, 0, -100));
			
			doorsLvlOne.put(4, doors);
			doors = new HashSet<Door>();
			
			doors.add(new Door(false, 264, 22, -340, 140));
			doors.add(new Door(false, 304, 22, -340, 140));
			doors.add(new Door(false, 344, 22, -340, 140));
			
			doorsLvlOne.put(5, doors);
			doors = new HashSet<Door>();
			
			doors.add(new Door(false, 848, 24, -80, -40));
			doors.add(new Door(false, 849, 24, -80, -40));
			doors.add(new Door(false, 850, 24, -80, -40));
			doors.add(new Door(false, 851, 24, -80, -40));
			doors.add(new Door(false, 852, 24, -80, -40));
			doors.add(new Door(false, 853, 24, -80, -40));
			doors.add(new Door(false, 854, 24, -80, -40));
			doors.add(new Door(false, 855, 24, -80, -40));
			
			doorsLvlOne.put(6, doors);
			//TODO add monestary doors
		}
		private void addChairsLvlOne(){
			Set<Chair> chairs = new HashSet<Chair>();
			
			chairs.add(new Chair(567, 0, 0, 0, 144, 282));
			chairs.add(new Chair(568, 0, 0, 0, 160, 282));
			
			chairsLvlOne.put(4, chairs);
			chairs = new HashSet<Chair>();
			
			chairs.add(new Chair(733, 1, 1, 0, 264, 362));
			chairs.add(new Chair(734, 1, 1, 0, 280, 362));
			chairs.add(new Chair(746, 1, 1, 0, 524, 362));
			chairs.add(new Chair(747, 1, 1, 0, 540, 362));

			chairsLvlOne.put(5, chairs);
			chairs = new HashSet<Chair>();
			
			chairs.add(new Chair(41, 0, 0, 0, 24, 22));
			chairs.add(new Chair(42, 0, 0, 0, 40, 22));

			chairsLvlOne.put(6, chairs);
			chairs = new HashSet<Chair>();
			//TODO these chairs put you in the lake
			chairs.add(new Chair(496, 0, 2, 0, 324, 240));
			chairs.add(new Chair(497, 0, 3, 0, 340, 240));
			chairs.add(new Chair(503, 0, 2, 0, 464, 240));
			chairs.add(new Chair(504, 0, 3, 0, 480, 240));
			
			chairsLvlOne.put(8, chairs);
			chairs = new HashSet<Chair>();
			
			
		}
		
		
		public void loadCity16(){
			int arrayNum = 1200;
			int s = 5;
			//setSecIn(6);
			
			for(int i=0; i<arrayNum; i++){
				//grass
				if(i>=0 && i < 1200){
					city1[s][i] = tl.TILE_GRASS_BASIC;
				}
				//brown fence
				if(i == 39 || i == 79 || i == 119 || i == 159 || i == 199 || i == 239 || i == 279 || i == 319
						|| i == 359 || i == 399 || i == 439 || i == 479 || i == 519 || i == 559 || i == 599 || i == 639
						|| i == 679 || i == 719 || i == 759 || i == 799 || i == 839 || i == 879 || i == 919 || i == 959
						|| i == 999 || i == 1039 || i == 1079 || i == 1119 || i == 1159 || i == 1199){
					city1[s][i] = tl.TILE_GRASS_FENCE_VERT;
				}
				//Monastery  
				if(i > 104 && i < 117 || i > 144 && i < 157 || i > 184 && i < 197 || i > 224 && i < 237 || i > 264 && i < 277
						|| i > 304 && i < 317 || i > 344 && i < 357 || i > 384 && i < 397 || i > 424 && i < 437 || i > 464 && i < 477
						|| i > 504 && i < 517 || i > 544 && i < 557 || i > 584 && i < 597 || i > 624 && i < 637 || i > 664 && i < 677
						|| i > 704 && i < 717 || i > 744 && i < 757 || i > 784 && i < 797 || i > 824 && i < 837 || i > 842 && i < 877
						|| i > 882 && i < 917 || i > 922 && i < 957 || i > 962 && i < 997 || i > 1002 && i < 1037 || i > 1042 && i < 1077
						|| i > 1082 && i < 1117){
					city1[s][i] = tl.TILE_HOUSE_SAND;
				}
				if(i > 849 && i < 854){
					city1[s][i] = tl.TILE_DOOR_HOUSE_SAND_UP;
				}
				if(i == 225 || i == 265 || i == 305 || i == 345 || i == 385 || i == 425){
					city1[s][i] = tl.TILE_DOOR_HOUSE_SAND_LEFT;
				}
				//grey path
				if(i >= 600 && i<=622 || i>= 640 && i <= 662 || i >= 680 && i <= 702 || i > 458 && i <462 || i > 737 && i < 743 
						|| i > 577 && i < 583 || i > 538 && i < 542 || i > 498 && i < 502 || i > 418 && i < 422 || i > 378 && i < 382 
						|| i > 338 && i < 345 || i > 298 && i < 305 || i > 258 && i < 262 || i > 218 && i < 222 
						|| i > 178 && i < 182 || i > 138 && i < 142 || i > 98 && i < 102 || i > 58 && i < 62 || i > 18 && i < 22
						|| i > 808 && i < 815 || i > 769 && i < 774 || i > 730 && i < 733 || i > 262 && i < 265 || i > 382 && i < 385
						|| i == 224 || i == 424){
					city1[s][i] = tl.TILE_PATH_GREYRED;
				}
				//black fence
				if(i > 560 && i < 564 || i > 565 && i < 579 || i > 0 && i < 18){
					city1[s][i] = tl.TILE_GRASS_FENCE_HORZ_BLACK;
				}
				if(i == 520 || i == 480 || i == 440 || i == 400 || i == 360 || i == 320 || i == 280 || i == 240 || i == 200 || i == 160 
						|| i == 120 || i == 80 || i == 40 || i == 538 || i == 498 || i == 458 || i == 418 || i == 378 || i == 338 || i == 298
						|| i == 258 || i == 218 || i == 98 || i == 58){
					city1[s][i] = tl.TILE_GRASS_FENCE_VERT_BLACK;
				}
				if(i == 560){
					city1[s][i] = tl.TILE_GRASS_FENCE_BLC_BLACK;
				}
				if(i == 578){
					city1[s][i] = tl.TILE_GRASS_FENCE_BRC_BLACK;
				}
				if(i == 18){
					city1[s][i] = tl.TILE_GRASS_FENCE_TRC_BLACK;
				}
				if(i == 0){
					city1[s][i] = tl.TILE_GRASS_FENCE_TLC_BLACK;
				}
				//tombstones
				if(i > 0 && i < 578){
					if(cityDeaths > 0){
						if(i == 481 && cityDeaths >= 1){
							city1[s][i] = tl.TILE_GRASS_TOMB_JB;
						}
						if(i == 483 && cityDeaths >= 2){
							city1[s][i] = tl.TILE_GRASS_TOMB_TY;
						}
						if(i == 401 && cityDeaths >= 3){
							city1[s][i] = tl.TILE_GRASS_TOMB_FR;
						}
						if(i == 403 && cityDeaths >= 4){
							city1[s][i] = tl.TILE_GRASS_TOMB_SQ;
						}
						if(i == 321 && cityDeaths >= 5){
							city1[s][i] = tl.TILE_GRASS_TOMB_JB;
						}
						if(i == 323 && cityDeaths >= 6){
							city1[s][i] = tl.TILE_GRASS_TOMB_TY;
						}
						if(i == 241 && cityDeaths >= 7){
							city1[s][i] = tl.TILE_GRASS_TOMB_SQ;
						}
						if(i == 243 && cityDeaths >= 8){
							city1[s][i] = tl.TILE_GRASS_TOMB_FR;
						}
						if(i == 161 && cityDeaths >= 9){
							city1[s][i] = tl.TILE_GRASS_TOMB_TY;
						}
						if(i == 163 && cityDeaths >= 10){
							city1[s][i] = tl.TILE_GRASS_TOMB_JB;
						}
						if(i == 44 && cityDeaths >= 11){
							city1[s][i] = tl.TILE_GRASS_TOMB_FR;
						}
						if(i == 46 && cityDeaths >= 12){
							city1[s][i] = tl.TILE_GRASS_TOMB_TY;
						}
						if(i == 48 && cityDeaths >= 13){
							city1[s][i] = tl.TILE_GRASS_TOMB_SQ;
						}
						if(i == 50 && cityDeaths >= 14){
							city1[s][i] = tl.TILE_GRASS_TOMB_FR;
						}
						if(i == 52 && cityDeaths >= 15){
							city1[s][i] = tl.TILE_GRASS_TOMB_SQ;
						}
						if(i == 54 && cityDeaths >= 16){
							city1[s][i] = tl.TILE_GRASS_TOMB_JB;
						}
						if(i == 56 && cityDeaths >= 17){
							city1[s][i] = tl.TILE_GRASS_TOMB_TY;
						}
						if(i == 207 && cityDeaths >= 18){
							city1[s][i] = tl.TILE_GRASS_TOMB_TY;
						}
						if(i == 209 && cityDeaths >= 19){
							city1[s][i] = tl.TILE_GRASS_TOMB_JB;
						}
						if(i == 211 && cityDeaths >= 20){
							city1[s][i] = tl.TILE_GRASS_TOMB_FR;
						}
						if(i == 213 && cityDeaths >= 21){
							city1[s][i] = tl.TILE_GRASS_TOMB_SQ;
						}
						if(i == 215 && cityDeaths >= 22){
							city1[s][i] = tl.TILE_GRASS_TOMB_JB;
						}
						if(i == 217 && cityDeaths >= 23){
							city1[s][i] = tl.TILE_GRASS_TOMB_FR;
							
						}
						if(i == 287 && cityDeaths >= 24){
							city1[s][i] = tl.TILE_GRASS_TOMB_JB;
							
						}
						if(i == 289 && cityDeaths >= 25){
							city1[s][i] = tl.TILE_GRASS_TOMB_FR;
							
						}
						if(i == 291 && cityDeaths >= 26){
							city1[s][i] = tl.TILE_GRASS_TOMB_TY;
							
						}
						if(i == 293 && cityDeaths >= 27){
							city1[s][i] = tl.TILE_GRASS_TOMB_SQ;
							
						}
						if(i == 295 && cityDeaths >= 28){
							city1[s][i] = tl.TILE_GRASS_TOMB_FR;
							
						}
						if(i == 297 && cityDeaths >= 29){
							city1[s][i] = tl.TILE_GRASS_TOMB_TY;
							
						}
						if(i == 367 && cityDeaths >= 30){
							city1[s][i] = tl.TILE_GRASS_TOMB_FR;
							
						}
						if(i == 369 && cityDeaths >= 31){
							city1[s][i] = tl.TILE_GRASS_TOMB_SQ;
							
						}
						if(i == 371 && cityDeaths >= 32){
							city1[s][i] = tl.TILE_GRASS_TOMB_TY;
							
						}
						if(i == 373 && cityDeaths >= 33){
							city1[s][i] = tl.TILE_GRASS_TOMB_JB;
							
						}
						if(i == 375 && cityDeaths >= 34){
							city1[s][i] = tl.TILE_GRASS_TOMB_SQ;
							
						}
						if(i == 377 && cityDeaths >= 35){
							city1[s][i] = tl.TILE_GRASS_TOMB_FR;
							
							}
						if(i == 447 && cityDeaths >= 36){
							city1[s][i] = tl.TILE_GRASS_TOMB_SQ;
							
						}
						if(i == 449 && cityDeaths >= 37){
							city1[s][i] = tl.TILE_GRASS_TOMB_TY;
							
						}
						if(i == 451 && cityDeaths >= 38){
							city1[s][i] = tl.TILE_GRASS_TOMB_FR;
							
						}
						if(i == 453 && cityDeaths >= 39){
							city1[s][i] = tl.TILE_GRASS_TOMB_SQ;
							
						}
						if(i == 455 && cityDeaths >= 40){
							city1[s][i] = tl.TILE_GRASS_TOMB_JB;
							
						}
						if(i == 457 && cityDeaths >= 41){
							city1[s][i] = tl.TILE_GRASS_TOMB_TY;
							
						}
					}
				}
				//dirt path
				if(i > 563 && i < 566 || i > 523 && i < 526 || i > 483 && i < 486 || i > 443 && i < 446 || i > 403 && i < 406 
						|| i > 363 && i < 366 || i > 323 && i < 326 || i > 283 && i < 286 || i > 243 && i < 246 || i > 203 && i < 206
						|| i > 163 && i < 179 || i > 123 && i < 139){
					city1[s][i] = tl.TILE_PATH_DIRT;
					
				}
				//bench
				if(i == 41 || i == 42){
					city1[s][41] = tl.TILE_GRASS_BENCH_LEFT_DOWN;
					city1[s][42] = tl.TILE_GRASS_BENCH_RIGHT_DOWN;
					
				} 
				//sign
				if(i == 808 || i == 464){
					city1[s][i] = tl.TILE_GRASS_SIGN;
					
				}
				
				
			}
			if(city1[s][1100].getImage() != null){
				System.out.println("the image 6 is good");
			}		
		}
		
		public void draw(Graphics g, Player p){
			int s = p.getCurrentSec()-1;
			if(p.getCurrentCity() == 1){
				if(p.getInside()){
					c1i.draw(g, p.getCurrentBuilding());
				}else{
					for(int i = 0; i < 1200; i++){
						if(i < 1200){
							g.drawImage( city1[s][i].getImage(), city1Blocks[i].x, city1Blocks[i].y, null);
						}
					}
				}
			}
			
			
			
		}
		public void drawTrees(Graphics g, Player p){
			if(p.getCurrentCity() == 1){
				if(p.getInside()){
					
				}else{
					int s = p.getCurrentSec()-1;
					for(int i = 0; i < 1200; i++){
						if(s>5)
							g.drawImage(city1Tree[s][i].getImage(), city1Blocks[i].x, city1Blocks[i].y, null);
					}
				}
			}
		}
		
}
	

