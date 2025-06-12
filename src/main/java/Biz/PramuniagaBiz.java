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
    
    public boolean authenticate(String username, String password) {
    	for (String key : dataUser.keySet()) {
        	User user = dataUser.get(key);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Obat> getDaftarObat() {
        return daftarObat;
    }

    public HashMap<String, User> getUsers() {
        return dataUser;
    }

    @Override
    public void initializeAdmin() {
    	dataUser.put("0812-3456-7890", new Pramuniaga("yanto", "L", "0812-3456-7890", 23, "awikwok123"));
    }

    @Override
    public void initializeObat() {
    	daftarObat.add(new Kapsul("obat1", "Antibiotik", 20000, 30));
    	daftarObat.add(new Tablet("obat2", "Antibiotik", 30500, 10));
    	daftarObat.add(new Sirup("obat3", "Antibiotik", 12000, 20));
    }

    @Override
    public void printAllPelanggan() {
    	int index = 1;
        System.out.println("------------------- Informasi Pelanggan -------------------");
        for (String key : dataUser.keySet()) {
    		User pelanggan = dataUser.get(key);
    		
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
    	dataUser.remove(noTelp);
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