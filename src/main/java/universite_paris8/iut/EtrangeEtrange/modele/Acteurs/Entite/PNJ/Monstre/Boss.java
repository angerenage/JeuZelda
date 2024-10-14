package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public abstract class Boss extends Monstre {


    public Boss(double x, double y, Direction direction, double pv,double atk, double defense,double atkSpe, double defenseSpecial, double vitesse, Hitbox hitbox) {
        super(x, y, direction, pv, atk,defense,atkSpe ,defenseSpecial, vitesse, hitbox);
    }


}
