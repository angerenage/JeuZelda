package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.*;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternBoss.PatternInvocationSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique.PatternAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique.PatternEnvoieOrbe;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique.PatternPrendrePotion;

import java.util.ArrayList;
import java.util.List;

public class ConstructeurDePattern {



    public static Pattern initPattern(RoiSquelette roiSquelette)
    {
        ArrayList<Pattern> patterns = new ArrayList<>();


        ConditionPatternDecorateur patternAttaquer = new ConditionJoueurDansRayonAttaqueDecorateur(roiSquelette,new PatternAttaque(roiSquelette));
        Pattern envoieOrbe = new PatternEnvoieOrbe(roiSquelette,roiSquelette.getMonde().getJoueur());
        Pattern invoqueSkelette = new PatternInvocationSquelette(roiSquelette);

        Pattern joueurDetecter = new ConditionJoueurDansVisionDecorateur(roiSquelette,new PatternComposite(new ArrayList<>(List.of(new PatternCompositeStrategie(new ArrayList<>(List.of(patternAttaquer)),envoieOrbe),invoqueSkelette))),5);

        Pattern patternInvoqueSkeletteQuandVieFaible = new ConditionVieInsuffisantDecorateur(roiSquelette,new PatternInvocationSquelette(roiSquelette),20);
        Pattern patternHeal = new ConditionVieInsuffisantDecorateur(roiSquelette,new PatternPrendrePotion(roiSquelette),70);


        patterns.add(patternInvoqueSkeletteQuandVieFaible);
        patterns.add(joueurDetecter);
        patterns.add(patternHeal);

        return new PatternComposite(patterns);
    }
}
