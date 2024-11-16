package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

public class ConditionJoueurDansVisionDecorateur extends ConditionPatternDecorateur
{
    private final double rayon;
    private final NPEs npe;

    public ConditionJoueurDansVisionDecorateur(NPEs npe,Pattern patternAeffectuer,double rayon) {
        super(patternAeffectuer);
        this.rayon = rayon;
        this.npe =npe;
    }

    @Override
    public boolean conditionRespecter() {
        return npe.getMonde().estDansRayon(npe.getPosition(),rayon);
    }
}
