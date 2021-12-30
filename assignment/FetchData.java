// code to fetch stdent data and fetch student data based in their subjects

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FetchData implements ActionListener {

    JFrame frame1;// creating object of first JFrame
    JLabel nameLabel;// creating object of JLabel
    JTextField nameTextField;// creating object of JTextfield
    JButton fetchButton; // creating object of JButton
    JButton resetButton; // creating object of JButton

    JFrame frame2;// creating object of second JFrame
    DefaultTableModel defaultTableModel;// creating object of DefaultTableModel
    JTable table;// Creating object of JTable
    Connection connection;// Creating object of Connection class
    Statement statement;// Creating object of Statement class
    int flag = 0;

    FetchData() {

        frame1 = new JFrame();
        frame1.setTitle("Search Database");// setting the title of first JFrame
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// setting default close operation
        GridBagLayout bagLayout = new GridBagLayout();// creating object of GridBagLayout
        GridBagConstraints bagConstraints = new GridBagConstraints();// creating object of GridBagConstratints
        frame1.setSize(500, 300);// setting the size of first JFrame
        frame1.setLayout(bagLayout);// setting the layout to GridBagLayout of first JFrame

        bagConstraints.insets = new Insets(15, 40, 0, 0);// Setting the padding between the components and neighboring
                                                         // components

        // Setting the property of JLabel and adding it to the first JFrame
        nameLabel = new JLabel("Enter Filter subject");
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        frame1.add(nameLabel, bagConstraints);

        // Setting the property of JTextfield and adding it to the first JFrame
        nameTextField = new JTextField(15);
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 0;
        frame1.add(nameTextField, bagConstraints);

        // Setting the property of JButton(Fetch Data) and adding it to the first JFrame
        fetchButton = new JButton("Fetch Data");
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.ipadx = 60;
        frame1.add(fetchButton, bagConstraints);

        // Setting the property of JButton(Reset Data) and adding it to the second
        // JFrame
        resetButton = new JButton("Reset Data");
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 1;
        frame1.add(resetButton, bagConstraints);

        // adding ActionListener to both buttons
        fetchButton.addActionListener(this);
        resetButton.addActionListener(this);

        frame1.setVisible(true);// Setting the visibility of First JFrame
        frame1.validate();// Performing relayout of the First JFrame

    }

    public static void main(String[] args) {
        new FetchData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fetchButton) {

            String userName = nameTextField.getText().toString();// getting text of username text field and storing it
                                                                 // in a String variable
            frameSecond(userName);// passing the text value to frameSecond method

        }
        if (e.getSource() == resetButton) {
            nameTextField.setText("");// resetting the value of username text field
        }

    }

    public void frameSecond(String userName) {

        // setting the properties of second JFrame
        frame2 = new JFrame("Database Results");
        frame2.setLayout(new FlowLayout());
        frame2.setSize(400, 400);

        // Setting the properties of JTable and DefaultTableModel
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(300, 100));
        table.setFillsViewportHeight(true);
        frame2.add(new JScrollPane(table));
        defaultTableModel.addColumn("FIRSTNAME");
        defaultTableModel.addColumn("LASTNAME");
        defaultTableModel.addColumn("GENDER");
        defaultTableModel.addColumn("regno");
        defaultTableModel.addColumn("SUBJECT");
        defaultTableModel.addColumn("AGE");
        defaultTableModel.addColumn("CLASS");
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Katikamu", "root", "@qwe34rty");

            statement = connection.createStatement();// crating statement object
            String query = "select * from students where COURSE_SUBJECT = '" + userName + "'";// Storing MySQL query in
                                                                                              // A
            // string variable
            ResultSet resultSet = statement.executeQuery(query);// executing query and storing result in ResultSet

            while (resultSet.next()) {

                // Retrieving details from the database and storing it in the String variables
                String fname = resultSet.getString("FIRSTNAME");
                String lname = resultSet.getString("LASTNAME");
                String gen = resultSet.getString("GENDER");
                String reg = resultSet.getString("regno");
                String sub = resultSet.getString("COURSE_SUBJECT");
                int age = resultSet.getInt("AGE");
                String studclass = resultSet.getString("CLASS");
                if (userName.equalsIgnoreCase(sub)) {
                    flag = 1;
                    defaultTableModel.addRow(new Object[] { fname, lname, gen, reg, sub, age, studclass });// Adding row
                                                                                                           // in Table
                    frame2.setVisible(true); // Setting the visibility of second Frame
                    frame2.validate();
                    break;
                }

            }

            if (flag == 0) {
                String query1 = "SELECT * FROM students";
                ResultSet resultSet1 = statement.executeQuery(query1);
                while (resultSet1.next()) {
                    String fname1 = resultSet1.getString("FIRSTNAME");
                    String lname1 = resultSet1.getString("LASTNAME");
                    String gen1 = resultSet1.getString("GENDER");
                    String reg1 = resultSet1.getString("regno");
                    String sub1 = resultSet1.getString("COURSE_SUBJECT");
                    int age1 = resultSet1.getInt("AGE");
                    String studclass1 = resultSet1.getString("CLASS");
                    defaultTableModel.addRow(new Object[] { fname1, lname1, gen1, reg1, sub1, age1, studclass1 });// Adding
                                                                                                                  // row
                    // in Table
                    frame2.setVisible(true); // Setting the visibility of second Frame
                    frame2.validate();
                    break;
                }

                // JOptionPane.showMessageDialog(null, "No Such Subject Found");// When invalid
                // subject is entered
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}