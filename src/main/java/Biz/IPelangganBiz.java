package Biz;

public interface IPelangganBiz {
	public void printAllObat();
	
	public void insertPelanggan(String username, String gender, String noTelp, int umur, String password);
	
	public void pesanObat(String namaObat, int jumlah);
	
	public void lihatRiwayatPembelian();
	
	public void changePassword(String noTelp, String passwordBaru);
}
