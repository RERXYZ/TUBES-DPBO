package GuiNew;
import javax.swing.*;

import Biz.*;
import Usr.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    private MainApp mainApp;
    private PelangganBiz pelangganBiz;
    
    public RegisterPanel(MainApp mainApp, PelangganBiz pelangganBiz) {
        this.mainApp = mainApp;
        this.pelangganBiz = pelangganBiz;
        
        initUI();
    }
    
    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Title
        JLabel titleLabel = new JLabel("Registrasi Pelanggan", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
        
        // Username
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("Username:"), gbc);
        
        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);
        
        // Gender
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Gender (L/P):"), gbc);
        
     // Ganti dari JTextField ke JComboBox
        String[] genderOptions = {"Laki-laki", "Perempuan"};
        JComboBox<String> genderComboBox = new JComboBox<>(genderOptions);
        genderComboBox.setPreferredSize(new Dimension(180, 25));
        gbc.gridx = 1;
        add(genderComboBox, gbc);
        
        // Phone
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(new JLabel("No. Telepon (xxxx-xxxx-xxxx):"), gbc);
        
        JTextField phoneField = new JTextField(20);
        gbc.gridx = 1;
        add(phoneField, gbc);
        
        // Age
        gbc.gridy = 4;
        gbc.gridx = 0;
        add(new JLabel("Umur:"), gbc);
        
        JTextField ageField = new JTextField(20);
        gbc.gridx = 1;
        add(ageField, gbc);
        
        // Password
        gbc.gridy = 5;
        gbc.gridx = 0;
        add(new JLabel("Password:"), gbc);
        
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);
        
        // Register Button
        JButton registerButton = new JButton("Daftar");
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        registerButton.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String gender =  genderComboBox.getSelectedItem().toString();
                String noTelp = phoneField.getText();
                int umur = Integer.parseInt(ageField.getText());
                String password = new String(passwordField.getPassword());
                
                for (String key : pelangganBiz.getUsers().keySet()) {
                	User user = pelangganBiz.getUsers().get(key);
                    if (user.getUsername().equalsIgnoreCase(username)) {
                    	JOptionPane.showMessageDialog(this, "Username sudah digunakan!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    if (user.getNoTelp().equals(noTelp)) {
                    	JOptionPane.showMessageDialog(this, "No telepon sudah digunakan!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                // Validate input
                if (!noTelp.matches("\\d{4}-\\d{4}-\\d{4}")) {
                    JOptionPane.showMessageDialog(this, "Format nomor telepon tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if(umur <= 0) {
                	JOptionPane.showMessageDialog(this, "Masukkan umur yang benar!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (password.length() < 6) {
                    JOptionPane.showMessageDialog(this, "Password minimal 6 karakter!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Register user
                pelangganBiz.insertPelanggan(username, gender, noTelp, umur, password);
                JOptionPane.showMessageDialog(this, "Registrasi berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
                usernameField.setText("");
                genderComboBox.setSelectedIndex(0);
                phoneField.setText("");
                ageField.setText("");
                passwordField.setText("");
                
                mainApp.showLoginPanel();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Umur harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(registerButton, gbc);
        
        // Back Button
        JButton backButton = new JButton("Kembali ke Login");
        gbc.gridy = 7;
        backButton.addActionListener(e -> mainApp.showLoginPanel());
        add(backButton, gbc);
    }
}