package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.tache.TachePluieDeFleches;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstantesSortilege;

public class SortilegePluieDeFleche extends Sortilege {
    @Override
    public boolean utiliseePar(Entite utilisateur) {
        if (peutLancerSort()) {
            for (int i = 0; i < ConstantesSortilege.NOMBRE_FLECHE_PLUIT_DE_FLECHES; i++) {
                Monde.getMonde().ajoutTache(new TachePluieDeFleches((i + 1) * 2, utilisateur));
            }
            estPossibleDeLancerSort(false);
            startCooldown();
        }

        return false;
    }

    @Override
    public long delaie() {
        return ConstantesSortilege.DELAIE_PLUIT_DE_FLECHES;
    }
}
