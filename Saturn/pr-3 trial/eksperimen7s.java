import java.util.Arrays;

class DaftarNama {
    private String[] nama;

    public DaftarNama(String[] nama) {
        this.nama = nama;
    }

    public void tampilkanNama() {
        for (String n : nama) {
            System.out.println(n);
        }
    }

    public boolean cariNama(String keyword) {
        for (String n : nama) {
            if (n.equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }

    public void urutkanNama() {
        Arrays.sort(nama);
    }
}

public class eksperimen7s {
    public static void main(String[] args) {
        String[] data = {"Andi", "Budi", "Citra", "Dewi", "Eka"};
        DaftarNama dn = new DaftarNama(data);

        dn.urutkanNama();
        dn.tampilkanNama();

        System.out.println("Apakah 'Dewi' ada? " + dn.cariNama("Dewi"));
    }
}
