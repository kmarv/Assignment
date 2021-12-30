//  creating a form for regisering student marks

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class RegistrationFormMarks implements ActionListener {
    JFrame frame;
    String[] coursesubject = { "Mathematics", "English", "Social Studies", "Science" };
    JLabel regnoLabel = new JLabel("REGISTRATION NUMBER");
    JLabel coursesubjectLabel = new JLabel("SUBJECT");
    JLabel scoreLabel = new JLabel("Score");

    JTextField regnoTextField = new JTextField();
    JComboBox coursesubjectComboBox = new JComboBox(coursesubject);
    JTextField scoreTextField = new JTextField();

    JButton registerButton = new JButton("REGISTER");
    JButton resetButton = new JButton("RESET");

    RegistrationFormMarks() {

        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }

    // Creating user-defined method
    public void createWindow() {
        // Setting properties of JFrame
        frame = new JFrame();
        frame.setTitle("Registration Form");
        frame.setBounds(40, 40, 380, 600);
        frame.getContentPane().setBackground(Color.pink);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void setLocationAndSize() {
        // Setting Location and Size of Each Component
        regnoLabel.setBounds(20, 20, 40, 70);
        coursesubjectLabel.setBounds(20, 70, 80, 70);
        scoreLabel.setBounds(20, 120, 100, 70);

        regnoTextField.setBounds(180, 43, 165, 23);
        coursesubjectComboBox.setBounds(180, 93, 165, 23);
        scoreTextField.setBounds(180, 143, 165, 23);
        registerButton.setBounds(70, 400, 100, 35);
        resetButton.setBounds(220, 400, 100, 35);
    }

    public void addComponentsToFrame() {
        // Adding components to Frame
        frame.add(regnoLabel);
        frame.add(coursesubjectLabel);
        frame.add(scoreLabel);
        frame.add(regnoTextField);
        frame.add(coursesubjectComboBox);
        frame.add(scoreTextField);
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
        int val = Integer.valueOf(scoreTextField.getText());
        if (e.getSource() == registerButton) {
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                // Creating Connection Object
                Connection connection = null;
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Katikamu", "root", "@qwe34rty");
                // Preapared Statement
                PreparedStatement Pstatement = connection.prepareStatement("insert into markstable values(?,?,?)");
                // Specifying the values of it's parameter
                Pstatement.setString(1, regnoTextField.getText());
                Pstatement.setString(2, coursesubjectComboBox.getSelectedItem().toString());
                Pstatement.setInt(3, val);

                Pstatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Registered Successfully");
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (Exception e2) {
                System.out.println(e2);
            }
        }

        if (e.getSource() == resetButton) {
            regnoTextField.setText("");
            coursesubjectComboBox.setSelectedItem("");
            scoreTextField.setText("");

        }
    }
}