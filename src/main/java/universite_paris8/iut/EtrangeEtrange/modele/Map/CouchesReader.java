package universite_paris8.iut.EtrangeEtrange.modele.Map;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.FileReaderTemplate;

public class CouchesReader extends FileReaderTemplate {
    private final int[][] couche;

    public CouchesReader(int[][] couche) {
        this.couche = couche;
    }

    @Override
    public void processValue(int value, int x, int y) {
        if (y < couche.length && x < couche[y].length) {
            couche[y][x] = value;
        }
    }
}
