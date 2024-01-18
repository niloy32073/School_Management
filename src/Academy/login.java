/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Academy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;


/**
 *
 * @author mdnif
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        getConnection();
    }
    
    public Connection getConnection(){
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        
        //try{}catch(Exception e){}
        try{
            Class.forName(driver).newInstance();
        }catch(Exception e){
            System.out.print(e);
        }
        
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection("jdbc:derby:AloDB");
            System.out.println("Opened default database successfully");
        }catch(Exception e){
            try{
                conn = DriverManager.getConnection("jdbc:derby:AloDB;create=true");
                Statement stmt=conn.createStatement();
                stmt.execute("CREATE TABLE ROLLFIRST(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,name VARCHAR(25) UNIQUE)");
                System.out.println("ROLLFIRST");
                //stmt.execute("INSERT INTO ROLLFIRST()");
                stmt.execute("CREATE TABLE ROLLMID(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,name VARCHAR(25) UNIQUE)");
                System.out.println("ROLLMID");
                stmt.execute("CREATE TABLE YEARNAME(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,name VARCHAR(25) UNIQUE)");
                System.out.println("YearNAME");
                stmt.execute("CREATE TABLE SUBJECT(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,subjectName VARCHAR(100)UNIQUE)");
                System.out.println("SUBJECT");
                stmt.execute("CREATE TABLE SCHOOL(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,schoolName VARCHAR(100) UNIQUE)");
                System.out.println("SUBJECT");
                stmt.execute("CREATE TABLE CLASS(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,className VARCHAR(100) UNIQUE)");
                System.out.println("SUBJECT");
                stmt.execute("CREATE TABLE BATCH(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,batchName VARCHAR(25),subjectName VARCHAR(100))");
                System.out.println("BATCH");
                stmt.execute("CREATE TABLE PAYMENTFOR(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,paymentName VARCHAR(25) UNIQUE)");
                System.out.println("payment");
                stmt.execute("CREATE TABLE STUDENT(roll VARCHAR(50) PRIMARY KEY,name VARCHAR(100),schoolName VARCHAR(90),class VARCHAR(50),fatherName VARCHAR(100),fatherMobile VARCHAR(50),motherName VARCHAR(100),motherMobile VARCHAR(50),subjectName VARCHAR(50),batchName VARCHAR(50),addmissionYear VARCHAR(50),month VARCHAR(50),address VARCHAR(500),DOB VARCHAR(50),AddBy VARCHAR(50))");
                System.out.println("STUDENT");
                stmt.execute("CREATE TABLE TEACHER(roll VARCHAR(50) PRIMARY KEY,name VARCHAR(100),instituteName VARCHAR(90),academicYear VARCHAR(50),fatherName VARCHAR(100),fatherMobile VARCHAR(50),motherName VARCHAR(100),motherMobile VARCHAR(50),subjectName VARCHAR(50),type VARCHAR(50),joiningYear VARCHAR(50),month VARCHAR(50),address VARCHAR(500),DOB VARCHAR(50),AddBy VARCHAR(50))");
                System.out.println("TEACHER");
                stmt.execute("CREATE TABLE PAYMENTINFO(id INT,roll VARCHAR(50),userType VARCHAR(20),paymentFor VARCHAR(50),yearName VARCHAR(50),localDate VARCHAR(50),time VARCHAR(50),amount VARCHAR(50),paymentType VARCHAR(50),receivedBy VARCHAR(50))");
                System.out.println("PAYMENTINFO");
                stmt.execute("CREATE TABLE ADMINPASSWORD(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,name VARCHAR(25),password VARCHAR(25))");
                System.out.println("Addmi");
                stmt.execute("CREATE TABLE MORDARETORPASSWORD(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,name VARCHAR(25),password VARCHAR(25))");
                System.out.println("ROLLFIRST");
                stmt.execute("INSERT INTO ADMINPASSWORD(name,password) values('anu','password')");
                System.out.println("ROLLFIRST");
                stmt.execute("INSERT INTO MORDARETORPASSWORD(name,password) values('mordaretor','password')");
                System.out.println("ROLLFIRST");
                System.out.println("Opened new table opened successfully");
            }catch(Exception ex){
                System.out.print(ex);
                System.out.println("Failed DB");
            }
        }
        
        return conn;
    }
    
    public String getPass(String Name){
        String password = "";
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM ADMINPASSWORD");
            if("anu".equals(Name)){
                ps = conn.prepareStatement("SELECT * FROM ADMINPASSWORD");
            }
            else if("mordaretor".equals(Name)){
                ps = conn.prepareStatement("SELECT * FROM MORDARETORPASSWORD");
            }
            
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            if(rs.next()){
                password = rs.getString("password");
                System.out.println("factching password successfull");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return password;
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        userpassword = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Welcome to Alo Education Group");

        jLabel2.setText("UserName : ");

        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        jLabel3.setText("Password :");

        userpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userpasswordActionPerformed(evt);
            }
        });

        login.setBackground(new java.awt.Color(51, 51, 51));
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("Log In");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(115, 115, 115))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(username)
                            .addComponent(userpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(190, 190, 190))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(login)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(33, 33, 33)
                .addComponent(login)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(161, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        String userName = username.getText();
        String password = userpassword.getText().toString();
        String passwordInDb = getPass(userName);
        if( passwordInDb.equals(password) ){
            mainScreen m = new mainScreen();
            this.hide();
            m.setVisible(true);
        }
    }//GEN-LAST:event_loginActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void userpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userpasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login;
    private javax.swing.JTextField username;
    private javax.swing.JPasswordField userpassword;
    // End of variables declaration//GEN-END:variables
}
