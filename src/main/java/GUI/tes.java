package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class tes extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	private JTextField usernameField, telpField, umurField;
    private JPasswordField passwordField;
    private JComboBox<String> genderBox;
    private JButton registerButton;

    public tes() {
        setTitle("Halaman Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center window

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Komponen
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Gender:"));
        String[] genders = { "Laki-laki", "Perempuan" };
        genderBox = new JComboBox<>(genders);
        panel.add(genderBox);

        panel.add(new JLabel("No. Telepon:"));
        telpField = new JTextField();
        panel.add(telpField);

        panel.add(new JLabel("Umur:"));
        umurField = new JTextField();
        panel.add(umurField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        registerButton = new JButton("Register");
        panel.add(registerButton);

        panel.add(new JLabel()); // spacer

        // Aksi tombol
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String gender = (String) genderBox.getSelectedItem();
                String telp = telpField.getText();
                String umurText = umurField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || gender.isEmpty() || telp.isEmpty() || umurText.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Semua field harus diisi!");
                    return;
                }

                try {
                    int umur = Integer.parseInt(umurText);
                    if (umur <= 0) {
                        JOptionPane.showMessageDialog(null, "Umur harus lebih dari 0!");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Umur harus berupa angka!");
                    return;
                }

                JOptionPane.showMessageDialog(null, "Registrasi berhasil!");
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Regist();
    }

}
