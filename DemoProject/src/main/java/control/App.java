package control;

import model.PolModel;
import view.PolView;

/**
 * <h1>Polynomial Calculator</h1> This application is a simple calculator
 * working with polynomials calculations take place in the background so no need
 * to instantiate another object here
 *
 * @author Gabor Csaba Laszlo
 * @version 1.0
 * @since 2018-03-11
 */
public class App {

	/**
	 * <p>
	 * This is the main method
	 * <p>
	 * it creates the model, view and controller for the GUI
	 * <p>
	 * calculations take place in the background so no need to instantiate another
	 * object here
	 */
	public static void main(String[] args) {
		// create the classes which make up the MVC model
		PolModel model = new PolModel();
		PolView view = new PolView(model);
		PolController controller = new PolController(model, view);
		// sets the view to be visible
		view.setVisible(true);

	}
}
