package monkWT.controller;

import monkWT.model.Model;
import monkWT.model.Model.State;
import monkWT.view.View;




public class Controller {

	
	private Model mainModel;
	private View mainView;
	private MonkKeyListener monkKL;
	private MonkMouseListener monkML;
	private SaveLoad saveLoad;
	
	
	
	public Controller(Model model, View view){
		mainModel = model;
		mainView = view;
		saveLoad = new SaveLoad();
		monkKL = new MonkKeyListener(mainModel);
		monkML = new MonkMouseListener(mainModel);
		mainView.linkMKL(monkKL);
		mainView.linkMML(monkML);
		monkKL.setSaveLoad(saveLoad);
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
			mainView.update();
			mainModel.prepareLoad(saveLoad);
			mainModel.update();
			mainModel.setState(State.PLAYING);
			System.out.println("LOADED");
			break;
		case PLAYING:
			mainModel.update();
			mainView.update();
			break;
		case INVENTORY:
        	// during the inventory state do this stuff
			mainModel.update();
			mainView.update();
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
