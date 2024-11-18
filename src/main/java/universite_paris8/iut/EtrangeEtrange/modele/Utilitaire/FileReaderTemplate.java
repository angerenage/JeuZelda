package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class FileReaderTemplate {
    public void readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int y = 0;

            while ((line = reader.readLine()) != null) {
                String[] blocks = line.split(",");
                for (int x = 0; x < blocks.length; x++) {
                    processValue(Integer.parseInt(blocks[x]), x, y);
                }
                y++;
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public abstract void processValue(int value, int x, int y);
}
