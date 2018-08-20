package mvc;

import javax.swing.JFrame;

public class DrawingApp {

	public static void main(String[] args) {
		
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();

		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);
		controller.addObserver(frame);

		frame.setSize(960, 840);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
