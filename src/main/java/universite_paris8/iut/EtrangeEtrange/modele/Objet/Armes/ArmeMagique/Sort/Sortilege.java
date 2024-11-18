package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.tache.TacheSortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Utilisable;

public abstract class Sortilege implements Utilisable {
    private boolean peutLancerSort;

    public Sortilege() {
        this.peutLancerSort = true;
    }

    public void startCooldown() {
        Monde.getMonde().ajoutTache(new TacheSortilege(delaie(), this));
    }

    public boolean peutLancerSort() {
        return peutLancerSort;
    }

    public void estPossibleDeLancerSort(boolean bool) {
        peutLancerSort = bool;
    }

    public abstract long delaie();
}
