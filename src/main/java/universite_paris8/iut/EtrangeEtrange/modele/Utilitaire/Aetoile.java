package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

import java.util.*;

public class Aetoile {
    private Monde monde;
    private Sommet[][] graphe;
    private List<Position> chemin;

    public Aetoile() {
        this.monde = Monde.getMonde();
        this.chemin = new ArrayList<>();
        initialiserGraphe();
    }

    private void initialiserGraphe() {
        int hauteur = Monde.getSizeMondeHauteur();
        int largeur = Monde.getSizeMondeLargeur();
        graphe = new Sommet[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                boolean traversable = monde.getCarte().estTraversable(x, y);
                graphe[y][x] = new Sommet(new Position(x, y), traversable);
            }
        }

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (graphe[y][x].isTraversable()) {
                    definirVoisins(graphe[y][x], x, y);
                }
            }
        }
    }

    private void definirVoisins(Sommet sommet, int x, int y) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (coordonneesValides(nx, ny) && graphe[ny][nx].isTraversable()) {
                sommet.addVoisin(graphe[ny][nx]);
            }
        }
    }

    private boolean coordonneesValides(int x, int y) {
        return x >= 0 && y >= 0 && x < graphe[0].length && y < graphe.length;
    }

    public void mettreAJourGraphe() {
        for (int y = 0; y < graphe.length; y++) {
            for (int x = 0; x < graphe[0].length; x++) {
                graphe[y][x].setTraversable(monde.getCarte().estTraversable(x, y));
            }
        }
    }

    public List<Position> trouverChemin(Position depart, Position arrivee) {
        mettreAJourGraphe();

        Sommet sommetDepart = positionToSommet(depart);
        Sommet sommetArrivee = positionToSommet(arrivee);

        if (sommetDepart == null || sommetArrivee == null) {
            return Collections.emptyList();
        }

        PriorityQueue<Noeud> openList = new PriorityQueue<>(Comparator.comparingDouble(Noeud::getF));
        Map<Sommet, Noeud> allNodes = new HashMap<>();

        Noeud startNode = new Noeud(sommetDepart, null, 0, sommetDepart.distance(sommetArrivee));
        openList.add(startNode);
        allNodes.put(sommetDepart, startNode);

        while (!openList.isEmpty()) {
            Noeud currentNode = openList.poll();

            if (currentNode.getSommet().equals(sommetArrivee)) {
                return reconstruireChemin(currentNode);
            }

            for (Sommet voisin : currentNode.getSommet().getVoisins()) {
                double tentativeG = currentNode.getG() + currentNode.getSommet().distance(voisin);

                Noeud voisinNode = allNodes.getOrDefault(voisin, new Noeud(voisin));
                if (tentativeG < voisinNode.getG()) {
                    voisinNode.setParent(currentNode);
                    voisinNode.setG(tentativeG);
                    voisinNode.setH(voisin.distance(sommetArrivee));
                    allNodes.put(voisin, voisinNode);
                    if (!openList.contains(voisinNode)) {
                        openList.add(voisinNode);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Position> reconstruireChemin(Noeud noeud) {
        List<Position> chemin = new ArrayList<>();
        while (noeud != null) {
            chemin.add(getCentreSommet(noeud.getSommet()));
            noeud = noeud.getParent();
        }
        Collections.reverse(chemin);
        this.chemin = chemin;
        return chemin;
    }

    public Sommet positionToSommet(Position position) {
        int x = (int) Math.floor(position.getX());
        int y = (int) Math.floor(position.getY());
        return coordonneesValides(x, y) ? graphe[y][x] : null;
    }

    public Position getCentreSommet(Sommet sommet) {
        return new Position(sommet.getPosition().getX() + 0.5, sommet.getPosition().getY() + 0.5);
    }

    public List<Position> getChemin() {
        return chemin;
    }

    private static class Noeud {
        private Sommet sommet;
        private Noeud parent;
        private double g;
        private double h;

        public Noeud(Sommet sommet) {
            this.sommet = sommet;
            this.g = Double.MAX_VALUE;
        }

        public Noeud(Sommet sommet, Noeud parent, double g, double h) {
            this.sommet = sommet;
            this.parent = parent;
            this.g = g;
            this.h = h;
        }

        public Sommet getSommet() {
            return sommet;
        }

        public Noeud getParent() {
            return parent;
        }

        public void setParent(Noeud parent) {
            this.parent = parent;
        }

        public double getG() {
            return g;
        }

        public void setG(double g) {
            this.g = g;
        }

        public double getF() {
            return g + h;
        }

        public void setH(double h) {
            this.h = h;
        }
    }
}
