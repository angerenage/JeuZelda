package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.comportementArc;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

public class ComportementArc implements Comportement {
    private Fleche fleche;
    public ComportementArc(Fleche fleche) {
        this.fleche = fleche;
    }

    @Override
    public void finit() {
        fleche = null;
    }

    @Override
    public void lancer(Entite utilisateur) {
        if (fleche != null) fleche.utiliseePar(utilisateur);
    }
}
