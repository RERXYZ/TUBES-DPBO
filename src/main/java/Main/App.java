package Main;

import java.util.*;
import Biz.*;
import Obt.*;
import Pengambilan.*;
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
		
		boolean runProgram = true;
		
        while (runProgram) {
            System.out.print("Login atau Register atau Keluar? (L/R/K): ");
            String pilihmenu = scanner.next();
            
            if(pilihmenu.equalsIgnoreCase("R")) {
                System.out.println( "----------------------------------" );
                System.out.println( " Menambahkan informasi pelanggan baru." );
                System.out.println( "----------------------------------" );
                
                try {
                	System.out.print("Masukkan Username: ");
                    String username = scanner.next();
                    
                    boolean usernameSudahAda = false;
                    
                    for (String key : users.keySet()) {
                        User user = users.get(key);
                        if (user.getUsername().equalsIgnoreCase(username)) {
                        	usernameSudahAda = true;
                        	break;
                        }
                    }
                    
                    if (usernameSudahAda) {
                        System.out.println("Username sudah digunakan. Silakan pilih username lain.");
                        continue;
                    }
                    
                    System.out.print("Masukkan Gender (L/P): ");
                    String gender = scanner.next();
                    
                    if(!gender.equalsIgnoreCase("L") && !gender.equalsIgnoreCase("P")) {
                    	System.out.println("Inputan gender hanya boleh (L) untuk laki-laki dan (P) untuk perempuan.");
                    	continue;
                    }
//                    
                    System.out.print("Masukkan no telepon: ");
                    String noTelp = scanner.next();
                    
                    if(users.containsKey(noTelp)) {
                    	System.out.println("Nomor telepon sudah digunakan.");
                    	System.out.print("Masukkan no telepon: ");
                        noTelp = scanner.next();
                        if (users.containsKey(noTelp)) {
                        	System.out.println("Nomor telepon sudah digunakan.");
							continue;
						}
                    }
                    
                    if (!noTelp.matches("\\d{4}-\\d{4}-\\d{4}")) {
                        System.out.println("Format nomor telepon tidak valid. Gunakan format: 0821-1234-5678");
                        continue;
                    }
                    
                    System.out.print("Masukkan umur: ");
                    int umur = scanner.nextInt();
                    
                    if(umur <= 0) {
                    	System.out.println("Umur tidak boleh kurang dari 1");
                    	continue;
                    }
                    
                    System.out.print("Masukkan password: ");
                    String password = scanner.next();
                    
                    if (password.trim().isEmpty()) {
                        System.out.println("Password tidak boleh kosong.");
                    } else if (password.length() < 6) {
                        System.out.println("Password terlalu pendek. Minimal 6 karakter.");
                    } else {
                    	pelangganlist.insertPelanggan(username, gender, noTelp, umur, password);
                    	pilihmenu = "L";
                    }
				} catch (InputMismatchException e) {
			        System.out.println("Input tidak valid. Umur harus berupa angka.");
			        scanner.nextLine(); // Bersihkan buffer agar program tidak macet
			    } catch (Exception e) {
			        System.out.println("Terjadi kesalahan saat mendaftarkan pelanggan: " + e.getMessage());
			    }
            } 
            
            if(pilihmenu.equalsIgnoreCase("L")) {
                System.out.println( "----------------------------------" );
                System.out.println( " Login." );
                System.out.println( "----------------------------------" );
                
                System.out.print("Masukkan Username: ");
                String username = scanner.next();
                System.out.print("Masukkan Password: ");
                String password = scanner.next();
                
                boolean berhasilLogin = false;
                User loginUser = null;
                
                for (String key : users.keySet()) {
                    User user = users.get(key);
                    if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        berhasilLogin = true;
                        loginUser = user;
                        
                        pelangganlist.setCurrentUser(user.getNoTelp());
                        
                        int menu = -1;
                        
                        if (user instanceof Pelanggan) {
                            while(menu != 9) {
                                printMenuPelanggan();
                                
                                try {
                                    menu = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Input tidak valid! Harus berupa angka.");
                                    scanner.nextLine();
                                    continue;
                                }
                                
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
                                    
                                    try {
                                        System.out.print("Masukkan nama obat: ");
                                        String namaObat = scanner.next();
                                        
                                        boolean cekTambahObat = false;
                                        double hargaObat = 0;
                                        for (Obat obt : daftarObat) {
                                            if (obt.getNama().equalsIgnoreCase(namaObat)) {
                                            	cekTambahObat = true;
                                            	hargaObat = obt.getHarga();
                                            	break;
                                            }
                                        }
                                        
                                        if(!cekTambahObat) {
                                        	System.out.println("Nama obat tidak ada.");
                                        	continue;
                                        }

                                        System.out.print("Masukkan jumlah yang ingin dibeli: ");
                                        int jumlah = scanner.nextInt();
                                        
                                        int totalBayar = (int) hargaObat * jumlah;
                                        System.out.println("Jumlah yang harus dibayar: " + totalBayar);
                                        System.out.print("Masukkan saldo: ");
	                                    int pembayaran = scanner.nextInt();
	                                    
//                                        if(pembayaran < totalBayar) {
//                                        	System.out.println("Maaf, saldo anda kurang");
//	                                  		break;
//	                                  	} else if(pembayaran > totalBayar) {
//	                                  		int kembalian = pembayaran-totalBayar;
//	                                  		System.out.println("Kembalian anda: " + kembalian);
//	                                  	}

                                        System.out.println("Pilih metode pengambilan:");
                                        System.out.println("1. Diantar");
                                        System.out.println("2. Diambil di tempat");
                                        System.out.print("Pilihan: ");
                                        int pilihan = scanner.nextInt();

                                        MetodePengambilan metode = pelangganlist.pilihMetodePengambilan(pilihan);
                                        pelangganlist.pesanObat(namaObat, jumlah, metode, pembayaran);
                                    } catch (InputMismatchException e) {
                                        System.out.println("Input tidak valid. Harap masukkan angka yang benar.");
                                        scanner.nextLine();
                                    } catch (Exception e) {
                                        System.out.println("Terjadi kesalahan: " + e.getMessage());
                                    }
                                } else if (menu == 3) {
                                    System.out.println( "----------------------------------" );
                                    System.out.println( "Lihat Riwayat Pembelian." );
                                    System.out.println( "----------------------------------" );
                                    
                                    pelangganlist.lihatRiwayatPembelian();
                                } else if (menu == 4) {
                                    System.out.println("----------------------------------");
                                    System.out.println("Ubah Password");
                                    System.out.println("----------------------------------");
                                    
                                    try {
                                        System.out.print("Masukkan password baru: ");
                                        String passwordBaru = scanner.next();
                                        
                                        if (passwordBaru.trim().isEmpty()) {
                                            System.out.println("Password tidak boleh kosong.");
                                        } else if (passwordBaru.length() < 6) {
                                            System.out.println("Password terlalu pendek. Minimal 6 karakter.");
                                        } else {
                                        	pelangganlist.changePassword(user.getNoTelp(), passwordBaru);
                                            System.out.println("Password berhasil diubah.");
                                        }
                                    } catch (NullPointerException e) {
                                        System.out.println("Terjadi kesalahan: data user tidak ditemukan.");
                                    } catch (Exception e) {
                                        System.out.println("Terjadi kesalahan saat mengubah password: " + e.getMessage());
                                    }
                                } else if (menu == 9) {
                                    System.out.println("----------------------------------");
                                    System.out.println("Selesai");
                                    System.out.println("----------------------------------");

                                } else {
                                    System.out.println("[Error] Silahkan input menu yang ada.");
                                }
                            }
                            
                        }
                        
                        if (user instanceof Pramuniaga) {
                            while(menu != 9) {
                                printMenuPramuniaga();
                                
                                try {
                                    menu = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Input tidak valid! Harus berupa angka.");
                                    scanner.nextLine();
                                    continue;
                                }
                                
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
                                    String userDelete = scanner.next();
                                    
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
                                    
                                    try {
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
                                        
                                        boolean cekTambahObat = false;
                                        for (Obat obt : daftarObat) {
											if(obt.getNama().equalsIgnoreCase(namaObatBaru)) {
												cekTambahObat = true;
												break;
											}
										}
                                        
                                        if(cekTambahObat) {
                                        	System.out.println("Nama obat sudah terdaftar.");
                                            continue;
                                        }

                                        System.out.print("Kategori obat: ");
                                        String kategoriObat = scanner.next();

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
                                                return;
                                        }

                                        if (obatBaru != null) {
                                            pramuniagalist.insertObat(obatBaru);
                                            System.out.println("Obat berhasil ditambahkan.");
                                        }

                                    } catch (InputMismatchException e) {
                                        System.out.println("Input tidak valid. Pastikan memasukkan angka untuk jenis obat, harga, dan stok.");
                                        scanner.nextLine();
                                    } catch (Exception e) {
                                        System.out.println("Terjadi kesalahan saat menambahkan obat: " + e.getMessage());
                                    }
                                } else if (menu == 5) {
                                    System.out.println("----------------------------------");
                                    System.out.println("Edit stok obat");
                                    System.out.println("----------------------------------");
                                    
                                    try {
                                        System.out.print("Masukkan nama obat: ");
                                        String namaObat = scanner.nextLine();
                                        
                                        boolean cekEditObat = false;
                                        for (Obat obt : daftarObat) {
                                            if (obt.getNama().equalsIgnoreCase(namaObat)) {
                                            	cekEditObat = true;
                                            	break;
                                            }
                                        }
                                        
                                        if(!cekEditObat) {
                                        	System.out.println("Nama obat tidak ada.");
                                        	continue;
                                        }

                                        System.out.print("Masukkan stok obat: ");
                                        int stokUpdate = scanner.nextInt();

                                        if (stokUpdate < 0) {
                                            System.out.println("Stok tidak boleh negatif.");
                                        } else {
                                            pramuniagalist.editStokObat(namaObat, stokUpdate);
                                            System.out.println("Berhasil ubah stok");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Input tidak valid! index harus berupa angka.");
                                        scanner.nextLine();
                                    } catch (Exception e) {
                                        System.out.println("Terjadi kesalahan saat mengedit stok: " + e.getMessage());
                                    }
                                } else if (menu == 6) {
                                    System.out.println("----------------------------------");
                                    System.out.println("Hapus obat");
                                    System.out.println("----------------------------------");
                                    
                                    try {
                                    	System.out.print("Masukkan index obat: ");
                                        int hapusObat = scanner.nextInt();
                                        
                                        pramuniagalist.deleteObat(hapusObat);
                                        System.out.println("Data obat berhasil dihapus");
									} catch (InputMismatchException e) {
                                        System.out.println("Input tidak valid! Stok harus berupa angka.");
                                        scanner.nextLine();
                                    } catch (Exception e) {
                                        System.out.println("Terjadi kesalahan saat mengedit stok: " + e.getMessage());
                                    }
                                } else if (menu == 9) {
                                    System.out.println("----------------------------------");
                                    System.out.println("Selesai");
                                    System.out.println("----------------------------------");

                                } else {
                                    System.out.println("[Error] Silahkan input menu yang ada.");
                                }
                            }
                        }
                    }
                }
                
                if(!berhasilLogin) {
                	System.out.println("Username atau Password salah!");
                }
                
            } else if(!pilihmenu.equalsIgnoreCase("R") && !pilihmenu.equalsIgnoreCase("K")) {
            	System.out.println("Plihan hanya L/R, pilih yang betul.");
            }
            
            if(pilihmenu.equalsIgnoreCase("K")) {
            	System.out.println("Bye Bye");
            	runProgram = false;
            }
        }
	}
	
	public static void printMenuPelanggan() {
        System.out.println( "\n=== << Program Pelanggan >> ===" );
        System.out.println( " 1. Tampilkan semua obat" );
        System.out.println( " 2. Pesan Obat" );
        System.out.println( " 3. Lihat riwayat pembelian" );
        System.out.println( " 4. Ubah password" );
        System.out.println( " 9. Logout" );
        System.out.println( "==========================" );
        System.out.print( "## masuk ke menu : " );
    }
	
	public static void printMenuPramuniaga() {
        System.out.println( "\n=== << Program Pelanggan >> ===" );
        System.out.println( " 1. Tampilkan semua pelanggan" );
        System.out.println( " 2. Hapus Pelanggan" );
        System.out.println( " 3. Tampilkan semua obat" );
        System.out.println( " 4. Tambah data obat" );
        System.out.println( " 5. Edit stok obat" );
        System.out.println( " 6. Hapus obat" );
        System.out.println( " 9. Logout" );
        System.out.println( "==========================" );
        System.out.print( "## masuk ke menu : " );
    }
}
