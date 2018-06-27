package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.PolModel;

/**
 * View Class for the MVC model of the GUI
 */
public class PolView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PolModel m_model;

	private Container content;
	private JPanel panel;
	private JPanel panel2;
	private JPanel panel3;
	private JTextField t_rem = new JTextField(25);
	private JTextField t_input = new JTextField(25);
	private JTextField t_output = new JTextField(25);
	private JButton b_addBtn = new JButton("Add");
	private JButton b_subBtn = new JButton("Substract");
	private JButton b_mulBtn = new JButton("Multiply");
	private JButton b_integrateBtn = new JButton("Integrate");
	private JButton b_derivateBtn = new JButton("Derivate");
	private JButton b_clearBtn = new JButton("Clear");
	private JButton b_divBtn = new JButton("Divide");
	private JLabel l_input = new JLabel("Input");
	private JLabel l_output = new JLabel("Result/Quotient");
	private JLabel l_rem = new JLabel("Remainder");
	private JLabel l_message = new JLabel("");
	private JLabel l_empty2 = new JLabel("");

	/**
	 * Constructs the view:sets the JFrame title, adds buttons,labels,fields to the
	 * panel, adds the panel to the frame
	 */
	public PolView(PolModel model) {
		this.m_model = model;
		content = getContentPane();
		content.setLayout((new BorderLayout()));

		panel = new JPanel(new GridLayout(7, 2));
		panel2 = new JPanel(new GridLayout(3, 2));
		panel3 = new JPanel();

		panel.add(l_message);
		panel.add(l_empty2);
		
		panel.add(l_input);
		panel.add(t_input);
		panel.add(l_output);
		panel.add(t_output);
		
		panel.add(l_rem);
		panel.add(t_rem);

		panel3.add(b_clearBtn,Component.CENTER_ALIGNMENT);
		
		panel2.add(b_addBtn);
		panel2.add(b_subBtn);
		panel2.add(b_mulBtn);
		panel2.add(b_divBtn);
		panel2.add(b_derivateBtn);
		panel2.add(b_integrateBtn);
		
		

		content.add(panel2, BorderLayout.SOUTH);
		content.add(panel, BorderLayout.NORTH);
		content.add(panel3, BorderLayout.CENTER);

		this.setTitle("Polinomial Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		// this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		reset();
	}

	/**
	 * Resets all the text fields with 0
	 */
	public void reset() {
		t_input.setText("0");
		t_rem.setText("0");
		t_output.setText("0");
		resetMessage();
	}

	/**
	 * gets the User input
	 */
	public String getInput() {
		return t_input.getText();
	}

	/**
	 * Sets the output/result text field
	 */
	public void setOutput(String newTotal) {
		t_output.setText(newTotal);
	}

	/**
	 * Sets the output/remainder text field
	 */
	public void setRem(String newTotal) {
		t_rem.setText(newTotal);
	}

	/**
	 * Sets the Error/Message text field
	 */
	public void setMessage(String message, int severity) {
		if (severity == 5)
			l_message.setForeground(Color.RED);
		else
			l_message.setForeground(Color.BLACK);
		l_message.setText(message + " (severity " + severity + ")");
	}

	/**
	 * Resets the Error/Message text field
	 */
	public void resetMessage() {
		l_message.setForeground(Color.BLACK);
		l_message.setText("");
	}

	/**
	 * Adds an ActionListener to the add button
	 */
	public void addAddListener(ActionListener mal) {
		b_addBtn.addActionListener(mal);
	}

	/**
	 * Adds an ActionListener to the substraction button
	 */
	public void addSubListener(ActionListener mal) {
		b_subBtn.addActionListener(mal);
	}

	/**
	 * Adds an ActionListener to the multiplication button
	 */
	public void addMulListener(ActionListener mal) {
		b_mulBtn.addActionListener(mal);
	}

	/**
	 * Adds an ActionListener to the integrate button
	 */
	public void addIntegrateAddListener(ActionListener mal) {
		b_integrateBtn.addActionListener(mal);
	}

	/**
	 * Adds an ActionListener to the derivate button
	 */
	public void addDerivateListener(ActionListener mal) {
		b_derivateBtn.addActionListener(mal);
	}

	/**
	 * Adds an ActionListener to the divider button
	 */
	public void addDivideListener(ActionListener mal) {
		b_divBtn.addActionListener(mal);
	}

	/**
	 * Adds an ActionListener to the clear button
	 */
	public void addClearListener(ActionListener mal) {
		b_clearBtn.addActionListener(mal);
	}

}
