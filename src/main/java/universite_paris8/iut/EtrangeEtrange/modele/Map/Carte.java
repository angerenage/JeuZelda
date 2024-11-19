package universite_paris8.iut.EtrangeEtrange.modele.Map;

import java.util.ArrayList;

import universite_paris8.iut.EtrangeEtrange.vues.constantes.PathRessources;

public class Carte {
    private int[][] sol;
    private int[][] traversable;
    private int[][] nontraversable;

    /**
     * Constructeur pour initialiser les couches de la carte à partir des fichiers CSV.
     *
     * @param chemin Chemin vers les fichiers.
     * @param nommap Nom de la carte.
     * @param hauteur Hauteur de la carte.
     * @param largeur Largeur de la carte.
     */
    public Carte(String nommap, int hauteur, int largeur) {
        this.sol = new int[hauteur][largeur];
        this.traversable = new int[hauteur][largeur];
        this.nontraversable = new int[hauteur][largeur];
        chargerCouches(nommap, hauteur, largeur);
    }

    /**
     * Charge les différentes couches de la carte à partir des fichiers CSV.
     *
     * @param chemin Chemin vers les fichiers.
     * @param nommap Nom de la carte.
     * @param hauteur Hauteur de la carte.
     * @param largeur Largeur de la carte.
     */
    private void chargerCouches(String nommap, int hauteur, int largeur) {
        ArrayList<int[][]> couches = new ArrayList<>();
        couches.add(this.sol);
        couches.add(this.traversable);
        couches.add(this.nontraversable);

        String[] fichiers = {"sol", "traversable", "nontraversable"};

        for (int i = 0; i < couches.size(); i++) {
			CouchesReader coucheReader = new CouchesReader(couches.get(i));
			coucheReader.readFile(String.format(PathRessources.MONDE_BASE_PATH, nommap, fichiers[i]));
		}
    }

    /**
     * Récupère le type de sol à une position donnée.
     *
     * @param x Coordonnée x.
     * @param y Coordonnée y.
     * @return Type de sol ou -1 si hors de la carte.
     */
    public int getTileType(int x, int y) {
        if (x >= 0 && x < sol[0].length && y >= 0 && y < sol.length) {
            return sol[y][x];
        } else {
            return -1;
        }
    }

    /**
     * Renvoie toutes les couches sous forme de liste d'ArrayList.
     *
     * @return Liste des couches.
     */
    public ArrayList<int[][]> getToutesLesCouches() {
        ArrayList<int[][]> couches = new ArrayList<>();
        couches.add(this.sol);
        couches.add(this.traversable);
        couches.add(this.nontraversable);
        return couches;
    }

    /**
     * Vérifie si une position donnée est traversable.
     *
     * @param x Coordonnée x.
     * @param y Coordonnée y.
     * @return true si traversable, false sinon.
     */
    public boolean estTraversable(int x, int y) {
        if (x >= 0 && x < traversable[0].length && y >= 0 && y < traversable.length) {
            return traversable[y][x] != -1;
        }
        return false;
    }

    /**
     * Vérifie si une position donnée est non traversable.
     *
     * @param x Coordonnée x.
     * @param y Coordonnée y.
     * @return true si non traversable, false sinon.
     */
    public boolean estNonTraversable(int x, int y) {
        if (x >= 0 && x < nontraversable[0].length && y >= 0 && y < nontraversable.length) {
            return nontraversable[y][x] != -1;
        }
        return false;
    }
}

