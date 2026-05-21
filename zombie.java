public class zombie extends musuh implements bisaloot {
    public zombie() {
    super("Zombie Merangkak", 150);
    }

    @Override
    public void serangPemain() {
    System.out.println(this.namaMusuh + " mencakar sampai kelluar darah! Player -20 HP");
    }

    @Override
    public void suaraKhas() {
    System.out.println(this.namaMusuh + " HAURRRR ");
    }

    @Override
    public void jatuhkanItem() {
    System.out.println(this.namaMusuh + " holy crystal ");
    }
}