package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Monstre;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

public class ConditionJoueurDansRayonAttaqueDecorateur extends ConditionPatternDecorateur
{
    private final Monstre monstre;

    public ConditionJoueurDansRayonAttaqueDecorateur(Monstre monstre,Pattern patternAeffectuer) {
        super(patternAeffectuer);
        this.monstre = monstre;
    }

    @Override
    public boolean conditionRespecter() {
        return monstre.getMonde().estDansRayon(monstre.getPosition(),1);
    }
}
