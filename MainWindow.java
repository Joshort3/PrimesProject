import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -3880026026104218593L;
	private Primes m_Primes;
	private JTextField tfPrimeFileName;
	private JTextField tfCrossFileName;
	private JLabel lblPrimeCount;
	private JLabel lblCrossCount;
	private JLabel lblLargestPrime;
	private JLabel lblLargestCross;
	private JLabel lblStatus = new JLabel("Status: Ready");
	
	MainWindow(String name, Primes p)
	{
	      JDialog main = new JDialog(this, name);
	      GridBagLayout gridLayout = new GridBagLayout();
	      main.getContentPane().setBackground(new Color(52, 0, 0));
	      main.getContentPane().setLayout(gridLayout);
	      
	      GridBagConstraints mgbcDialog = new GridBagConstraints();
	      mgbcDialog.fill = GridBagConstraints.HORIZONTAL;
	      mgbcDialog.anchor = GridBagConstraints.WEST;
	      mgbcDialog.insets = new Insets(1,2,2,0);
	      mgbcDialog.weightx = .5;
	      mgbcDialog.gridx = 0;
	      mgbcDialog.gridy = 0;
	  
	      GridBagConstraints mgbcPanel1 = new GridBagConstraints();
	      mgbcPanel1.fill = GridBagConstraints.HORIZONTAL;
	      mgbcPanel1.anchor = GridBagConstraints.WEST;
	      mgbcPanel1.insets = new Insets(2,2,1,0);
	      mgbcPanel1.weightx = .5;
	      mgbcPanel1.gridx = 0;
	      mgbcPanel1.gridy = 0;
		
		  //1st Panel
	      JPanel panel1 = new JPanel();
	      panel1.setLayout(new GridBagLayout());
	      
	      
	      //Panel 1: Adds the text field to for Primes text file
	      tfPrimeFileName = new JTextField(Config.PRIMEFILE);
	      tfPrimeFileName.setColumns(30);
	      panel1.add(tfPrimeFileName, mgbcPanel1);
	      
	      //Panel 1: Adds Zero to the left of tfPrimeFileName
	      lblPrimeCount = new JLabel("0");
	      mgbcPanel1.gridx = 2;
	      mgbcPanel1.gridy = 0;
	      panel1.add(lblPrimeCount, mgbcPanel1);
	      
	      //Panel 1:  "Primes File" text is added
	      JLabel labelP1 = new JLabel("Primes File");
	      labelP1.setFont(new Font("Tahoma", Font.PLAIN, 20));
	      mgbcPanel1.gridwidth = 1;
	      mgbcPanel1.gridy = 1;
	      mgbcPanel1.gridx = 0;
	      panel1.add(labelP1, mgbcPanel1);
	      
	      
	      //Panel 1: Load Button
	      JButton btnLd1 = new JButton("Load");
	      //make it load when you click the button
	      btnLd1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          String filename = new String();
	          
	          if(tfPrimeFileName.getText().equals("")) {
	            filename = Config.PRIMEFILE;
	          } 
	          
	          else {
	            filename =tfPrimeFileName.getText();
	          }
	          
	          try
	          {
	            if(FileAccess.loadPrimes(p, filename)) {
	              lblStatus.setText("Status: Loaded " + filename + " successfully.");
	              updateStats();
	            } 
	            else {
	              lblStatus.setText("Status: Error loading " + filename + ".");
	            }
	          }
	          catch(NumberFormatException ex)
	          {
	            lblStatus.setText("Status: Error loading " + filename + ".");
	          }
	        }
	      });
	      
	      //Panel 1: Load Button Location
	      mgbcPanel1.gridx = 1;
	      mgbcPanel1.gridy = 1;
	      panel1.add(btnLd1, mgbcPanel1);
	      
	      //Panel 1: Save Button
	      JButton btnSv1 = new JButton("Save");
	      //make it save when you click the button
	      btnSv1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          String filename = new String();
	          
	          if(tfPrimeFileName.getText().equals("")) {
	            filename = Config.PRIMEFILE;
	          } 
	          else {
	            filename = tfPrimeFileName.getText();
	          }
	          
	          try
	          {
	            if(FileAccess.savePrimes(p, filename)) {
	              lblStatus.setText("Status: Saved " + filename + " successfully.");
	            } 
	            else {
	              lblStatus.setText("Status: Error saving " + filename + ".");
	            }
	          }
	          catch(NumberFormatException ex)
	          {
	            lblStatus.setText("Status: Error saving " + filename + ".");
	          }
	        }
	      });
	      mgbcPanel1.gridx = 2;
	      panel1.add(btnSv1, mgbcPanel1);
	      
	      //add the panel
	      main.add(panel1, mgbcDialog);
	      
	    //2nd Panel
	      JPanel panel2 = new JPanel();
	      panel2.setLayout(new GridBagLayout());
	      
	      // Panel 2: Hexagon Cross text field
	      tfCrossFileName = new JTextField(Config.CROSSFILE);
	      tfCrossFileName.setColumns(30);
	      mgbcPanel1.gridx = 0;
	      mgbcPanel1.gridy = 0;
	      panel2.add(tfCrossFileName, mgbcPanel1);
	      
	    // Panel 2: Adds the beginning zero to right of text field
	      lblCrossCount = new JLabel("0");
	      mgbcPanel1.anchor = GridBagConstraints.EAST;
	      mgbcPanel1.gridx = 2;
	      panel2.add(lblCrossCount, mgbcPanel1);
	      
	      //Panel 2: "Hexagon Cross File" text
	      JLabel labelP2 = new JLabel("Hexagon Cross File");
	      labelP2.setFont(new Font("Tahoma", Font.PLAIN, 20));
	      mgbcPanel1.gridwidth = 1;
	      mgbcPanel1.gridy = 1;
	      mgbcPanel1.gridx = 0;
	      panel2.add(labelP2, mgbcPanel1);
	      
	      //Panel 2: load button
	      JButton btnld2 = new JButton("Load");
	      //make it load when you click the button
	      btnld2.addActionListener(new ActionListener() {
	    	  
	        public void actionPerformed(ActionEvent e) {
	          String filename = new String();
	          
	          if(tfCrossFileName.getText().equals("")) {
	            filename = Config.CROSSFILE;
	          } 
	          else {
	            filename = tfCrossFileName.getText();
	          }
	          
	          try
	          {
	            if(FileAccess.loadCrosses(p, filename)) {
	              lblStatus.setText("Status: Loaded " + filename + " successfully.");
	              updateStats();
	            } 
	            else {
	              lblStatus.setText("Status: Error loading " + filename + ".");
	            }
	          }
	          catch(NumberFormatException ex)
	          {
	            lblStatus.setText("Status: Error loading " + filename + ".");
	          }
	        }
	      });
	      
	      //gbcPnl.anchor = GridBagConstraints.WEST;
	      mgbcPanel1.gridx = 1;
	      mgbcPanel1.gridy = 1;
	      panel2.add(btnld2, mgbcPanel1);
	      
	      //Panel2 : Save button
	      JButton btnsv2 = new JButton("Save");
	      //make it save when you click the button
	      btnsv2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          String filename = new String();
	          
	          if(tfCrossFileName.getText().equals("")) {
	            filename = Config.CROSSFILE;
	          } 
	          else {
	            filename = tfCrossFileName.getText();
	          }
	          
	          try
	          {
	            if(FileAccess.saveCrosses(p, filename)) {
	              lblStatus.setText("Status: Saved " + filename + " successfully.");
	            } 
	            else {
	              lblStatus.setText("Status: Error saving " + filename + ".");
	            }
	          }
	          catch(NumberFormatException ex)
	          {
	            lblStatus.setText("Status: Error saving " + filename + ".");
	          }
	        }
	      });
	      //gbcPnl.anchor = GridBagConstraints.EAST;
	      mgbcPanel1.gridx = 2;
	      panel2.add(btnsv2, mgbcPanel1);
	      
	      //add 2nd Panel
	      mgbcDialog.gridy = 1;
	      main.add(panel2, mgbcDialog);
	      
	      //3rd Panel
	      JPanel pnlGen = new JPanel();
	      pnlGen.setLayout(new GridBagLayout());
	      
	      //Panel 3: Generate Primes Button
	      JButton btnGenPrm = new JButton("Generate Primes");
	      //make prime generation button pop up when you click it
	      btnGenPrm.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {  
	          popupGeneratePrimes();
	        }
	      });
	      mgbcPanel1.gridx = 0;
	      mgbcPanel1.gridy = 0;
	      pnlGen.add(btnGenPrm, mgbcPanel1);
	      
	      // Panel 3: lblLargestPrime text
	      lblLargestPrime = new JLabel("The largest prime has 0 digits.");
	      mgbcPanel1.gridx = 1;
	      mgbcPanel1.gridy = 0;
	      mgbcPanel1.anchor = GridBagConstraints.NORTH;
	      mgbcPanel1.insets = new Insets(2,25,1,25);
	      pnlGen.add(lblLargestPrime, mgbcPanel1);
	      
	      //Panel 3: lblLargestCross text
	      lblLargestCross = new JLabel("The largest cross has 0 and 0 digits.");
	      mgbcPanel1.gridx = 1;
	      mgbcPanel1.gridy = 0;
	      mgbcPanel1.anchor = GridBagConstraints.SOUTH;
	      pnlGen.add(lblLargestCross, mgbcPanel1);
	      
	      // Panel 3: Generate Crosses Button
	      JButton btnGenCrs = new JButton("Generate Crosses");
	      //generate crosses from existing primes when button is pressed
	      btnGenCrs.addActionListener(new ActionListener() {
	    	  
	    	  public void actionPerformed(ActionEvent e) {
	    	      	try
	    	      	{
	    	      		  m_Primes.generateTwinPrimes();
	    		          m_Primes.generateHexPrimes();
	    		          m_Primes.printTwins();
	    		          m_Primes.printHexes();          
	    		          lblStatus.setText("Status: Hexagon Crosses have been created and printed out");
	    		          updateStats();  
	    	      	}
	    	      	catch(NumberFormatException ex)
	    	      	{
	    	      		lblStatus.setText("Status: Hexagon Crosses generation has failed");
	    	      	}
	        }
	      });
	      mgbcPanel1.anchor = GridBagConstraints.EAST;
	      mgbcPanel1.gridx = 2;
	      mgbcPanel1.gridy = 0;
	      mgbcPanel1.insets = new Insets(2,2,1,0);
	      pnlGen.add(btnGenCrs, mgbcPanel1);
	      
	      //add 3rd panel
	      mgbcDialog.gridy = 2;
	      main.add(pnlGen, mgbcDialog);
	      
	      //Last panel
	      JPanel pnlStat = new JPanel();
	      pnlStat.setLayout(new GridBagLayout());
	      
	      //Last Panel: Statues
	      mgbcPanel1.anchor = GridBagConstraints.WEST;
	      mgbcPanel1.gridx = 0;
	      mgbcPanel1.gridy = 0;
	      pnlStat.add(lblStatus, mgbcPanel1);
	      
	      //add last panel
	      mgbcDialog.gridy = 3;
	      main.add(pnlStat, mgbcDialog);
	      
	      //Now finish
	      main.setSize(1000, 400);
	      main.pack();
	      main.setVisible(true);
	    }

	protected void popupGeneratePrimes()
	{
		JDialog dPrimes = new JDialog(this, "Prime Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(new Color(52, 0, 0));
		dPrimes.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("Number of Primes to Generate");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);
		
		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);
		
		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);
		
		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);
		
		dPrimes.add(pnlGenerate, gbcDialog);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		BigInteger start = new BigInteger(tfStart.getText());
      		int count = Integer.parseInt(tfCount.getText());
       		m_Primes.generatePrimes(start, count);
       		lblStatus.setText("Status: Excited. Primes have been generated.");
       		m_Primes.printPrimes();
       		updateStats();
      		dPrimes.dispose();
      	}
      	catch(NumberFormatException ex)
      	{
      		lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
      		dPrimes.dispose();
      	}
      	
      }
    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	dPrimes.dispose();
      }
    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);		
		
		gbcDialog.gridy = 1;
		dPrimes.add(pnlButtons, gbcDialog);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);
		
		gbcDialog.gridy = 2;
		dPrimes.add(pnlStatus, gbcDialog);
		
		dPrimes.setSize(200,200);
		dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dPrimes.setVisible(true);		
	}

	// This function updates all the GUI statistics. (# of primes, # of crosses, etc)
	private void updateStats()
	{
		//update all information to the main window to be displayed
	      String numPrimes = Integer.toString(m_Primes.primeCount());
	      lblPrimeCount.setText(numPrimes); 
	      String sizePrime = Integer.toString(m_Primes.sizeofLastPrime());
	      lblLargestPrime.setText("The largest prime has " + sizePrime + " digits.");
	      String numCrosses = Integer.toString(m_Primes.crossesCount());
	      lblCrossCount.setText(numCrosses);
	      String p1 = Integer.toString(m_Primes.sizeofLastCross().p1);
	      String p2 = Integer.toString(m_Primes.sizeofLastCross().p2);
	      lblLargestCross.setText("The largest cross has " + p1 + " and " + p2 + " digits.");
 	}

}
