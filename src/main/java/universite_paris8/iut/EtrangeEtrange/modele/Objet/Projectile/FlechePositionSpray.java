package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.ComportementFlechePositionSpray;

public class FlechePositionSpray extends Fleche
{
    @Override
    public Comportement getComportement(){
        return new ComportementFlechePositionSpray(this);
    }
}
