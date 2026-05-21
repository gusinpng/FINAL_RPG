public class naga extends musuh implements bisaterbang, bisaloot {
    public naga() {
    super("Naga Dao pembawa Surga", 500);
    }

    @Override
    public void serangPemain() {
    System.out.println(this.namaMusuh + "  terbang dan menyemprot aura heavenly Dao! Player -50 HP");
    }

    @Override
    public void suaraKhas() {
    System.out.println(this.namaMusuh + " HAI PENGECUT DUNIA IMMORTAL ");
    }

     @Override
    public void lepasLandas() {
    System.out.println(this.namaMusuh + " terbang tinggi! Susah diserang. ");
    }

     @Override
    public void seranganUdara() {
    System.out.println(this.namaMusuh + " menyemburkan napas surgawi! Pemain -80 HP. ");
    }

    @Override
    public void jatuhkanItem() {
    System.out.println(this.namaMusuh + " Blade Of Despair ");
    }
}