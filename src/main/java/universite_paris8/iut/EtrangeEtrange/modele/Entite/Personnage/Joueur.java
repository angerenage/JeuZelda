package universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families.Familie;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Consommable;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ActionAttaqueDistance.ParametreActionAttaqueArc;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ActionAttaqueMelee.ParametreActionAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Humanoide;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.FlecheSimple;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public abstract class Joueur extends Humanoide
{
    protected Carquois carquois;
    private Familie familier;

    public Joueur(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse,Sac sac, Objet objetMainGauche, Objet objetMainDroite, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(pv, attaque, defense, attaqueSpecial, defenseSpecial,vitesse, sac, objetMainGauche, objetMainDroite, monde, x, y, direction, hitbox);
    }


    public boolean recupereObjet(Objet objet)
    {
        return getSac().ajoutItem(objet);
    }


    public void setFamilier(Familie familier) {
        this.familier = familier;
    }

    @Override
    //donne le feu vert au familier pour se deplacer
    public void seDeplace() {
        super.seDeplace();
        if (familier != null) {
            familier.mettreAJourPosition();
        }
    }

    public boolean ifSeDeplace() {
        return this.isSeDeplace();
    }


    @Override
    public void actionMainDroite()
    {
        Objet objet = getObjetMainDroite();

        if (objet != null)
        {
            if (objet instanceof Utilisable)
            {
                if (objet instanceof Arme)
                {
                    attaque();
                }

                if (objet instanceof Consommable)
                {
                    consommer();
                }


            }
        }
    }


    @Override
    public void attaque()
    {
        Arme arme = (Arme) objetMainDroite;
        ParametreActionAttaque actionAttaquer = null;

        if (arme instanceof Epee)
        {
            actionAttaquer = new ParametreActionAttaqueEpee(this);
        }
        else if (arme instanceof Arc)
        {
            actionAttaquer = new ParametreActionAttaqueArc(this,new FlecheSimple());
        }

        arme.attaque(actionAttaquer);


    }

    @Override
    public void consommer()
    {

    }


}
