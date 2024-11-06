package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionNombreTour;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionPatternDecorateur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementEpee.Pattern.ComportementAttaqueEpee;

public class ConditionNombreDeTour extends ConditionPatternDecorateur
{
    private long tour;
    private ComportementAttaqueEpee comportementAttaqueEpee;
    private ComparateurStrategy comparateurStrategy;


    public ConditionNombreDeTour(ComportementAttaqueEpee comportementAttaqueEpee,Pattern patternAeffectuer,ComparateurStrategy comparateurStrategy,long tour) {
        super(patternAeffectuer);
        this.comportementAttaqueEpee = comportementAttaqueEpee;
        this.tour = tour;
        this.comparateurStrategy = comparateurStrategy;
    }


    @Override
    public boolean conditionRespecter() {
        return comparateurStrategy.comparer(comportementAttaqueEpee.getTourFait(), tour);
    }
}
