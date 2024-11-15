package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Monstre;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

public class ConditionJoueurNonAtteignableParAttaqueDecorateur extends ConditionPatternDecorateur
{
    private Monstre monstre;

    public ConditionJoueurNonAtteignableParAttaqueDecorateur(Monstre monstre,Pattern patternAeffectuer) {
        super(patternAeffectuer);
        this.monstre = monstre;
    }

    @Override
    public boolean conditionRespecter() {
        return true;
    }




}
