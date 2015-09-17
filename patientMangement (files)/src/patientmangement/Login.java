package patientmangement;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author Darren Smith R00117899
 * 
 * This program is a dentist payment management system.
 * 
 * This is the login class.
 * 
 * <<<<<<<NOTE username = test password = test>>>>>>>
 */
public class Login 
{
    JTextField username;
    JTextField password;

    public int loginSystem ()
    {
        username = new JTextField();
        password = new JPasswordField(); 
        Object [] message = 
        {
            "Username:", username,
            "Password:", password
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION)
        {
            if (username.getText().equals("test") && password.getText().equals("test")) 
            {
                System.out.println("Login successful");
                return 2;
            } 
            else 
            {
                System.out.println("Login failed");
                return 1;
            }
        } 
        else System.out.println("Login canceled");
        System.exit(option);
        return 0;
    }
}
