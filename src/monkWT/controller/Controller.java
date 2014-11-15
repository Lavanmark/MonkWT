package monkWT.controller;

import monkWT.model.Model;
import monkWT.model.Model.State;
import monkWT.view.View;




public class Controller {

	
	private Model mainModel;
	private View mainView;
	private MonkKeyListener monkKL;
	private MonkMouseListener monkML;
	
	
	
	public Controller(Model model, View view){
		mainModel = model;
		mainView = view;
		
		monkKL = new MonkKeyListener(mainModel);
		monkML = new MonkMouseListener();
		mainView.linkMKL(monkKL);
		mainView.linkMML(monkML);
	}
	
	
	
	public void update(){
		
		switch(mainModel.getState()){
		case INITIALIZED:
			//initialized so switch states
			mainModel.setState(State.MENU);
			break;
		case MENU:
			mainModel.update();
			mainView.update();
			break;
		case LOAD:
			//TODO finish load
		    //load the game then start it
			
			mainModel.update();
			mainView.update();
			mainModel.setState(State.PLAYING);
			break;
		case PLAYING:
			mainModel.update();
			mainView.update();
			break;
		case INVENTORY:
        	// during the inventory state do this stuff

			break;
		case PAUSED:
			// while paused do this stuff
			break;
		case GAMEOVER:
			//menu.section = 3;
       	 
       	 	// break the loop to finish the current play
			break;
		default:
			System.out.println("ERROR: Controller Update Switch Hit Default.");
			break;
			
		}
	}
	
	
}
