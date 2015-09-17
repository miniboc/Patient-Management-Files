package patientmangement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.MONTH;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Darren Smith R00117899
 * 
 * This program is a dentist payment management system.
 * 
 * This is the controller class.
 */
public class MyController 
{ 
    private List <Patient> patientList;
    private List <Procedure> procedureList;
    private int uniqueProcNumber;
    private int uniquePatientNumber;
    private int uniquePaymentNumber;
    File uniqueNumbersFile = new File ("uniqueNumbers.txt");
    
    //Constructor
    public MyController ()
    {
        patientList = new ArrayList <> ();
        procedureList = new ArrayList <> ();
        load ();        
    }
    
    //This method is used to load
    public void load ()
    {
        try
        {
            if (!uniqueNumbersFile.exists())
            {     
                PrintStream outputFile = new PrintStream(uniqueNumbersFile); 
                uniquePatientNumber = 1000;
                uniqueProcNumber = 2000;
                uniquePaymentNumber = 3000;
            }
            else
            {
                Scanner uniqueScanner = new Scanner(uniqueNumbersFile);
                uniquePatientNumber = uniqueScanner.nextInt();
                uniqueProcNumber = uniqueScanner.nextInt();
                uniquePaymentNumber = uniqueScanner.nextInt();
            }
           
            FileInputStream patientFIS = new FileInputStream ("patients.txt");
            ObjectInputStream patientOIS = new ObjectInputStream (patientFIS);
            patientList = (List<Patient>) patientOIS.readObject();
            patientOIS.close();
            FileInputStream procedureFIS = new FileInputStream ("procedures.txt");
            ObjectInputStream procedureOIS = new ObjectInputStream (procedureFIS);
            procedureList = (List<Procedure>) procedureOIS.readObject();
            procedureOIS.close();
            
        }
        catch (FileNotFoundException e)
        {
            System.out.print ("file not found from load");
        }
        catch (Exception ex)
        {
            System.out.print ("exception from load");
        }
    }
    
    //This method is used to save
    public void save ()
    {
        try
        {
            FileOutputStream patientFOS = new FileOutputStream ("patients.txt");
            ObjectOutputStream patientOOS = new ObjectOutputStream (patientFOS);
            patientOOS.writeObject(patientList);
            patientOOS.close();
            FileOutputStream procedureFOS = new FileOutputStream ("procedures.txt");
            ObjectOutputStream procedureOOS = new ObjectOutputStream (procedureFOS);
            procedureOOS.writeObject(procedureList);
            procedureOOS.close(); 
            PrintStream writeToFile = new PrintStream (uniqueNumbersFile);
            writeToFile.print (uniquePatientNumber + "\n" + uniqueProcNumber + "\n" + uniquePaymentNumber);
        }
        catch (FileNotFoundException e)
        {
            System.out.print ("file not found from save");
        }
        catch (Exception ex)
        {
            System.out.print ("exception from save");
        }
    }
    
    //This method is used to get a patient
    public Patient getPatient (int position)
    {
        return patientList.get(position);
    }
    
    //This method is used to update patient details
    public void updatePatient(Patient temp, String patientName, String patientAdd, String patientPhone)
    {
        temp.setName(patientName);
        temp.setAddress(patientAdd);
        temp.setPhone(patientPhone);
    }
    
    //This method is used to remove a patient
    public void removePatient (Patient temp)
    {
        patientList.remove (temp);
    }
    
    //This method is used to add a patient
    public void addPatientToSystem (String patientName, String patientAdd, String patientPhone)
    {    
        Patient tempPatient = new Patient (patientName, patientAdd, patientPhone, uniquePatientNumber++);
        this.patientList.add (tempPatient);
    }
    
    //This method is used to get a procedure from system
    public Procedure getProcedure (int position)
    {
        return procedureList.get(position);
    }
    
    //This method is used to remove a procedure from system
    public void removeProcedure (int postion)
    {
        procedureList.remove (postion);
    }
    
    //This method is used to add a procedure to system
    public boolean addProcedureToSystem (String procName, String cost)
    {
        try
        {
            double procCost  = Double.parseDouble( cost);
            Procedure tempProcedure = new Procedure (procName, procCost);
            this.procedureList.add (tempProcedure);
            return true;
        }
        catch (Exception e) 
        {
           MyFrame.error("Add procedure failed! Cost field must contain numbers only!");
           return false;
        }
        
    }
    
