/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Academy;

import java.awt.print.PrinterException;
import java.awt.event.ItemListener;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.sql.ConnectionPoolDataSource;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author mdnif
 */
public class mainScreen extends javax.swing.JFrame {

    /**
     * Creates new form mainScreen
     */
    public mainScreen() {
        initComponents();
        getConnection();
        fillComboBox();
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
                System.out.println("Opened new database successfully");
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
                System.out.println("PAYMENTFOR");
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
    
    public void setBatch(String subjectName,JComboBox<String> VariableName){
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT batchName FROM BATCH WHERE subjectName=?");
            ps.setString(1, subjectName);
            ResultSet rs = ps.executeQuery();
            VariableName.removeAllItems();
            while(rs.next()){
                VariableName.addItem(rs.getString(1));
            }
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public String fillComboBox(){
        String S="1";
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM YEARNAME");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                yearcombo.addItem(rs.getString(1));
                tyearcombo.addItem(rs.getString(1));
                apy.addItem(rs.getString(1));
                jComboBox3.addItem(rs.getString(1));
            }
            PreparedStatement ps2 = conn.prepareStatement("SELECT subjectName FROM SUBJECT");
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                subjectcombo.addItem(rs2.getString(1));
                updateSubjectCombo.addItem(rs2.getString(1));
                addSubjectToBatch.addItem(rs2.getString(1));
                batchslcombosub.addItem(rs2.getString(1));
            }
            setBatch(subjectcombo.getSelectedItem().toString(),batchcombo);
            setBatch(updateSubjectCombo.getSelectedItem().toString(),updateBatchCombo);
            setBatch(batchslcombosub.getSelectedItem().toString(),batchslcombo);
            PreparedStatement ps3 = conn.prepareStatement("SELECT name FROM ROLLFIRST");
            ResultSet rs3 = ps3.executeQuery();
            while(rs3.next()){
                rollfirstcombo.addItem(rs3.getString(1));
                rollfirstcombo1.addItem(rs3.getString(1));
                trollfirstcombo.addItem(rs3.getString(1));
                aprf.addItem(rs3.getString(1));
            }
            PreparedStatement ps4 = conn.prepareStatement("SELECT name FROM ROLLMID");
            ResultSet rs4 = ps4.executeQuery();
            while(rs4.next()){
                rollseccombo.addItem(rs4.getString(1));
                rollseccombo1.addItem(rs4.getString(1));
                trollseccombo.addItem(rs4.getString(1));
                aprs.addItem(rs4.getString(1));
            }
            /*PreparedStatement ps5 = conn.prepareStatement("SELECT batchName FROM BATCH");
            ResultSet rs5 = ps5.executeQuery();
            while(rs5.next()){
                batchslcombo.addItem(rs5.getString(1));
            }*/
            PreparedStatement ps6 = conn.prepareStatement("SELECT paymentName FROM PAYMENTFOR");
            ResultSet rs6 = ps6.executeQuery();
            while(rs6.next()){
                pfplcombo.addItem(rs6.getString(1));
                appf.addItem(rs6.getString(1));
                ttdlpfcb.addItem(rs6.getString(1));
                jComboBox2.addItem(rs6.getString(1));
            }
            PreparedStatement ps7 = conn.prepareStatement("SELECT schoolName FROM SCHOOL");
            ResultSet rs7 = ps7.executeQuery();
            while(rs7.next()){
                jComboBox4.addItem(rs7.getString(1));
                updateSchoolTextField.addItem(rs7.getString(1));
            }
            PreparedStatement ps8 = conn.prepareStatement("SELECT className FROM CLASS");
            ResultSet rs8 = ps8.executeQuery();
            while(rs8.next()){
                jComboBox5.addItem(rs8.getString(1));
                updateClassTextField.addItem(rs8.getString(1));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return S;
    }
    
    public double calculateColumnTotal(JTable table, int columnIndexToTotal) {
    int rowCount = table.getRowCount();
    double total = 0.0;

    for (int i = 0; i < rowCount; i++) {
        Object value = table.getValueAt(i, columnIndexToTotal);
        if (value instanceof Number) {
            total += ((Number) value).doubleValue();
        } else if (value instanceof String) {
            try {
                total += Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
    return total;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public void exportTable(JTable table, String filePath) {
        TableModel model = table.getModel();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int c = 0; c < model.getColumnCount(); c++) {
                headerRow.createCell(c).setCellValue(model.getColumnName(c));
            }

            // Write data rows
            for (int r = 0; r < model.getRowCount(); r++) {
                Row row = sheet.createRow(r + 1);
                for (int c = 0; c < model.getColumnCount(); c++) {
                    Object value = model.getValueAt(r, c);
                    if (value != null) {
                        row.createCell(c).setCellValue(value.toString());
                    }
                }
            }

            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Data exported successfully to Excel file: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error exporting data to Excel: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rollfirstcombo = new javax.swing.JComboBox<>();
        rollseccombo = new javax.swing.JComboBox<>();
        rolllnumber = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        studentnamefield = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fathernamefield = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fathermobilefield = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        mothernamefield = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        mothermobilefield = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        subjectcombo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        batchcombo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        yearcombo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        monthcombo = new javax.swing.JComboBox<>();
        addstudentbutton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentInfoPanel = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentinfotable = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        saddressfield = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        fathernamefield1 = new javax.swing.JTextField();
        dobfield = new com.toedter.calendar.JDateChooser();
        jLabel80 = new javax.swing.JLabel();
        rollfirstcombo1 = new javax.swing.JComboBox<>();
        rollseccombo1 = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        adPas = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        tmonthcombo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        tyearcombo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tbatchcombo = new javax.swing.JComboBox<>();
        tmothermobilefield = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tmothernamefield = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tfathernamefield = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tfathermobilefield = new javax.swing.JTextField();
        tclassfield = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        tschoolnamefield = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tstudentnamefield = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        trollfirstcombo = new javax.swing.JComboBox<>();
        trollseccombo = new javax.swing.JComboBox<>();
        trolllnumber = new javax.swing.JTextField();
        tsubjectcombo = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        teacherinfotable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        teacherDataPanel = new javax.swing.JTextArea();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        tsaddressfield = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        fathernamefield2 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        tdobfield = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        aprf = new javax.swing.JComboBox<>();
        aprs = new javax.swing.JComboBox<>();
        aprn = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        aput = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        appf = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        apy = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        apa = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        incometable = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        aprb = new javax.swing.JTextField();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        searchRP = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        updateNameTextField = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        updateFatherNameTextField = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        updateFatherMobileTextField = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        updateMotherNameTextField = new javax.swing.JTextField();
        updateMotherMobileTextField = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        updateSubjectCombo = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        updateBatchCombo = new javax.swing.JComboBox<>();
        jButton14 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        updateRollfield = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        plsearchbox = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        searchResult = new javax.swing.JTable();
        jButton19 = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        paymentlisttabe = new javax.swing.JTable();
        jLabel67 = new javax.swing.JLabel();
        updateSchoolTextField = new javax.swing.JComboBox<>();
        updateClassTextField = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        batchslcombo = new javax.swing.JComboBox<>();
        jButton20 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        batchsltable = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        paidlisttable = new javax.swing.JTable();
        jButton21 = new javax.swing.JButton();
        pfplcombo = new javax.swing.JComboBox<>();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        batchslcombosub = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        teachertypeLC = new javax.swing.JComboBox<>();
        jButton24 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        ttlisttable = new javax.swing.JTable();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        ttpl = new javax.swing.JTable();
        jButton27 = new javax.swing.JButton();
        ttdlpfcb = new javax.swing.JComboBox<>();
        jLabel73 = new javax.swing.JLabel();
        ttdlcb = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        incomeTable = new javax.swing.JTable();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        expenseTable = new javax.swing.JTable();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        fromDate = new com.toedter.calendar.JDateChooser();
        toDate = new com.toedter.calendar.JDateChooser();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        adminPassForDC = new javax.swing.JPasswordField();
        eEN = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        eEA = new javax.swing.JTextField();
        jButton49 = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        expenseName = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        expenseAmount = new javax.swing.JTextField();
        jButton39 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        addSubjectToBatch = new javax.swing.JComboBox<>();
        addBatchName = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        inputNewSubject = new javax.swing.JTextField();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        newRollFirst = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        newYear = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        batchlistTable = new javax.swing.JTable();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        cpc = new javax.swing.JPasswordField();
        jLabel63 = new javax.swing.JLabel();
        cpn = new javax.swing.JPasswordField();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        mcpn = new javax.swing.JPasswordField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        mcpc = new javax.swing.JPasswordField();
        jLabel66 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        newRollSec = new javax.swing.JTextField();
        jButton41 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        newPaymentFor = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        upPaymentId = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        upPP = new javax.swing.JPasswordField();
        jTextField1 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        newPaymentFor1 = new javax.swing.JTextField();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        newPaymentFor2 = new javax.swing.JTextField();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        batchlistTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1300, 800));
        setSize(new java.awt.Dimension(1300, 800));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1024, 700));

        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 600));

        jLabel1.setText("Roll :");

        jLabel2.setText("Name  :");

        jLabel3.setText("School :");

        jLabel4.setText("Class :");

        jLabel5.setText("Father :");

        jLabel6.setText("Mobile :");

        jLabel7.setText("Mother :");

        jLabel8.setText("Mobile :");

        jLabel9.setText("Subject : ");

        subjectcombo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                subjectcomboPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        subjectcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectcomboActionPerformed(evt);
            }
        });

        jLabel10.setText("Batch :");

        jLabel11.setText("Year :");

        jLabel12.setText("Month :");

        monthcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        addstudentbutton.setText("Add Info");
        addstudentbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addstudentbuttonActionPerformed(evt);
            }
        });

        studentInfoPanel.setColumns(20);
        studentInfoPanel.setRows(5);
        studentInfoPanel.setText("\n");
        jScrollPane1.setViewportView(studentInfoPanel);

        jButton3.setText("Show student List");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        studentinfotable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(studentinfotable);

        jButton4.setText("Export In Excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel42.setText("Address :");

        jLabel43.setText("DOB :");

        jLabel47.setText("Add. By");

        dobfield.setForeground(new java.awt.Color(0, 51, 255));
        dobfield.setDateFormatString("yyyy-MM-dd");

        jLabel80.setText("Roll :");

        jScrollPane7.setViewportView(jTextPane1);

        jLabel37.setText("School :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(266, 266, 266)
                        .addComponent(adPas, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rollfirstcombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rollseccombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(addstudentbutton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fathernamefield, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fathermobilefield, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(subjectcombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mothernamefield, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(yearcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(monthcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mothermobilefield, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(batchcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rollfirstcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rollseccombo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rolllnumber, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(studentnamefield)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(fathernamefield1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dobfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(saddressfield, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(rollfirstcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rollseccombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rolllnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel37)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(studentnamefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(fathernamefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(fathermobilefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(mothernamefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(mothermobilefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(subjectcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(batchcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(yearcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(monthcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(saddressfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dobfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel43)
                                .addComponent(jLabel47)
                                .addComponent(fathernamefield1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addstudentbutton)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel80)
                    .addComponent(rollfirstcombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rollseccombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(adPas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Student", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(1012, 700));

        jButton2.setText("Add Info");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tmonthcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        jLabel13.setText("Month :");

        jLabel14.setText("Year :");

        jLabel15.setText("Subject : ");

        jLabel16.setText("Type :");

        tbatchcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Academic Teacher", "Tutor" }));

        jLabel17.setText("Mobile :");

        jLabel18.setText("Mother :");

        jLabel19.setText("Father :");

        jLabel20.setText("Mobile :");

        jLabel21.setText("Year :");

        tschoolnamefield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tschoolnamefieldActionPerformed(evt);
            }
        });

        jLabel22.setText("Institute :");

        jLabel23.setText("Name  :");

        jLabel24.setText("Roll :");

        teacherinfotable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(teacherinfotable);

        teacherDataPanel.setColumns(20);
        teacherDataPanel.setRows(5);
        jScrollPane4.setViewportView(teacherDataPanel);

        jButton6.setText("Show Teacher List");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setText("Export in Excel");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel44.setText("Address :");

        jLabel45.setText("DOB :");

        jLabel79.setText("Add. By");

        tdobfield.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(1087, Short.MAX_VALUE)
                .addComponent(jButton8))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(jButton2)
                .addGap(124, 124, 124)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tsaddressfield))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tschoolnamefield)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tclassfield, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(trollfirstcombo, 0, 150, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(trollseccombo, 0, 150, Short.MAX_VALUE)
                                .addGap(12, 12, 12)
                                .addComponent(trolllnumber, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tstudentnamefield))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfathernamefield, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfathermobilefield))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                        .addComponent(tyearcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tmothernamefield, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                            .addComponent(tsubjectcombo))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tmonthcombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tmothermobilefield))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tbatchcombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fathernamefield2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tdobfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(trollfirstcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trollseccombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trolllnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(tstudentnamefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(tschoolnamefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(tclassfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(tfathernamefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(tfathermobilefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(tmothernamefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(tmothermobilefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(tbatchcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tsubjectcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(tyearcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(tmonthcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(tsaddressfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel79)
                                .addComponent(fathernamefield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel45))
                            .addComponent(tdobfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Teacher", jPanel2);

        jLabel25.setText("Roll :");

        jLabel26.setText("User Type : ");

        aput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student", "Academic Teacher", "Tutor" }));
        aput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aputActionPerformed(evt);
            }
        });

        jLabel27.setText("Payment For : ");

        jLabel28.setText("Year :");

        jLabel29.setText("Amount : ");

        apa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apaKeyTyped(evt);
            }
        });

        jButton9.setText("Add");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane5.setViewportView(jTextArea3);

        incometable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(incometable);

        jButton10.setText("Export to Excel");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Print");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Today Income");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel78.setText("Received By :");

        jScrollPane18.setViewportView(jTextPane2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(apy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(aput, 0, 472, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(aprf, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(aprs, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(aprn, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel78))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aprb)
                                    .addComponent(apa))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                        .addGap(297, 297, 297)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                        .addGap(352, 352, 352)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(aprf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aprs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aprn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(aput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(appf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(apy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(apa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel78)
                            .addComponent(aprb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9)
                            .addComponent(jButton11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton10)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(236, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Payment", jPanel3);

        jPanel4.setPreferredSize(new java.awt.Dimension(1012, 700));

        jLabel30.setText("Roll / Phone No :");

        jButton13.setText("Search");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel31.setText("Class :");

        jLabel32.setText("School :");

        jLabel33.setText("Name  :");

        jLabel34.setText("Father :");

        jLabel35.setText("Mobile :");

        updateFatherMobileTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateFatherMobileTextFieldActionPerformed(evt);
            }
        });

        jLabel36.setText("Mother :");

        updateMotherNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMotherNameTextFieldActionPerformed(evt);
            }
        });

        jLabel38.setText("Subject : ");

        updateSubjectCombo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                updateSubjectComboPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        updateSubjectCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSubjectComboActionPerformed(evt);
            }
        });

        jLabel39.setText("Batch :");

        jButton14.setText("Want to update");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel40.setText("Roll :");

        jButton15.setText(" Update");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Change Batch");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Delete From Batch");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel41.setText("Roll :");

        plsearchbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plsearchboxActionPerformed(evt);
            }
        });

        jButton18.setText("Payment List");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        searchResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(searchResult);

        jButton19.setText("Print");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        paymentlisttabe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane17.setViewportView(paymentlisttabe);

        jLabel67.setText("Mobile :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(191, 191, 191))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(184, 184, 184))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(58, 58, 58))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(searchRP, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(updateRollfield)
                                            .addComponent(updateNameTextField)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(updateSchoolTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(updateFatherMobileTextField)
                                                    .addComponent(updateClassTextField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(updateMotherMobileTextField)))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(updateFatherNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                                                    .addComponent(updateMotherNameTextField))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(updateSubjectCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(updateBatchCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addComponent(jScrollPane8)
                                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel41)
                            .addGap(18, 18, 18)
                            .addComponent(plsearchbox, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(searchRP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(plsearchbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(updateRollfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(updateNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31)
                            .addComponent(updateSchoolTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateClassTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(updateFatherNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(updateFatherMobileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(updateMotherNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateMotherMobileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel38)
                                    .addComponent(updateSubjectCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel39)
                                    .addComponent(updateBatchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17)
                        .addGap(18, 18, 18)
                        .addComponent(jButton16)
                        .addGap(291, 291, 291))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Update Info", jPanel4);

        jLabel46.setText("Batch Name :");

        batchslcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchslcomboActionPerformed(evt);
            }
        });

        jButton20.setText("Student List");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        batchsltable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(batchsltable);

        paidlisttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(paidlisttable);

        jButton21.setText("Paid list");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setText("Export In Excel");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setText("Export In Excel");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        batchslcombosub.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                batchslcombosubPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        batchslcombosub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchslcombosubActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(batchslcombosub, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(batchslcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(pfplcombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton21)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jButton20)
                    .addComponent(pfplcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21)
                    .addComponent(batchslcombosub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batchslcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton22)
                    .addComponent(jButton23))
                .addContainerGap(248, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Batch Info", jPanel5);

        jLabel48.setText("Teacher Type :");

        teachertypeLC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Academic Teacher", "Tutor" }));

        jButton24.setText("Teacher List");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        ttlisttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(ttlisttable);

        jButton25.setText("Export In Excel");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setText("Export In Excel");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        ttpl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(ttpl);

        jButton27.setText("Paid List");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jLabel73.setText("Teacher Type :");

        ttdlcb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Academic Teacher", "Tutor" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teachertypeLC, 0, 160, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(190, 190, 190))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(369, 369, 369)
                        .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(281, 281, 281)
                                .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ttdlcb, 0, 145, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ttdlpfcb, 0, 145, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel73)
                        .addComponent(ttdlcb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(teachertypeLC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton24)
                        .addComponent(ttdlpfcb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton26)
                    .addComponent(jButton25))
                .addContainerGap(248, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Teacher Info", jPanel6);

        incomeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(incomeTable);

        jButton29.setText("Export In Excel");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setText("Export In Excel");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        expenseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(expenseTable);

        jLabel49.setText("From :");

        jLabel50.setText("To :");

        jButton28.setText("See Result");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel51.setText("Pass.");

        fromDate.setDateFormatString("yyyy-MM-dd");

        toDate.setDateFormatString("yyyy-MM-dd");

        jScrollPane19.setViewportView(jTextPane3);

        jScrollPane20.setViewportView(jTextPane4);

        adminPassForDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminPassForDCActionPerformed(evt);
            }
        });

        jLabel88.setText("Name :");

        jLabel89.setText("Extra Expance :");

        jLabel90.setText("Amount :");

        jButton49.setText("Add");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jLabel91.setText("Extra Income :");

        jLabel92.setText("Name :");

        jLabel93.setText("Amount :");

        jButton39.setText("Add");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane13))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jScrollPane19)
                        .addGap(50, 50, 50)
                        .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(expenseAmount))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(expenseName, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 106, Short.MAX_VALUE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jScrollPane20)
                        .addGap(13, 13, 13)
                        .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(354, 354, 354))
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel90, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                            .addComponent(jLabel88, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(eEN)
                                            .addComponent(eEA))))
                                .addGap(324, 324, 324)))))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fromDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminPassForDC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(132, 132, 132))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel49)
                        .addComponent(jLabel50)
                        .addComponent(jButton28)
                        .addComponent(jLabel51)
                        .addComponent(adminPassForDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton30)
                            .addComponent(jButton29)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel89)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel88)
                            .addComponent(eEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel90)
                            .addComponent(eEA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton49))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel91)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel92)
                            .addComponent(expenseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel93)
                            .addComponent(expenseAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton39)))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Debit - Credit", jPanel7);

        jLabel52.setText("Add/Delete Batch :");

        jLabel53.setText("Batch Name :");

        jLabel54.setText("Subject  Name :");

        jButton31.setText("Add Batch");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jLabel55.setText("Add/Delete  Subject :");

        jLabel56.setText("Subject  Name :");

        jButton32.setText("Add Subject");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setText("Add Roll");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jLabel57.setText("Roll  Name :");

        jLabel58.setText("Add/Delete Roll :");

        jLabel59.setText("Add/Delete Year :");

        jLabel60.setText("Year");

        jButton34.setText("Add Year");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton35.setText("Batch List");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        batchlistTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane15.setViewportView(batchlistTable);

        jLabel61.setText("Admin Password Change :");

        jLabel62.setText("Current Password : ");

        jLabel63.setText("New Password :");

        jButton37.setText("Change");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.setText("Change");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jLabel64.setText("New Password :");

        jLabel65.setText("Current Password : ");

        jLabel66.setText("Modaretor Password Change : ");

        jLabel74.setText("Add/Delete  Roll Year :");

        jLabel75.setText("Roll  Year :");

        jButton41.setText("Add Roll");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jLabel76.setText("Add/Delete  Payment For : ");

        jLabel77.setText("Payment For :");

        jButton42.setText("Add PF");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.setText("Delete Batch");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton44.setText("Delete Subject");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jButton45.setText("Delete  Roll");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jButton46.setText("Delete Roll");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        jButton47.setText("Delete Year");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton48.setText("Delete  PF");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        jLabel81.setText("Payment Id :");

        jButton1.setText("Want to update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel82.setText("Roll :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student", "Academic Teacher", "Tutor" }));

        jLabel83.setText("UserType:");

        jLabel84.setText("Payment For");

        jLabel85.setText("Year");

        jLabel86.setText("Amount");

        jLabel87.setText("Recived By");

        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel94.setText("Add/Delete  School : ");

        jLabel95.setText("School :");

        jButton50.setText("Add School");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jButton51.setText("Delete School");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jLabel96.setText("Add/Delete  Class : ");

        jLabel97.setText("class :");

        jButton52.setText("Add Class");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        jButton53.setText("Delete Class");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        batchlistTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane16.setViewportView(batchlistTable1);

        jButton7.setText("Check BirthDay :");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(106, 106, 106))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(addBatchName))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(addSubjectToBatch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(106, 106, 106))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(newRollFirst))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newYear))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(86, 86, 86))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputNewSubject))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jButton35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(69, 69, 69))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jButton42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newPaymentFor))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(73, 73, 73))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newRollSec))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jButton41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel84, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel87))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(upPP)
                            .addComponent(upPaymentId)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newPaymentFor2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(newPaymentFor1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel94, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(mcpn))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel62)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cpc))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel65)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(mcpc))
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cpn))
                        .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(jButton35))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel53)
                                    .addComponent(addBatchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel54)
                                    .addComponent(addSubjectToBatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton31)
                                    .addComponent(jButton43))
                                .addGap(47, 47, 47)
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel56)
                                    .addComponent(inputNewSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton32)
                                    .addComponent(jButton44)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel57)
                                    .addComponent(newRollFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton33)
                                    .addComponent(jButton45)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel74)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel75)
                                    .addComponent(newRollSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton41)
                                    .addComponent(jButton46))))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel60)
                                    .addComponent(newYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton34)
                                    .addComponent(jButton47)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel76)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel77)
                                    .addComponent(newPaymentFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton42)
                                    .addComponent(jButton48)))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel61)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(upPaymentId, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel62)
                                .addComponent(cpc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel81)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(cpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(upPP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton37)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel82))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel83))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel84))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel85))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel86))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel87))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel94)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel95)
                                    .addComponent(newPaymentFor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton50)
                                    .addComponent(jButton51))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel96)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel97)
                                    .addComponent(newPaymentFor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton52)
                                    .addComponent(jButton53)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addGap(1, 1, 1)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel65)
                                    .addComponent(mcpc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel64)
                                    .addComponent(mcpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton38)
                                .addGap(11, 11, 11)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(186, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Setting", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tschoolnamefieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tschoolnamefieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tschoolnamefieldActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        exportTable(paidlisttable,"BatchPaymentHistory.xlsx");
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        exportTable(batchsltable,"BatchList.xlsx");
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        exportTable(ttlisttable,"TeacherList.xlsx");
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
       exportTable(ttpl,"TeacherPaymentList.xlsx");
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        exportTable(incomeTable,"IncomeList.xlsx");
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
    exportTable(expenseTable,"IncomeList.xlsx");
    }//GEN-LAST:event_jButton30ActionPerformed

    private void addstudentbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addstudentbuttonActionPerformed
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dDate = dobfield.getDate();
        String schoolNameM = jTextField4.getText();
        String studentName = studentnamefield.getText();
        String schoolNamed = jComboBox4.getSelectedItem().toString();
        String rollFirst = rollfirstcombo.getSelectedItem().toString();
        String rollSec = rollseccombo.getSelectedItem().toString();
        String rollNumber = rolllnumber.getText();
        String className = jComboBox5.getSelectedItem().toString();
        String fatherName = fathernamefield.getText();
        String fatherMobile = fathermobilefield.getText();
        String motherName = mothernamefield.getText();
        String motherMobile = mothermobilefield.getText();
        String subjectName = subjectcombo.getSelectedItem().toString();
        String batchName = batchcombo.getSelectedItem().toString();
        String addmissionYear = yearcombo.getSelectedItem().toString();
        String addmissionMonth = monthcombo.getSelectedItem().toString();
        String address = saddressfield.getText();
        String DOB = dateFormat.format(dDate);
        String AddBy = fathernamefield1.getText();
        String roll = rollFirst+rollSec+rollNumber;
        String schoolName;
        if(schoolNameM.length()>2){
            schoolName=schoolNameM;
        }
        else
        {
            schoolName=schoolNamed;
        }
        if(studentName.isEmpty()|| schoolName.isEmpty() || rollNumber.isEmpty() || className.isEmpty() || fatherName.isEmpty() || fatherMobile.length()!=13 || motherName.isEmpty() || motherMobile.length()!=13 || subjectName.isEmpty() || batchName.isEmpty() || addmissionYear.isEmpty() || address.isEmpty() || DOB.isEmpty() || AddBy.isEmpty() ){
            JOptionPane.showMessageDialog(null, "Please Fill Properly...");
        }
        else {
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO STUDENT(roll,name,schoolName,class,fatherName,fatherMobile,motherName,motherMobile,subjectName,batchName,addmissionYear,month,address,DOB,AddBy) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, roll);
            ps.setString(2, studentName);
            ps.setString(3, schoolName);
            ps.setString(4, className);
            ps.setString(5, fatherName);
            ps.setString(6, fatherMobile);
            ps.setString(7, motherName);
            ps.setString(8, motherMobile);
            ps.setString(9, subjectName);
            ps.setString(10, batchName);
            ps.setString(11, addmissionYear);
            ps.setString(12, addmissionMonth);
            ps.setString(13, address);
            ps.setString(14, DOB);
            ps.setString(15, AddBy);
            ps.execute();
            
            studentnamefield.setText("");
            rolllnumber.setText("");
            fathernamefield.setText("");
            fathermobilefield.setText("");
            mothernamefield.setText("");
            mothermobilefield.setText("");
            saddressfield.setText("");
            //jTextField4.setText("");
            dobfield.setDate(null);
            String PanelData = "Roll :"+roll+"\nName :"+studentName+"\nSchool :"+schoolName +"\nClass :"+className+"\nSubject :"+subjectName+"\nBatch :"+batchName+"\n";
            studentInfoPanel.setText(PanelData);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        }
    }//GEN-LAST:event_addstudentbuttonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String rollFirst = rollfirstcombo1.getSelectedItem().toString();
        String rollSec = rollseccombo1.getSelectedItem().toString();
        String roll = rollFirst+rollSec;
        int totalStudent = 0 ;
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM STUDENT WHERE roll LIKE ?");
            ps.setString(1, roll+"%");
            ResultSet rs = ps.executeQuery();
            studentinfotable.setModel(DbUtils.resultSetToTableModel(rs));
            totalStudent = studentinfotable.getRowCount();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        jTextPane1.setText("Total Student : "+totalStudent);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dDate = tdobfield.getDate();
        String studentName = tstudentnamefield.getText();
        String schoolName = tschoolnamefield.getText();
        String rollFirst = trollfirstcombo.getSelectedItem().toString();
        String rollSec = trollseccombo.getSelectedItem().toString();
        String rollNumber = trolllnumber.getText();
        String className = tclassfield.getText();
        String fatherName = tfathernamefield.getText();
        String fatherMobile = tfathermobilefield.getText();
        String motherName = tmothernamefield.getText();
        String motherMobile = tmothermobilefield.getText();
        String subjectName = tsubjectcombo.getText();
        String type = tbatchcombo.getSelectedItem().toString();
        String addmissionYear = tyearcombo.getSelectedItem().toString();
        String addmissionMonth = tmonthcombo.getSelectedItem().toString();
        String address = tsaddressfield.getText();
        String DOB = dateFormat.format(dDate);
        String AddBy = fathernamefield2.getText();
        String roll = rollFirst+rollSec+rollNumber;
        if(studentName.isEmpty()|| schoolName.isEmpty() || rollNumber.isEmpty() || className.isEmpty() || fatherName.isEmpty() || fatherMobile.length()!=13 || motherName.isEmpty() || motherMobile.length()!=13 || subjectName.isEmpty() || type.isEmpty() || addmissionYear.isEmpty() || address.isEmpty() || DOB.isEmpty()||AddBy.isEmpty() ){
            JOptionPane.showMessageDialog(null, "Please Fill Properly...");
        }
        else {
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO TEACHER(roll,name,instituteName,academicYear,fatherName,fatherMobile,motherName,motherMobile,subjectName,type,joiningYear,month,address,DOB,AddBy) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, roll);
            ps.setString(2, studentName);
            ps.setString(3, schoolName);
            ps.setString(4, className);
            ps.setString(5, fatherName);
            ps.setString(6, fatherMobile);
            ps.setString(7, motherName);
            ps.setString(8, motherMobile);
            ps.setString(9, subjectName);
            ps.setString(10, type);
            ps.setString(11, addmissionYear);
            ps.setString(12, addmissionMonth);
            ps.setString(13, address);
            ps.setString(14, DOB);
            ps.setString(15, AddBy);
            ps.execute();
            
            String PanelData = "Roll :"+roll+"\nName :"+studentName+"\nSchool :"+schoolName +"\nClass :"+className+"\nSubject :"+subjectName+"\nType :"+type+"\n";
            teacherDataPanel.setText(PanelData);
            tdobfield.setDate(null);
            tstudentnamefield.getText();
            tschoolnamefield.setText("");
            trolllnumber.setText("");
            tclassfield.setText("");
            tfathernamefield.setText("");
            tfathermobilefield.setText("");
            tmothernamefield.setText("");
            tmothermobilefield.setText("");
            tsubjectcombo.setText("");
            tsaddressfield.setText("");
            fathernamefield2.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT roll,name,instituteName,academicYear,subjectName,type,joiningYear,address FROM TEACHER");
            ResultSet rs = ps.executeQuery();
            teacherinfotable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String apiKey = "$2y$10$T79EXsGv5Dfc47nHCXrggultAe2H3VzzIsLlB7xN5uPNFQ14MKmFu122";
        String senderId = "8809612442500";
        String transactionType = "T";
        String campaignId = "T";
        String rollFirst = aprf.getSelectedItem().toString();
        String rollSec = aprs.getSelectedItem().toString();
        String rollNumber = aprn.getText();
        String userType = aput.getSelectedItem().toString();
        String paymentFor = appf.getSelectedItem().toString();
        String paymentYear = apy.getSelectedItem().toString();
        String amount = apa.getText();
        String receivedBy = aprb.getText();
        String roll = rollFirst+rollSec+rollNumber;
        String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm-ss-a");
        String formattedTime = localTime.format(formatter);
        String paymentType = "";
        if(userType == "Student"){
            paymentType = "Income";
        }
        else if(userType == "Academic Teacher"){
            paymentType = "Expense";
        }
        
        else if(userType == "Tutor"){
            paymentType = "Income";
        }

        if(rollNumber.isEmpty() || userType.isEmpty() || paymentFor.isEmpty() ||paymentYear.isEmpty() || amount.isEmpty() ){
            JOptionPane.showMessageDialog(null, "Please Fill Properly...");
        }
        else {
        try{
            Connection conn = getConnection();
            String getLastIDQuery = "SELECT MAX(id) FROM PAYMENTINFO";
            PreparedStatement getLastIDStmt = conn.prepareStatement(getLastIDQuery);
            ResultSet rsi = getLastIDStmt.executeQuery();
            int lastID = 0;
            if (rsi.next()) {
                lastID = rsi.getInt(1);
            }
            System.out.print(lastID);
            int newID = lastID + 1;
            PreparedStatement ps = conn.prepareStatement("INSERT INTO PAYMENTINFO(id,roll,userType,paymentFor,yearName,localDate,time,amount,paymentType,receivedBy) VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, newID);
            ps.setString(2, roll);
            ps.setString(3, userType);
            ps.setString(4, paymentFor);
            ps.setString(5, paymentYear);
            ps.setString(6, currentdate);
            ps.setString(7, formattedTime);
            ps.setString(8, amount);
            ps.setString(9, paymentType);
            ps.setString(10, receivedBy);
            ps.execute();
            aprn.setText("");
            apa.setText("");
            aprb.setText("");
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        try{
            Connection conn = getConnection();
            PreparedStatement ps;
            if(userType == "Student"){
                ps = conn.prepareStatement("SELECT name,schoolName,fatherName,fatherMobile,class,subjectName,PAYMENTINFO.id FROM STUDENT JOIN PAYMENTINFO ON STUDENT.roll = PAYMENTINFO.roll WHERE STUDENT.roll=? AND PAYMENTINFO.paymentFor= ? AND PAYMENTINFO.yearName=? AND PAYMENTINFO.localDate=? AND PAYMENTINFO.amount=?");
            }
            else{
                ps = conn.prepareStatement("SELECT name,instituteName,fatherName,fatherMobile,academicYear,subjectName,PAYMENTINFO.id FROM TEACHER JOIN PAYMENTINFO ON TEACHER.roll = PAYMENTINFO.roll WHERE TEACHER.roll=? AND PAYMENTINFO.paymentFor= ? AND PAYMENTINFO.yearName=? AND PAYMENTINFO.localDate=? AND PAYMENTINFO.amount=?");
            }
            ps.setString(1, roll);
            ps.setString(2, paymentFor);
            ps.setString(3, paymentYear);
            ps.setString(4, currentdate);
            ps.setString(5, amount);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String PrintData = "\n\n\n\nPayment of "+ paymentFor +"/"+paymentYear+"\nDate : "+currentdate +"\nTime : "+formattedTime+"\n\nReceipt No. : "+rs.getString(7)+"\nRoll : "+roll+"\nName : "+rs.getString(1)+"\nFather : "+rs.getString(3)+"\nSchool/Institute : "+rs.getString(2)+"\nClass: "+rs.getString(5)+"\nSubject : "+rs.getString(6)+"\nAmount : "+amount+"\n\nReceived By : "+receivedBy;
                jTextArea3.setText(PrintData);
                String Message = "Payment of "+ paymentFor +"/"+paymentYear+"\nDate : "+currentdate +"\nTime : "+formattedTime+"\n\nReceipt No. : "+rs.getString(7)+"\nRoll : "+roll+"\nName : "+rs.getString(1)+"\nAmount : "+amount+" BDT\nReceived, Thank You.";
            }
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void aputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aputActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_aputActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id,roll,userType,paymentFor,localDate,time,amount FROM PAYMENTINFO where paymentType ='Income'");
            ResultSet rs = ps.executeQuery();
            incometable.setModel(DbUtils.resultSetToTableModel(rs));
            double total = calculateColumnTotal(incometable, 6);
            jTextPane2.setText("Total Income :"+total);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String searchText = searchRP.getText();
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT roll , name FROM STUDENT WHERE roll = ? or fatherMobile = ? or motherMobile = ?");
            ps.setString(1, searchText);
            ps.setString(2, searchText);
            ps.setString(3, searchText);
            ResultSet rs = ps.executeQuery();
            searchResult.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        String roll = updateRollfield.getText();
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT roll,name,schoolName,class,fatherName,fatherMobile,motherName,motherMobile,subjectName,batchName FROM STUDENT WHERE roll = ?");
            ps.setString(1, roll);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String batchName = rs.getString("batchName");
                String subjectName = rs.getString("subjectName");
                updateClassTextField.setSelectedItem(rs.getString("class"));
                 updateSchoolTextField.setSelectedItem(rs.getString("schoolName"));
                updateFatherMobileTextField.setText(rs.getString("fatherMobile"));
                updateFatherNameTextField.setText(rs.getString("fatherName"));
                updateMotherMobileTextField.setText(rs.getString("motherMobile"));
                updateMotherNameTextField.setText(rs.getString("motherName"));
                updateNameTextField.setText(rs.getString("name"));
                updateRollfield.setText(rs.getString("roll"));
                updateSubjectCombo.removeAllItems();
                updateSubjectCombo.addItem(subjectName);
                updateBatchCombo.removeAllItems();
                updateBatchCombo.addItem(batchName);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }       
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        String roll = updateRollfield.getText();
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE STUDENT SET name=?,schoolName=?,class=?,fatherName=?,fatherMobile=?,motherName=?,motherMobile=?,subjectName=?,batchName=? WHERE roll = ?");
            ps.setString(1, updateNameTextField.getText());
            ps.setString(2, updateSchoolTextField.getSelectedItem().toString());
            ps.setString(3, updateClassTextField.getSelectedItem().toString());
            ps.setString(4, updateFatherNameTextField.getText());
            ps.setString(5, updateFatherMobileTextField.getText());
            ps.setString(6, updateMotherNameTextField.getText());
            ps.setString(7, updateMotherMobileTextField.getText());
            ps.setString(8, updateSubjectCombo.getSelectedItem().toString());
            ps.setString(9, updateBatchCombo.getSelectedItem().toString());
            ps.setString(10, roll);
            ps.execute();
            updateFatherMobileTextField.setText("");
            updateFatherNameTextField.setText("");
            updateMotherMobileTextField.setText("");
            updateMotherNameTextField.setText("");
            updateNameTextField.setText("");
            updateRollfield.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        String roll = plsearchbox.getText();
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT paymentFor,yearName,localDate,time,amount FROM PAYMENTINFO WHERE roll= ?");
            ps.setString(1, roll);
            ResultSet rs = ps.executeQuery();
            paymentlisttabe.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        String batchName = batchslcombo.getSelectedItem().toString();
        String subjectName = batchslcombosub.getSelectedItem().toString();
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT roll,name FROM STUDENT WHERE batchName= ? AND subjectName=?");
            ps.setString(1, batchName);
            ps.setString(2, subjectName);
            ResultSet rs = ps.executeQuery();
            batchsltable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        String subjectName = batchslcombosub.getSelectedItem().toString();
        String batchName = batchslcombo.getSelectedItem().toString();
        String paymentFor = pfplcombo.getSelectedItem().toString();
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT STUDENT.roll,name,paymentFor,amount  FROM STUDENT JOIN PAYMENTINFO ON STUDENT.roll = PAYMENTINFO.roll WHERE batchName = ? AND subjectName=? AND paymentFor = ?");
            ps.setString(1, batchName);
            ps.setString(2, subjectName);
            ps.setString(3, paymentFor);
            ResultSet rs = ps.executeQuery();
            paidlisttable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        String roll = updateRollfield.getText();
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE STUDENT SET subjectName=?,batchName=? WHERE roll = ?");
            ps.setString(1, "");
            ps.setString(2, "");
            ps.setString(3, roll);
            ps.execute();
            updateSubjectCombo.removeAllItems();
            PreparedStatement ps2 = conn.prepareStatement("SELECT subjectName FROM SUBJECT");
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                updateSubjectCombo.addItem(rs2.getString(1));
            }
            setBatch(updateSubjectCombo.getSelectedItem().toString(),updateBatchCombo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        String type = teachertypeLC.getSelectedItem().toString();
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT roll,name,instituteName,academicYear,type  FROM TEACHER WHERE type = ?");
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            ttlisttable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
        
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        String type = ttdlcb.getSelectedItem().toString();
        String paymentFor = ttdlpfcb.getSelectedItem().toString();
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT TEACHER.roll,name,instituteName,academicYear,amount FROM TEACHER JOIN PAYMENTINFO ON TEACHER.roll = PAYMENTINFO.roll WHERE TEACHER.type = ? AND PAYMENTINFO.paymentFor=?");
            ps.setString(1, type);
            ps.setString(2, paymentFor);
            ResultSet rs = ps.executeQuery();
            ttpl.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ftDate = fromDate.getDate();
        Date ttDate = toDate.getDate();
        String fDate = dateFormat.format(ftDate);
        String tDate = dateFormat.format(ttDate);
        String adminPass = adminPassForDC.getText().toString();
        if(adminPass.equals(getPass("anu"))){
            try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id,roll,paymentFor,localDate,amount FROM PAYMENTINFO WHERE localDate BETWEEN ? AND ? AND paymentType = 'Income'");
            PreparedStatement ps2 = conn.prepareStatement("SELECT id,roll,paymentFor,localDate,amount FROM PAYMENTINFO WHERE localDate BETWEEN ? AND ? AND paymentType = 'Expense'");
            ps.setString(1, fDate);
            ps.setString(2, tDate);
            ps2.setString(1, fDate);
            ps2.setString(2, tDate);
            ResultSet rs = ps.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            System.out.print(fDate);
            System.out.print(tDate);
            incomeTable.setModel(DbUtils.resultSetToTableModel(rs));
            expenseTable.setModel(DbUtils.resultSetToTableModel(rs2));
            double totalIncome = calculateColumnTotal(incomeTable, 4);
            double totalExpense = calculateColumnTotal(expenseTable, 4);
            jTextPane3.setText("Total Income :"+totalIncome);
            jTextPane4.setText("Total Income :"+totalExpense);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void subjectcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectcomboActionPerformed
        
    }//GEN-LAST:event_subjectcomboActionPerformed

    private void subjectcomboPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_subjectcomboPopupMenuWillBecomeInvisible
       setBatch(subjectcombo.getSelectedItem().toString(),batchcombo);
    }//GEN-LAST:event_subjectcomboPopupMenuWillBecomeInvisible

    private void updateSubjectComboPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_updateSubjectComboPopupMenuWillBecomeInvisible
        setBatch(updateSubjectCombo.getSelectedItem().toString(),updateBatchCombo);
    }//GEN-LAST:event_updateSubjectComboPopupMenuWillBecomeInvisible

    private void batchslcombosubPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_batchslcombosubPopupMenuWillBecomeInvisible
        setBatch(batchslcombosub.getSelectedItem().toString(),batchslcombo);
    }//GEN-LAST:event_batchslcombosubPopupMenuWillBecomeInvisible

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        String roll = updateRollfield.getText();
        String subjectName = updateSubjectCombo.getSelectedItem().toString();
        String batchName = updateBatchCombo.getSelectedItem().toString();
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE STUDENT SET subjectName=?,batchName=? WHERE roll = ?");
            ps.setString(1, subjectName);
            ps.setString(2, batchName);
            ps.setString(3, roll);
            ps.execute();
            PreparedStatement ps2 = conn.prepareStatement("SELECT subjectName FROM SUBJECT");
            ResultSet rs2 = ps2.executeQuery();
            updateSubjectCombo.removeAllItems();
            while(rs2.next()){
                updateSubjectCombo.addItem(rs2.getString(1));
            }
            setBatch(updateSubjectCombo.getSelectedItem().toString(),updateBatchCombo);
            updateFatherMobileTextField.setText("");
            updateFatherNameTextField.setText("");
            updateMotherMobileTextField.setText("");
            updateMotherNameTextField.setText("");
            updateNameTextField.setText("");
            updateRollfield.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void updateSubjectComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSubjectComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateSubjectComboActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        String adminPass = adPas.getText();
        if(adminPass.equals(getPass("anu"))){
        exportTable(studentinfotable,"StudentList.xlsx");
        }
        else{
            JOptionPane.showMessageDialog(null, "No Access");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
                exportTable(teacherinfotable,"TeacherList.xlsx");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        
                exportTable(incometable,"TodaysIncome.xlsx");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        
                exportTable(paymentlisttabe,"PaymentHistory.xlsx");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
                // Print the contents of the JTextArea
                jTextArea3.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void batchslcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchslcomboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batchslcomboActionPerformed

    private void batchslcombosubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchslcombosubActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batchslcombosubActionPerformed

    private void apaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apaKeyTyped
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_apaKeyTyped

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        String name = newPaymentFor.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM PAYMENTFOR WHERE paymentName=?");
            ps.setString(1, name);
            ps.executeUpdate();
            newPaymentFor.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        String name = newYear.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM YEARNAME WHERE name=?");
            ps.setString(1, name);
            ps.executeUpdate();
            newYear.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        String name = newRollSec.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
            try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM ROLLMID WHERE name=?");
            ps.setString(1, name);
            ps.executeUpdate();
            newRollSec.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
        
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        String name = newRollFirst.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
            try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM ROLLFIRST WHERE name=?");
            ps.setString(1, name);
            ps.executeUpdate();
            newRollFirst.setText("");
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        String subjectName = inputNewSubject.getText();
        if(subjectName.isEmpty()){
            JOptionPane.showMessageDialog(null,"Error");
        }
        else{
            try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM SUBJECT WHERE subjectName=?");
            ps.setString(1, subjectName);
            ps.executeUpdate();
            inputNewSubject.setText("");
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        String batchName = addBatchName.getText();
        String subjectName = addSubjectToBatch.getSelectedItem().toString();
        if(batchName.isEmpty()||subjectName.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
            try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM BATCH WHERE batchName=? AND subjectName= ?");
            ps.setString(1, batchName);
            ps.setString(2, subjectName);
            ps.executeUpdate();
            addBatchName.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
        }
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed

        String name = newPaymentFor.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO PAYMENTFOR(paymentName) VALUES(?)");
            ps.setString(1, name);
            ps.execute();
            newPaymentFor.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed

        String name = newRollSec.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
            try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ROLLMID(name) VALUES(?)");
            ps.setString(1, name);
            ps.execute();
            newRollSec.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        String currentPass = mcpc.getText().toString();
        String newPass = mcpn.getText().toString();
        if(currentPass == getPass("mordaretor")){
            try{
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement("UPDATE MORDARETORPASSWORD SET password = ? WHERE name='mordaretor'");
                ps.setString(1, newPass);
                ps.executeUpdate();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.print(e);
            }
        }
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        String currentPass = cpc.getText();
        String newPass = cpn.getText();
        if(currentPass.equals(getPass("anu").toString())){
            try{
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement("UPDATE ADMINPASSWORD SET password = ? WHERE name='anu'");
                ps.setString(1, newPass);
                ps.executeUpdate();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.print(e);
            }
        }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed

        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT batchName,subjectName FROM BATCH");
            ResultSet rs = ps.executeQuery();
            batchlistTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }

    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        
        String name = newYear.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
             try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO YEARNAME(name) VALUES(?)");
            ps.setString(1, name);
            ps.execute();
            newYear.setText("");
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        String name = newRollFirst.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
            try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ROLLFIRST(name) VALUES(?)");
            ps.setString(1, name);
            ps.execute();
            newRollFirst.setText("");
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        String subjectName = inputNewSubject.getText();
        if(subjectName.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
            try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO SUBJECT(subjectName) VALUES(?)");
            ps.setString(1, subjectName);
            ps.execute();
            inputNewSubject.setText("");
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        String batchName = addBatchName.getText();
        String subjectName = addSubjectToBatch.getSelectedItem().toString();
        if(batchName.isEmpty()||subjectName.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
            try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO BATCH(batchName,subjectName) VALUES(?,?)");
            ps.setString(1, batchName);
            ps.setString(2, subjectName);
            ps.execute();
            addBatchName.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String id = upPaymentId.getText();
        String pass = upPP.getText();
        String rPass = getPass("anu");
        if(pass == null ? rPass == null : pass.equals(rPass)){
            try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT roll,userType,paymentFor,yearName,amount,receivedBy FROM PAYMENTINFO WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                jTextField1.setText(rs.getString("roll"));
                jTextField2.setText(rs.getString("amount"));
                jTextField3.setText(rs.getString("receivedBy"));
                jComboBox1.setSelectedItem(rs.getString("userType"));
                jComboBox2.setSelectedItem(rs.getString("paymentFor"));
                jComboBox3.setSelectedItem(rs.getString("yearName"));
            }
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String id = upPaymentId.getText();
        String pass = upPP.getText();
        String rPass = getPass("anu");
        if(pass == null ? rPass == null : pass.equals(rPass)){
            try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE PAYMENTINFO SET roll=?,userType=?,paymentFor=?,yearName=?,amount=?,receivedBy=? WHERE id = ?");
            ps.setString(1, jTextField1.getText());
            ps.setString(2, jComboBox1.getSelectedItem().toString());
            ps.setString(3, jComboBox2.getSelectedItem().toString());
            ps.setString(4, jComboBox3.getSelectedItem().toString());
            ps.setString(5, jTextField2.getText());
            ps.setString(6, jTextField3.getText());
            ps.setString(7, id);
            ps.execute();
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            upPaymentId.setText("");
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        String expensename = eEN.getText();
        String expenseamount = eEA.getText();
        String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm-ss-a");
        String formattedTime = localTime.format(formatter);
        if(expensename.isEmpty()||expenseamount.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
        try{
            Connection conn = getConnection();
            String getLastIDQuery = "SELECT MAX(id) FROM PAYMENTINFO";
            PreparedStatement getLastIDStmt = conn.prepareStatement(getLastIDQuery);
            ResultSet rsi = getLastIDStmt.executeQuery();
            int lastID = 0;
            if (rsi.next()) {
                lastID = rsi.getInt(1);
            }
            System.out.print(lastID);
            int newID = lastID + 1;
            PreparedStatement ps = conn.prepareStatement("INSERT INTO PAYMENTINFO(id,roll,userType,paymentFor,yearName,localDate,time,amount,paymentType,receivedBy) VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, newID);
            ps.setString(2, "extra");
            ps.setString(3, "extra");
            ps.setString(4, expensename);
            ps.setString(5, "extra");
            ps.setString(6, currentdate);
            ps.setString(7, formattedTime);
            ps.setString(8, expenseamount);
            ps.setString(9, "Expense");
            ps.setString(10, "extra");
            ps.execute();
            eEN.setText("");
            eEA.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
        }
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        String expensename = expenseName.getText();
        String expenseamount = expenseAmount.getText();
        String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm-ss-a");
        String formattedTime = localTime.format(formatter);
        if(expensename.isEmpty()||expenseamount.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO PAYMENTINFO(roll,userType,paymentFor,yearName,localDate,time,amount,paymentType,receivedBy) VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, "extra");
            ps.setString(2, "extra");
            ps.setString(3, expensename);
            ps.setString(4, "extra");
            ps.setString(5, currentdate);
            ps.setString(6, formattedTime);
            ps.setString(7, expenseamount);
            ps.setString(8, "Income");
            ps.setString(9, "extra");
            ps.execute();
            eEN.setText("");
            eEA.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
        }
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        String name = newPaymentFor1.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO SCHOOL(schoolName) VALUES(?)");
            ps.setString(1, name);
            ps.execute();
            newPaymentFor1.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        String name = newPaymentFor1.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM SCHOOL WHERE schoolName=?");
            ps.setString(1, name);
            ps.executeUpdate();
            newPaymentFor1.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        String name = newPaymentFor2.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO CLASS(className) VALUES(?)");
            ps.setString(1, name);
            ps.execute();
            newPaymentFor2.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        String name = newPaymentFor2.getText();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error");
        }
        else{
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM CLASS WHERE className=?");
            ps.setString(1, name);
            ps.executeUpdate();
            newPaymentFor2.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT roll,name FROM STUDENT WHERE DOB = ?");
            ps.setString(1, currentdate);
            ResultSet rs = ps.executeQuery();
            batchlistTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void plsearchboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plsearchboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plsearchboxActionPerformed

    private void updateFatherMobileTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateFatherMobileTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateFatherMobileTextFieldActionPerformed

    private void updateMotherNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMotherNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateMotherNameTextFieldActionPerformed

    private void adminPassForDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminPassForDCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminPassForDCActionPerformed

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
            java.util.logging.Logger.getLogger(mainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adPas;
    private javax.swing.JTextField addBatchName;
    private javax.swing.JComboBox<String> addSubjectToBatch;
    private javax.swing.JButton addstudentbutton;
    private javax.swing.JPasswordField adminPassForDC;
    private javax.swing.JTextField apa;
    private javax.swing.JComboBox<String> appf;
    private javax.swing.JTextField aprb;
    private javax.swing.JComboBox<String> aprf;
    private javax.swing.JTextField aprn;
    private javax.swing.JComboBox<String> aprs;
    private javax.swing.JComboBox<String> aput;
    private javax.swing.JComboBox<String> apy;
    private javax.swing.JComboBox<String> batchcombo;
    private javax.swing.JTable batchlistTable;
    private javax.swing.JTable batchlistTable1;
    private javax.swing.JComboBox<String> batchslcombo;
    private javax.swing.JComboBox<String> batchslcombosub;
    private javax.swing.JTable batchsltable;
    private javax.swing.JPasswordField cpc;
    private javax.swing.JPasswordField cpn;
    private com.toedter.calendar.JDateChooser dobfield;
    private javax.swing.JTextField eEA;
    private javax.swing.JTextField eEN;
    private javax.swing.JTextField expenseAmount;
    private javax.swing.JTextField expenseName;
    private javax.swing.JTable expenseTable;
    private javax.swing.JTextField fathermobilefield;
    private javax.swing.JTextField fathernamefield;
    private javax.swing.JTextField fathernamefield1;
    private javax.swing.JTextField fathernamefield2;
    private com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JTable incomeTable;
    private javax.swing.JTable incometable;
    private javax.swing.JTextField inputNewSubject;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JPasswordField mcpc;
    private javax.swing.JPasswordField mcpn;
    private javax.swing.JComboBox<String> monthcombo;
    private javax.swing.JTextField mothermobilefield;
    private javax.swing.JTextField mothernamefield;
    private javax.swing.JTextField newPaymentFor;
    private javax.swing.JTextField newPaymentFor1;
    private javax.swing.JTextField newPaymentFor2;
    private javax.swing.JTextField newRollFirst;
    private javax.swing.JTextField newRollSec;
    private javax.swing.JTextField newYear;
    private javax.swing.JTable paidlisttable;
    private javax.swing.JTable paymentlisttabe;
    private javax.swing.JComboBox<String> pfplcombo;
    private javax.swing.JTextField plsearchbox;
    private javax.swing.JComboBox<String> rollfirstcombo;
    private javax.swing.JComboBox<String> rollfirstcombo1;
    private javax.swing.JTextField rolllnumber;
    private javax.swing.JComboBox<String> rollseccombo;
    private javax.swing.JComboBox<String> rollseccombo1;
    private javax.swing.JTextField saddressfield;
    private javax.swing.JTextField searchRP;
    private javax.swing.JTable searchResult;
    private javax.swing.JTextArea studentInfoPanel;
    private javax.swing.JTable studentinfotable;
    private javax.swing.JTextField studentnamefield;
    private javax.swing.JComboBox<String> subjectcombo;
    private javax.swing.JComboBox<String> tbatchcombo;
    private javax.swing.JTextField tclassfield;
    private com.toedter.calendar.JDateChooser tdobfield;
    private javax.swing.JTextArea teacherDataPanel;
    private javax.swing.JTable teacherinfotable;
    private javax.swing.JComboBox<String> teachertypeLC;
    private javax.swing.JTextField tfathermobilefield;
    private javax.swing.JTextField tfathernamefield;
    private javax.swing.JComboBox<String> tmonthcombo;
    private javax.swing.JTextField tmothermobilefield;
    private javax.swing.JTextField tmothernamefield;
    private com.toedter.calendar.JDateChooser toDate;
    private javax.swing.JComboBox<String> trollfirstcombo;
    private javax.swing.JTextField trolllnumber;
    private javax.swing.JComboBox<String> trollseccombo;
    private javax.swing.JTextField tsaddressfield;
    private javax.swing.JTextField tschoolnamefield;
    private javax.swing.JTextField tstudentnamefield;
    private javax.swing.JTextField tsubjectcombo;
    private javax.swing.JComboBox<String> ttdlcb;
    private javax.swing.JComboBox<String> ttdlpfcb;
    private javax.swing.JTable ttlisttable;
    private javax.swing.JTable ttpl;
    private javax.swing.JComboBox<String> tyearcombo;
    private javax.swing.JPasswordField upPP;
    private javax.swing.JTextField upPaymentId;
    private javax.swing.JComboBox<String> updateBatchCombo;
    private javax.swing.JComboBox<String> updateClassTextField;
    private javax.swing.JTextField updateFatherMobileTextField;
    private javax.swing.JTextField updateFatherNameTextField;
    private javax.swing.JTextField updateMotherMobileTextField;
    private javax.swing.JTextField updateMotherNameTextField;
    private javax.swing.JTextField updateNameTextField;
    private javax.swing.JTextField updateRollfield;
    private javax.swing.JComboBox<String> updateSchoolTextField;
    private javax.swing.JComboBox<String> updateSubjectCombo;
    private javax.swing.JComboBox<String> yearcombo;
    // End of variables declaration//GEN-END:variables
}
