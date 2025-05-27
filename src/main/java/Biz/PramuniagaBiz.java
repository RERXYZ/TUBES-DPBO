package Biz;

import java.util.*;

import Obt.*;
import Usr.*;

public class PramuniagaBiz implements IPramuniagaBiz {
	private HashMap<String, User> dataUser;
    private ArrayList<Obat> daftarObat;
    
    public PramuniagaBiz(ArrayList<Obat> daftarObat, HashMap<String, User> dataUser) {
        this.daftarObat = daftarObat;
        this.dataUser = dataUser;
    }

    @Override
    public void initializeAdmin() {
    	dataUser.put("Admin", new Pramuniaga("yanto", "L", "08123456789", 23, "awikwok123"));
    }

    @Override
    public void initializeObat() {
    	daftarObat.add(new Kapsul("obat1", "Antibiotik", 20000, 30));
    	daftarObat.add(new Tablet("obat2", "Antibiotik", 20000, 10));
    	daftarObat.add(new Sirup("obat3", "Antibiotik", 20000, 30));
    }

    @Override
    public void printAllPelanggan() {
    	int index = 1;
        System.out.println("------------------- Informasi Pelanggan -------------------");
        for (String murid : dataUser.keySet()) {
    		User pelanggan = dataUser.get(murid);
    		
    		System.out.print((index++) + ". ");
    		System.out.print("Nama: " + pelanggan.getUsername() + "\t");
    		System.out.print("Umur: " + pelanggan.getUmur() + "\t");
    		System.out.print("Jenis Kelamin : " + pelanggan.getGender() + "\t");
    		System.out.println("Nomor Telepon: " + pelanggan.getNoTelp());
    	}
        
		System.out.println("-----------------------------------------------------------");
    }

    @Override
    public void deletePelanggan(String noTelp) {
    	String Username = null;
    	
    	for (String key : dataUser.keySet()) {
			User user = dataUser.get(key);
			
			if (user instanceof Pelanggan && user.getNoTelp().equals(noTelp)) {
	            Username = user.getUsername();
	            break;
	        }
    	}
    	
    	if (Username != null) {
            dataUser.remove(Username);
            System.out.println("Pelanggan dengan no. telepon \"" + noTelp + "\" berhasil dihapus.");
        } else {
            System.out.println("Pelanggan dengan no. telepon \"" + noTelp + "\" tidak ditemukan.");
        }
    }

//    @Override
//    public int getPelangganNumber() {
//    	return dataUser.size();
//    }

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
    public void insertObat(Obat obat) {
        daftarObat.add(obat);
    }

    @Override
    public void editStokObat(String namaObat, int stokBaru) {
        for (Obat obt : daftarObat) {
            if (obt.getNama().equalsIgnoreCase(namaObat)) {
            	obt.setStok(stokBaru);
                break;
            }
        }
    }

    @Override
    public void deleteObat(int number) {
    	daftarObat.remove(number-1);
    }
}