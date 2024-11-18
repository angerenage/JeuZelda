package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.tache.TacheArme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

public abstract class Arme implements Objet {

    private boolean peuUtiliser;

    public Arme() {
        this.peuUtiliser = true;
    }

    public void startCooldown() {
        Monde.getMonde().ajoutTache(new TacheArme(delaie(), this));
    }

    public boolean peuUtiliser() {
        return peuUtiliser;
    }

    public void peutEtreUtiliser(boolean bool) {
        peuUtiliser = bool;
    }

    public abstract Comportement initComportement();

    public abstract long delaie();
}
