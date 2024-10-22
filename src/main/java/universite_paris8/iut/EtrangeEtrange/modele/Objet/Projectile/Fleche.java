package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;

public class Fleche extends Projectile {

    public Fleche() {
        super(ConstanteObjet.DURABILITE_FLECHE, ConstanteObjet.VITESSE_FLECHE, ConstanteObjet.HITBOX_FLECHE);
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


    @Override
    public String typeActeur() {
        return "fleche";
    }

    @Override
    public void dropApresMort() {

    }

    @Override
    public boolean estUnEnemie() {
        return false;
    }

    @Override
    public int stackMax() {
        return ConstanteObjet.STACK_MAX_FLECHE;
    }

    @Override
    public double durabilitee() {
        return getPv();
    }

    @Override
    public int prixAchat() {
        return ConstanteObjet.PRIX_ACHAT_FLECHE;
    }

}