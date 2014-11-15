package monkWT.view;


import monkWT.controller.MonkKeyListener;
import monkWT.controller.MonkMouseListener;
import monkWT.model.Model;
import monkWT.model.Model.State;;


public class View {

	private Model mainModel;
	private ViewFrame frame;
	
	private MonkKeyListener contrlMKL;
	private MonkMouseListener contrlMML;
	
	
	public View(Model model){
		mainModel = model;
		frame = new ViewFrame();
		frame.linkModel(mainModel);
		mainModel.setState(State.MENU);
	}
	
	public void linkMKL(MonkKeyListener p){
		contrlMKL = p;
		frame.linkMKLtoJF(contrlMKL);
	}
	public void linkMML(MonkMouseListener p){
		contrlMML = p;
		frame.linkMMLtoJF(contrlMML);
	    contrlMML.setMenu(frame.getMenu());
	}
	
	public void update(){
		frame.update();
	}
	
}
