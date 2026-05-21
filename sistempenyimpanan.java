import java.io.*;
import java.util.ArrayList;

public class sistempenyimpanan {
    private static final String NAMA_FILE = "savegame_rpg.dat";
    public static void simpanGame(ArrayList<musuh> dataMonster) {

        try {
            FileOutputStream fileOut = new FileOutputStream(NAMA_FILE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(dataMonster);

            objectOut.close();
            fileOut.close();

            System.out.println("[SISTEM] PROGRESS PERMAINAN BERHASIL DISIMPAN!");
        } catch (IOException e) {
            System.out.println("[ERROR] GAGAL MENYIMPAN GAME: " + e.getMessage());
        }
    }

    public static ArrayList<musuh> loadGame() {
        ArrayList<musuh> dataTermuat = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream(NAMA_FILE);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            dataTermuat = (ArrayList<musuh>) objectIn.readObject();

            objectIn.close();
            fileIn.close();

            System.out.println("[SISTEM] PROGRESS PERMAINAN BERHASIL DIMUAT!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[SISTEM] TIDAK ADA SAVE DATA. MEMULAI GAME BARU.");
        }
        return dataTermuat;
    }
}