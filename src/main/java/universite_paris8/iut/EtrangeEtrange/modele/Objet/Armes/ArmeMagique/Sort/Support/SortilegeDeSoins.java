package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Support;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Guerrisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesSortilege;

public class SortilegeDeSoins extends Sortilege implements Guerrisable
{

    @Override
    public boolean utiliseePar(Entite entite)
    {
        if (peutLancerSort())
        {
            estPossibleDeLancerSort(false);
            entite.soigner(restoration());
            startCooldown();
        }

        return false;
    }

    @Override
    public long delaie() {
        return ConstantesSortilege.DELAIE_GUERISON;
    }

    @Override
    public double restoration() {
        return ConstantesSortilege.PV_RESTORER_GUERISON;
    }
}
