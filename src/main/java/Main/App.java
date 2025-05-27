package Main;

import java.util.*;


import Biz.*;
import Obt.*;
import Usr.*;

public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<Obat> daftarObat = new ArrayList<>(); 
		HashMap<String, User> users = new HashMap<String, User>();
		
		PelangganBiz pelangganlist = new PelangganBiz(daftarObat, users);
		PramuniagaBiz pramuniagalist = new PramuniagaBiz(daftarObat, users);
		pramuniagalist.initializeObat();
		pramuniagalist.initializeAdmin();
		pelangganlist.initializePelanggan();
		
//		System.out.println("User terdaftar:");
//		for (String key : users.keySet()) {
//			
//		    System.out.println("- " + key);
//		}
		
		System.out.print("Login atau Register? (L/R): ");
		String pilihmenu = scanner.next();
		
		if(pilihmenu.equalsIgnoreCase("R")) {
			System.out.println( "----------------------------------" );
			System.out.println( " Menambahkan informasi pelanggan baru." );
			System.out.println( "----------------------------------" );
	        
	        System.out.print("Masukkan Username: ");
	        String username = scanner.next();
	        System.out.print("Masukkan Gender (L/P): ");
	        String gender = scanner.next();
	        System.out.print("Masukkan no telepon: ");
	        String noTelp = scanner.next();
	        System.out.print("Masukkan umur: ");
	        int umur = scanner.nextInt();
	        System.out.print("Masukkan password: ");
	        String password = scanner.next();
	          
	        pelangganlist.insertPelanggan(username, gender, noTelp, umur, password);
	        pilihmenu = "L";
	        
		} 
		
		if(pilihmenu.equalsIgnoreCase("L")) {
			System.out.println( "----------------------------------" );
			System.out.println( " Login." );
			System.out.println( "----------------------------------" );
			
			System.out.print("Masukkan Username: ");
			String username = scanner.next();
			System.out.print("Masukkan Password: ");
			String password = scanner.next();

			for (String key : users.keySet()) {
				User user = users.get(key);
				if (user != null && user.getPassword().equals(password)) {
				    System.out.println("Login berhasil sebagai: " + user.getUsername());
				    
				    int menu = -1;
				    
				    if (user instanceof Pelanggan) {
				        while(menu != 9) {
				        	printMenuPelanggan();
							
							menu = scanner.nextInt();
							
							if(menu == 1) {
								System.out.println( "----------------------------------" );
				                System.out.println( "Data Obat." );
				                System.out.println( "----------------------------------" );
								try {			
									pelangganlist.printAllObat();
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							} else if (menu == 2) {
								System.out.println( "----------------------------------" );
				                System.out.println( "Pesan Obat." );
				                System.out.println( "----------------------------------" );
				                
				                System.out.print("Masukkan nama obat: ");
				                String namaObat = scanner.next();
				                System.out.print("Masukkan jumlah yang ingin dibeli: ");
				                int jumlah = scanner.nextInt();
				                
				                pelangganlist.pesanObat(namaObat, jumlah);
							} else if (menu == 3) {
								System.out.println( "----------------------------------" );
				                System.out.println( "Lihat Riwayat Pembelian." );
				                System.out.println( "----------------------------------" );
				                
				                pelangganlist.lihatRiwayatPembelian();
							} else if (menu == 4) {
				                System.out.println("----------------------------------");
				                System.out.println("Ubah Password");
				                System.out.println("----------------------------------");
				                
				                System.out.print("Masukkan password baru: ");
				                String passwordBaru = scanner.next();
				                
				                pelangganlist.changePassword(user.getNoTelp(), passwordBaru);
				                
				                System.out.println("Password berhasil diubah");
				            } else if (menu == 9) {
				                System.out.println("----------------------------------");
				                System.out.println("Selesai");
				                System.out.println("----------------------------------");

				            } else {
				                System.out.println("[Error] Silahkan input menu yang ada.");
				            }
						}
				        
				    } else if (user instanceof Pramuniaga) {
				        while(menu != 9) {
				        	printMenuPramuniaga();
							
							menu = scanner.nextInt();
							
							if(menu == 1) {
								System.out.println( "----------------------------------" );
				                System.out.println( "Data seluruh pelanggan." );
				                System.out.println( "----------------------------------" );
								try {					
									pramuniagalist.printAllPelanggan();
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							} else if (menu == 2) {
								System.out.println( "----------------------------------" );
				                System.out.println( "Hapus pelanggan." );
				                System.out.println( "----------------------------------" );
				                
				                System.out.print("Masukkan Nomor Telepon: ");
				                String userDelete = scanner.nextLine();
				                
				                pramuniagalist.deletePelanggan(userDelete);
							} else if (menu == 3) {
								System.out.println( "----------------------------------" );
				                System.out.println( "Data semua obat." );
				                System.out.println( "----------------------------------" );
				                
				                pramuniagalist.printAllObat();
							} else if (menu == 4) {
				                System.out.println("----------------------------------");
				                System.out.println("Tambah data obat.");
				                System.out.println("----------------------------------");
				                
				                System.out.println("Pilih jenis obat:");
				                System.out.println("1. Kapsul");
				                System.out.println("2. Salep");
				                System.out.println("3. Sirup");
				                System.out.println("4. Tablet");
				                System.out.println("5. Injeksi");
				                System.out.print("Pilihan: ");
				                int jenis = scanner.nextInt();
				                scanner.nextLine();

				                System.out.print("Nama obat: ");
				                String namaObatBaru = scanner.nextLine();
				                System.out.print("Kategori obat: ");
				                String kategoriObat = scanner.nextLine();
				                System.out.print("Harga obat: ");
				                double harga = scanner.nextDouble();
				                System.out.print("Stok obat: ");
				                int stok = scanner.nextInt();
				                
				                Obat obatBaru = null;

				                switch (jenis) {
				                    case 1:
				                        obatBaru = new Kapsul(namaObatBaru, kategoriObat, harga, stok);
				                        break;
				                    case 2:
				                    	obatBaru = new Salep(namaObatBaru, kategoriObat, harga, stok);
				                        break;
				                    case 3:
				                    	obatBaru = new Sirup(namaObatBaru, kategoriObat, harga, stok);
				                        break;
				                    case 4:
				                    	obatBaru = new Tablet(namaObatBaru, kategoriObat, harga, stok);
				                        break;
				                    case 5:
				                    	obatBaru = new Injeksi(namaObatBaru, kategoriObat, harga, stok);
				                        break;
				                    default:
				                        System.out.println("Jenis obat tidak valid.");
				                        break;
				                }

				                if (obatBaru != null) {
				                    pramuniagalist.insertObat(obatBaru);
				                    System.out.println("Obat berhasil ditambahkan.");
				                }
				            } else if (menu == 5) {
				                System.out.println("----------------------------------");
				                System.out.println("Edit stok obat");
				                System.out.println("----------------------------------");
				                
				                System.out.print("Masukkan nama obat: ");
				                String namaObat = scanner.nextLine();
				                System.out.print("Masukkan stok obat: ");
				                int stokUpdate = scanner.nextInt();
				                
				                pramuniagalist.editStokObat(namaObat, stokUpdate);
				                System.out.println("Berhasil ubah stok");
				            } else if (menu == 6) {
				                System.out.println("----------------------------------");
				                System.out.println("Hapus obat");
				                System.out.println("----------------------------------");
				                
				                System.out.print("Masukkan index obat: ");
				                int hapusObat = scanner.nextInt();
				                
				                pramuniagalist.deleteObat(hapusObat);
				                System.out.println("Data obat berhasil dihapus");
				            } else if (menu == 9) {
				                System.out.println("----------------------------------");
				                System.out.println("Selesai");
				                System.out.println("----------------------------------");

				            } else {
				                System.out.println("[Error] Silahkan input menu yang ada.");
				            }
						}
				    }
				} else {
				    System.out.println("Username atau Password salah!");
				}
			}
			
			
		}
		
		if(!pilihmenu.equalsIgnoreCase("R") && !pilihmenu.equalsIgnoreCase("L")) {	
			System.out.println("Plihan hanya L/R, pilih yang betul.");
		}
		
	}
	
	public static void printMenuPelanggan() {
        System.out.println( "=== << Program Pelanggan >> ===" );
        System.out.println( " 1. Tampilkan semua obat" );
        System.out.println( " 2. Pesan Obat" );
        System.out.println( " 3. Lihat riwayat pembelian" );
        System.out.println( " 4. Ubah password" );
        System.out.println( " 9. Keluar sistem" );
        System.out.println( "==========================" );
        System.out.print( "## masuk ke menu : " );
    }
	
	public static void printMenuPramuniaga() {
        System.out.println( "=== << Program Pelanggan >> ===" );
        System.out.println( " 1. Tampilkan semua pelanggan" );
        System.out.println( " 2. Hapus Pelanggan" );
        System.out.println( " 3. Tampilkan semua obat" );
        System.out.println( " 4. Tambah data obat" );
        System.out.println( " 5. Edit stok obat" );
        System.out.println( " 6. Hapus obat" );
        System.out.println( " 9. Keluar sistem" );
        System.out.println( "==========================" );
        System.out.print( "## masuk ke menu : " );
    }
}
