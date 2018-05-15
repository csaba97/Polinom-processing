package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PolModel;
import view.PolView;

/**
 * Controller Class for the MVC model of the GUI
 */
public class PolController {
	private PolModel m_model;
	private PolView m_view;

	/**
	 * The constructor adds Listeners to the PolView
	 */

	public PolController(PolModel model, PolView view) {
		m_model = model;
		m_view = view;

		view.addAddListener(new AddListener());
		view.addSubListener(new SubListener());
		view.addMulListener(new MulListener());
		view.addDivideListener(new DivideListener());
		view.addDerivateListener(new DerivateListener());
		view.addIntegrateAddListener(new IntegrateListener());
		view.addClearListener(new ClearListener());
	}

	/**
	 * Class for adding ActionListener to the add button in PolView
	 */
	class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String userInput = "";
			userInput = m_view.getInput();
			m_model.add(userInput);
			m_view.setOutput(m_model.getResult());
			m_view.resetMessage();
		}
	}

	/**
	 * Class for adding ActionListener to the substraction button in PolView
	 */
	class SubListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String userInput = "";
			userInput = m_view.getInput();
			m_model.sub(userInput);
			m_view.setOutput(m_model.getResult());
			m_view.resetMessage();
		}
	}

	/**
	 * Class for adding ActionListener to the multiplication button in PolView
	 */
	class MulListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String userInput = "";
			userInput = m_view.getInput();
			m_model.mul(userInput);
			m_view.setOutput(m_model.getResult());
			m_view.resetMessage();
		}
	}

	/**
	 * Class for adding ActionListener to the divide button in PolView
	 */
	class DivideListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String userInput = "";
			userInput = m_view.getInput();
			try {
				m_model.divide(userInput);
				m_view.setOutput(m_model.getResult());
				m_view.setRem(m_model.getRest());
				m_view.resetMessage();
			} catch (IllegalArgumentException exc) {
				System.err.println(exc.getMessage());
				m_view.setOutput(m_model.getResult());
				m_view.setRem("0");
				m_view.setMessage("Cannot divide with 0", 5);
			}

		}
	}

	/**
	 * Class for adding ActionListener to the derivate button in PolView
	 */
	class DerivateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_model.derivate();
			m_view.setOutput(m_model.getResult());
			m_view.resetMessage();
		}
	}

	/**
	 * Class for adding ActionListener to the integrate button in PolView
	 */
	class IntegrateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_model.integrate();
			m_view.setOutput(m_model.getResult());
			m_view.resetMessage();
		}
	}

	/**
	 * Class for adding ActionListener to the clear button in PolView
	 */
	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_model.reset();
			m_view.reset();
		}
	}

}
