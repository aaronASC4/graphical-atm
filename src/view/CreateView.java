package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import controller.ViewManager;
import model.BankAccount;
import model.User;


@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton logoutButton;
	private JTextField fName;
	private JTextField lName;
	private JTextField address;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JFormattedTextField dob;
	private JTextField phone;
	private JPasswordField pinField;
	private JButton submitButton;
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	
	private void initialize() {
		
		// TODO
		//
		// this is a placeholder for this view and should be removed once you start
		// building the CreateView.
		this.setLayout(null);
		
		this.initFnameField();
		this.initLnameField();
		this.initAddressField();
		this.initCityField();
		this.initStateField();
		this.initZipField();
		this.initDobField();
		this.initPhoneField();
		this.initPinField();
		this.initLogoutButton();
		this.initSubmitButton();
		
		// TODO
		//
		// this is where you should build the CreateView (i.e., all the components that
		// allow the user to enter his or her information and create a new account).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}
	
	private void initFnameField() {
		JLabel label = new JLabel("FirstName", SwingConstants.LEFT);
		label.setBounds(100, 60, 95, 35);
		label.setLabelFor(fName);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		fName = new JTextField(20);
		fName.setBounds(205, 60, 200, 35);
		
		this.add(label);
		this.add(fName);
	}
	
	private void initLnameField() {
		JLabel label = new JLabel("LastName", SwingConstants.LEFT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(lName);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		lName = new JTextField(20);
		lName.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(lName);
	}
	
	private void initAddressField() {
		JLabel label = new JLabel("Address", SwingConstants.LEFT);
		label.setBounds(100, 140, 95, 35);
		label.setLabelFor(address);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		address = new JTextField(20);
		address.setBounds(205, 140, 200, 35);
		
		this.add(label);
		this.add(address);
	}
	
	private void initCityField() {
		JLabel label = new JLabel("City", SwingConstants.LEFT);
		label.setBounds(100, 180, 95, 35);
		label.setLabelFor(city);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		city = new JTextField(20);
		city.setBounds(205, 180, 200, 35);
		
		this.add(label);
		this.add(city);
	}
	
	private void initStateField() {
		JLabel label = new JLabel("State", SwingConstants.LEFT);
		label.setBounds(100, 220, 95, 35);
		label.setLabelFor(state);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		state = new JTextField(20);
		state.setBounds(205, 220, 200, 35);
		
		this.add(label);
		this.add(state);
	}
	
	private void initZipField() {
		JLabel label = new JLabel("ZIP", SwingConstants.LEFT);
		label.setBounds(100, 260, 95, 35);
		label.setLabelFor(zip);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		zip = new JTextField(20);
		zip.setBounds(205, 260, 200, 35);
		
		this.add(label);
		this.add(zip);
	}
	
	private void initDobField() {
		JLabel label = new JLabel("Date of Birth", SwingConstants.RIGHT);
		label.setBounds(100, 300, 95, 35);

	    try {
			MaskFormatter dateFormat = new MaskFormatter("##/##/####");
			dateFormat.setPlaceholderCharacter('_');
			dob = new JFormattedTextField(dateFormat);
		} catch (ParseException e) {
			dob.setText("");
		}
		dob.setBounds(205, 300, 200, 35);

		this.add(label);
	    this.add(dob);
	}
	
	private void initPhoneField() {
		JLabel label = new JLabel("PHONE", SwingConstants.LEFT);
		label.setBounds(100, 340, 95, 35);
		label.setLabelFor(phone);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
	    try {
			MaskFormatter dateFormat = new MaskFormatter("(###)-###-####");
			dateFormat.setPlaceholderCharacter('_');
			phone = new JFormattedTextField(phoneFormat);
		} catch (ParseException e) {
			dob.setText("");
		}
		phone.setBounds(205, 340, 200, 35);
		
		this.add(label);
		this.add(phone);
	}
	
	private void initPinField() {
		JLabel label = new JLabel("PIN", SwingConstants.LEFT);
		label.setBounds(100, 380, 95, 35);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JPasswordField(20);
		pinField.setBounds(205, 380, 200, 35);
		addKeyListener(pinField, 4);
		
		this.add(label);
		this.add(pinField);
	}
	
	private void initLogoutButton() {
        logoutButton = new JButton("Cancel");
        logoutButton.setBounds(50, 420, 100, 50);
        logoutButton.addActionListener(this);
        
        this.add(logoutButton);
    }
	
	
	private void addKeyListener(JTextField field, int length) {
		field.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        if (field.getText().length() >= length) 
		            e.consume();
		    }
		});
	}
	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void initcancelButton() {
		cancelButton = new JButton("Back");
		cancelButton.setBounds(5, 5, 100, 50);
		cancelButton.addActionListener(this);
		this.add(cancelButton);
	}
	
	private void initSubmit() {
		submitButton = new JButton("Submit");
		submitButton.setBounds(200, 5, 100, 50);
		submitButton.addActionListener(this);
		this.add(submitButton);
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS ///////////------------////////////////////////
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(cancelButton)) {
			manager.switchTo(ATM.LOGIN_VIEW);
			this.removeAll();
			this.initialize();
			updateErrorMessage("");
		} else if (source.equals(submitButton)) {
			System.out.println("Hello");
			try {
				int pin = Integer.valueOf(new String(pinField.getPassword()));
				if (String.valueOf(pinField).length() != 4) {
					throw new InvalidParameterException("Please enter a 4 digit PIN");
				}
				
				String dob = monthField.getSelectedItem().toString() + dayField.getSelectedItem().toString() + yearField.getSelectedItem().toString();
				
				String phoneString = phoneField.getText(); 
				System.out.println("a");
				long phone = Long.valueOf(phoneString);
				System.out.println("h");
				
				if (String.valueOf(phoneField).length() != 10) {
					throw new InvalidParameterException("Please enter a valid Phone Number");
				}
				
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				
				String address = addressField.getText();
				String state = stateField.getSelectedIndex() + "";
				String city = cityField.getText();
				String postal = postalField.getText();
				
				if (postal.length() != 5) {
					throw new InvalidParameterException("Please enter a valid Postal Code");
				}
				
				if (firstName.length() == 0 || lastName.length() == 0 || address.length() == 0 || city.length() == 0) {
					throw new InvalidParameterException("Please complete all fields");
				}
				
				createdUser = new User(pin, dob, phone, firstName, lastName, address, city, state, postal);
				
				long accountNumber = manager.getDatabase().getMaxAccountNumber() + 1;
				
				if (accountNumber == -1) {
					throw new InvalidParameterException("Error retreiving Account Number");
				}
				account = new BankAccount('Y', accountNumber, 0.0, createdUser);
				manager.setAccount(account); 
				System.out.println("Hello 2");
				manager.login(manager.getAccount().getAccountNumber() + "",
						String.valueOf(manager.getAccount().getUser().getPin()).toCharArray());
				updateErrorMessage("");
				System.out.println("Hello 3");
				manager.switchTo(ATM.LOGIN_VIEW);
			}
			catch (NumberFormatException e2){
				System.out.println("help");
				updateErrorMessage("Please complete all fields");
			}
			catch(InvalidParameterException e3) {
				System.out.println("help1");
				updateErrorMessage(e3.getMessage());
			}
			catch(NullPointerException e4) {
				System.out.println("help2");
				updateErrorMessage("Null Pointer");
			}
			catch (SQLException e5) {
				System.out.println("help3");
				updateErrorMessage("SQL Exception");
			}
		}  
		else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
	}
}
