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
public class TransferView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton logoutButton;
	private JButton cancelButton; 
	private JButton transferButton; 
	private JLabel errorMessageLabel;
	private JTextField transferField; 
	private JTextField recepientField; 
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public TransferView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		initLogOut();
		initcancelButton();
		initTransferField(); 
		initRecepientField(); 
		initErrorMessageLabel(); 
		initTransferButton(); 
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

	private void initTransferField() {
		JLabel label = new JLabel("Amount to Transfer");
		label.setBounds(50, 140, 200, 35);
		label.setLabelFor(transferField);
		
		transferField = new JTextField(20);
		transferField.setBounds(260, 140, 150, 40);
		
		this.add(label);
		this.add(transferField);
	}
	
	private void initRecepientField() {
		JLabel label = new JLabel("Recepient's Account Number");
		label.setBounds(30, 70, 220, 35);
		label.setLabelFor(recepientField);
		
		recepientField = new JTextField(20);
		recepientField.setBounds(260, 140, 150, 40);
		
		this.add(label);
		this.add(recepientField);
	}
	
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(0, 420, 500, 35);
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	private void initTransferButton() {
		transferButton = new JButton("Transfer");
		transferButton .setBounds(35, 5, 100, 50);
		transferButton .addActionListener(this);
		this.add(transferButton );
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
	
	private void setValues() {
		long accountNumber = Long.valueOf(recepientField.getText());
		
		double amount = Double.valueOf(transferField.getText());
		
		if (String.valueOf(accountNumber).length() != 9) {
			throw new InvalidParameterException("Please enter valid account number"); 
		}
		else if (String.valueOf(amount).length() == 0) {
			throw new InvalidParameterException("Please enter valid amount"); 
		}
		manager.transfer(accountNumber, amount); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(logoutButton)) {
			manager.shutdown();
		} else if (source.equals(cancelButton)) {
			manager.switchTo(ATM.HOME_VIEW);
		} else if (source.equals(transferButton)){
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
