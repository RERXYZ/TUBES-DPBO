package Obt;

public class Obat {
	protected String nama;
    protected String kategori;
    protected double harga;
    protected int stok;

    public Obat(String nama, String kategori, double harga, int stok) {
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }
    
    public String getNama() { 
    	return nama; 
    }
    
    public String getKategori() { 
    	return kategori; 
    }
    
    public double getHarga() {
    	return harga;
    }
    
    public void setHarga(int harga) {
    	this.harga = harga;
    }
    
    public int getStok() {
    	return stok;
    }

    public void setStok(int stok) {
    	this.stok = stok;
    }

    public void tampilkanInfo() {
        System.out.print("Nama Obat: " + nama);
        System.out.print("\tKategori: " + kategori);
        System.out.print("\tHarga: " + harga);
        System.out.println("\tStok: " + stok);
    }
}
