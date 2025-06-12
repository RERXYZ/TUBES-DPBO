package GuiNew;
import javax.swing.*;

import Biz.*;
import Obt.*;
import Usr.*;

import java.awt.*;
import java.util.*;

public class MainApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Data structures
    private ArrayList<Obat> daftarObat;
    private HashMap<String, User> users;
    
    // Business logic
    private PelangganBiz pelangganBiz;
    private PramuniagaBiz pramuniagaBiz;
    
    public MainApp() {
        // Initialize data structures
        daftarObat = new ArrayList<>();
        users = new HashMap<>();
        
        // Initialize business logic
        pelangganBiz = new PelangganBiz(daftarObat, users);
        pramuniagaBiz = new PramuniagaBiz(daftarObat, users);
        
        // Initialize sample data
        pramuniagaBiz.initializeObat();
        pramuniagaBiz.initializeAdmin();
        pelangganBiz.initializePelanggan();
        
        // Setup main window
        setTitle("Apotek Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create card layout for navigation
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create and add panels
        mainPanel.add(new LoginPanel(this, pelangganBiz, pramuniagaBiz), "login");
        mainPanel.add(new RegisterPanel(this, pelangganBiz), "register");
        mainPanel.add(new PelangganMenuPanel(this, pelangganBiz), "pelangganMenu");
        mainPanel.add(new PramuniagaMenuPanel(this, pramuniagaBiz), "pramuniagaMenu");
        
        add(mainPanel);
        
        // Show login panel first
        showLoginPanel();
    }
    
    public void showLoginPanel() {
        cardLayout.show(mainPanel, "login");
    }
    
    public void showRegisterPanel() {
        cardLayout.show(mainPanel, "register");
    }
    
    public void showPelangganMenu() {
        cardLayout.show(mainPanel, "pelangganMenu");
    }
    
    public void showPramuniagaMenu() {
        cardLayout.show(mainPanel, "pramuniagaMenu");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}