    //This method is used to get system procedure list
    public String [] systemProcedureList ()
    { 
        String [] procedureArray = new String [procedureList.size ()];
        for (int counter = 0; counter < procedureArray.length; counter ++)
        {
            String details = "";
            details += procedureList.get(counter).getProcName();
            details += "           " + procedureList.get(counter).getProcCost();
            
            procedureArray [counter] = details;
        }
        return procedureArray;
    }
    
    //This method is used to find patient by ID
    public Patient findPatientById (String id)
    {
        try
        {
            int number = Integer.parseInt(id);
            for (int counter = 0; counter < patientList.size (); counter++)
            {
                if (patientList.get(counter).getPatientNumber() == number)
                {
                    return patientList.get(counter);
                }
            }
        }
        catch (Exception e) 
        {
            MyFrame.error("Search Patient id failed! Patient id field must contain numbers only!");
        }
        return null;
    }
    
    //This method is used to find patient by name
    public Patient findPatientByName (String name)
    {
        for (int counter = 0; counter < patientList.size (); counter++)
        {
            if (patientList.get(counter).getPatientName ().equalsIgnoreCase (name))
            {
                return patientList.get(counter);
            }
        }
        return null;
    }
   
    //This method is used to get patient list
    public String [] fullPatientList ()
    {
        String [] patientArray = new String [patientList.size()];
        for (int counter = 0; counter < patientArray.length; counter ++)
        {
            String details = "";
            details += patientList.get(counter).getPatientNumber ();
            details += "           " + patientList.get(counter).getPatientName();
            patientArray [counter] = details;
        }
        return patientArray;
    }
    
    //This method is used to add a procedure to patient
    public void addProcedureToPatient (Patient tempPatient, int selectedIndex)
    {
        //should i cretae this opject in the  patient class
        String procedureName = procedureList.get(selectedIndex).getProcName();
        double procedureCost = procedureList.get(selectedIndex).getProcCost();
        Procedure tempProcedure = new Procedure (procedureCost, procedureName, uniqueProcNumber++);
        tempPatient.addProcedure(tempProcedure);
    }
    
    //This method is used to get procedure list
    public String [] procedureList (Patient tempPatient)
    { 
        String [] procedureArray = new String [tempPatient.getProcedureList().size () + 5];
        double total = 0;
        double paid = 0;
        for (int counter = 0; counter < procedureArray.length - 5; counter ++)
        {
            String details = "";
            details += tempPatient.getProcedure (counter).getProcNumber ();
            details += "           " + tempPatient.getProcedure (counter).getProcName();
            details += "           " + tempPatient.getProcedure (counter).getProcCost();
            total += tempPatient.getProcedure (counter).getProcCost();
            procedureArray [counter] = details;
        }
        procedureArray [procedureArray.length -5] = "Total                                   " + total;
        
        for (int counter2 = 0; counter2 < tempPatient.getPaymentList().size (); counter2++)
        {
            paid += tempPatient.getPayment(counter2).getPaymentAmt();
        }
        procedureArray [procedureArray.length -3] = "Paid                                    " + paid;
        procedureArray [procedureArray.length -1] = "Amount Owed                   " + (total - paid);
        return procedureArray;
    }
    
