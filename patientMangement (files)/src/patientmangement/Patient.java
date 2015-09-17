package patientmangement;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author Darren Smith R00117899
 * 
 * This program is a dentist payment management system.
 * 
 * This is the patient class.
 */
public class Patient implements Serializable
{
    private int uniquePatientNumber;
    private int patientNumber;
    private String patientName;
    private String patientAdd;
    private String patientPhone;
    private List <Payment> paymentList;
    private List <Procedure> procedureList;
    
    public Patient (String patientName, String patientAdd, String patientPhone, int uniqueNumber)
    {
        this.patientName = patientName;
        this.patientAdd = patientAdd;
        this.patientPhone = patientPhone;
        uniquePatientNumber = uniqueNumber;
        patientNumber = uniquePatientNumber++;
        paymentList = new ArrayList <> ();
        procedureList = new ArrayList <> ();
    }
    
    public int getPatientNumber ()
    {
        return patientNumber;
    }
    
    public String getPatientName ()
    {
        return patientName;
    }
    
    public String getPatientAdd ()
    {
        return patientAdd;
    }
    
    public String getPatientPhone ()
    {
        return patientPhone;
    }
    
    public List getPaymentList ()
    {
        return paymentList;
    }
    
    public Payment getPayment (int number)
    {
        return paymentList.get (number);
    }
    
    public List getProcedureList ()
    {
        return procedureList;
    }
    
    public Procedure getProcedure (int number)
    {
        return procedureList.get (number);
    }
    
    public void setName (String patientName)
    {
        this.patientName = patientName;
    }
    
    public void setAddress (String patientAdd)
    {
        this.patientAdd = patientAdd;
    }
    
    public void setPhone (String patientPhone)
    {
        this.patientPhone = patientPhone;
    }
    
    public void addPayment (Payment tempPayment)
    {
        paymentList.add (tempPayment);
    }
    
    public void addProcedure (Procedure tempProcedure)
    {
        procedureList.add (tempProcedure);
    }
    @Override public String toString ()
    {
        return patientNumber + patientName + patientAdd + patientPhone +
               paymentList + procedureList;
    }
    
    public void print ()
    {
        System.out.println (toString ());
    }
}