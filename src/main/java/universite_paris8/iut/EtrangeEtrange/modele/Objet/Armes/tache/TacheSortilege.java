package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.tache;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;

public class TacheSortilege extends Tache {
    private final Sortilege sortilege;

    public TacheSortilege(long delaie, Sortilege sortilege) {
        super(delaie);
        this.sortilege = sortilege;
    }

    @Override
    public void tache() {
        sortilege.estPossibleDeLancerSort(true);
    }
}
