package Obt;

public class Kapsul extends Obat {
	private String jenis;
	
	public Kapsul(String nama, String kategori, double harga, int stok) {
		super(nama, kategori, harga, stok);
		this.jenis = "Kapsul";
	}
	
	public void tampilkanInfo() {
        System.out.print("Jenis: " + this.jenis + "\t");
        super.tampilkanInfo();
    }
}