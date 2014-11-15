package monkWT;


import javax.swing.SwingUtilities;


import monkWT.controller.Controller;
import monkWT.model.Model;
import monkWT.model.Model.State;
import monkWT.view.View;

public class Game {

	private Model model;
	private View view;
	private Controller controller;
	
	static final int UPDATE_RATE = 60;    // number of game update per second
	static final long UPDATE_PERIOD = 1000000000L / UPDATE_RATE;  // nanoseconds
	static int TICKS = 0;
	
	
	   
	public Game(){
	     gameInit();
	     gameStart();
	   }
	private void gameInit(){
		model = new Model();
		view = new View(model);
		controller = new Controller(model, view);
		model.setState(State.INITIALIZED);
	}
	private void gameStart(){
		Thread gameThread =  new Thread() {
		     // Override run() to provide the running behavior of this thread.
		     @Override
		     public void run() {
		        gameLoop();
		     }
		  };
		  // Start the thread. start() calls run(), which in turn calls gameLoop().
		  gameThread.start();
	}
	private void gameLoop(){
		long beginTime, timeTaken, timeLeft;
		while(true){
			beginTime = System.nanoTime();
			
			
			
			controller.update();
			
			
			timeTaken = System.nanoTime() - beginTime;
	        timeLeft = (UPDATE_PERIOD - timeTaken) / 1000000L;  // in milliseconds
	        if(timeLeft < 10) timeLeft = 6;   // set a minimum
	        TICKS++;
	        try{
	        	// Provides the necessary delay and also yields control so that other thread can do work.
	            Thread.sleep(timeLeft);
	         }catch(InterruptedException ex){
	        	 System.out.println("ERROR: Thread Interupted.");
	         }
		}
	}
	
	public static void main(String[] args) {
		// Use the event dispatch thread to build the UI for thread-safety.
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new Game();
	         }
	      });
	}

}
