package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.tache;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;

public class TacheArme extends Tache {
    private final Arme arme;

    public TacheArme(long delaie, Arme arme) {
        super(delaie);
        this.arme = arme;
    }

    @Override
    public void tache() {
        arme.peutEtreUtiliser(true);
    }
}
