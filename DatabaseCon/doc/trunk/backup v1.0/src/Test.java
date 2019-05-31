import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test extends JFrame implements ActionListener{
   
   private JTextArea jtaCenter = new JTextArea();
   private JButton jbTest = new JButton("Test");
   private JButton jbDriver = new JButton("Driver");
   //private JButton jbConnect = new JButton("Connect");
   //private JButton jbSearch = new JButton("Search");
   //private JButton jbAbstract = new JButton("Load Abstract");
   
   /**
   * Data Source
   */
   private final String GETDRIVER = "com.mysql.cj.jdbc.Driver";
   
   public static void main(String[] args)
   {
      new Test();
   } // Main
    
   public Test()
   {
      setupWindow();
      
      /**
      * Setup Window
      */
      JPanel jpCenter = new JPanel();
      JPanel jpSouth = new JPanel();
      
      /**
      * The Fields
      */
      jpCenter.add(jtaCenter);
      jpSouth.add(jbTest);
      jpSouth.add(jbDriver);
      //jpSouth.add(jbConnect);
      //jpSouth.add(jbAbstract);
      
      /**
      * Add Feilds
      */
      this.add(jpCenter, BorderLayout.CENTER);
      this.add(jpSouth, BorderLayout.SOUTH);
      
      /**
      * Make Buttons Work
      */
      jbTest.addActionListener(this);
      jbDriver.addActionListener(this);
      
      /** 
      * Makes text area work
      */
      Font currentFont = jtaCenter.getFont();
      Font newFont = new Font("Courier", Font.PLAIN, 16);
      jtaCenter.setFont(newFont);
      jtaCenter.setLineWrap(true);
      jtaCenter.setWrapStyleWord(true);
      JScrollPane jspCenter = new JScrollPane(jtaCenter);
      this.add(jspCenter, BorderLayout.CENTER);
      
      this.setVisible(true);
   } // Test()
   
   /**
   * Sets up the window
   **/
   public void setupWindow()
   {
      this.setTitle("Test");
      this.setSize(700, 500);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   } // setupWindow()
     
   
   /**
   * Button Event Handling
   **/
   public void actionPerformed(ActionEvent ae)
   {
      switch(ae.getActionCommand())
      {
         case "Test":
            doTest();
            break;
         case "Driver":
            getDriver();
            break;
      }
   } // actionPerformed()
   
   //this is where the test code will go
   public void doTest(){
      jtaCenter.append("START TEST\n");
      jtaCenter.append("TEST Admin.java\n");
      Admin ad = new Admin();
      jtaCenter.append("" + ad.addUser("rcs5166", "pass", "Cam", "s")+ "\n");
      jtaCenter.append("" + ad.editPassword("rcs5166", "newPass")+ "\n");
      jtaCenter.append("" + ad.editUserType("rcs5166", "F")+ "\n");
      jtaCenter.append("" + ad.editName("rcs5166", "Campbell")+ "\n");
      jtaCenter.append("" + ad.deleteUser("rcs5166")+ "\n");
      jtaCenter.append("" + ad.addUser("rcs5166", "pass", "Cam", "s")+ "\n");
      jtaCenter.append("TEST Users.java\n");
      Users us = new Users();
      jtaCenter.append("" + us.findUserByName("rcs5166")+ "\n");
      jtaCenter.append("" + us.findUsersByKeyword("g")+ "\n");
      jtaCenter.append("" + us.findUserByUsername("rcs5166")+ "\n");
      jtaCenter.append("END TEST\n");
   } // doTest
   
   public void getDriver(){
      try {
			Class.forName(GETDRIVER);
			JOptionPane.showMessageDialog(null,"Driver Loaded! \n" +
	    		GETDRIVER);
		}
		catch(ClassNotFoundException cnfe) {
	    	JOptionPane.showMessageDialog(null,"Fail to load \n" +
	    		GETDRIVER);
		}
   }// load a Driver
   
} // Test