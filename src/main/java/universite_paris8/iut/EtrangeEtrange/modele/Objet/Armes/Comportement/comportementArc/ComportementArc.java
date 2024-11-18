package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.comportementArc;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;

public class ComportementArc implements Comportement {
    private Arc arc;

    public ComportementArc(Arc arc) {
        this.arc = arc;
    }

    @Override
    public void finit() {
        arc.setFleche(null);
    }

    @Override
    public void lancer(Entite utilisateur) {
        Fleche fleche = arc.getFleche();
        if (fleche != null) {
            fleche.utiliseePar(utilisateur);
        }
    }
}
