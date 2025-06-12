package Biz;

import Pengambilan.*;

public interface IPelangganBiz {
	public void printAllObat();
	
	public void setCurrentUser(String noTelp);
	
	public void initializePelanggan();
	
	public void insertPelanggan(String username, String gender, String noTelp, int umur, String password);
	
	public void pesanObat(String namaObat, int jumlah, MetodePengambilan metodePengambilan, int pembayaran);
	
	public void lihatRiwayatPembelian();
	
	public void changePassword(String noTelp, String passwordBaru);
}
