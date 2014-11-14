package monkWT.view;

import monkWT.controller.MonkKeyListener;
import monkWT.controller.MonkMouseListener;
import monkWT.model.Model;



public class View {

	private Model mainModel;
	private ViewFrame frame;
	
	private MonkKeyListener contrlMKL;
	private MonkMouseListener contrlMML;
	
	static enum State {
	      INITIALIZED, MENU, LOAD, STARTNEW, PLAYING, INVENTORY, PAUSED, GAMEOVER, DESTROYED
	   }
	static State state;   // current state of the game
	
	public View(Model model){
		mainModel = model;
		frame = new ViewFrame();
		frame.linkModel(mainModel);
		state = State.MENU;
	}
	
	public void linkMKL(MonkKeyListener p){
		contrlMKL = p;
		frame.linkMKLtoJF(contrlMKL);
	}
	public void linkMML(MonkMouseListener p){
		contrlMML = p;
		frame.linkMMLtoJF(contrlMML);
	}
	
	public void update(){
		frame.update();
	}
}
