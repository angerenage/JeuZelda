package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.ComportementOrbe;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;

public class Orbe extends Projectile {
    public Orbe() {
        super(ConstanteObjet.VITESSE_ORBE, ConstanteObjet.NOMBRE_UTLISATION_ORBE, new Hitbox(ConstanteObjet.HITBOX_ORBE_HAUTEUR, ConstanteObjet.HITBOX_ORBE_LARGEUR));
    }

    @Override
    public Comportement getComportement() {
        return new ComportementOrbe(this);
    }

    @Override
    public double degatPhysique() {
        return ConstanteObjet.DEGAT_PHYSIQUE_ORBE;
    }

    @Override
    public double degatSpecial() {
        return ConstanteObjet.DEGAT_SPECIAL_ORBE;
    }

    @Override
    public String getNom() {
        return "orbe";
    }

    @Override
    public int stackMax() {
        return ConstanteObjet.STACK_MAX_ORBE;
    }

    @Override
    public int prixAchat() {
        return ConstanteObjet.PRIX_ACHAT_ORBE;
    }
}
