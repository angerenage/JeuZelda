package universite_paris8.iut.EtrangeEtrange.modele.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaqueDistance.ActionAttaqueAvecArc;
import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaqueMelee.ActionAttaquerAvecEpee;
import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaquer;
import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionSurObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Mangeable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.FlecheSimple;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public abstract class Humanoide extends EntiteOffensif
{
    protected Objet objetMainGauche;
    protected Objet objetMainDroite;

    protected Sac sac;
    protected Carquois carquois;

    public Humanoide(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Sac sac, Objet objetMainGauche,Objet objetMainDroite, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, monde, x, y, direction, hitbox);
        this.sac = sac;
        this.objetMainGauche = objetMainGauche;
        this.objetMainDroite = objetMainDroite;
    }


    public abstract void actionMainDroite();





    @Override
    protected double subitDegatPhysique(double attaque,double forceEntite) {
        return attaque - getDefense().getDefenseActuelle();
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial,double forceEntite) {
        return attaqueSpecial - getDefenseSpecial().getDefenseSpecialActuelle();
    }

    public Sac getSac()
    {
        return this.sac;
    }
    public Objet getObjetMainDroite()
    {
        return this.objetMainDroite;
    }
}