    //This method is used to add a payment
    public boolean addPayment (String paymentDate, String paymentAmount, Patient tempPatient)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try 
        {
            Date date = formatter.parse(paymentDate);
            double amount = Double.parseDouble( paymentAmount);
            Payment tempPayment = new Payment (date, amount, uniquePaymentNumber++);
            tempPatient.addPayment(tempPayment);
            return true;
        }
        catch (Exception e) 
        {
            MyFrame.error("Add procedure failed! Cost field must contain numbers only! Date field must use format dd/MM/yyyy");
            return false;
        }      
    }
    
    //This method is used to get payment list
    public String [] paymentList (Patient tempPatient)
    { 
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String [] paymentArray = new String [tempPatient.getPaymentList().size () + 1];
        double total = 0;
        for (int counter = 0; counter < paymentArray.length -1; counter ++)
        {
            String details = "";
            details += tempPatient.getPayment (counter).getPaymentNumer ();
            details += "           " + formatter.format(tempPatient.getPayment (counter).getPaymentDate());
            details += "           " + tempPatient.getPayment (counter).getPaymentAmt();
            total += tempPatient.getPayment(counter).getPaymentAmt();
            paymentArray [counter] = details;
        }
        paymentArray [paymentArray.length -1] = "Total                                           " + total;
        return paymentArray;
    }
    
    //This method is used to get a full report
    public String reportFull ()
    {
        String report = "Full report";
        
        for (int counter = 0; counter < patientList.size(); counter ++)
        {
            Patient tempPatient = patientList.get(counter);
            List <Procedure> tempProcedureList = tempPatient.getProcedureList();
            String [] paymentArray = paymentList (tempPatient);
            
            report += "\n---------------------------------------------"; 
            report += "\n---------------------------------------------"; 
            report += "\n" + tempPatient.getPatientNumber();
            report += "\n" + tempPatient.getPatientName();
            report += "\n" + tempPatient.getPatientAdd();
            report += "\n" + tempPatient.getPatientPhone();
            report += "\n\nProcedures\n---------------------------------------------";
            
            for (int counter2 = 0;counter2 < tempProcedureList.size(); counter2++)
            {
                Procedure tempProcedure = tempProcedureList.get(counter2);
                report += "\n" + tempProcedure.getProcNumber();
                report += "\n" + tempProcedure.getProcName();
                report += "\n" + tempProcedure.getProcCost();
            }
            
            report += "\n\nPayments\n---------------------------------------------";
               
            for (int counter3 = 0; counter3 < paymentArray.length; counter3++)
            {
                report += "\n" + paymentArray [counter3];
            }    
        }
        return report;
    }
    
    //This method is used to find out what patients owe money and not payed in 6 months
    public String oweReport ()
    {
        String report = "Owe Report";
        
        for (int counter = 0; counter < patientList.size(); counter ++)
        {
            double totalProcedure = 0;
            double totalPayment = 0;
            double amountOwed;
            boolean olderSixMonths = false;
            Patient tempPatient = patientList.get(counter);
            List <Procedure> tempProcedureList = tempPatient.getProcedureList();
            List <Payment> tempPaymentList = tempPatient.getPaymentList();
            String [] paymentArray = paymentList (tempPatient);
            
            for (int counter2 = 0;counter2 < tempProcedureList.size(); counter2++)
            {
                Procedure tempProcedure = tempProcedureList.get(counter2);
                totalProcedure += tempProcedure.getProcCost();
            }
            
            for (int counter3 = 0;counter3 < tempPaymentList.size(); counter3++)
            {
                Payment tempPayment = tempPaymentList.get(counter3);
                olderSixMonths = isSixMonths (tempPayment.getPaymentDate());
                totalPayment += tempPayment.getPaymentAmt();
                
            }
            
            amountOwed = totalProcedure - totalPayment;
            
            if (amountOwed > 0 && olderSixMonths == true)
            {
                report += "\n---------------------------------------------"; 
                report += "\n---------------------------------------------"; 
                report += "\n" + tempPatient.getPatientNumber();
                report += "\n" + tempPatient.getPatientName();
                report += "\n" + tempPatient.getPatientAdd();
                report += "\n" + tempPatient.getPatientPhone();
                report += "\n\nProcedures\n---------------------------------------------";
            
                for (int counter2 = 0;counter2 < tempProcedureList.size(); counter2++)
                {
                    Procedure tempProcedure = tempProcedureList.get(counter2);
                    report += "\n" + tempProcedure.getProcNumber();
                    report += "\n" + tempProcedure.getProcName();
                    report += "\n" + tempProcedure.getProcCost();
                }
            
                report += "\n\nPayments\n---------------------------------------------";
               
                for (int counter3 = 0; counter3 < paymentArray.length; counter3++)
                {
                    report += "\n" + paymentArray [counter3];
                }
            }       
        }
        return report;
    }
    
    //This method is to check if a date is more then 6 months old
     private boolean isSixMonths(Date aDate) 
     {
        Calendar calendar = Calendar.getInstance();
        calendar.add( MONTH ,  -6 );
        return aDate.compareTo( calendar.getTime() ) < 0;
    }
}
