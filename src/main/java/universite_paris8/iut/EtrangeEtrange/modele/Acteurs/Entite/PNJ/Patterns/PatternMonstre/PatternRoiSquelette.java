package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternMonstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.*;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComplexe;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComposite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternDeplacementAleatoire;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternSeDirigerVersCible;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique.PatternAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique.PatternTourSurSois;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.tache.Tache;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public class PatternRoiSquelette extends PatternComplexe {
    private RoiSquelette roiSquelette;
    private int i;

    public PatternRoiSquelette(RoiSquelette roiSquelette)
    {
        super();
        this.roiSquelette = roiSquelette;
        addPattern(initPattern1());
        addPattern(initPattern2());
    }

    @Override
    protected int getIndex() {
        return i;
    }


    private Pattern initPattern1()
    {
        ArrayList<Pattern> patterns = new ArrayList<>();
        Position j = Monde.getMonde().getJoueur().getPosition();

        patterns.add(() -> roiSquelette.setPosition(j.getX()+1, j.getY()+0.5));

        patterns.add(() -> roiSquelette.invoqueSquelette(5));

        patterns.add(() -> Monde.getMonde().ajoutTache(new Tache(1000) {
            @Override
            public void tache() {
               roiSquelette.teleportePosDepart();
            }
        }));

        patterns.add(() -> i = 1);


        Pattern pattern = new ConditionJoueurDansVisionDecorateur(
                roiSquelette,
                new PatternComposite(patterns),
                6
        );

        return pattern;
    }

    private Pattern initPattern2() {
        ArrayList<Pattern> patterns = new ArrayList<>();

        patterns.add(new ConditionJoueurDansRayonAttaqueDecorateur(roiSquelette, new PatternAttaque(roiSquelette)));
        patterns.add(new ConditionDelaieRespecter(() -> roiSquelette.invoqueSquelette(), 1000));
        patterns.add(new ConditionDelaieRespecter(() -> roiSquelette.soigner(3), 500));
        patterns.add(new PatternSeDirigerVersCible(roiSquelette,Monde.getMonde().getJoueur()));

        ArrayList<Pattern> patterns2 = new ArrayList<>();

        patterns2.add(() -> roiSquelette.soigner(10000));

        patterns2.add(() -> roiSquelette.invoqueSquelette(1));

        patterns2.add(()->roiSquelette.teleportePosDepart());
        patterns2.add(() -> i = 0);


        Pattern pattern = new PatternCompositeStrategie  (
                new ConditionJoueurDansVisionDecorateur(roiSquelette, new PatternComposite(patterns), 8),
                new PatternComposite(patterns2)
        );


        return pattern;
    }




}
