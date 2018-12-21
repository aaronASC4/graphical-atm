package view;
 
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
 
import controller.ViewManager;
 
@SuppressWarnings("serial")
public class HomeView extends JPanel implements ActionListener {
   
    private ViewManager manager;        // manages interactions between the views, model, and database
    private JLabel errorMessageLabel;       // label for potential error messages
    private JButton logoutButton;
   
    public HomeView(ViewManager manager) {
        super();
       
        this.manager = manager;
        this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
        initialize();
    }
   
    ///////////////////// PRIVATE METHODS /////////////////////////////////////////////
   
    /*
     * Initializes the HomeView components.
     */
   
    private void initialize() {
       
        this.setLayout(null);
       
        initLogoutButton();
       
        this.add(new javax.swing.JLabel("HomeView", javax.swing.SwingConstants.CENTER));
       
    }
 
    private void initLogoutButton() {
        logoutButton = new JButton();
        logoutButton.setBounds(5, 5, 50, 50);
        logoutButton.addActionListener(this);
       
        try {
            Image image = ImageIO.read(new File("images/logout.ico"));
            logoutButton.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            logoutButton.setText("OFF");
        }
       
        this.add(logoutButton);
    }
   
    private void writeObject(ObjectOutputStream oos) throws IOException {
        throw new IOException("ERROR: The HomeView class is not serializable.");
    }
   
    ///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
   
    /*
     * Responds to button clicks and other actions performed in the HomeView.
     *
     * @param e
     */
   
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
       
        if (source.equals(logoutButton)) {
            manager.logout();
        }
    }
}
