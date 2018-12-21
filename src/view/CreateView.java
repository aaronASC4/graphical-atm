package view;
 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
 
 
 
import controller.ViewManager;
 
@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
   
    private ViewManager manager;        // manages interactions between the views, model, and database
    private JButton fnameButton;
    private JTextField nameField;
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
       
        initFirstName();
       
        this.add(new javax.swing.JLabel("", javax.swing.SwingConstants.CENTER));
       
        // TODO
        //
        // this is where you should build the CreateView (i.e., all the components that
        // allow the user to enter his or her information and create a new account).
        //
        // feel free to use my layout in LoginView as an example for laying out and
        // positioning your components.
    }
   
    private void initFirstName() {
        JLabel label = new JLabel("First Name", SwingConstants.RIGHT);
        label.setBounds(50, 100, 95, 35);
        label.setLabelFor(nameField);
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
       
        nameField = new JTextField(20);
        nameField.setBounds(205, 100, 200, 35);
       
        this.add(label);
        this.add(nameField);
    }
   
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
       
        // TODO
        //
        // this is where you'll setup your action listener, which is responsible for
        // responding to actions the user might take in this view (an action can be a
        // user clicking a button, typing in a textfield, etc.).
        //
        // feel free to use my action listener in LoginView.java as an example.
    }
}
