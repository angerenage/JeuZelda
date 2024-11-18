package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort;


import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Tache;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

public abstract class Sortilege implements Utilisable {
    private boolean peutLancerSort;

    public Sortilege() {
        this.peutLancerSort = true;
    }

    public void startCooldown(){
        Monde.getMonde().ajoutTache(gestionCooldown());
    }

    public Tache gestionCooldown(){
        return new Tache(delaie()) {
            @Override
            public void tache() {
                peutLancerSort = true;
            }
        };
    }

    public boolean peutLancerSort(){
        return peutLancerSort;
    }

    public void estPossibleDeLancerSort(boolean bool){
        peutLancerSort = bool;
    }

    public abstract long delaie();
}
