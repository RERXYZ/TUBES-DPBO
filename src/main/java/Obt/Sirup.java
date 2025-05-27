package Obt;

public class Sirup extends Obat {
	private String jenis;
	
	public Sirup(String nama, String kategori, double harga, int stok) {
		super(nama, kategori, harga, stok);
		this.jenis = "Sirup";
	}
	
	public void tampilkanInfo() {
        System.out.print("Jenis: " + this.jenis + "\t\t");
        super.tampilkanInfo();
    }
}
