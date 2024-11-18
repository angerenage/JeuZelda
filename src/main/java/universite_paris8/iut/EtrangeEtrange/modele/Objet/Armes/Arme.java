package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.Comportement;

public abstract class Arme implements Objet {



    private boolean peuUtiliser;

    public Arme() {
        this.peuUtiliser = true;

    }

    public void startCooldown(){
        Monde.getMonde().ajoutTache(gestionCooldown());
    }

    public Tache gestionCooldown()
    {
        return new Tache(delaie()) {
            @Override
            public void tache() {
                peuUtiliser = true;
            }
        };
    }

    public boolean peuUtiliser(){
        return peuUtiliser;
    }

    public void peutEtreUtiliser(boolean bool){
        peuUtiliser = bool;
    }
    public abstract Comportement initComportement();

    public abstract long delaie();
}
