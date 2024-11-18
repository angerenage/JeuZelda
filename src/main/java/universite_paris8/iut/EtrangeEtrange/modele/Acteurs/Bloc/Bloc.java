package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Bloc;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Dropable;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Offensif;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.Piece;
import universite_paris8.iut.EtrangeEtrange.modele.stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ParametreMonstre;


public class Bloc extends Acteur implements Dropable {
    public Bloc(double x, double y, Direction direction)
    { super(x, y, direction, ParametreMonstre.PV_BLOC,0, ParametreMonstre.HITBOX_BLOC);}

    @Override
    public void seFaitPousser(Acteur acteur)
    {
        setDirection(acteur.getDirection());
        setVitesse(acteur.getVitesse());

        if (peutSeDeplacer()) {
            seDeplace(1);
            setVitesse(0);
        }

    }

    @Override
    public void subitCollision(Acteur acteur) {acteur.causeCollision(this);}

    @Override
    public String typeActeur() { return "bloc";}

    @Override
    public void dropApresMort() {
        double x = getPosition().getX();
        double y = getPosition().getY();
        getMonde().ajouterDropAuSol(new DropAuSol(new Piece(), new Position(x, y)));
    }

    @Override
    public boolean estUnEnemie() { return false; }
    @Override
    public void drop() { monde.ajouterDropAuSol(new DropAuSol(new Arc(),  new Position(position.getX(), position.getY()))); }
    @Override
    public void agit() {/*NE FAIT RIEN*/}
    @Override
    public void causeCollision(Acteur acteur) {/*NE FAIT RIEN*/}

    @Override
    public void subitAttaque(Dommageable causeDegat, Offensif entiteOffensif) { enleveToutPv();}

}
