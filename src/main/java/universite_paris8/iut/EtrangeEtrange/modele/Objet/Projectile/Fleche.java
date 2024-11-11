package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.ComportementFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class Fleche extends Projectile
{
    public static final double DEGAT_PHYSIQUE = ConstanteObjet.DEGAT_PHYSIQUE_FLECHE;
    public static final double DEGAT_SPECIAL = ConstanteObjet.DEGAT_SPECIAL_FLECHE;
    public static final double VITESSE = ConstanteObjet.VITESSE_FLECHE;
    public static final Hitbox HITBOX = ConstanteObjet.HITBOX_FLECHE;
    public static final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_FLECHE;
    public static final int STACK_MAX = ConstanteObjet.STACK_MAX_FLECHE;


    public Fleche()
    {
        super(VITESSE,ConstanteObjet.NOMBRE_UTLISATION_FLECHE,HITBOX);
    }

    @Override
    public double degatPhysique() {
        return DEGAT_PHYSIQUE;
    }

    @Override
    public double degatSpecial() {
        return DEGAT_SPECIAL;
    }

    @Override
    public String getNom() {
        return "fleche";
    }

    @Override
    public int stackMax() {return STACK_MAX;}

    @Override
    public int prixAchat() { return PRIX_ACHAT; }

    @Override
    public Comportement getComportement() {
        return new ComportementFleche(this);
    }
}