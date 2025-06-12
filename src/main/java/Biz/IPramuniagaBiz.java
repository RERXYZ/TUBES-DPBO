package Biz;

import Obt.Obat;
import Usr.User;

public interface IPramuniagaBiz {
	public void initializeAdmin();
	
	public void printAllPelanggan();
	
	public void deletePelanggan(String noTelp);
    
    public void initializeObat();
    
    public void insertObat(Obat obat);
    
    public void editStokObat(String namaObat, int stokBaru);
    
    public void deleteObat(int number);
    
    public void printAllObat();
}
