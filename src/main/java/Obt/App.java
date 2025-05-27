package Obt;

import java.util.*;

public class App {

	public static void main(String[] args) {
		ArrayList<Obat> obats = new ArrayList<Obat>();
		
		obats.add(new Kapsul("betadine", "Antibiotik", 20000, 30));
		obats.add(new Tablet("adwdw", "Antibiotik", 20000, 10));
		obats.add(new Kapsul("adwdwdwda", "Antibiotik", 20000, 30));
		
		for (Obat obat2 : obats) {
			obat2.tampilkanInfo();
		}
	}

}
