package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Bloc;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dropable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Offensif;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.PieceOr;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;


public class Bloc extends Acteur implements Dropable {
    public Bloc(double x, double y, Direction direction, double pv ,Hitbox hitbox)
    { super(x, y, direction, pv,0, hitbox);}

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
        getMonde().ajouterDropAuSol(new DropAuSol(new PieceOr(), 1, new Position(x, y)));
    }

    @Override
    public boolean estUnEnemie() { return false; }
    @Override
    public void drop() { monde.ajouterDropAuSol(new DropAuSol(new Arc(), 1, new Position(position.getX(), position.getY()))); }
    @Override
    public void agit() {/*NE FAIT RIEN*/}
    @Override
    public void causeCollision(Acteur acteur) {/*NE FAIT RIEN*/}

    @Override
    public void subitAttaque(Dommageable causeDegat, Offensif entiteOffensif) { enleveToutPv();}

}
