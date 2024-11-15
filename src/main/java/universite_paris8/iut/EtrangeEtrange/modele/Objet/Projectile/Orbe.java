package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.*;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.ComportementOrbe;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.BFS;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Orbe extends Projectile {
    private static final double DEGAT_PHYSIQUE = ConstanteObjet.DEGAT_PHYSIQUE_ORBE;
    private static final double DEGAT_SPECIAL = ConstanteObjet.DEGAT_SPECIAL_ORBE;
    private static final double VITESSE = ConstanteObjet.VITESSE_ORBE;
    private static final Hitbox HITBOX = ConstanteObjet.HITBOX_ORBE;
    private static final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_ORBE;
    private static final int STACK_MAX  = ConstanteObjet.STACK_MAX_ORBE;

    public Orbe() {
        super(VITESSE,ConstanteObjet.NOMBRE_UTLISATION_ORBE,HITBOX);
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
