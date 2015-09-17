package patientmangement;
import javax.swing.JFrame;
/**
 *
 * @author Darren Smith R00117899
 * 
 * This program is a dentist payment management system.
 * 
 * This is the driver class.
 * 
 * <<<<<<<NOTE: username = test, password = test>>>>>>>
 */
public class PatientMangement 
{  
    public static void main(String[] args) 
    {   
        Login myLogin = new Login ();
        int number = 0;
        while (number != 2)
        {
            number = myLogin.loginSystem();
        }
        MyFrame gui = new MyFrame ("Patient Management System");
        
    }
}