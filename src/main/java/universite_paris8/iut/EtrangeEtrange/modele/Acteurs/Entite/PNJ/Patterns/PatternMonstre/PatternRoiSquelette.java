package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternMonstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.*;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComposite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternDeplacementAleatoire;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternSeDirigerVersCible;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique.PatternAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique.PatternTourSurSois;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

import java.util.ArrayList;

public class PatternRoiSquelette implements Pattern {
    private RoiSquelette roiSquelette;
    private Pattern pattern1;
    private Pattern pattern2;
    private Pattern patternAct;

    public PatternRoiSquelette(RoiSquelette roiSquelette) {
        this.roiSquelette = roiSquelette;
        initPattern2();
        initPattern1();

        patternAct = pattern2;

    }

    private void initPattern1() {
        ArrayList<Pattern> patterns = new ArrayList<>();
        patterns.add(new PatternTourSurSois(roiSquelette));
        patterns.add(roiSquelette::invoqueSquelette);
        patterns.add(() -> patternAct = pattern2);

        this.pattern1 = new ConditionJoueurDansVisionDecorateur(
                roiSquelette,
                new PatternComposite(patterns),
                6
        );
    }

    private void initPattern2() {
        ArrayList<Pattern> patterns = new ArrayList<>();

        patterns.add(new ConditionJoueurDansRayonAttaqueDecorateur(roiSquelette, new PatternAttaque(roiSquelette)));
        patterns.add(new ConditionDelaieRespecter(() -> roiSquelette.invoqueSquelette(), 1000));
        patterns.add(new ConditionDelaieRespecter(() -> roiSquelette.soigner(3), 500));
        patterns.add(new PatternSeDirigerVersCible(roiSquelette, Monde.getMonde().getJoueur()));

        ArrayList<Pattern> patterns2 = new ArrayList<>();
        patterns2.add(new PatternTourSurSois(roiSquelette));
        patterns2.add(roiSquelette::invoqueSquelette);
        patterns2.add(()->roiSquelette.teleportePosDepart());
        patterns2.add(() -> patternAct = pattern1);

        this.pattern2 = new PatternCompositeStrategie(
                new ConditionJoueurDansVisionDecorateur(roiSquelette, new PatternComposite(patterns), 8),
                new PatternComposite(patterns2)
        );
    }

    @Override
    public void effectue() {
        if (patternAct != null) {
            patternAct.effectue();
        }
    }

}
