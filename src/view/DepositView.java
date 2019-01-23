package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidParameterException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class DepositView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton logoutButton;
	private JButton cancelButton; 
	private JTextField depositField; 
	private JButton depositButton;
	private JLabel errorMessageLabel; 
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */

	public DepositView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER); 
		initLogOut();
		initcancelButton();
		initDepositField(); 
		initDepositButton();
		initErrorMessageLabel(); 
	}
	
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	
	private void initLogOut() {
		
		// TODO
		//
		// this is a placeholder for this view and should be removed once you start
		// building the HomeView.
		
		logoutButton = new JButton("Log Out");
		logoutButton.setBounds(100, 180, 200, 35);
		logoutButton.addActionListener(this);
		this.add(logoutButton); 
	}

	private void initcancelButton() {
		cancelButton = new JButton("Back");
		cancelButton.setBounds(5, 5, 100, 50);
		cancelButton.addActionListener(this);
		this.add(cancelButton);
	}
	
	private void initDepositField() {
		JLabel label = new JLabel("Deposit Amount");
		label.setBounds(50, 70, 200, 35);
		label.setLabelFor(depositField);
		
		depositField = new JTextField(20);
		depositField.setBounds(260, 70, 150, 40);
		
		this.add(label);
		this.add(depositField);
	}
	
	private void initDepositButton() {
		depositButton = new JButton("Deposit");
		depositButton.setBounds(35, 5, 100, 50);
		depositButton.addActionListener(this);
		this.add(depositButton);
	}

	private void setValues() {
		double amount = Double.valueOf(depositField.getText());
		if (String.valueOf(amount).length() == 0) {
			throw new InvalidParameterException("Please enter a valid amount"); 
		}
		manager.deposit(amount); 
	}
	
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(0, 420, 500, 35);
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	
	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the CreateView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(logoutButton)) {
			manager.shutdown();
		} else if (source.equals(cancelButton)) {
			manager.switchTo(ATM.HOME_VIEW);
		} else if (source.equals(depositButton)) {
			try {
				setValues();
				manager.switchTo(ATM.HOME_VIEW);
				updateErrorMessage(""); 
			} catch (NumberFormatException e1) {
				updateErrorMessage("Please complete all fields");
			} catch (InvalidParameterException e2) {
				updateErrorMessage(e2.getMessage());
			} catch (NullPointerException e3) {
				updateErrorMessage("Null Pointer");
			}
		} else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
	}
}
