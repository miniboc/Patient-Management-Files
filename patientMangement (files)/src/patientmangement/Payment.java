package patientmangement;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author Darren Smith R00117899
 * 
 * This program is a dentist payment management system.
 * 
 * This is the payment class.
 */
public class Payment implements Serializable
{
    private int uniquePaymentNumber;
    private int paymentNumber;
    private double paymentAmount;
    private Date paymentDate;
    private boolean isPaid;
    
    public Payment (Date paymentDate, double paymentAmount, int uniqueNumber)
    {
        this.paymentDate = paymentDate;
        this .paymentAmount = paymentAmount;
        uniquePaymentNumber = uniqueNumber;
        paymentNumber = uniquePaymentNumber++;
    }
    
    public int getPaymentNumer ()
    {
        return paymentNumber;
    }
    
    public double getPaymentAmt ()
    {
        return paymentAmount;
    }
    
    public Date getPaymentDate ()
    {
        return paymentDate;
    }
    
    public boolean getIsPaid ()
    {
        return isPaid;
    }
    
    public void setPaymentAmount (double paymentAmount)
    {
        this.paymentAmount = paymentAmount;
    }
    
    public void setPaymentDate (Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }
    
    public void setIsPaid (boolean isPaid)
    {
        this.isPaid = isPaid;
    }
    
    @Override public String toString ()
    {
        String pp = null;
        return  pp + paymentNumber + paymentAmount + paymentDate + isPaid;
    }
    
    public void print ()
    {
        System.out.println (toString ());
    }
}