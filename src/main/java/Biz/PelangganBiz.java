package Biz;

import java.util.*;

import Obt.*;
import Usr.*;

public class PelangganBiz implements IPelangganBiz {
	private HashMap<String, User> pelanggan;
    private ArrayList<Obat> daftarObat;
    private ArrayList<String> riwayat;
    
    public PelangganBiz(ArrayList<Obat> daftarObat, HashMap<String, User> pelanggan) {
        this.daftarObat = daftarObat;
        this.pelanggan = pelanggan;
        this.riwayat = new ArrayList<>();
    }
    
    public void initializePelanggan() {
    	pelanggan.put("orang", new Pelanggan("orang4", "Perempuan", "081290192122", 20, "awdewqeqwq"));
    }

    
    @Override
    public void printAllObat() {
    	System.out.println("------------------- Informasi Obat -------------------");
		for (int i = 0; i < daftarObat.size(); i++) {
			System.out.print((i + 1) + ". ");
			daftarObat.get(i).tampilkanInfo();
		}
		System.out.println("-----------------------------------------------------------");
    }
    
    @Override
    public void insertPelanggan(String username, String gender, String noTelp, int umur, String password) {
    	Pelanggan p = new Pelanggan(username, gender, noTelp, umur, password);
        pelanggan.put(noTelp, p);
        System.out.println("Pelanggan berhasil didaftarkan.");
    }

    @Override
    public void pesanObat(String namaObat, int jumlah) {
        for (Obat obt : daftarObat) {
            if (obt.getNama().equalsIgnoreCase(namaObat)) {
                if (obt.getStok() >= jumlah) {
                	obt.setStok(obt.getStok() - jumlah);
                    double total = obt.getHarga() * jumlah;
                    String log = "Beli " + jumlah + " x " + obt.getNama() + " = Rp" + total;
                    riwayat.add(log);
                    System.out.println("Pembelian berhasil!");
                } else {
                    System.out.println("Stok tidak cukup!");
                }
                return;
            }
        }
        System.out.println("Obat tidak ditemukan!");
    }

    @Override
    public void lihatRiwayatPembelian() {
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada pembelian.");
        } else {
            System.out.println("Riwayat Pembelian:");
            for (String r : riwayat) {
                System.out.println("- " + r);
            }
        }
    }

    @Override
    public void changePassword(String noTelp, String passwordBaru) {
    	pelanggan.get(noTelp).setPassword(passwordBaru);
    }
}
