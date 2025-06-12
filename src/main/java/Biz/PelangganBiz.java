package Biz;

import java.util.*;


import Obt.*;
import Pengambilan.*;
import Usr.*;

public class PelangganBiz implements IPelangganBiz {
	private HashMap<String, User> pelanggan;
    private ArrayList<Obat> daftarObat;
    private String currentUserPhone;
    
    public PelangganBiz(ArrayList<Obat> daftarObat, HashMap<String, User> pelanggan) {
        this.daftarObat = daftarObat;
        this.pelanggan = pelanggan;
    }
    
    public boolean authenticate(String username, String password) {
        for (String key : pelanggan.keySet()) {
        	User user = pelanggan.get(key);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                setCurrentUser(user.getNoTelp());
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Obat> getDaftarObat() {
        return daftarObat;
    }

    public HashMap<String, User> getUsers() {
        return pelanggan;
    }

    public User getCurrentUser() {
        return pelanggan.get(currentUserPhone);
    }

    public void setCurrentUser(String noTelp) {
        this.currentUserPhone = noTelp;
    }
    
    @Override
    public void initializePelanggan() {
    	pelanggan.put("0812-1234-5678", new Pelanggan("orang4", "Perempuan", "0812-1234-5678", 20, "orang4"));
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
    public void pesanObat(String namaObat, int jumlah, MetodePengambilan metodePengambilan, int pembayaran) {
        for (Obat obt : daftarObat) {
            if (obt.getNama().equalsIgnoreCase(namaObat)) {
            	if (obt.getStok() >= jumlah) {
            		int total = (int) obt.getHarga() * jumlah;
                    int kembalian = pembayaran-total;
                    
                    if(pembayaran < total) {
                		System.out.println("Maaf, saldo anda kurang");
                		return;
                	} else if(pembayaran > total) {
                		System.out.println("Kembalian anda: " + kembalian);
                	}
                    
                    obt.setStok(obt.getStok() - jumlah);
                    
                    String log = " membeli obat " + obt.getNama() + " sebanyak " + jumlah + "| Rp." + total;
                    Pelanggan pelangganAktif = (Pelanggan) pelanggan.get(currentUserPhone);
                    pelangganAktif.getRiwayat().add(log);
                    
                    System.out.println("Obat " + namaObat + " sebanyak " + jumlah + " berhasil dipesan.");
                    System.out.print("Metode pengambilan: ");
                    metodePengambilan.ambil();
                } else {
                    System.out.println("Stok tidak cukup!");
                }
                return;
            }
        }
        System.out.println("Obat tidak ditemukan!");
    }
    
    
    public MetodePengambilan pilihMetodePengambilan(int pilihan) {
        switch (pilihan) {
            case 1:
                return new Diantar();
            case 2:
                return new DiambilDiTempat();
            default:
                System.out.println("Pilihan tidak valid, default: Diambil di tempat.");
                return new DiambilDiTempat();
        }
    }

    @Override
    public void lihatRiwayatPembelian() {
    	Pelanggan pelangganAktif = (Pelanggan) pelanggan.get(currentUserPhone);
    	ArrayList<String> riwayat = pelangganAktif.getRiwayat();
    	
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada pembelian.");
        } else {
            System.out.println("Riwayat Pembelian:");
            for (String r : riwayat) {
                System.out.println("- " + r);
            }
        }
    }
    
    public String getRiwayatPembelian() {
        Pelanggan pelangganAktif = (Pelanggan) pelanggan.get(currentUserPhone);
        ArrayList<String> riwayat = pelangganAktif.getRiwayat();
        
        if (riwayat.isEmpty()) {
            return "Belum ada pembelian.";
        } else {
            StringBuilder sb = new StringBuilder("Riwayat Pembelian:\n");
            for (String r : riwayat) {
                sb.append("- ").append(r).append("\n");
            }
            return sb.toString();
        }
    }

    @Override
    public void changePassword(String noTelp, String passwordBaru) {
    	pelanggan.get(noTelp).setPassword(passwordBaru);
    }
}
