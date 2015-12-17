package resources;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;

import javax.imageio.ImageIO;

import monkWT.model.levels.Tile;
import monkWT.model.levels.TileXY;

public class ResourceLoader implements Serializable{


	private static final long serialVersionUID = 1L;

	
	public Image getIcon(String fileName){
		return Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/images/icons/" + fileName));
	}
	
	public Image getTitle(String fileName){
		return Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/images/title/" + fileName));
	}
	
	public BufferedImage getSprite(String filename) throws IOException{
		URL url = this.getClass().getResource("/resources/images/sprites/" + filename);
		BufferedImage img = ImageIO.read(url);
		return img;
	}
	
	public BufferedImage getSquares(String filename) throws IOException{
		URL url = this.getClass().getResource("/resources/images/squares/" + filename);
		BufferedImage img = ImageIO.read(url);
		return img;
	}
	
	public static BufferedImage getSheet(String filename) throws IOException{
		URL url = ResourceLoader.class.getResource("/resources/images/squares/" + filename);
		BufferedImage img = ImageIO.read(url);
		return img;
	}
	
	public TileXY[] getMap(String city, boolean in, String filename) throws IOException{
		TileXY[] mint = new TileXY[1200];
		//TODO having a file input stream from a certain location might break if it is running from a jar
		FileInputStream fin;
		if(in){
			fin = new FileInputStream("./res/resources/files/maps/"+ city + "/inside/" + filename + ".mmc");
		}else{
			fin = new FileInputStream("./res/resources/files/maps/" + city + "/" + filename + ".mmc");
		}
		ObjectInputStream finstrm = new ObjectInputStream(fin);
		for(int i = 0; i < mint.length; i++){
			mint[i] = new TileXY(finstrm.readInt(),finstrm.readInt());
		}
		finstrm.close();
		fin.close();
		return mint;
	}
	
	public void saveMap(Tile[] min, String filename) throws IOException{
		TileXY[] omap = new TileXY[1200];
		for(int i = 0; i < 1200; i++){
			omap[i] = new TileXY(min[i].getImgX(), min[i].getImgY());
		}
		
		File hhm = new File(System.getProperty("user.home")+"/.monkwt/" + filename + ".txt");
		if(!hhm.exists()){
			FileOutputStream saveFile = new FileOutputStream(System.getProperty("user.home") + "/.monkwt/" + filename + ".txt");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			for(int i = 0; i < omap.length; i++){
	        	 save.writeInt(omap[i].x);
	        	 save.writeInt(omap[i].y);
	        }
			save.close();
			saveFile.close();
		}
	}
	
	
	/*public OutputStream getSaveFileOut() throws IOException{
		
		OutputStream in = this.getClass().getResource("/resources/files/save.txt");
		return in;
	}*/
}
