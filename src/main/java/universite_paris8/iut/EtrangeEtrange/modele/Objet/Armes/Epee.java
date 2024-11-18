package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementEpee.Pattern.ComportementAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;

public class Epee extends Arme implements Dommageable {
    private Hitbox hitbox = new Hitbox(ConstanteObjet.HITBOX_EPEE_HAUTEUR, ConstanteObjet.HITBOX_EPEE_LARGEUR);
    private Hitbox h;

    private int durabiliteeRestant;

    public Epee() {
        h = hitbox;
        durabiliteeRestant = ConstanteObjet.DURABILITE_EPEE;
    }

    @Override
    public Comportement initComportement() {
        return new ComportementAttaqueEpee(this);
    }

    public long delaie() {
        return ConstanteObjet.DELAI_UTILISATION_EPEE;
    }

    @Override
    public String getNom() {
        return "epee";
    }

    @Override
    public int stackMax() {
        return ConstanteObjet.STACK_MAX_EPEE;
    }

    @Override
    public double durabilitee() {
        return durabiliteeRestant;
    }

    @Override
    public int prixAchat() {
        return ConstanteObjet.PRIX_ACHAT_EPEE;
    }

    public Hitbox getHitbox() {
        return h;
    }

    public double getVitesse() {
        return ConstanteObjet.VITESSE_EPEE;
    }

    @Override
    public double degatPhysique() {
        return ConstanteObjet.DEGAT_PHYSIQUE_EPEE;
    }

    @Override
    public double degatSpecial() {
        return ConstanteObjet.DEGAT_SPECIAL_EPEE;
    }

    @Override
    public boolean utiliseePar(Entite entite) {
        if (peuUtiliser()) {
            System.out.println("urse");
            initComportement().lancer(entite);
            peutEtreUtiliser(false);
            startCooldown();
            durabiliteeRestant--;
        }

        return durabiliteeRestant <= 0;
    }
}
