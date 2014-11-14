package monkWT.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import monkWT.controller.MonkKeyListener;
import monkWT.controller.MonkMouseListener;
import monkWT.model.Model;


public class ViewFrame extends JFrame{

	
	private GameCanvas canvas;
	private MonkKeyListener contrlMKL;
	private MonkMouseListener contrlMML;
	private Model mainModel;
	
	// width and height of the game screen
	static final int CANVAS_WIDTH = 1000;   //800 was original 
	static final int CANVAS_HEIGHT = 600;	//600
	
	
	public ViewFrame(){
		canvas = new GameCanvas();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		this.setContentPane(canvas);
	      // Other UI components such as button, score board, if any.
	      // ......
	      //this.setIconImage(gameIcon);
	      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	      this.pack();
	      //do most things after packing it
	      this.setLocationRelativeTo(null);
	      this.setTitle("Modus PONENS");
	      this.setVisible(true);
	      this.setResizable(false);
	}
	public void update(){
		repaint();
	}
	
	private void gameDraw(Graphics2D g2d){
	      /*switch (state) {
	         case INITIALIZED:
	            // ......
	            break;
	         case MENU:
	        	 //draw the menu
	        	 menu.drawMain(g2d);
	        	break;
	         case LOAD:
	        	 //draw the load thing while its loading 
	        	 menu.drawLoad(g2d);
	        	 break;
	         case PLAYING:
	             //draw the main map
	        	 lvl.draw(g2d);
	        	 //draw the player over map
	        	 player.draw(g2d);
	        	 //draw trees over player and map
	        	 lvl.drawTrees(g2d);
	        	 //draw HUD over everything
	        	 player.HUD.draw(g2d);
	        	 
	            break;private GameCanvas canvas;
	private PonKeyListener contrlMKL;
	private PonMouseListener contrlMML;
	private Model mainModel;
	
	// width and height of the game screen
	static final int CANVAS_WIDTH = 1000;   //800 was original 
	static final int CANVAS_HEIGHT = 600;	//600
	
	
	public ViewFrame(){
		canvas = new GameCanvas();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		this.setContentPane(canvas);
	      // Other UI components such as button, score board, if any.
	      // ......
	      //this.setIconImage(gameIcon);
	      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	      this.pack();
	      //do most things after packing it
	      this.setLocationRelativeTo(null);
	      this.setTitle("Modus PONENS");
	      this.setVisible(true);
	      this.setResizable(false);
	}
	public void update(){
		repaint();
	}
	
	private void gameDraw(Graphics2D g2d){
	      /*switch (state) {
	         case INITIALIZED:
	            // ......
	            break;
	         case MENU:
	        	 //draw the menu
	        	 menu.drawMain(g2d);
	        	break;
	         case LOAD:
	        	 //draw the load thing while its loading 
	        	 menu.drawLoad(g2d);
	        	 break;
	         case PLAYING:
	             //draw the main map
	        	 lvl.draw(g2d);
	        	 //draw the player over map
	        	 player.draw(g2d);
	        	 //draw trees over player and map
	        	 lvl.drawTrees(g2d);
	        	 //draw HUD over everything
	        	 player.HUD.draw(g2d);
	        	 
	            break;
	         case INVENTORY:
	        	 lvl.draw(g2d);
	        	 player.draw(g2d);
	        	 lvl.drawTrees(g2d);
	        	 player.HUD.draw(g2d);
	        	 break;public void linkPKLtoJP(PonKeyListener p){
			
		}
	         case PAUSED:
	         	 lvl.draw(g2d);
	        	 player.draw(g2d);
	        	 lvl.drawTrees(g2d);
	        	 player.HUD.draw(g2d);
	            break;
	         case GAMEOVER:
	            menu.drawMain(g2d);
	            break;
		default:
			break;
	         
	      } */
	}
	
	public void linkMKLtoJF(MonkKeyListener p){
		contrlMKL = p;
		canvas.setMKLtoJP();
	}
	public void linkMMLtoJF(MonkMouseListener p){
		contrlMML = p;
		canvas.setMMLtoJP();
	}
	public void linkModel(Model m){
		mainModel = m;
	}
	class GameCanvas extends JPanel {

		private static final long serialVersionUID = 1L;
		
		public GameCanvas(){
			setFocusable(true);  // so that can receive key-events
	        requestFocus();
	        setResizable(false);
		}
		
		private void setMKLtoJP(){
			addKeyListener(contrlMKL);
		}
		private void setMMLtoJP(){
			addMouseListener(contrlMML);
			addMouseMotionListener(contrlMML);
		}
		
		public void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D)g;
	        super.paintComponent(g2d);   // paint background
	        setBackground(Color.WHITE);  // may use an image for background
	        g2d.setColor(Color.BLACK);
	        // Draw the game objects
	        gameDraw(g2d);
	     }
		
	}
	         
}
