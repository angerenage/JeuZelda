package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionNombreTour.ComparateurInfEgal;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionNombreTour.ComparteurEgal;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionNombreTour.ConditionNombreDeTour;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.PatternCompositeStrategie;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComplexe;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComposite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementEpee.Pattern.ComportementAttaqueEpee;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente un pattern de coup d'épée.
 * Si c'est le premier tour, l'épée est placée à droite et une attaque est lancée.
 * Sinon, si le coup n'est pas terminé, l'épée se déplace.
 */


public class PatternCoupEpee extends PatternComplexe {


    public PatternCoupEpee()
    {

        addPattern(initPattern1());

    }

    private Pattern initPattern1()
    {
        Pattern p = new Pattern() {
            @Override
            public void effectue() {

            }
        };

        return p;
    }

    @Override
    protected int getIndex() {
        return 0;
    }
}
