package Usr;

import java.util.*;

public class Pelanggan extends User {
	private ArrayList<String> riwayat = new ArrayList<>();
	
	public Pelanggan(String username, String gender, String noTelp, int umur, String password) {
		super(username, gender, noTelp, umur, 0, password);
	}
	
	public void tambahRiwayat(String catatan) {
	    riwayat.add(catatan);
	}

	public ArrayList<String> getRiwayat() {
	    return riwayat;
	}
}
