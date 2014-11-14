package monkWT.controller;

import monkWT.model.Model;
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
		
		
		
		
		
		mainModel.update();
		mainView.update();
	}
}
