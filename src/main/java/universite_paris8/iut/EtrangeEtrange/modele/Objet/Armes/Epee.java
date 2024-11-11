package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Tache;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementEpee.Pattern.ComportementAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class Epee extends Arme implements Dommageable {

    private Hitbox hitbox = ConstanteObjet.HITBOX_EPEE;
    private Hitbox h;

    private int durabiliteeRestant;

    public Epee()
    {
        h = hitbox;
        durabiliteeRestant = ConstanteObjet.DURABILITE_EPEE;
    }

    @Override
    public Comportement initComportement() {
        return new ComportementAttaqueEpee(this);
    }

    public long delaie() {
        return ConstanteObjet.DELAIE_UTILISATION_EPEE;
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
    public boolean utiliseePar(Entite entite)
    {
        if (peuUtiliser()) {
            initComportement().lancer(entite);
            peutEtreUtiliser(false);
            startCooldown();
            durabiliteeRestant--;
        }

        return durabiliteeRestant <= 0;
    }

}
