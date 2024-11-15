package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionJoueurDansVisionDecorateur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.PatternCompositeStrategie;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternMonstre.PatternSquelette;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ParametreMonstre;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.PieceOr;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;

public class Squelette extends Monstre {
    private Epee epee ;

    public Squelette(double x, double y, Direction direction, Hitbox hitbox, Joueur joueur, Aetoile aetoile) {
        super(x, y, direction,
              ParametreMonstre.PV_SQUELETTE,
              ParametreMonstre.ATTAQUE_SQUELETTE,
              ParametreMonstre.DEFENSE_SQUELETTE,
              ParametreMonstre.ATTAQUE_SPECIALE_SQUELETTE,
              ParametreMonstre.DEFENSE_SPECIALE_SQUELETTE,
              ParametreMonstre.VITESSE_SQUELETTE,
              hitbox);

        epee = new Epee();
    }

    @Override
    protected Pattern initPattern() {
        return new PatternSquelette(this);
    }

    @Override
    public String typeActeur() {
        return "Squelette";
    }

    @Override
    public void dropApresMort() {
        double x = getPosition().getX();
        double y = getPosition().getY();
        getMonde().ajouterDropAuSol(new DropAuSol(new PieceOr(), 1, new Position(x, y)));
    }

    @Override
    public boolean estUnEnemie() {
        return true;
    }

    @Override
    public void faitUneAttaque() {
        epee.utiliseePar(this);
    }
}
