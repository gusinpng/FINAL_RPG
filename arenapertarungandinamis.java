import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class arenapertarungandinamis {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<musuh> gelombangMonster = new ArrayList<>();
        gelombangMonster.add(new slime());
        gelombangMonster.add(new naga());
        gelombangMonster.add(new slime());
        gelombangMonster.add(new zombie());

        System.out.println("==============================");
        System.out.println(" SELAMAT DATANG DI ARENA PERTEMPURAN RPG ");
        System.out.println("==============================\n");
        System.out.println("AWAS! Sekelompok monster immortal menghadang Anda!");

        boolean isBermain = true;

        while (isBermain && !gelombangMonster.isEmpty()) {

            System.out.println("\n--- STATUS MONSTER ---");
            for (int i = 0; i < gelombangMonster.size(); i++) {
                musuh m = gelombangMonster.get(i);
                System.out.println((i + 1) + ". " + m.namaMusuh + " (HP: " + m.healthPoint + ")");
            }

            System.out.println("-------------------------");
            System.out.println("6. [SAVE GAME] SIMPAN PROGRESS PERTARUNGAN");
            System.out.println("7. [LOAD GAME] MUAT PROGRESS SEBELUMNYA");
            System.out.println("0. KABUR DARI PERTARUNGAN");
            System.out.println("\n PILIH TARGET MONSTER (1." + gelombangMonster.size() + ") atau aksi lainnya; ");

            try {

                int pilihanTarget = input.nextInt();
                if (pilihanTarget == 0) {
                    System.out.println("Anda lari karena ketakutan dari aura Heavenly Dao monster...");
                    isBermain = false;
                    continue;
                }
                else if (pilihanTarget == 6 ) {
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savegame_rpg.dat"))) {
                        oos.writeObject(gelombangMonster);
                        System.out.println (">>> BERHASIL: GAME TELAH DISIMPAN! <<<");
                    } catch (IOException e) {
                        System.out.println(">>> GAGAL: TERJADI KESALAHAN SAAT MENYIMPAN GAME." + e.getMessage());
                    }
                    continue; //mengulang menu tanpa memicu monstrer
                }
                else if (pilihanTarget == 7 ) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savegame_rpg.dat"))) {
                        gelombangMonster = (ArrayList<musuh>) ois.readObject();
                        System.out.println(">>> BERHASIL: Game berhasil dimuat! <<<");

                    } catch (FileNotFoundException e) {
                        System.out.println (">>> GAGAL: File save game belum ada. Silakan Save Game terlebih dahulu!");

                }     catch (IOException | ClassNotFoundException e) {
                        System.out.println (">>> GAGAL: Terjadi kesalahan saat membaca file save. " + e.getMessage());
                    }
                    continue; // Mengulang menu tanpa memicu serangan monster
                }
                
                if (pilihanTarget < 1 || pilihanTarget > gelombangMonster.size()) {
                    System.out.println("Pilihan tidak valid!");
                    continue;
                }

                int indeksMonster = pilihanTarget - 1;
                musuh target = gelombangMonster.get(indeksMonster);
                System.out.print("Masukkan kekuatan serangan Anda (10 - 100): ");

                int power = input.nextInt();
                if (power < 10 || power > 100) {
                    throw new serangantidakvalidexception("Kekuatan serangan harus di antara 10 sampai 100!");
                }

                System.out.println("\n>>> HASIL SERANGAN ANDA <<<");
                target.terimaDamage(power);
                if (target.healthPoint <= 0) {
                    System.out.println(target.namaMusuh + " hancur menjadi debu!");
                    if (target instanceof bisaloot) {
                        bisaloot loot = (bisaloot) target;
                        loot.jatuhkanItem();
                    }
                    gelombangMonster.remove(indeksMonster);
                }

            } catch (Exception e) {
                System.out.println("Target kesalahan sistem!" + e.getMessage());
                input.nextLine();
                continue;
            }

            if (gelombangMonster.isEmpty()) {
                System.out.println("\nSELAMAT! SEMUA MONSTER TELAH DISAPU BERSIH");
                break;
            }

            System.out.println("\n--- GILIRAN MONSTER MEMBALAS HEAVENLY DAO ---");
            for (int i = 0; i < gelombangMonster.size(); i++) {
                if (gelombangMonster.get(i).healthPoint > 0) {
                    musuh monsterAktif = gelombangMonster.get(i);
                    monsterAktif.suaraKhas();
                    if (monsterAktif instanceof bisaterbang) {
                        System.out.println("PERINGATAN! SERANGAN UDARA TERDETEKSI!");
                        bisaterbang monsterTerbang = (bisaterbang) monsterAktif;
                        monsterTerbang.lepasLandas();
                        monsterTerbang.seranganUdara();

                    } else {
                        monsterAktif.serangPemain();
                    }
                }
            }

            boolean semuaMati = true;
            for (int i = 0; i < gelombangMonster.size(); i++) {
                if (gelombangMonster.get(i).healthPoint > 0) {
                    semuaMati = false;
                    break;
                }
            }

            if (semuaMati) {
                System.out.println("\nSELAMAT! ANDA TELAH MEMBUNUH SEMUA GELOMBANG MONSTER!");
                isBermain = false;
            }
        }
        input.close();
        System.out.println("Permainan Berakhir.");
    }
}