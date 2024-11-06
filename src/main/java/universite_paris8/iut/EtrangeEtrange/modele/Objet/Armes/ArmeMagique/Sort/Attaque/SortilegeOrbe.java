package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Orbe;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesSortilege;

public class SortilegeOrbe extends Sortilege
{
    private Orbe orbe;

    public SortilegeOrbe()
    {
        this.orbe = new Orbe();
    }

    @Override
    public boolean utiliseePar(Entite entite)
    {
        this.orbe = new Orbe();

        if (peutLancerSort())
        {
            estPossibleDeLancerSort(false);
            startCooldown();

            this.orbe.utiliseePar(entite);
        }

        return false;
    }

    @Override
    public long delaie() {
        return ConstantesSortilege.DELAIE_ORBE;
    }
}
