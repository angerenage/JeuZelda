package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.ComportementFleche;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;

public class Fleche extends Projectile {
    public Fleche() {
        super(ConstanteObjet.VITESSE_FLECHE, ConstanteObjet.DURABILITE_FLECHE, ConstanteObjet.HITBOX_FLECHE);
    }

    @Override
    public double degatPhysique() {
        return ConstanteObjet.DEGAT_PHYSIQUE_FLECHE;
    }

    @Override
    public double degatSpecial() {
        return ConstanteObjet.DEGAT_SPECIAL_FLECHE;
    }

    @Override
    public String getNom() {
        return "fleche";
    }

    public int stackMax() {
        return ConstanteObjet.STACK_MAX_FLECHE;
    }

    @Override
    public int prixAchat() {
        return ConstanteObjet.PRIX_ACHAT_FLECHE;
    }

    @Override
    public Comportement getComportement() {
        return new ComportementFleche(this);
    }
}