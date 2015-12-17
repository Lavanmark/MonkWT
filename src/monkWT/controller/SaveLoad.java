package monkWT.controller;

import java.io.*;

import monkWT.model.Item;



public class SaveLoad {

	//make these have starting game values
	private int Scity = 1;
	private int Ssec = 4;
	private int Sbuilding = 18;
	private boolean Sout = false;
	private int Sx = 260;
	private int Sy = 320;
	private double Scash = 1000;
	private double Sbankcash = 0;
	private int Squest = 0;
	private int Sdeaths = 0;
	private String[] Shouse = {"Bella","Jeffery","Jim Bob","Tiffany","Moe","David","Thomas","Larry","Jeb","Alfred"};
	private int[] Sinv = {0,0,0,0};
	
	public void save(int city,int sec,int building,boolean out,int x, int y,double cash,double bankcash, int quest,int deaths, String house[], Item inv[]) throws IOException{
		//couldn't save items most likely because images can't be serialized.
		//so i just made an array of ints which will be undone to create items in your inventory at load time.
		int[] saveInv = new int[29];
		for(int i = 0; i < inv.length; i++){
			saveInv[i] = inv[i].getItemNum();
		}
		FileOutputStream saveFile = new FileOutputStream(System.getProperty("user.home")+"/.monkwt/save.txt");
		ObjectOutputStream save = new ObjectOutputStream(saveFile);
		save.writeObject(city);
		save.writeObject(sec);
		save.writeObject(building);
		save.writeObject(out);
		save.writeObject(x);
		save.writeObject(y);
		save.writeObject(cash);
		save.writeObject(bankcash);
		save.writeObject(quest);
		save.writeObject(deaths);
		save.writeObject(house);
		save.writeObject(saveInv);
		save.close();
		saveFile.close();
		System.out.println("saved to file");
	}
	
	public void load() throws IOException{
		//"/home/tyler/Desktop/Game/MonkWT/res/resources/files/save.txt"
		String filePathString = System.getProperty("user.home") + "/.monkwt/save.txt";
		File f = new File(filePathString);
		if(f.exists()) {  
			FileInputStream loadFile = new FileInputStream(filePathString);
			ObjectInputStream load = new ObjectInputStream(loadFile);
			try {
				Scity = (Integer) load.readObject();
				Ssec = (Integer) load.readObject();
				Sbuilding = (Integer) load.readObject();
				Sout = (Boolean) load.readObject();
				Sx = (Integer) load.readObject();
				Sy = (Integer) load.readObject();
				Scash = (Double) load.readObject();
				Sbankcash = (Double) load.readObject();
				Squest = (Integer) load.readObject();
				Sdeaths = (Integer) load.readObject();
				Shouse = (String[]) load.readObject();
				Sinv = (int[]) load.readObject();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			load.close();
			loadFile.close();
			
			System.out.println("files loaded");
		}else{
			File d = new File(System.getProperty("user.home")+"/.monkwt");
			d.mkdirs();
				System.out.println("Dir made");
			File o = new File(System.getProperty("user.home")+"/.monkwt/save.txt");
			o.createNewFile();
		}
	}
	public int getCity(){
		return Scity;
	}
	public  int getSec(){
		return Ssec;
	}
	public  int getBuilding(){
		return Sbuilding;
	}
	public  boolean getOutside(){
		return Sout;
	}
	public  int getXLoc(){
		return Sx;
	}
	public  int getYLoc(){
		return Sy;
	}
	public  double getCash(){
		return Scash;
	}
	public  double getBankCash(){
		return Sbankcash;
	}
	public int getQuest(){
		return Squest;
	}
	public int getDeaths(){
		return Sdeaths;
	}
	public String[] getOwners(){
		return Shouse;
	}
	public int[] getInv(){
		return Sinv;
	}
}
