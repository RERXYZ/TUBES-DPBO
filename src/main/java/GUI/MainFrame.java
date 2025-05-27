package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Aplikasi Apotek");
        setSize(526, 356);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Panel Utama dengan Tombol
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 1, 10, 10));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        menuPanel.add(loginButton);
        menuPanel.add(registerButton);

        // Tambah semua panel ke mainPanel
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(new Register(this), "login");
        mainPanel.add(new Register(this), "register");

        // Aksi tombol
        loginButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        registerButton.addActionListener(e -> cardLayout.show(mainPanel, "register"));

        getContentPane().add(mainPanel);
        cardLayout.show(mainPanel, "menu"); // tampilan awal
        setVisible(true);
    }

    public void showMenu() {
        cardLayout.show(mainPanel, "menu");
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
