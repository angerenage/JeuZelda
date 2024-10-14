package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Monstre;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Sommet;

import java.util.ArrayList;
import java.util.List;

public class PatternSeDeplaceVersJoueur implements Pattern {

    private NPEs npe;
    private Aetoile aetoile;

    private final int delaie = 1500;
    private long derniereApelle;
    private List<Position> chemin;

    public PatternSeDeplaceVersJoueur(NPEs npe) {
        this.aetoile = new Aetoile();
        this.derniereApelle = 0;
        this.chemin = new ArrayList<>();
        this.npe = npe;
    }

    @Override
    public void effectue() {
        mettreAjourLeGraphe();
        seDeplaceVersJoueur();
    }

    private void mettreAjourLeGraphe() {
        long apelleActuelle = System.currentTimeMillis();

        if (derniereApelle + delaie > apelleActuelle) {
            aetoile.mettreAJourGraphe();
            chemin.clear();
            chemin.addAll(aetoile.trouverChemin(npe.getPosition(), Monde.getMonde().getJoueur().getPosition()));
        }
    }

    private void seDeplaceVersJoueur() {
        if (!chemin.isEmpty()) {
            Position prochainePosition = chemin.remove(0);
            Position positionNpe = npe.getPosition();

            double deltaX = prochainePosition.getX() - positionNpe.getX();
            double deltaY = prochainePosition.getY() - positionNpe.getY();

            Direction direction = Direction.calculerDirection(deltaX, deltaY);

            if (direction != null){
                npe.setDirection(direction);
                npe.seDeplace(1);
                npe.setSeDeplace(true);

                if (Math.abs(deltaX) < 0.2 && Math.abs(deltaY) < 0.2)
                    chemin.remove(0);

            }
            else
            {
                npe.setSeDeplace(false);
            }
        }
    }


}
