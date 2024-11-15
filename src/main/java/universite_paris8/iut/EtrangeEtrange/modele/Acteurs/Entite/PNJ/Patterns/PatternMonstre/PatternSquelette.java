package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternMonstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionJoueurDansRayonAttaqueDecorateur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionJoueurDansVisionDecorateur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionPatternDecorateur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.PatternCompositeStrategie;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComposite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternDeplacementAleatoire;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternSeDirigerVersCible;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique.PatternAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

import java.util.ArrayList;
import java.util.List;

public class PatternSquelette implements Pattern
{
    private Squelette squelette;
    private Pattern pattern;

    public PatternSquelette(Squelette squelette)
    {
        this.squelette = squelette;
        initPattern();
    }


    // SI joueur detecter
            // voir si on peux l' attaquer
                    // attaquer
            // sinon
                    // se deplacer
    // SINON
            // SE DEPLACER ALEATOIREMENT

    private void initPattern(){

        Pattern p = () -> squelette.setSeDeplace(false);


        pattern = new PatternCompositeStrategie (
                new ConditionJoueurDansVisionDecorateur(squelette,
                        new PatternCompositeStrategie (
                                new ConditionJoueurDansRayonAttaqueDecorateur(squelette,
                                        new PatternAttaque(squelette)),
                                new PatternSeDirigerVersCible(squelette, Monde.getMonde().getJoueur())
                        ),
                        6
                ),
                new PatternComposite(new ArrayList<>(List.of(p,
                        new PatternDeplacementAleatoire(squelette))))

        );


    }


    @Override
    public void effectue() {
        pattern.effectue();

    }
}
