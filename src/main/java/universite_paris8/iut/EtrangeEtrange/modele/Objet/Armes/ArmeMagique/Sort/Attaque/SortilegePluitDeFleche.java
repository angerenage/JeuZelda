package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Tache;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.FlechePositionSpray;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstantesSortilege;

public class SortilegePluitDeFleche extends Sortilege {
    @Override
    public boolean utiliseePar(Entite utilisateur) {
        if (peutLancerSort()) {
            for (int i = 0; i < ConstantesSortilege.NOMBRE_FLECHE_PLUIT_DE_FLECHES; i++) {
                int delaie = i + 1;

                Tache tache = new Tache(delaie * 2) {
                    @Override
                    public void tache() {
                        FlechePositionSpray fleche = new FlechePositionSpray();
                        fleche.utiliseePar(utilisateur);
                    }
                };

                Monde.getMonde().ajoutTache(tache);
                estPossibleDeLancerSort(false);
                startCooldown();
            }
        }

        return false;
    }

    @Override
    public long delaie() {
        return ConstantesSortilege.DELAIE_PLUIT_DE_FLECHES;
    }
}