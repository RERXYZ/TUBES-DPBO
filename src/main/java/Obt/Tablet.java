package Obt;

public class Tablet extends Obat {
	private String jenis;

	public Tablet(String nama, String kategori, double harga, int stok) {
		super(nama, kategori, harga, stok);
		this.jenis = "Tablet";
	}
	
	public void tampilkanInfo() {
        System.out.print("Jenis: " + this.jenis + "\t");
        super.tampilkanInfo();
    }
}
