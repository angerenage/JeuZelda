package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.tache;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.FlechePositionSpray;

public class TachePluieDeFleches extends Tache {
    private Entite utilisateur;

    public TachePluieDeFleches(long delaie, Entite utilisateur) {
        super(delaie);
        this.utilisateur = utilisateur;
    }

    @Override
    public void tache() {
        FlechePositionSpray fleche = new FlechePositionSpray();
        fleche.utiliseePar(utilisateur);
    }
}
