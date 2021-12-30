// code for creating a login framepage

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
// import java.sql.SQLException;
// import javax.swing.*;
// import java.awt.event.*;
// import java.awt.*;

public class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("EMAIL");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");

    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponents();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);

    }

    public void addComponents() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = String.valueOf(passwordField.getPassword());

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Creating Connection Object
                Connection connection = null;
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Katikamu", "root", "@qwe34rty");

                PreparedStatement ps;
                ResultSet rs;

                String query = "SELECT * FROM `users` WHERE `EMAIL` =? AND `PASSWRD` =?";
                ps = connection.prepareStatement(query);

                ps.setString(1, userText);
                ps.setString(2, pwdText);

                rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Welcome < " + userText + " >");

                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
                }

            } catch (Exception e1) {
                System.out.println(e1);
            }
        }

    }

}
