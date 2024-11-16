package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Invocation;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.FabriquePnj;

public abstract class SortilegeInvocation extends Sortilege implements Utilisable {
    private final Class<? extends Acteur> acteur;

    public SortilegeInvocation(Class<? extends Acteur> acteur) {
        this.acteur = acteur;
    }

    private boolean peuInvoquer(Entite utilisateur) {
        boolean peuInvoquer = !(utilisateur instanceof Joueur joueur);
        return peuInvoquer;
    }

    @Override
    public boolean utiliseePar(Entite entite) {
        if (peuInvoquer(entite)) FabriquePnj.fabriquePnj(acteur,5,entite.getMonde(),entite.getPosition());
        return false;
    }
}
