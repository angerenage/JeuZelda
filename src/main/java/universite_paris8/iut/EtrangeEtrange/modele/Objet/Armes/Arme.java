package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaquer;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

public abstract class Arme  extends Objet implements Dommageable, Utilisable
{
    public void attaque(ActionAttaquer action)
    {
        utilise(action);
    }
}
