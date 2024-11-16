package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

public class ConditionVieInsuffisantDecorateur extends ConditionPatternDecorateur
{
    private final int pourcentagePvMini;
    private final NPEs npe;

    public ConditionVieInsuffisantDecorateur(NPEs npe,Pattern patternAeffectuer, int pourcentagePvMini) {
        super(patternAeffectuer);
        this.pourcentagePvMini = pourcentagePvMini;
        this.npe = npe;
    }

    @Override
    public boolean conditionRespecter() {
        return npe.getStatsPv().getPourcentageDePv() <= pourcentagePvMini;
    }
}
