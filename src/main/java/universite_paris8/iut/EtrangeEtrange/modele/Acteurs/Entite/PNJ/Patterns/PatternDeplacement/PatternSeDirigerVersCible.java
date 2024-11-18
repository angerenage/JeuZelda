package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;
import java.util.List;

public class PatternSeDirigerVersCible implements Pattern {
    private Acteur acteur, cible;
    private Aetoile aetoile;
    private final int delai = 1;
    private long derniereMiseAJour;
    private List<Position> chemin;

    public PatternSeDirigerVersCible(Acteur acteur, Acteur cible) {
        this.acteur = acteur;
        this.cible = cible;
        this.aetoile = new Aetoile();
        this.chemin = new ArrayList<>();
        this.derniereMiseAJour = 0;
    }

    @Override
    public void effectue() {
        mettreAJourChemin();
        deplacerVersProchainePosition();
    }

    private void mettreAJourChemin() {
        long currentTime = System.currentTimeMillis();

        if (chemin.isEmpty() || currentTime - derniereMiseAJour >= delai ) {
            chemin = aetoile.trouverChemin(acteur.getPosition(), cible.getPosition());
            derniereMiseAJour = currentTime;
        }
    }



    private void deplacerVersProchainePosition() {
        if (chemin.size() > 1) {
            Position prochainePosition = chemin.get(1);




            Direction direction = calculerDirectionVers(prochainePosition);




            if (direction != null) {
                acteur.setDirection(direction);

                if (acteur.peutSeDeplacer()) {
                    acteur.setSeDeplace(true);
                    acteur.seDeplace(1);
                } else {
                    acteur.setSeDeplace(false);
                }

                if (positionAtteinte(prochainePosition)) {
                    chemin.remove(1);
                }
            } else {
                acteur.setSeDeplace(false);
            }
        } else {
            acteur.setSeDeplace(false);
        }
    }

    private Direction calculerDirectionVers(Position prochainePosition) {
        double deltaX = prochainePosition.getX() - acteur.getPosition().getX();
        double deltaY = prochainePosition.getY() - acteur.getPosition().getY();

        System.out.println("Position Acteur: " + acteur.getPosition().getX() + ", " + acteur.getPosition().getY());
        System.out.println("Prochaine Position: " + prochainePosition.getX() + ", " + prochainePosition.getY());
        System.out.println("DeltaX: " + deltaX + ", DeltaY: " + deltaY);
        return Direction.calculerDirection(deltaX, deltaY);
    }

    private boolean positionAtteinte(Position position) {
        // Vérifier si l'acteur est suffisamment proche de la position cible
        double tolerance = 0.2;  // Ajuster la tolérance si nécessaire
        return Math.abs(acteur.getPosition().getX() - position.getX()) < tolerance &&
                Math.abs(acteur.getPosition().getY() - position.getY()) < tolerance;
    }
}
