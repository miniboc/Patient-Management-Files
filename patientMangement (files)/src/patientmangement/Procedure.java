package patientmangement;
import java.io.Serializable;
/**
 *
 * @author Darren Smith R00117899
 * 
 * This program is a dentist payment management system.
 * 
 * This is the procedure class.
 */
public class Procedure implements Serializable
{
    private int uniqueProNumber;
    private int procNumber;
    private String procName;
    private double procCost;
      
    public Procedure (String procName, double procCost)
    {
        this.procName = procName;
        this.procCost = procCost;
    }
    
    public Procedure (double procCost, String procName, int uniqueNumber)
    {
        this.procName = procName;
        this.procCost = procCost;
        uniqueProNumber = uniqueNumber;
        procNumber = uniqueProNumber++;
    }
    
    public int getProcNumber ()
    {
        return procNumber;
    }
    
    public String getProcName ()
    {
        return procName;
    }
    
    public double getProcCost ()
    {
        return procCost;
    }
    
    public void setProcName (String procName)
    {
        this.procName = procName;
    }
    
    public void setProcCost (int procCost)
    {
        this.procCost = procCost;
    }
    
    @Override public String toString ()
    {
        return procNumber + procName + procCost;
    }
    
    public void print ()
    {
        System.out.println (toString ());
    }
}