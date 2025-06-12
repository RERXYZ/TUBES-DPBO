package GuiNew;
import javax.swing.*;


import Biz.*;
import Obt.*;
import Usr.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PramuniagaMenuPanel extends JPanel {
    private MainApp mainApp;
    private PramuniagaBiz pramuniagaBiz;
    
    public PramuniagaMenuPanel(MainApp mainApp, PramuniagaBiz pramuniagaBiz) {
        this.mainApp = mainApp;
        this.pramuniagaBiz = pramuniagaBiz;
        
        initUI();
    }
    
    private void initUI() {
        setLayout(new BorderLayout());
        
        // Header
        JLabel titleLabel = new JLabel("Menu Pramuniaga", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        
        // Menu Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        JButton viewPelangganBtn = new JButton("Lihat Daftar Pelanggan");
        JButton deletePelangganBtn = new JButton("Hapus Pelanggan");
        JButton viewObatBtn = new JButton("Lihat Daftar Obat");
        JButton addObatBtn = new JButton("Tambah Obat");
        JButton editStokBtn = new JButton("Edit Stok Obat");
        JButton deleteObatBtn = new JButton("Hapus Obat");
        JButton logoutBtn = new JButton("Logout");
        
        // Add buttons to panel
        buttonPanel.add(viewPelangganBtn);
        buttonPanel.add(deletePelangganBtn);
        buttonPanel.add(viewObatBtn);
        buttonPanel.add(addObatBtn);
        buttonPanel.add(editStokBtn);
        buttonPanel.add(deleteObatBtn);
        buttonPanel.add(logoutBtn);
        
        add(buttonPanel, BorderLayout.CENTER);
        
        // Button actions
        viewPelangganBtn.addActionListener(e -> showDaftarPelanggan());
        deletePelangganBtn.addActionListener(e -> showHapusPelanggan());
        viewObatBtn.addActionListener(e -> showDaftarObat());
        addObatBtn.addActionListener(e -> showTambahObat());
        editStokBtn.addActionListener(e -> showEditStokObat());
        deleteObatBtn.addActionListener(e -> showHapusObat());
        logoutBtn.addActionListener(e -> mainApp.showLoginPanel());
    }
    
    private void showDaftarPelanggan() {
        StringBuilder sb = new StringBuilder();
        sb.append("Daftar Pelanggan:\n\n");
        sb.append(String.format("%-20s %-25s %-5s\n", "Nama Pelanggan", "No.Telp", "Gender"));
        sb.append("--------------------------------------------------------------------------\n");
        
        for (String key : pramuniagaBiz.getUsers().keySet()) {
    		User pelanggan = pramuniagaBiz.getUsers().get(key);
    		if(pelanggan instanceof Pelanggan) {
    			sb.append(String.format("%-25s %-20s %-5s\n", pelanggan.getUsername(), pelanggan.getNoTelp(), pelanggan.getGender()));
    		}
        }
        
        JOptionPane.showMessageDialog(this, sb.toString(), "Daftar Pelanggan", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showHapusPelanggan() {
    	JComboBox<String> pelangganCombo = new JComboBox<>();
    	
    	for (String key : pramuniagaBiz.getUsers().keySet()) {
    		User pelanggan = pramuniagaBiz.getUsers().get(key);
    		if(pelanggan instanceof Pelanggan) {
    			pelangganCombo.addItem(pelanggan.getUsername());
    		}
        }
        
        int result = JOptionPane.showConfirmDialog(this, pelangganCombo, "Pilih Pelanggan yang Akan Dihapus", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
        if (result == JOptionPane.OK_OPTION) {
        	String noTelp = "";
            String namaPelanggan = (String) pelangganCombo.getSelectedItem();
            
            for (String key : pramuniagaBiz.getUsers().keySet()) {
        		User pelanggan = pramuniagaBiz.getUsers().get(key);
        		if(namaPelanggan == pelanggan.getUsername()) {
        			noTelp = pelanggan.getNoTelp();
        		}
            }
            
            pramuniagaBiz.deletePelanggan(noTelp);
            JOptionPane.showMessageDialog(this, "pelanggan berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void showDaftarObat() {
        StringBuilder sb = new StringBuilder();
        sb.append("Daftar Obat:\n\n");
        
        for (Obat obat : pramuniagaBiz.getDaftarObat()) {
            sb.append(obat.getNama()).append(" - Rp").append(obat.getHarga())
              .append(" - Stok: ").append(obat.getStok()).append(" - Kategori: ")
              .append(obat.getKategori()).append("\n");
        }
        
        JOptionPane.showMessageDialog(this, sb.toString(), "Daftar Obat", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showTambahObat() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        // Obat type
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{
            "Kapsul", "Salep", "Sirup", "Tablet", "Injeksi"
        });
        panel.add(new JLabel("Jenis Obat:"));
        panel.add(typeCombo);
        
        // Name
        JTextField nameField = new JTextField();
        panel.add(new JLabel("Nama Obat:"));
        panel.add(nameField);
        
        // Category
        JTextField categoryField = new JTextField();
        panel.add(new JLabel("Kategori:"));
        panel.add(categoryField);
        
        // Price
        JTextField priceField = new JTextField();
        panel.add(new JLabel("Harga:"));
        panel.add(priceField);
        
        // Stock
        JSpinner stockSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        panel.add(new JLabel("Stok:"));
        panel.add(stockSpinner);
        
        int result = JOptionPane.showConfirmDialog(
            this, panel, "Tambah Obat", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
        if (result == JOptionPane.OK_OPTION) {
            try {
                String jenis = (String) typeCombo.getSelectedItem();
                String nama = nameField.getText();
                String kategori = categoryField.getText();
                double harga = Double.parseDouble(priceField.getText());
                int stok = (int) stockSpinner.getValue();
                
                for (Obat obat : pramuniagaBiz.getDaftarObat()) {
                    if(nama.equalsIgnoreCase(obat.getNama())) {
                    	JOptionPane.showMessageDialog(this, "Nama obat sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
                    	return;
                    }
                }
                
                Obat obatBaru = null;
                switch (jenis) {
                    case "Kapsul":
                        obatBaru = new Kapsul(nama, kategori, harga, stok);
                        break;
                    case "Salep":
                        obatBaru = new Salep(nama, kategori, harga, stok);
                        break;
                    case "Sirup":
                        obatBaru = new Sirup(nama, kategori, harga, stok);
                        break;
                    case "Tablet":
                        obatBaru = new Tablet(nama, kategori, harga, stok);
                        break;
                    case "Injeksi":
                        obatBaru = new Injeksi(nama, kategori, harga, stok);
                        break;
                }
                
                if (obatBaru != null) {
                    pramuniagaBiz.insertObat(obatBaru);
                    JOptionPane.showMessageDialog(this, "Obat berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void showEditStokObat() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        // Obat selection
        JComboBox<String> obatCombo = new JComboBox<>();
        for (Obat obat : pramuniagaBiz.getDaftarObat()) {
            obatCombo.addItem(obat.getNama());
        }
        panel.add(new JLabel("Pilih Obat:"));
        panel.add(obatCombo);
        
        // New stock
        JSpinner stockSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
        panel.add(new JLabel("Stok Baru:"));
        panel.add(stockSpinner);
        
        int result = JOptionPane.showConfirmDialog(
            this, panel, "Edit Stok Obat", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
        if (result == JOptionPane.OK_OPTION) {
            String namaObat = (String) obatCombo.getSelectedItem();
            int stokBaru = (int) stockSpinner.getValue();
            
            pramuniagaBiz.editStokObat(namaObat, stokBaru);
            JOptionPane.showMessageDialog(this, "Stok berhasil diupdate!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void showHapusObat() {
        // Create dialog to select obat to delete
        JComboBox<String> obatCombo = new JComboBox<>();
        for (Obat obat : pramuniagaBiz.getDaftarObat()) {
            obatCombo.addItem(obat.getNama());
        }
        
        int result = JOptionPane.showConfirmDialog(
            this, obatCombo, "Pilih Obat yang Akan Dihapus", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
        if (result == JOptionPane.OK_OPTION) {
            String namaObat = (String) obatCombo.getSelectedItem();
            
            // Find obat index
            int index = -1;
            for (int i = 0; i < pramuniagaBiz.getDaftarObat().size(); i++) {
                if (pramuniagaBiz.getDaftarObat().get(i).getNama().equals(namaObat)) {
                    index = i+1;
                    break;
                }
            }
            
            if (index != -1) {
                pramuniagaBiz.deleteObat(index);
                JOptionPane.showMessageDialog(this, "Obat berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}