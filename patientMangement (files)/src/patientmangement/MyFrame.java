package patientmangement;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
/**
 *
 * @author Darren Smith R00117899
 * 
 * This program is a dentist payment management system.
 * 
 * This is the view class.
 */
public class MyFrame extends JFrame
{
    
    private JTabbedPane tabbedPane;
    private JPanel border;
    private JPanel page1;
    private JPanel page2;
    private JPanel page3;
    private GridBagConstraints gc = new GridBagConstraints ();
    private JList list;
    //change to controler better name
    private MyController controller;
    
    //Constructor
    public MyFrame(String title)
    {
        super (title);
        this.controller = new MyController ();
        //Creates the frame  
        setSize (800,600);
        setLocationRelativeTo(null);
        
        //Creates menu
        JMenuBar menuBar = new JMenuBar ();
        this.setJMenuBar (menuBar);
        JMenu file = new JMenu ("File");
        menuBar.add (file);
        JMenuItem save = new JMenuItem ("Save");
        file.add (save);
        save.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                controller.save();
                JOptionPane.showMessageDialog(null, "Saved!");
	    }
	} );   
        JMenuItem load = new JMenuItem ("Load");
        file.add (load);
        load.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                controller.load();
                JOptionPane.showMessageDialog(null, "Loaded!");
	    }
	} );  
        
        //Creates the border
        border = new JPanel();
        border.setLayout (new BorderLayout ());
        border.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        setContentPane (border);
        
        //Creates the pages
        createPatientSection(); 
        createProcedureSection ();
        createReportsSection ();
        createTabSection ();
          
        closeWindow ();  
        this.setVisible(true);
    }
    
    //This method is a listener it is used when the user clicks on the x button.
    private void closeWindow ()
    {
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() 
        {
            @Override
	    public void windowClosing(WindowEvent e) 
            {
                int choice = JOptionPane.showConfirmDialog(null, "Do you wish to save before shutting down?");
                
                //Save
                if (choice == JOptionPane.YES_OPTION) 
                {
                    JOptionPane.showMessageDialog(null, "Saved!");
                    controller.save();
                    System.exit(0);
                }
                
                //Dont save
                if (choice == JOptionPane.NO_OPTION)   
                {
                    JOptionPane.showMessageDialog(null, "Not Saved!");
                    System.exit(0);
                }
	    }
	} );   
    }
    
    //This method is used to create the tabs
    private void createTabSection ()
    {       
        tabbedPane = new JTabbedPane ();
        tabbedPane.addTab("Patients", page1);
        tabbedPane.addTab ("System", page2);
        tabbedPane.addTab ("Reports", page3);
        border.add(tabbedPane, BorderLayout.CENTER);
    }
    
    //This method is used to create the patient section
    private void createPatientSection ()
    {    
        page1 = new JPanel ();
        page1.setLayout(new BorderLayout ());      
  
        //Creates the 2 panel sections
        JPanel panelTop = new JPanel ();
        page1.add (panelTop, BorderLayout.NORTH); 
        panelTop.setPreferredSize(new Dimension (500,70));
        final JPanel panelCenter = new JPanel (new GridBagLayout ());
        page1.add (panelCenter, BorderLayout.CENTER);
        
        //Set title
        TitledBorder title = BorderFactory.createTitledBorder("Patient Section Options");
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelTop.setBorder(title); 
        
        //Creates the buttons for the top section
        JButton add = new JButton("Add"); 
        add.setBackground (new Color (255,255,153));
        panelTop.add(add);
        add.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                patientSectionAdd (panelCenter);
	    }
	} );  
        
        JButton remove = new JButton("Search");
        remove.setBackground (new Color (255,255,153));
        panelTop.add(remove);
        remove.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                patientSearch (panelCenter);
	    }
	} );   
        JButton listButton = new JButton("List");
        listButton.setBackground (new Color (255,255,153));
        panelTop.add(listButton);
        listButton.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                patientSectionList (panelCenter);
	    }
	} );   
    } 
    
    //This method is used to create the patient add window
    private void patientSectionAdd (final JPanel panelCenter)
    {
        //Clears panel and sets title
        panelCenter.removeAll ();
        TitledBorder title = BorderFactory.createTitledBorder("Enter Patient Personal Details");
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelCenter.setBorder(title);
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        //First column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;    
        JLabel labelName = new JLabel ("Name: ");
        panelCenter.add (labelName, gc);
         
        gc.gridx = 0;
        gc.gridy = 1;
        JLabel labelAdd = new JLabel ("Address: ");  
        panelCenter.add (labelAdd, gc);
        
        gc.gridx = 0;
        gc.gridy = 2;
        JLabel labelPhone = new JLabel ("Phone: ");
        panelCenter.add (labelPhone, gc);
        
        //Second column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;   
        final JTextField textName = new JTextField (10);
        panelCenter.add (textName, gc);
        
        gc.gridx = 1;
        gc.gridy = 1;
        final JTextField textAdd = new JTextField (10);
        panelCenter.add (textAdd, gc);
        
        gc.gridx = 1;
        gc.gridy = 2;
        final JTextField textPhone = new JTextField (10);
        panelCenter.add (textPhone, gc);
        
        //Buttons
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 3;
        JButton addPatient = new JButton ("Store");
        addPatient.setBackground (new Color (102,255,102));
        panelCenter.add (addPatient, gc);
        addPatient.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                String patientName = textName.getText();
                String patientAdd = textAdd.getText();
                String patientPhone = textPhone.getText();
               
                controller.addPatientToSystem (patientName, patientAdd, patientPhone);
                JOptionPane.showMessageDialog (null, patientName + " has been added to the system");
                patientSectionAdd (panelCenter);
	    }
	} ); 
        
        gc.gridx = 1;
        gc.gridy = 3;
        JButton cancel = new JButton ("Back");
        cancel.setBackground (new Color (153,255,255));
        panelCenter.add (cancel, gc);
        cancel.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                patientSectionAdd (panelCenter);
	    }
	} ); 
            
        repaint();
    } 
    
    //This method is used to create the patient search window
    private void patientSearch (final JPanel panelCenter)
    {
        //Clears panel and sets title
        panelCenter.removeAll ();
        TitledBorder title = BorderFactory.createTitledBorder("Enter Patient Details to Search");
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelCenter.setBorder(title);
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        //First column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;    
        JLabel labelName = new JLabel ("Patient ID: ");
        panelCenter.add (labelName, gc);
         
        gc.gridx = 0;
        gc.gridy = 2;
        JLabel labelAdd = new JLabel ("Patient Name: ");  
        panelCenter.add (labelAdd, gc);
        
        //Second column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;   
        final JTextField textId = new JTextField (10);
        panelCenter.add (textId, gc);
        
        gc.gridx = 1;
        gc.gridy = 2;
        final JTextField textName = new JTextField (10);
        panelCenter.add (textName, gc);
        
        //Buttons
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 1;
        JButton searchId = new JButton ("Search ID");
        searchId.setBackground (new Color (102,255,102));
        panelCenter.add (searchId, gc);
        searchId.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                String patientId = textId.getText();
                Patient tempPatient = controller.findPatientById(patientId);
                //If no patient by entered ID
                if (tempPatient == null)
                {
                    JOptionPane.showMessageDialog (null, "There is no patient on the system with an ID: " + patientId);
                    patientSearch (panelCenter);
                }
                //Display patient profile
                else 
                {
                    patientProfile (panelCenter, tempPatient);
                }
	    }
	} ); 
        
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 3;
        final JButton searchName = new JButton ("Search Name");
        searchName.setBackground (new Color (102,255,102));
        panelCenter.add (searchName, gc);
        searchName.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                // ****************need to code for if there is more then one patient witht the same name********************
                String patientName = textName.getText();
                Patient tempPatient = controller.findPatientByName(patientName);
                //If no patient by entered name
                if (tempPatient == null)
                {
                    JOptionPane.showMessageDialog (null, "There is no patient on the system with the name: " + patientName);
                    patientSearch (panelCenter);
                }
                //Display patient profile
                else 
                {
                    patientProfile (panelCenter, tempPatient);
                }
	    }
	} ); 
        
        repaint(); 
    }
    
    //This method is used to create the patient list window
    private void patientSectionList (final JPanel panelCenter)
    {
        //Clears panel and sets title
        panelCenter.removeAll ();
        TitledBorder title = BorderFactory.createTitledBorder("Patient List");
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelCenter.setBorder(title);
        JPanel panelCenter2 = new JPanel (new BorderLayout ());
        panelCenter.add (panelCenter2);
        
        JLabel labelId = new JLabel ("ID Num       Patient Name");
        panelCenter2.add (labelId, BorderLayout.NORTH);
        
        //Creates list of patients
        list = new JList (controller.fullPatientList());
        list.setFixedCellHeight(20);
        list.setFixedCellWidth(200);
        list.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        panelCenter2.add (new JScrollPane (list), BorderLayout.CENTER);
        
        //Button
        JButton searchPatient = new JButton ("Patient Profile");
        searchPatient.setBackground (new Color (102,255,102));
        panelCenter2.add (searchPatient, BorderLayout.SOUTH);
        searchPatient.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                int position = list.getSelectedIndex();
                //If no patient is selected
                if (position == -1)
                {
                    JOptionPane.showMessageDialog (null, "Please select a patient from the list");
                }
                //Load patient profile
                else 
                {
                    Patient tempPatient = controller.getPatient(position);
                    patientProfile (panelCenter, tempPatient);
                }             
	    }
	} ); 
        
        repaint();
    }
    
    //This method is used to create the patient profile window
    private void patientProfile (final JPanel panelCenter, final Patient tempPatient)
    {
        //******************** might add a list of procedures and payments to this gui*********************
        
        //Clears panel and sets title
        panelCenter.removeAll ();
        TitledBorder title = BorderFactory.createTitledBorder("Patient Profile");
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelCenter.setBorder(title);
        
        //Patient profile picture
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.gridx = 0;
        gc.gridy = 0;
        JPanel picture = new JPanel ();
        picture.setBorder(BorderFactory.createCompoundBorder((BorderFactory.createRaisedBevelBorder()), (BorderFactory.createLoweredBevelBorder())));
        picture.setPreferredSize(new Dimension (100,100));
        panelCenter.add (picture, gc);
        
        //Patient details
        gc.anchor = GridBagConstraints.WEST;
        gc.gridx = 0;
        gc.gridy = 1;    
        JLabel labelName = new JLabel ("Name: ");
        panelCenter.add (labelName, gc);
        
        gc.gridx = 0;
        gc.gridy = 2;
        JLabel labelAdd = new JLabel ("Address:  ");  
        panelCenter.add (labelAdd, gc);
      
        gc.gridx = 0;
        gc.gridy = 3;    
        JLabel labelPhone = new JLabel ("Phone:  ");
        panelCenter.add (labelPhone, gc);
        
        gc.gridx = 1;
        gc.gridy = 1;
        final JTextField patientNameText = new JTextField (tempPatient.getPatientName(),20);
        panelCenter.add (patientNameText, gc);
        
        gc.gridx = 1;
        gc.gridy = 2;
        final JTextField patientAddText = new JTextField (tempPatient.getPatientAdd(),20);
        panelCenter.add (patientAddText, gc);
        
        gc.gridx = 1;
        gc.gridy = 3;
        final JTextField patientPhoneText = new JTextField (tempPatient.getPatientPhone(),20);
        panelCenter.add (patientPhoneText, gc);
        
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 4;
        JButton procedures = new JButton ("Procedures");
        procedures.setBackground (new Color (153,255,255));
        panelCenter.add (procedures, gc);
        procedures.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                {
                    patientProcedureList (panelCenter, tempPatient);
                }      
	    }
        } ); 
        
        gc.gridx = 1;
        gc.gridy = 4;
        JButton payments = new JButton ("Payments");
        payments.setBackground (new Color (153,255,255));
        panelCenter.add (payments, gc);
        payments.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                {
                    paymentSectionList (panelCenter, tempPatient);
                }      
	    }
        } ); 
        
        gc.gridx = 2;
        gc.gridy = 4;
        JButton updatePatient = new JButton ("Update");
        updatePatient.setBackground (new Color (102,255,102));
        panelCenter.add (updatePatient, gc);
        updatePatient.addActionListener(new ActionListener () 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String patientName = patientNameText.getText();
                String patientAdd = patientAddText.getText();
                String patientPhone = patientPhoneText.getText();
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to update patient with entered details ", "Warning!", JOptionPane.YES_NO_OPTION );
                //update patient if yes is clicked
                if (choice == JOptionPane.YES_OPTION) 
                {
                    controller.updatePatient(tempPatient, patientName,patientAdd,patientPhone);
                    patientSearch (panelCenter);
                }
                patientProfile (panelCenter, tempPatient);    
            }
        } );
        
        gc.gridx = 3;
        gc.gridy = 4;
        JButton removePatient = new JButton ("Remove");
        removePatient.setBackground (new Color (255,105,105));
        panelCenter.add (removePatient, gc);
        removePatient.addActionListener(new ActionListener () 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove " + tempPatient.getPatientName(), "Warning!", JOptionPane.YES_NO_OPTION );
                //Removes patient if yes is clicked
                if (choice == JOptionPane.YES_OPTION) 
                {
                    controller.removePatient(tempPatient);
                    patientSearch (panelCenter);
                }      
            }
        } );

        repaint();
    }
    
    private void patientProcedureAdd (final JPanel panelCenter, final Patient tempPatient)
    {
        //Clears panel and sets title
        panelCenter.removeAll ();
        TitledBorder title = BorderFactory.createTitledBorder("Select Procedure For "+ tempPatient.getPatientNumber() + " " + tempPatient.getPatientName());
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelCenter.setBorder(title);
        
        //Changed the combo box color
        UIManager.put("ComboBox.background", new ColorUIResource(UIManager.getColor("TextField.background")));
	UIManager.put("ComboBox.foreground", new ColorUIResource(UIManager.getColor("TextField.foreground")));
	UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Color.LIGHT_GRAY));
	UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
        
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        //First column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;
        JLabel labelName = new JLabel ("Procedure: ");  
        panelCenter.add (labelName, gc);
        
        //Second column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;   
        final JComboBox procedures = new JComboBox (controller.systemProcedureList ());
        panelCenter.add (procedures, gc);  
        
        //Buttons
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 3;
        JButton addPatient = new JButton ("Store");
        addPatient.setBackground (new Color (102,255,102));
        panelCenter.add (addPatient, gc);
        addPatient.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                int selectedIndex = procedures.getSelectedIndex();
                if (selectedIndex < 0)
                {
                    error ("No item selected!");
                }
                else
                {
                    Object selectedName = procedures.getSelectedItem();
                    controller.addProcedureToPatient(tempPatient, selectedIndex);
                    JOptionPane.showMessageDialog (null, selectedName + " has been added to " +  tempPatient.getPatientName());
                    patientProcedureAdd (panelCenter, tempPatient);
                }
	    }
	} ); 
        
        gc.gridx = 1;
        gc.gridy = 3;
        JButton cancel = new JButton ("Back");
        cancel.setBackground (new Color (153,255,255));
        panelCenter.add (cancel, gc);
        cancel.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                patientProcedureList (panelCenter, tempPatient);
	    }
	} ); 
            
        repaint();
    }
    
    //This method is used to create the patient procedure list window
    private void patientProcedureList (final JPanel panelCenter, final Patient tempPatient)
    {
        //Clears panel and sets title
        panelCenter.removeAll ();
        TitledBorder title = BorderFactory.createTitledBorder("Procedure List for " + tempPatient.getPatientNumber() + " " + tempPatient.getPatientName());
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelCenter.setBorder(title);  
        JPanel panelCenter2 = new JPanel (new BorderLayout ());
        panelCenter.add (panelCenter2);
        JPanel panelTop = new JPanel (new BorderLayout ());
        panelCenter2.add (panelTop, BorderLayout.NORTH);
        JPanel panelMid = new JPanel (new BorderLayout ());
        panelCenter2.add (panelMid, BorderLayout.CENTER);
        
        JLabel labelId = new JLabel ("Number      Name              Cost");
        panelTop.add (labelId, BorderLayout.NORTH);
        
        //Creates list of procedures
        list = new JList (controller.procedureList(tempPatient));
        list.setFixedCellHeight(20);
        list.setFixedCellWidth(200);
        list.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        panelTop.add (new JScrollPane (list), BorderLayout.CENTER);
        
        //Buttons
        JButton addProc = new JButton ("Add");
        addProc.setBackground (new Color (102,255,102));
        panelMid.add (addProc, BorderLayout.WEST);
        addProc.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                patientProcedureAdd (panelCenter,tempPatient);
            }
        });
        
        JButton removeProc = new JButton ("Remove");
        removeProc.setBackground (new Color (255,105,105));
        panelMid.add (removeProc, BorderLayout.CENTER);
        removeProc.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                int position = list.getSelectedIndex();
                //If no procedure is selected
                if (position == -1)
                {
                    JOptionPane.showMessageDialog (null, "Please select a procedure from the list");
                }
                //If wrong thing is selected
                else if (!(position < tempPatient.getProcedureList().size ()))
                {
                    JOptionPane.showMessageDialog (null, "You did not select a procedure from the list");
                }
                //Remove procedure
                else 
                {
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove " + tempPatient.getProcedure(position).getProcName(), "Warning!", JOptionPane.YES_NO_OPTION );
                    if (choice == JOptionPane.YES_OPTION) 
                    {
                        tempPatient.getProcedureList().remove(position);
                    }      
                    //Reload page
                    patientProcedureList (panelCenter,tempPatient);
                }             
	    }
	} ); 
        
        JButton cancelProc = new JButton ("Back");
        cancelProc.setBackground (new Color (153,255,255));
        panelMid.add (cancelProc, BorderLayout.EAST);
        cancelProc.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                patientProfile (panelCenter, tempPatient);
            }
        });
        
         repaint();
    }
    
    //This method is used to create the payment add window
    private void paymentSectionAdd (final JPanel panelCenter, final Patient tempPatient)
    {
        //Clears panel and sets title
        panelCenter.removeAll ();
        TitledBorder title = BorderFactory.createTitledBorder("Enter Payment Details for " + tempPatient.getPatientNumber() + " " + tempPatient.getPatientName());
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelCenter.setBorder(title);
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        //First column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;
        JLabel labelDate = new JLabel ("Date: ");  
        panelCenter.add (labelDate, gc);
        
        gc.gridx = 0;
        gc.gridy = 1;
        JLabel labelAmount = new JLabel ("Amount: ");
        panelCenter.add (labelAmount, gc);
        
        //Second column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;   
        final JTextField textDate = new JTextField (10);
        panelCenter.add (textDate, gc);  
        
        gc.gridx = 1;
        gc.gridy = 1;
        final JTextField textAmount = new JTextField (10);
        panelCenter.add (textAmount, gc);
        
        //Buttons
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 3;
        JButton addPatient = new JButton ("Store");
        addPatient.setBackground (new Color (102,255,102));
        panelCenter.add (addPatient, gc);
        addPatient.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                String paymentDate = textDate.getText();
                String paymentAmount = textAmount.getText();
               
                if (controller.addPayment (paymentDate, paymentAmount, tempPatient)== true)
                {
                    JOptionPane.showMessageDialog (null, paymentAmount + " has been added to " + tempPatient.getPatientName());
                }
                paymentSectionList (panelCenter,tempPatient);
	    }
	} ); 
        
        gc.gridx = 1;
        gc.gridy = 3;
        JButton cancel = new JButton ("Back");
        cancel.setBackground (new Color (153,255,255));
        panelCenter.add (cancel, gc);
        cancel.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                paymentSectionList (panelCenter,tempPatient);
	    }
	} ); 
            
        repaint();
    }
    
    //This method is used to create the payment list window
    private void paymentSectionList (final JPanel panelCenter, final Patient tempPatient)
    {
        //Clears panel and sets title
        panelCenter.removeAll ();
        TitledBorder title = BorderFactory.createTitledBorder("Procedure List for " + tempPatient.getPatientNumber() + " " + tempPatient.getPatientName());
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        JPanel panelCenter2 = new JPanel (new BorderLayout ());
        panelCenter.add (panelCenter2);
        JPanel panelTop = new JPanel (new BorderLayout ());
        panelCenter2.add (panelTop, BorderLayout.NORTH);
        JPanel panelMid = new JPanel (new BorderLayout ());
        panelCenter2.add (panelMid, BorderLayout.CENTER);
        
        JLabel labelId = new JLabel ("Number      Date                       Cost");
        panelTop.add (labelId, BorderLayout.NORTH);
        
        //Creates list of payments
        list = new JList (controller.paymentList(tempPatient));
        list.setFixedCellHeight(20);
        list.setFixedCellWidth(200);
        list.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        panelTop.add (new JScrollPane (list), BorderLayout.CENTER);
        
        //Buttons
        JButton addPay = new JButton ("Add");
        addPay.setBackground (new Color (102,255,102));
        panelMid.add (addPay, BorderLayout.WEST);
        addPay.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                paymentSectionAdd (panelCenter, tempPatient);
            }
        });
        
        JButton removePay = new JButton ("Remove");
        removePay.setBackground (new Color (255,105,105));
        panelMid.add (removePay, BorderLayout.CENTER);
        removePay.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                int position = list.getSelectedIndex();
                //If no payment is selected
                if (position == -1)
                {
                    JOptionPane.showMessageDialog (null, "Please select a payment from the list");
                }
                //If wrong thing is selected
                else if (!(position < tempPatient.getPaymentList().size ()))
                {
                    JOptionPane.showMessageDialog (null, "You did not select a payment from the list");
                }
                //Remove payment
                else 
                {
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove payment number " + tempPatient.getPayment(position).getPaymentNumer(), "Warning!", JOptionPane.YES_NO_OPTION );
                    if (choice == JOptionPane.YES_OPTION) 
                    {
                        tempPatient.getPaymentList().remove(position);
                    }      
                    //Reload page
                    paymentSectionList (panelCenter,tempPatient);
                }
            }
        });
        
        JButton cancelPay = new JButton ("Back");
        cancelPay.setBackground (new Color (153,255,255));
        panelMid.add (cancelPay, BorderLayout.EAST);
        cancelPay.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                patientProfile (panelCenter, tempPatient);
            }
        });
        
        repaint();
    }
    
    //This method is used to create the procedure section
    private void createProcedureSection ()
    {
        page2 = new JPanel ();
        page2.setLayout(new BorderLayout ());
        
        //Creates the 2 panel sections
        JPanel panelTop = new JPanel ();
        page2.add (panelTop, BorderLayout.NORTH);
        panelTop.setPreferredSize(new Dimension (500,70));
        final JPanel panelCenter = new JPanel (new GridBagLayout ());
        page2.add (panelCenter, BorderLayout.CENTER);
        
        //Set title
        TitledBorder title = BorderFactory.createTitledBorder("Procedure Section Options");
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelTop.setBorder(title);
        
        //Creates the buttons for the top section
        JButton addButton = new JButton("Add"); 
        addButton.setBackground (new Color (255,255,153));
        panelTop.add(addButton);
        addButton.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
               systemProcedureAdd (panelCenter);
	    }
	} ); 
        JButton listButton = new JButton("List / Remove");
        listButton.setBackground (new Color (255,255,153));
        panelTop.add(listButton);  
        listButton.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                systemProcedureList (panelCenter);
	    }
	} ); 
    }
    
    //This method is used to create add procedure to the system window
    private void systemProcedureAdd (final JPanel panelCenter)
    {
        //Clears panel and sets title
        panelCenter.removeAll ();
        TitledBorder title = BorderFactory.createTitledBorder("Enter Procedure Details To The System");
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelCenter.setBorder(title);
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        //First column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;
        JLabel labelName = new JLabel ("Name: ");  
        panelCenter.add (labelName, gc);
        
        gc.gridx = 0;
        gc.gridy = 1;
        JLabel labelCost = new JLabel ("Cost: ");
        panelCenter.add (labelCost, gc);
        
        //Second column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;   
        final JTextField textName = new JTextField (10);
        panelCenter.add (textName, gc);  
        
        gc.gridx = 1;
        gc.gridy = 1;
        final JTextField textCost = new JTextField (10);
        panelCenter.add (textCost, gc);
        
        //Buttons
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 3;
        JButton addPatient = new JButton ("Store");
        addPatient.setBackground (new Color (102,255,102));
        panelCenter.add (addPatient, gc);
        addPatient.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                String procedureName = textName.getText();
                String procedureCost = textCost.getText();
                if (controller.addProcedureToSystem (procedureName, procedureCost) == true)
                {
                    JOptionPane.showMessageDialog (null, procedureName + " has been added to the system");
                }
                systemProcedureAdd (panelCenter);
	    }
	} ); 
        
        gc.gridx = 1;
        gc.gridy = 3;
        JButton cancel = new JButton ("Back");
        cancel.setBackground (new Color (153,255,255));
        panelCenter.add (cancel, gc);
        cancel.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                systemProcedureAdd (panelCenter);
	    }
	} ); 
            
        repaint();
    }
    
    //This method is used to create error pop ups
    public static void error (String errorType)
    {
        JOptionPane.showMessageDialog (null, errorType);
    }    
    
    //This method is used to create the system procedure list window
    private void systemProcedureList (final JPanel panelCenter)
    {
        //Clears panel and sets title
        panelCenter.removeAll ();
        TitledBorder title = BorderFactory.createTitledBorder("System Procedure List");
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelCenter.setBorder(title);  
        JPanel panelCenter2 = new JPanel (new BorderLayout ());
        panelCenter.add (panelCenter2);
        
        JLabel labelId = new JLabel ("Name              Cost");
        panelCenter2.add (labelId, BorderLayout.NORTH);
        
        //Creates list of procedures
        list = new JList (controller.systemProcedureList());
        list.setFixedCellHeight(20);
        list.setFixedCellWidth(200);
        list.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        panelCenter2.add (new JScrollPane (list), BorderLayout.CENTER);
        
        //Button 
        JButton removeProc = new JButton ("Remove");
        removeProc.setBackground (new Color (255,105,105));
        panelCenter2.add (removeProc, BorderLayout.SOUTH);
        removeProc.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                int position = list.getSelectedIndex();
                //If no procedure is selected
                if (position == -1)
                {
                    JOptionPane.showMessageDialog (null, "Please select a procedure from the list");
                }
                //Remove procedure
                else 
                {
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove " + controller.getProcedure(position).getProcName(), "Warning!", JOptionPane.YES_NO_OPTION );
                    if (choice == JOptionPane.YES_OPTION) 
                    {
                        controller.removeProcedure (position);
                    }      
                    //Reload page
                    systemProcedureList (panelCenter);
                }             
	    }
	} ); 
        
        repaint();
    }
   
    //This method is used to create the report section
    private void createReportsSection ()
    {
        page3 = new JPanel ();
        page3.setLayout(new BorderLayout ());
        
        //Creates the 2 panel sections
        JPanel panelTop = new JPanel ();
        page3.add (panelTop, BorderLayout.NORTH);
        panelTop.setPreferredSize(new Dimension (500,70));
        final JPanel panelCenter = new JPanel (new GridBagLayout ());
        page3.add (panelCenter, BorderLayout.CENTER);
        
        //Set title
        TitledBorder title = BorderFactory.createTitledBorder("Procedure Section Options");
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelTop.setBorder(title);
        
        //Creates the buttons for the top section
        JButton fullReport = new JButton("Full Report"); 
        fullReport.setBackground (new Color (255,255,153));
        panelTop.add(fullReport);
        fullReport.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                String reportString = controller.reportFull ();
                printReport (panelCenter, reportString);
	    }
	} );  
        
        JButton dueReport = new JButton("Due Report");
        dueReport.setBackground (new Color (255,255,153));
        panelTop.add(dueReport);
        dueReport.addActionListener(new ActionListener () 
        {
            @Override
	    public void actionPerformed(ActionEvent e) 
            {
                String reportString = controller.oweReport ();
                printReport (panelCenter, reportString);
	    }
	} ); 
    }   
    
    private void printReport (final JPanel panelCenter, String reportString)
    {
        panelCenter.removeAll ();
        TitledBorder title = BorderFactory.createTitledBorder("Patient Report");
        title.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        panelCenter.setBorder(title);
        JTextArea report = new JTextArea (reportString, 20 ,20);
        JScrollPane sp = new JScrollPane(report);
        report.setEditable(false);
        report.setLineWrap (true);
        Border myBorder = BorderFactory.createLineBorder(Color.BLUE);
        report.setBorder(myBorder);
        panelCenter.add (sp);
        repaint ();
    }
}
