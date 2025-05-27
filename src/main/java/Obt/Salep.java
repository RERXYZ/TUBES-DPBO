package Obt;

public class Salep extends Obat {
	private String jenis;
	
	public Salep(String nama, String kategori, double harga, int stok) {
		super(nama, kategori, harga, stok);
		this.jenis = "Salep";
	}
	
	public void tampilkanInfo() {
        System.out.print("Jenis: " + this.jenis + "\t");
        super.tampilkanInfo();
    }
}
