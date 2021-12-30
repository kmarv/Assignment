
// code for registering a student
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;

public class RegistrationFormStud implements ActionListener {

    // Creating object of JFrame class
    JFrame frame;

    // Creating objects

    // (labels)
    String[] gender = { "---", "Male", "Female" };
    JLabel FnameLabel = new JLabel("FIRST NAME");
    JLabel LNameLabel = new JLabel("LAST NAME");
    JLabel genderLabel = new JLabel("GENDER");
    JLabel studclassLabel = new JLabel("Class");
    // JLabel emailLabel = new JLabel("EMAIL");
    String[] coursesubject = { "---", "English", "Mathematics", "SocialStudies", "Science" };
    JLabel coursesubjectLabel = new JLabel("Subject");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel RegnoLabel = new JLabel("Registrtion number");
    JLabel DOB = new JLabel("Date Of Birth");
    // Year Label and TextField
    JLabel Year = new JLabel("Year Born:");

    // (fields)
    JTextField FnameTextField = new JTextField();
    JTextField LNameTextField = new JTextField();
    JTextField studclassTextField = new JTextField();
    JComboBox genderComboBox = new JComboBox(gender);
    // JTextField emailTextField = new JTextField();
    JComboBox coursesubjectField = new JComboBox(coursesubject);
    JTextField YearText = new JTextField();
    // JPasswordField passwordField = new JPasswordField();
    JTextField RegnoField = new JTextField();

    JButton registerButton = new JButton("REGISTER");
    JButton resetButton = new JButton("RESET");

    // constructor
    RegistrationFormStud() {

        createWindow();
        setLocationAndSize();
        addComponents();
        actionEvent();
    }

    public void createWindow() {
        // properties of JFrame
        frame = new JFrame();
        frame.setTitle("Katikamu Registration Form");
        frame.setBounds(40, 40, 380, 600);
        frame.getContentPane().setBackground(Color.pink);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void setLocationAndSize() {
        // Setting Location and Size of Each Component
        FnameLabel.setBounds(20, 20, 100, 70);
        LNameLabel.setBounds(20, 70, 100, 70);
        Year.setBounds(20, 220, 100, 70);
        genderLabel.setBounds(20, 120, 80, 70);
        coursesubjectLabel.setBounds(20, 170, 80, 70);
        // passwordLabel.setBounds(20, 270, 100, 70);
        RegnoLabel.setBounds(20, 320, 140, 70);
        studclassLabel.setBounds(20, 220, 100, 70);

        FnameTextField.setBounds(180, 43, 165, 23);
        genderComboBox.setBounds(180, 143, 165, 23);
        LNameTextField.setBounds(180, 93, 165, 23);
        coursesubjectField.setBounds(180, 193, 165, 23);
        // passwordField.setBounds(180, 293, 165, 23);
        RegnoField.setBounds(180, 343, 165, 23);
        YearText.setBounds(180, 243, 165, 23);
        studclassTextField.setBounds(180, 243, 165, 23);
        registerButton.setBounds(70, 400, 100, 35);
        resetButton.setBounds(220, 400, 100, 35);
    }

    public void addComponents() {
        // Adding components to Frame

        // labels
        frame.add(FnameLabel);
        frame.add(LNameLabel);
        frame.add(genderLabel);
        frame.add(studclassLabel);
        frame.add(coursesubjectLabel);
        // frame.add(passwordLabel);
        frame.add(RegnoLabel);
        frame.add(Year);

        // fields
        frame.add(FnameTextField);
        frame.add(LNameTextField);
        frame.add(genderComboBox);
        frame.add(studclassTextField);
        frame.add(coursesubjectField);
        frame.add(YearText);
        // frame.add(passwordField);
        frame.add(RegnoField);
        frame.add(registerButton);
        frame.add(resetButton);
    }

    public void actionEvent() {
        // Adding Action Listener to buttons
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LocalDate currentdate = LocalDate.now();
        int currentYear = currentdate.getYear();
        int val = currentYear - Integer.valueOf(YearText.getText());

        if (e.getSource() == registerButton) {
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                // Creating Connection Object
                Connection connection = null;
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Katikamu", "root", "@qwe34rty");
                // Preapared Statement
                PreparedStatement Pstatement = connection
                        .prepareStatement("insert into students values(?,?,?,?,?,?,?)");
                // Specifying the values of it's parameter
                Pstatement.setString(1, FnameTextField.getText());
                Pstatement.setString(2, genderComboBox.getSelectedItem().toString());
                Pstatement.setString(3, LNameTextField.getText());
                // Pstatement.setString(4, passwordField.getText());
                Pstatement.setString(4, RegnoField.getText());
                Pstatement.setString(5, coursesubjectField.getSelectedItem().toString());
                Pstatement.setInt(6, val);
                Pstatement.setString(7, studclassTextField.getText());
                // Checking for the Password match
                // if (passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText()))
                // {
                // Executing query
                Pstatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Registered Successfully");
                // } else {
                // JOptionPane.showMessageDialog(null, "password did not match");
                // }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (Exception e2) {
                System.out.println(e2);
            }

        }
        if (e.getSource() == resetButton) {
            // Clearing Fields
            FnameTextField.setText("");
            genderComboBox.setSelectedItem("");
            LNameTextField.setText("");
            // passwordField.setText("");
            RegnoField.setText("");
            coursesubjectField.setSelectedItem("");
            // emailTextField.setText("");
        }

    }
}
