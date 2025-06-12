package GuiNew;
import javax.swing.*;

import Biz.*;
import Usr.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private MainApp mainApp;
    private PelangganBiz pelangganBiz;
    private PramuniagaBiz pramuniagaBiz;
    
    public LoginPanel(MainApp mainApp, PelangganBiz pelangganBiz, PramuniagaBiz pramuniagaBiz) {
        this.mainApp = mainApp;
        this.pelangganBiz = pelangganBiz;
        this.pramuniagaBiz = pramuniagaBiz;
        
        initUI();
    }
    
    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Title
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
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
        
        // Password
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Password:"), gbc);
        
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);
        
        // Login Button
        JButton loginButton = new JButton("Login");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        loginButton.addActionListener(e -> {
        	try {				
        		String username = usernameField.getText();
        		String password = new String(passwordField.getPassword());
        		
        		// Check login
        		boolean isPelanggan = pelangganBiz.authenticate(username, password);
        		boolean isPramuniaga = pramuniagaBiz.authenticate(username, password);
        		
        		if (isPelanggan && pelangganBiz.getCurrentUser().getAccess() == 0) {
        			mainApp.showPelangganMenu();
        		} else if (isPramuniaga && pelangganBiz.getCurrentUser().getAccess() == 1) {
        			mainApp.showPramuniagaMenu();
        		} else {
        			JOptionPane.showMessageDialog(this, "Username atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        		}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Error: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
        });
        add(loginButton, gbc);
        
        // Register Link
        gbc.gridy = 4;
        JButton registerButton = new JButton("Belum punya akun? Daftar disini");
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setForeground(Color.BLUE);
        registerButton.addActionListener(e -> mainApp.showRegisterPanel());
        add(registerButton, gbc);
    }
}