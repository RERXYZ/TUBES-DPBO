package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JPanel {
    public Register(MainFrame frame) {
        setLayout(new GridLayout(6, 2, 10, 10));

        JTextField usernameField = new JTextField();
        JTextField telpField = new JTextField();
        JTextField umurField = new JTextField();
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Laki-laki", "Perempuan"});
        JPasswordField passwordField = new JPasswordField();
        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Kembali");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Gender:"));
        add(genderBox);
        add(new JLabel("No. Telepon:"));
        add(telpField);
        add(new JLabel("Umur:"));
        add(umurField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(registerBtn);
        add(backBtn);

        registerBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String gender = (String) genderBox.getSelectedItem();
            String telp = telpField.getText();
            String umur = umurField.getText();
            String pass = new String(passwordField.getPassword());

            if (username.isEmpty() || gender.isEmpty() || telp.isEmpty() || umur.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                return;
            }

            JOptionPane.showMessageDialog(this, "Registrasi berhasil!");
        });

        backBtn.addActionListener(e -> frame.showMenu());
    }
}
