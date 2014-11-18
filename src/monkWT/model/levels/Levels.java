package monkWT.model.levels;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import monkWT.model.Door;
import monkWT.model.Tile;
import monkWT.model.TileLoader;
import resources.ResourceLoader;




public class Levels {

		
	
		//Level boxes
		public Tile[][] city1Tree = new Tile[9][1200];
		public Tile[][] city1 = new Tile[9][1200];
		public Rectangle[] city1Blocks = new Rectangle[1200];
		
		//player local
		private int playerInSec;
		private int playerInCity = 1;
		private boolean playerInside = false;
		
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
		
		
		//class declarations
		//city one insides
		public CityOneInsides c1i = new CityOneInsides();
		private ResourceLoader rl = new ResourceLoader();
		private TileLoader tl = new TileLoader();
		
		public void setBuildingEnt(int buildingNum){
			c1i.setBuildingEnt(buildingNum);
			if(buildingNum == 0){
				setPlayerInside(false);
			}else{
				setPlayerInside(true);
			}
		}
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
		 		/*
				loadCity11();
				loadCity12();
				loadCity13();
				loadCity14();
				loadCity15();
				loadCity16();
				loadCity17();
				loadCity18();
				loadCity19();
				try {
					rl.saveMap(city1Tree[6], "city1s7tree");
					rl.saveMap(city1Tree[7], "city1s8tree");
					rl.saveMap(city1Tree[8], "city1s9tree");
				} catch (IOException e) {
					e.printStackTrace();
				}*/
				c1i.cityOneInsidesInit(tl);
				c1i.addInsideDoors();
			}
			
			if(whichCity == 2){
				//add second city here
			}
		}
		
		public Tile[] setMapSecs(int[] mapIn){
			Tile[] m = new Tile[1200];
			
			for(int i = 0; i < 1200; i++){
				m[i] = tl.tileArray[mapIn[i]];
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
			
			doors.add(new Door(false, 264, 21, -340, 140));
			doors.add(new Door(false, 304, 21, -340, 140));
			doors.add(new Door(false, 344, 21, -340, 140));
			
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
		}
		
		//loads levels
		/*public void loadCity11(){
			int arrayNum = 1200;
			int s = 0;
			playerInSec = 1;
			
			
			spawnRect = new Rectangle(400, 243, 15, 15);
	        spawnLeft = new Rectangle(400, 245, 1, 13);
	        spawnRight = new Rectangle(415, 245, 1, 13);
	        spawnTop = new Rectangle(401, 243, 13, 1);
	 		spawnFeet = new Rectangle(401, 258, 13, 1);
	 		int x = 0, y = 0;
			for(int i=0; i<arrayNum; i++){
				if(x>=800){
					x = 0;
					y+=20;
				}
				city1Blocks[i] = new Rectangle(x, y, 20, 20);
				x+=20;
				//grass
				if(i>=0 && i < 1200){
					city1[s][i] = TILE_GRASS_BASIC;
				}
				//purple house
				if(i >= 530 && i<= 534 || i>=490 && i<= 494 || i>=450 && i<=454 || i>= 410 && i<=414 || i>= 370 && i<= 374){
					city1[s][i] = TILE_HOUSE_PURPLE;
					
				}
				if(i == 454){
					city1[s][i] = TILE_DOOR_HOUSE_PURPLE;
				}
				//orange house
				if(i <= 550 && i>= 546 || i<=510 && i>= 506 || i<=470 && i>=466 || i<= 430 && i>=426 || i<= 390 && i>= 386){
					city1[s][i] = TILE_HOUSE_ORANGE;
				}
				if(i == 466){
					city1[s][i] = TILE_DOOR_HOUSE_ORANGE;
				}
				//red house
				if(i >= 930 && i<= 934 || i>=890 && i<= 894 || i>=850 && i<=854 || i>= 810 && i<=814 || i>= 770 && i<= 774){
					city1[s][i] = TILE_HOUSE_RED;
				}
				if(i == 854){
					city1[s][i] = TILE_DOOR_HOUSE_RED;
				}
				//blue house
				if(i <= 950 && i>= 946 || i<=910 && i>= 906 || i<=870 && i>=866 || i<= 830 && i>=826 || i<= 790 && i>= 786){
					city1[s][i] = TILE_HOUSE_BLUE;
				}
				if(i == 866){
					city1[s][i] = TILE_DOOR_HOUSE_BLUE;
				}
				//fence
				if(i == 40 || i == 80 || i == 120 || i == 160 || i == 200 || i == 240 || i == 280 || i == 320
						|| i == 360 || i == 400 || i == 440 || i == 480 || i == 520 || i == 560 || i == 600 || i == 640
						|| i == 680 || i == 720 || i == 760 || i == 800 || i == 840 || i == 880 || i == 920 || i == 960
						|| i == 1000 || i == 1040 || i == 1080 || i == 1120 || i == 1160){
					city1[s][i] = TILE_GRASS_FENCE_VERT;
				}
				if(i > 0 && i < 40){
					city1[s][i] = TILE_GRASS_FENCE_HORZ;
				}
				if(i == 0){, "city1s9tree");
				} catch (IOException e) {
					city1[s][i] = TILE_GRASS_FENCE_TLC;
				}
				//grey path
				if(i >= 610 && i<=639 || i>= 650 && i <= 679 || i >= 690 && i <= 719 || i > 454 && i <466  || i > 854 && i < 866 
						|| i > 898 && i < 902 || i > 938 && i < 942 || i > 818 && i < 822 || i > 778 && i < 782 || i > 737 && i < 743 
						|| i > 577 && i < 583 || i > 538 && i < 542 || i > 498 && i < 502 || i > 418 && i < 422 || i > 378 && i < 382
						|| i > 978 && i < 982 || i > 1018 && i < 1022 || i > 1058 && i < 1062 || i > 1098 && i < 1102 || i > 1138 && i < 1142
						|| i > 1178 && i < 1182){
					city1[s][i] = TILE_PATH_GREYRED;
				}
				//sign
				if(i == 495 || i == 505 || i == 895 || i == 905){
					city1[s][i] = TILE_GRASS_SIGN;
				}
				
			}
			if(city1[0][1100].getImage() != null){
				System.out.println("the image 1 is good");
			}		
		}
		public void loadCity12(){
			int arrayNum = 1200;
			int s = 1;
			playerInSec = 2;
			
			for(int i=0; i<arrayNum; i++){
				
				//grass
				if(i>=0 && i < 1200){
					city1[s][i] = TILE_GRASS_BASIC;
				}
				//fence
				if(i >= 0 && i < 40){
					city1[s][i] = TILE_GRASS_FENCE_HORZ;
				}
				//Bank
				if(i > 122 && i < 157 || i > 162 && i < 197 || i > 202 && i < 237 || i > 242 && i < 277 
						|| i > 282 && i < 317 || i > 322 && i < 357 || i > 362 && i < 397 || i > 402 && i < 437 || i > 442 && i < 477
						|| i == 497 || i == 503){
					city1[s][i] = TILE_HOUSE_GOLD;i > 848 && i < 855)
				}
				if(i > 457 && i < 463){
					city1[s][i] = TILE_DOOR_HOUSE_GOLD;
				}
				//police station
				if(i > 801 && i < 818 || i > 841 && i < 858 || i > 881 && i < 898 || i > 921 && i < 938 || i > 961 && i < 978
						|| i > 1001 && i < 1018 || i > 1041 && i < 1058 || i > 1081 && i < 1098){
					city1[s][i] = TILE_HOUSE_GREY_PAT_BLUE;
				}
				if(i > 808 && i < 811){
					city1[s][i] = TILE_DOOR_HOUSE_GREY_PAT_BLUE;
				}
				//houses
				if(i > 783 && i < 789 || i > 823 && i < 829 || i > 863 && i < 869 || i > 903 && i < 909 
						|| i > 983 && i < 989 || i > 1023 && i < 1029 || i > 1063 && i < 1069 || i > 1103 && i < 1109
						|| i > 792 && i < 798 || i > 832 && i < 838 || i > 872 && i < 878 || i > 912 && i < 918 
						|| i > 992 && i < 998 || i > 1032 && i < 1038 || i > 1072 && i < 1078 || i > 1112 && i < 1118){
					city1[s][i] = TILE_HOUSE_WHITE_PAT_BLUE;
				}
				//right side house doors
				if(i == 833 || i == 873 || i == 1033 || i == 1073){
					city1[s][i] = TILE_DOOR_HOUSE_WHITE_PAT_BLUE_LEFT;
				}
				//left side house doors
				if(i == 828 || i == 868 || i == 1028 || i == 1068){
					city1[s][i] = TILE_DOOR_HOUSE_WHITE_PAT_BLUE_RIGHT;
				}rl.saveMap(city1Inside[17], "city1iBusStation");
				//grey pathi > 848 && i < 855)
				if(i >= 600 && i<=639 || i>, "city1s9tree");
				} catch (IOException e) {= 640 && i <= 679 || i >= 680 && i <= 719 || i > 858 && i < 862 
						|| i > 898 && i < 902 || i > 938 && i < 942 || i > 818 && i < 822 || i > 778 && i < 782 || i > 737 && i < 743 
						|| i > 577 && i < 583 || i > 978 && i < 982 || i > 1018 && i < 1022 || i > 1058 && i < 1062 || i > 1098 && i < 1102 
						|| i > 1138 && i < 1142 || i > 1178 && i < 1182 || i > 497 && i < 503 || i > 538 && i < 542 || i > 768 && i < 771
						|| i > 728 && i < 731 || i > 749 && i < 752 || i > 789 && i < 792 || i > 828 && i < 833 || i > 868 && i < 873 
						|| i > 909 && i < 912 || i > 949 && i < 952 || i > 989 && i < 992 || i > 1028 && i < 1033 || i > 1068 && i < 1073
						|| i > 1109 && i < 1112){
					city1[s][i] = TILE_PATH_GREYRED;
				}
				//sign
				if(i == 496 || i == 768 || i == 909 || i == 912 || i == 1109 || i == 1112){
					city1[s][i] = TILE_GRASS_SIGN;
				}
				
			}
			if(city1[s][1100].getImage() != null){
				System.out.println("the image 2 is good");
			}
			
		}
		public void loadCity13(){
			int arrayNum = 1200;
			int s = 2;
			playerInSec = 3;
			
			, "city1s9tree");
				} catch (IOException e) {
			for(int i=0; i<arrayNum; i++){
				
				//grass
				if(i>=0 && i < 1200){
					city1[s][i] = TILE_GRASS_BASIC;
				}
				//fence
				if(i == 39 || i == 79 || i == 119 || i == 159 || i == 199 || i == 239 || i == 279 || i == 319
						|| i == 359 || i == 399 || i == 439 || i == 479 || i == 519 || i == 559 || i == 599 || i == 639
						|| i == 679 || i == 719 || i == 759 || i == 799 || i == 839 || i == 879 || i == 919 || i == 959
						|| i == 999 || i == 1039 || i == 1079 || i == 1119 || i == 1159 || i == 1199){
					city1[s][i] = TILE_GRASS_FENCE_VERT;
				}
				if(i >= 0 && i < 40){
					city1[s][i] = TILE_GRASS_FENCE_HORZ;
				}
				if(i == 39){
					city1[s][i] = TILE_GRASS_FENCE_TRC;
				}
				//school
				if(i > 122 && i < 157 || i > 162 && i < 197 || i > 202 && i < 237 || i > 242 && i < 277 
						|| i > 282 && i < 317 || i > 322 && i < 357 || i > 362 && i < 397 || i > 402 && i < 437 || i > 442 && i < 477
						|| i > 504 && i < 517 || i > 544 && i < 557 || i > 584 && i < 597 || i > 624 && i < 637 || i > 664 && i < 677
						|| i > 704 && i < 717 || i > 744 && i < 757 || i > 784 && i < 797 || i > 824 && i < 837 || i > 864 && i < 877
						|| i > 904 && i < 917 || i > 944 && i < 957 || i > 984 && i < 997 || i > 1024 && i < 1037 || i > 1064 && i < 1077
						|| i > 1104 && i < 1117){
					city1[s][i] = TILE_HOUSE_LBLUE;
				}
				if(i > 448 && i < 455){
					city1[s][i] = TILE_DOOR_, "city1s9tree");
				} catch (IOException e) {HOUSE_LBLUE_DOWN;
				}
				if(i == 905 || i == 945 || i == 985){
					city1[s][i] = TILE_DOOR_HOUSE_LBLUE_LEFT;
				}
				//grey path
				if(i >= 600 && i<=622 || i>= 640 && i <= 662 || i >= 680 && i <= 702 || i > 858 && i < 862 
						|| i > 898 && i < 905 || i > 938 && i < 945 || i > 818 && i < 822 || i > 778 && i < 782 || i > 737 && i < 743 
						|| i > 577 && i < 583 || i > 978 && i < 985 || i > 1018 && i < 1022 || i > 1058 && i < 1062 || i > 1098 && i < 1102 
						|| i > 1138 && i < 1142 || i > 1178 && i < 1182 || i > 488 && i < 495 || i > 528 && i < 535 || i > 568 && i < 575){
					city1[s][i] = TILE_PATH_GREYRED;
				}
				//sign
				if(i == 488 || i == 1024){
					city1[s][i] = TILE_GRASS_SIGN;
				}
				//TODO add garden 
				
				
				
			}rl.saveMap(city1[5], "city1s6");
			if(city1[s][1100].getImage() != null){
				System.out.println("the image 3 is good");
			}		
		}
		public void loadCity14(){
			int arrayNum = 1200;
			int s = 3;
			playerInSec = 4;
			
			
			for(int i=0; i<arrayNum; i++){
				//make grass
				if(i>=0 && i < 1200){
					city1[s][i] = TILE_GRASS_BASIC;
				}
				//fence
				if(i == 0 || i == 40 || i == 80 || i == 120 || i == 160 || i == 200 || i == 240 || i == 280 || i == 320
						|| i == 360 || i == 400 || i == 440 || i == 480 || i == 520 || i == 560 || i == 600 || i == 640
						|| i == 680 || i == 720 || i == 760 || i == 800 || i == 840 || i == 880 || i == 920 || i == 960
						|| i == 1000 || i == 1040 || i == 1080 || i == 1120 || i == 1160){
					city1[s][i] = TILE_GRASS_FENCE_VERT;
				}
				//bus station
				if(i > 439 && i < 445 || i > 479 && i < 485 || i > 519 && i < 525 || i > 559 && i < 566 || i > 599 && i < 605
						|| i > 639 && i < 645 || i > 679 && i < 685 || i > 719 && i < 726 || i > 759 && i < 765 || i > 799 && i < 805
						|| i > 839 && i < 845){
					city1[s][i] = TILE_HOUSE_RED_PAT_GREY;
				}
				if(i == 605 || i == 645 || i == 685){
					city1[s][i] = TILE_DOOR_HOUSE_RED_PAT_GREY;
				}
				//hotel
				if( i > 846 && i < 877|| i > 886 && i < 917 || i > 926 && i < 957 || i > 966 && i < 997 || i > 1006 && i < 1037
						||i > 1046 && i < 1077 || i > 1086 && i < 1117){
					city1[s][i] = TILE_HOUSE_BLUE_PAT_PINK;
				}
				if(i > 857 && i < 863){
					city1[s][i]= TILE_DOOR_HOUSE_BLUE_PAT_PINK;
				}
				//houses
				if(i > 63 && i < 69 || i > 103 && i < 109 || i > 143 && i < 149 || i > 183 && i < 189 
						|| i > 383 && i < 389 || i > 423 && i < 429 || i > 463 && i < 469 || i > 503 && i < 509
						|| i > 72 && i < 78 || i > 112 && i < 118 || i > 152 && i < 158 || i > 192 && i < 198 
						|| i > 392 && i < 398 || i > 432 && i < 438 || i > 472 && i < 478 || i > 512 && i < 518
						//big houses
						|| i > 42 && i < 49 || i > 82 && i < 89 || i > 122 && i < 129 || i > 162 && i < 169
						|| i > 50 && i < 57 || i > 90 && i < 97 || i > 130 && i < 137 || i > 170 && i < 177){
					city1[s][i] = TILE_HOUSE_WHITE_PAT_ORANGE;
				}
				//bottom house doors
				if(i == 165 || i == 166 || i == 173 || i == 174 || i == 186 || i == 195 ){
					city1[s][i] = TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_DOWN;
				}
				//top house doors
				if(i == 386 || i == 395){
					city1[s][i] = TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_UP;
				}
				//path
				if(i >= 606 && i <= 639 || i >= 646 && i <= 679 || i >= 686 && i <= 719 || i > 458 && i < 462 || i > 737 && i < 743 
						|| i > 577 && i < 583 || i > 538 && i < 542 || i > 498 && i < 502 || i > 418 && i < 422 || i > 378 && i < 382
						|| i > 338 && i < 342 || i > 282 && i < 318 || i > 242 && i < 278 || i > 218 && i < 222 
						|| i > 178 && i < 182 || i > 138 && i < 142 || i > 98 && i < 102 || i > 58 && i < 62 || i > 18 && i < 22
						|| i > 778 && i < 782 || i > 817 && i < 823 || i == 226 || i == 235 || i == 346 || i == 355 || i == 205 
						|| i == 206 || i == 213 || i == 214){
					city1[s][i] = TILE_PATH_GREYRED;
				}
				//bench
				if(i == 567){
					city1[s][i] = TILE_GRASS_BENCH_LEFT_DOWN;
				}
				if(i == 568){
					city1[s][i] = TILE_GRASS_BENCH_RIGHT_DOWN;
				}
				//sign
				if(i == 765 || i == 817 || i == 225 || i == 234 || i == 345 || i == 354 || i == 204 || i == 212){
					city1[s][i] = TILE_GRASS_SIGN;
				}
				, "city1s9tree");
				} catch (IOException e) {
			}
			if(city1[s][1100].getImage() != null){
				System.out.println("the image 4 is good");
			}		
		}
		public void loadCity15(){
			int arrayNum = 1200;
			int s = 4;
			playerInSec = 5;
			
			
			for(int i=0; i<arrayNum; i++){
				//grass
				if(i>=0 && i < 1200){
					rl.saveMap(city1[5], "city1s6");city1[s][i] = TILE_GRASS_BASIC;
				}
				//store
				if(i > 81 && i < 97 || i > 121 && i < 137 || i > 161 && i < 177 || i > 201 && i < 217 || i > 241 && i < 257 
						|| i > 281 && i < 297 || i > 321 && i < 337 || i > 361 && i < 377 || i > 401 && i < 417 || i > 441 && i < 457 
						|| i > 481 && i < 497){
					city1[s][i] = TILE_HOUSE_BROWN_PAT_MUST;
				}
				if(i == 256 || i == 296 || i == 336){
					city1[s][i] = TILE_DOOR_HOUSE_BROWN_PAT_MUST;
				}
				//club
				if(i > 103 && i < 118 || i > 143 && i < 158 || i > 183 && i < 198 || i > 223 && i < 238 || i > 263 && i < 278 
						|| i > 303 && i < 318 || i > 343 && i < 358 || i > 383 && i < 398 || i > 423 && i < 438 || i > 463 && i < 478 
						|| i > 503 && i < 518){
					city1[s][i] = TILE_HOUSE_BLACK_PAT_GREY;
				}
				if(i == 264 || i == 304 || i == 344){
					city1[s][i] = TILE_DOOR_HOUSE_BLACK_PAT_GREY;
				}, "city1s9tree");
				} catch (IOException e) {
				//town hall
				if(i > 803 && i < 836 || i > 843 && i < 876 || i > 883 && i < 916 || i > 923 && i < 956 || i > 963 && i < 996
						|| i > 1003 && i < 1036 || i > 1043 && i < 1076 || i > 1083 && i < 1116 ){
					city1[s][i] = TILE_HOUSE_WHITE;
				}
				if(i > 816 && i < 824){
					city1[s][i] = TILE_DOOR_HOUSE_WHITE;
				}
				//grey path
				if(i >= 600 && i <= 639 || i >= 640 && i <= 679 || i >= 680 && i <= 719 || i > 458 && i <462 
						|| i > 737 && i < 743 || i > 577 && i < 583 || i > 538 && i < 542 || i > 498 && i < 502 || i > 418 && i < 422 
						|| i > 378 && i < 382 || i > 336 && i < 344 || i > 296 && i < 304 || i > 256 && i < 264 || i > 218 && i < 222 
						|| i > 178 && i < 182 || i > 138 && i < 142 || i > 98 && i < 102 || i > 58 && i < 62 || i > 18 && i < 22 || i > 776 && i < 784 ){
					city1[s][i] = TILE_PATH_GREYRED;
				}
				//bench
				if(i == 733 || i == 746){
					city1[s][i] = TILE_GRASS_BENCH_LEFT_UP;
				}
				if(i == 734 || i == 747){
					city1[s][i] = TILE_GRASS_BENCH_RIGHT_UP;
				}
				//sign
				if(i == 377 || i == 383 || i == 776){
					city1[s][i] = TILE_GRASS_SIGN;
				}

			}
			if(city1[s][1100].getImage() != null){
				System.out.println("the image 5 is good");
			}		
		}
		*/
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
		/*
		public void loadCity17(){
			int arrayNum = 1200;
			int s = 6;
			playerInSec = 7;
			
			
			
			for(int i=0; i < arrayNum; i++){
				city1Tree[s][i] = TILE_BLANK;
				
			}
			
			for(int i=0; i<arrayNum; i++){
				
				//grass
				if(i>=0 && i < 1200){
					city1[s][i] = TILE_GRASS_BASIC;
					

					
				}
				//fence
				if(i == 0 || i == 40 || i == 80 || i == 120 || i == 160 || i == 200 || i == 240 || i == 280 || i == 320
						|| i == 360 || i == 400 || i == 440 || i == 480 || i == 520 || i == 560 || i == 600 || i == 640
						|| i == 680 || i == 720 || i == 760 || i == 800 || i == 840 || i == 880 || i == 920 || i == 960
						|| i == 1000 || i == 1040 || i == 1080 || i == 1120){
					city1[s][i] = TILE_GRASS_FENCE_VERT;
					
				}
				if(i > 1160 && i < 1200){
					city1[s][i] = TILE_GRASS_FENCE_HORZ;
					
				}
				if(i == 1160){
					city1[s][i] = TILE_GRASS_FENCE_BLC;
					
				}
				//blacksmiths
				if(i > 1004 && i < 1009 || i > 1044 && i < 1049 || i > 1084 && i < 1089 || i > 1124 && i < 1129){
					city1[s][i] = TILE_HOUSE_BLACK_PAT_BROWN;
					
				}
				if(i == 1045 || i == 1085){
					city1[s][i] = TILE_DOOR_HOUSE_BLACK_PAT_BROWN;
				}
				//sign]
				if(i == 1004){
					city1[s][i] = TILE_GRASS_SIGN;
				}
				//tree
				if( i == 42|| i == 86|| i == 50 || i == 96 || i == 60 || i == 106 || i == 112 || i == 76  
						|| i == 204 || i == 248 || i == 212 || i == 256 || i == 230 || i == 274 
						|| i == 362 || i == 406 || i == 370 || i == 380 || i == 424 || i == 428 || i == 392
						|| i == 524 || i == 528 || i == 572 || i == 538 || i == 542 || i == 587 || i == 553 
						|| i == 681 || i == 725 || i == 690 || i == 695 || i == 740 || i == 704 || i == 748 || i == 716
						|| i == 843 || i == 849 || i == 893 || i == 857 || i == 864 || i == 869 || i == 915
						|| i == 1015 || i == 1060 || i == 1027 || i == 1071 || i == 1037
						){
					city1Tree[s][i] = TILE_TREE_LEFT_TOP;
					city1Tree[s][i+1] = TILE_TREE_CENT_TOP;
					city1Tree[s][(i+2)] = TILE_TREE_RIGHT_TOP;
					city1Tree[s][(i+40)] = TILE_TREE_LEFT_CENT;
					city1Tree[s][(i+41)] = TILE_TREE_CENT_CENT;
					city1Tree[s][(i+42)] = TILE_TREE_RIGHT_CENT;
					city1Tree[s][(i+80)] = TILE_TREE_LEFT_BTM;
					city1Tree[s][(i+81)] = TILE_TREE_CENT_BTM;
					city1Tree[s][(i+82)] = TILE_TREE_RIGHT_BTM;
				}
			}
			if(city1[s][1100].getImage() != null){
				System.out.println("the image 7 is good");
			}		
		}
		public void loadCity18(){
			int arrayNum = 1200;
			int s = 7;
			playerInSec = 8;
			
			
			
			for(int i=0; i < arrayNum; i++){
				city1Tree[s][i] = TILE_BLANK;
				
			}
			
			for(int i=0; i<arrayNum; i++){
				
				//grass
				if(i>=0 && i < 1200){
					city1[s][i] = TILE_GRASS_BASIC;
					

					
				}
				//lake
				if(i > 533 && i < 547 || i > 571 && i < 589 || i > 609 && i < 631 || i > 647 && i < 673 || i > 686 && i < 714 || i > 725 && i < 755
						|| i > 765 && i < 795 || i > 805 && i < 835 || i > 845 && i < 875 || i > 885 && i < 915 || i > 925 && i < 955 
						|| i > 966 && i < 994 || i > 1007 && i < 1033 || i > 1049 && i < 1071 || i > 1091 && i < 1109 || i > 1133 && i < 1147){
					city1[s][i] = TILE_WATER_BASIC;
					
				}
				if(i > 498 && i < 502 || i > 538 && i < 542 || i > 578 && i < 582 || i > 618 && i < 622 || i > 658 && i < 662 || i > 698 && i < 702){
					city1[s][i] = TILE_DOOR_HOUSE_MINE;
					
				}
				//tree
				if( i == 42|| i == 86|| i == 50 || i == 96 || i == 106 || i == 112 || i == 76  
						|| i == 204 || i == 248 || i == 212 || i == 256 || i == 230 || i == 274 
						|| i == 362 || i == 406 || i == 370 || i == 384 || i == 428 || i == 392 || i == 553
						|| i == 523 || i == 681 || i == 715 || i == 877 || i == 964 || i == 1036 || i == 1041 || i == 1072  
						){
					city1Tree[s][i] = TILE_TREE_LEFT_TOP;
					city1Tree[s][i+1] = TILE_TREE_CENT_TOP;
					city1Tree[s][(i+2)] = TILE_TREE_RIGHT_TOP;
					city1Tree[s][(i+40)] = TILE_TREE_LEFT_CENT;
					city1Tree[s][(i+41)] = TILE_TREE_CENT_CENT;
					city1Tree[s][(i+42)] = TILE_TREE_RIGHT_CENT;
					city1Tree[s][(i+80)] = TILE_TREE_LEFT_BTM;
					city1Tree[s][(i+81)] = TILE_TREE_CENT_BTM;
					city1Tree[s][(i+82)] = TILE_TREE_RIGHT_BTM;
				}
				//benches
				if(i == 496 || i == 503){
					city1[s][i] = TILE_GRASS_BENCH_LEFT_DOWN;
					
				}
				if(i == 497 || i == 504){
					city1[s][i] = TILE_GRASS_BENCH_RIGHT_DOWN;
					
				}
				//sign
				if(i == 458){
					city1[s][i] = TILE_GRASS_SIGN;
				}
				//fence
				if(i > 1159 && i < 1200){
					city1[s][i] = TILE_GRASS_FENCE_HORZ;9
					
				}
				
				
			}
			if(city1[s][1100].getImage() != null){
				System.out.println("the image 8 is good");
			}		
		}	
		public void loadCity19(){
			int arrayNum = 1200;
			int s = 8;
			playerInSec = 9;
			
			
			for(int i=0; i < arrayNum; i++){
				city1Tree[s][i] = TILE_BLANK;
				
			}
			
			for(int i=0; i<arrayNum; i++){
				//grass
				if(i>=0 && i < 1200){
					city1[s][i] = TILE_GRASS_BASIC;
					

					
				}
				//tree					city1IsSign[s][i] = true;

				if( i == 41|| i == 85|| i == 51 || i == 95 || i == 61 || i == 108 || i == 113 || i == 36  
						|| i == 202 || i == 248 || i == 211 || i == 255 || i == 232 || i == 276 
						|| i == 361 || i == 406 || i == 370 || i == 380 || i == 424 || i == 428 || i == 392
						|| i == 524 || i == 528 || i == 572 || i == 538 || i == 542 || i == 587 || i == 553 
						|| i == 681 || i == 725 || i == 690 || i == 695 || i == 740 || i == 704 || i == 748 || i == 716
						|| i == 843 || i == 849 || i == 893 || i == 857 || i == 864 || i == 869 || i == 915
						|| i == 1001 || i == 1045 || i == 1010 || i == 1015 || i == 1060 || i == 1027 || i == 1071 || i == 1036
						){
					city1Tree[s][i] = TILE_TREE_LEFT_TOP;
					city1Tree[s][i+1] = TILE_TREE_CENT_TOP;
					city1Tree[s][(i+2)] = TILE_TREE_RIGHT_TOP;
					city1Tree[s][(i+40)] = TILE_TREE_LEFT_CENT;
					city1Tree[s][(i+41)] = TILE_TREE_CENT_CENT;
					city1Tree[s][(i+42)] = TILE_TREE_RIGHT_CENT;
					city1Tree[s][(i+80)] = TILE_TREE_LEFT_BTM;
					city1Tree[s][(i+81)] = TILE_TREE_CENT_BTM;
					city1Tree[s][(i+82)] = TILE_TREE_RIGHT_BTM;
				}
				if(i == 1158){
					city1[s][i] = TILE_DOOR_HOUSE_MINE;
				}
				if(i == 1157){
					city1[s][i] = TILE_GRASS_SIGN;
				}
				//fence
				if(i == 39 || i == 79 || i == 119 || i == 159 || i == 199 || i == 239 || i == 279 || i == 319
						|| i == 359 || i == 399 || i == 439 || i == 479 || i == 519 || i == 559 || i == 599 || i == 639
						|| i == 679 || i == 719 || i == 759 || i == 799 || i == 839 || i == 879 || i == 919 || i == 959
						|| i == 999 || i == 1039 || i == 1079 || i == 1119 || i == 1159){
					city1[s][i] = TILE_GRASS_FENCE_VERT;
					
				}
				if(i > 1159 && i < 1199){
					city1[s][i] = TILE_GRASS_FENCE_HORZ;
					
				}
				if(i == 1199){
					city1[s][i] = TILE_GRASS_FENCE_BRC;
					
				}
				
				
				
			}
			if(city1[s][1100].getImage() != null){
				System.out.println("the image 9 is good");
			}		
		}
		
		*/
		
		public void draw(Graphics g){
			int s = getSecIn()-1;
			if(getCityIn() == 1){
				if(isPlayerInside()){
					c1i.draw(g);
				}else{
					for(int i = 0; i < 1200; i++){
						if(i < 1200){
							g.drawImage( city1[s][i].getImage(), city1Blocks[i].x, city1Blocks[i].y, null);
						}
					}
				}
			}
			
			
			
		}
		public void drawTrees(Graphics g){
			if(getCityIn() == 1){
				if(isPlayerInside()){
					
				}else{
					int s = getSecIn()-1;
					for(int i = 0; i < 1200; i++){
						if(s>5)
							g.drawImage(city1Tree[s][i].getImage(), city1Blocks[i].x, city1Blocks[i].y, null);
					}
				}
			}
		}
		public int getSecIn() {
			return playerInSec;
		}
		public void setSecIn(int playerInSec) {
			this.playerInSec = playerInSec;
		}
		public int getCityIn() {
			return playerInCity;
		}
		public void setCityIn(int playerInCity) {
			this.playerInCity = playerInCity;
		}
		public boolean isPlayerInside() {
			return playerInside;
		}
		public void setPlayerInside(boolean playerInside) {
			this.playerInside = playerInside;
		}
}
	

