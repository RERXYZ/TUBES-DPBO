package Usr;

import java.util.*;

public class Registrasi {

	public static void main(String[] args) {
		ArrayList<User> users = new ArrayList<User>();
		
		users.add(new Pramuniaga("admin", "dewa", "000000", 20, "awww"));
		
		users.add(new Pelanggan("orang1", "Laki-Laki", "082393022190", 16, "wewewew"));
		users.add(new Pelanggan("orang2", "Perempuan", "081290192122", 22, "eeee"));
		users.add(new Pelanggan("orang3", "Laki-Laki", "081290192122", 18, "sssww"));
		users.add(new Pelanggan("orang4", "Perempuan", "081290192122", 20, "awdewqeqwq"));
		
		System.out.println("Daftar user: ");
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);

			System.out.println(user.getUsername());

		}
		
//		for (User user : users) {
//			System.out.println(user.getUsername());		}
	}

}
