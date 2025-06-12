package GuiNew;
import javax.swing.*;

import Biz.*;
import Obt.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PelangganMenuPanel extends JPanel {
    private MainApp mainApp;
    private PelangganBiz pelangganBiz;
    
    public PelangganMenuPanel(MainApp mainApp, PelangganBiz pelangganBiz) {
        this.mainApp = mainApp;
        this.pelangganBiz = pelangganBiz;
        
        initUI();
    }
    
    private void initUI() {
        setLayout(new BorderLayout());
        
        // Header
        JLabel titleLabel = new JLabel("Menu Pelanggan", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        
        // Menu Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        JButton viewObatBtn = new JButton("Lihat Daftar Obat");
        JButton pesanObatBtn = new JButton("Pesan Obat");
        JButton riwayatBtn = new JButton("Lihat Riwayat Pembelian");
        JButton gantiPasswordBtn = new JButton("Ganti Password");
        JButton logoutBtn = new JButton("Logout");
        
        // Add buttons to panel
        buttonPanel.add(viewObatBtn);
        buttonPanel.add(pesanObatBtn);
        buttonPanel.add(riwayatBtn);
        buttonPanel.add(gantiPasswordBtn);
        buttonPanel.add(logoutBtn);
        
        add(buttonPanel, BorderLayout.CENTER);
        
        // Button actions
        viewObatBtn.addActionListener(e -> showDaftarObat());
        pesanObatBtn.addActionListener(e -> showPesanObat());
        riwayatBtn.addActionListener(e -> showRiwayatPembelian());
        gantiPasswordBtn.addActionListener(e -> showGantiPassword());
        logoutBtn.addActionListener(e -> mainApp.showLoginPanel());
    }
    
    private void showDaftarObat() {
    	try {
    		StringBuilder sb = new StringBuilder();
            sb.append("Daftar Obat:\n\n");
            sb.append(String.format("%-20s %-10s %-5s\n", "Nama Obat", "Harga", "Stok"));
            sb.append("---------------------------------------------\n");
            
            String harga = "";
            String stok = "";
            
            for (Obat obat : pelangganBiz.getDaftarObat()) {
            	harga = Double.toString((int) obat.getHarga());
            	stok = Integer.toString(obat.getStok());
            	sb.append(String.format("%-20s Rp.%-9s %-5s\n", obat.getNama(), harga, stok));
            }
            
            JOptionPane.showMessageDialog(this, sb.toString(), "Daftar Obat", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }
    
    private void showPesanObat() {
        // Create dialog for ordering medicine
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        // Obat selection
        JComboBox<String> obatCombo = new JComboBox<>();
        for (Obat obat : pelangganBiz.getDaftarObat()) {
            obatCombo.addItem(obat.getNama() + " - Rp" + obat.getHarga());
        }
        panel.add(new JLabel("Pilih Obat:"));
        panel.add(obatCombo);
        
        // Quantity
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        panel.add(new JLabel("Jumlah:"));
        panel.add(quantitySpinner);
        
        // Payment method
        JComboBox<String> metodeCombo = new JComboBox<>(new String[]{"Diantar", "Ambil di Tempat"});
        panel.add(new JLabel("Metode Pengambilan:"));
        panel.add(metodeCombo);
        
        // Payment amount
        JTextField paymentField = new JTextField();
        panel.add(new JLabel("Jumlah Bayar:"));
        panel.add(paymentField);
        
        int result = JOptionPane.showConfirmDialog(
            this, panel, "Pesan Obat", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
        if (result == JOptionPane.OK_OPTION) {
            try {
                String selectedObat = (String) obatCombo.getSelectedItem();
                String namaObat = selectedObat.split(" - ")[0];
                int jumlah = (int) quantitySpinner.getValue();
                int metode = metodeCombo.getSelectedIndex() + 1;
                int pembayaran = Integer.parseInt(paymentField.getText());
                
                int total = 0;
                for (Obat obat : pelangganBiz.getDaftarObat()) {
                	if(obat.getNama().equalsIgnoreCase(namaObat)) {
                		total = (int) (obat.getHarga()*jumlah);
                		
                		if(jumlah > obat.getStok()) {
                			JOptionPane.showMessageDialog(this, "Stok tidak cukup!", "Error", JOptionPane.ERROR_MESSAGE);
                			return;
                		} else if(pembayaran < total) {
                			JOptionPane.showMessageDialog(this, "Uang anda kurang, minimal bayar : Rp." + total, "Error", JOptionPane.ERROR_MESSAGE);
                			return;
                		}
                	}
                }
                
                int kembalian = pembayaran - total;
                pelangganBiz.pesanObat(namaObat, jumlah, pelangganBiz.pilihMetodePengambilan(metode), pembayaran);
                
                JOptionPane.showMessageDialog(this, "Pesanan berhasil!\n " + (kembalian != 0 ? ("Kembalian anda: " + kembalian):""), "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void showRiwayatPembelian() {
        String riwayat = pelangganBiz.getRiwayatPembelian();
        JTextArea textArea = new JTextArea(riwayat);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Riwayat Pembelian", JOptionPane.PLAIN_MESSAGE);
    }
    
    private void showGantiPassword() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        JPasswordField newPassField = new JPasswordField(20);
        JPasswordField confirmPassField = new JPasswordField(20);
        
        panel.add(new JLabel("Password Baru:"));
        panel.add(newPassField);
        panel.add(new JLabel("Konfirmasi Password:"));
        panel.add(confirmPassField);
        
        int result = JOptionPane.showConfirmDialog(
            this, panel, "Ganti Password", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
        if (result == JOptionPane.OK_OPTION) {
            String newPass = new String(newPassField.getPassword());
            String confirmPass = new String(confirmPassField.getPassword());
            
            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this, "Password tidak cocok!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (newPass.length() < 6) {
                JOptionPane.showMessageDialog(this, "Password minimal 6 karakter!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            pelangganBiz.changePassword(pelangganBiz.getCurrentUser().getNoTelp(), newPass);
            JOptionPane.showMessageDialog(this, "Password berhasil diubah!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}