import java.util.Scanner;

public class arenapertarungan {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        musuh[] gelombangMonster = new musuh[4];
        gelombangMonster[0] = new slime();
        gelombangMonster[1] = new naga();
        gelombangMonster[2] = new slime();
        gelombangMonster[3] = new zombie();

        System.out.println("==============================");
        System.out.println(" SELAMAT DATANG DI ARENA PERTEMPURAN RPG ");
        System.out.println("==============================\n");
        System.out.println("AWAS! Sekelompok monster immortal menghadang Anda!");

        boolean isBermain = true;

        while (isBermain) {
            System.out.println("\n--- STATUS MONSTER ---");
            for (int i = 0; i < gelombangMonster.length; i++) {
                if (gelombangMonster[i].healthPoint > 0) {
                    System.out.println((i + 1) + ". "+ gelombangMonster[i].namaMusuh+ " (HP: "+ gelombangMonster[i].healthPoint + ")");
                } else {
                    System.out.println((i + 1) + ". " + gelombangMonster[i].namaMusuh + " [TEWAS]");
                }
            }

            System.out.println("5. Kabur dari pertarungan");
            System.out.print("Pilih target monster 1/2/3/4 atau 5 untuk kabur: ");

            try {
                int pilihanTarget = input.nextInt();

                if (pilihanTarget == 5) {
                    System.out.println("Anda lari karena ketakutan dari aura Heavenly Dao monster...");
                    isBermain = false;
                    continue;
                }

                if (pilihanTarget < 1 || pilihanTarget > 4) {
                    System.out.println("Pilihan tidak valid!");
                    continue;
                }

                int indeksMonster = pilihanTarget - 1;

                if (gelombangMonster[indeksMonster].healthPoint <= 0) {
                    throw new targetmatiexception(
                        "TINDAKAN ILEGAL: ANDA TIDAK BISA MENYERANG MONSTER YANG SUDAH MATI!"
                    );
                }

                System.out.print("Masukkan kekuatan serangan Anda (10 - 100): ");
                int power = input.nextInt();
                
                if (power < 10 || power > 100) {
                    throw new serangantidakvalidexception("Kekuatan serangan harus di antara 10 sampai 100!");
                }

                System.out.println("\n>>> HASIL SERANGAN ANDA <<<");
                gelombangMonster[indeksMonster].terimaDamage(power);
                if (gelombangMonster[indeksMonster].healthPoint <= 0) {
                    System.out.println(gelombangMonster[indeksMonster].namaMusuh + " berhasil dikalahkan!");
                    if (gelombangMonster[indeksMonster] instanceof bisaloot) {
                        bisaloot monsterLoot = (bisaloot) gelombangMonster[indeksMonster];
                        monsterLoot.jatuhkanItem();
                    }
                }

                System.out.println("\n--- GILIRAN MONSTER MEMBALAS HEAVENLY DAO ---");
                for (int i = 0; i < gelombangMonster.length; i++) {
                    if (gelombangMonster[i].healthPoint > 0) {
                        musuh monsterAktif = gelombangMonster[i];
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

                for (int i = 0; i < gelombangMonster.length; i++) {
                    if (gelombangMonster[i].healthPoint > 0) {
                        semuaMati = false;
                        break;
                    }
                }
                if (semuaMati) {
                    System.out.println(
                        "\nSELAMAT! ANDA TELAH MEMBUNUH SEMUA GELOMBANG MONSTER!"
                    );
                    isBermain = false;
                }

                System.out.println("--------------------------------------------------");

            } catch (serangantidakvalidexception e) {
                System.out.println("KESALAHAN GAME: " + e.getMessage());

            } catch (targetmatiexception e) {
                System.out.println("KESALAHAN GAME: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Input harus berupa angka bosku!");
                input.nextLine();
            }
        }
        input.close();
        System.out.println("Permainan Berakhir.");
    }
}