package resources;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;

import javax.imageio.ImageIO;

import monkWT.model.levels.Tile;

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
	public int[] getMap(String city, boolean in, String filename) throws IOException{
		int[] mint = new int[1200];
		InputStream fin;
		if(in){
			fin = this.getClass().getResourceAsStream("/resources/files/maps/"+ city + "/inside/" + filename + ".txt");
		}else{
			fin = this.getClass().getResourceAsStream("/resources/files/maps/" + city + "/" + filename + ".txt");
		}
		ObjectInputStream finstrm = new ObjectInputStream(fin);
		try {
			mint = (int[]) finstrm.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		return mint;
	}
	public void saveMap(Tile[] min, String filename) throws IOException{
		int[] omap = new int[1200];
		for(int i = 0; i < 1200; i++){
			omap[i] = min[i].getId();
		}
		
		File hhm = new File(System.getProperty("user.home")+"/.monkwt/" + filename + ".txt");
		if(!hhm.exists()){
			FileOutputStream saveFile = new FileOutputStream(System.getProperty("user.home")+"/.monkwt/" + filename + ".txt");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(omap);
			save.close();
			saveFile.close();
		}
	}
	
	
	/*public OutputStream getSaveFileOut() throws IOException{
		
		OutputStream in = this.getClass().getResource("/resources/files/save.txt");
		return in;
	}*/
}
