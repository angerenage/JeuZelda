package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionNombreTour.ComparateurInfEgal;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionNombreTour.ComparteurEgal;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionNombreTour.ConditionNombreDeTour;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.PatternCompositeStrategie;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComposite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementEpee.Pattern.ComportementAttaqueEpee;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente un pattern de coup d'épée.
 * Si c'est le premier tour, l'épée est placée à droite et une attaque est lancée.
 * Sinon, si le coup n'est pas terminé, l'épée se déplace.
 */
public class PatternCoupEpee extends PatternCompositeStrategie {

    public PatternCoupEpee(ComportementAttaqueEpee comportementEpee) {
        super(
                new ArrayList<>(List.of(
                        new ConditionNombreDeTour(comportementEpee,new PatternComposite(new ArrayList<>(List.of(new PatternSetPosition(comportementEpee),new PatternDeplacementEpee(comportementEpee)))),new ComparteurEgal(),0)  // Positionner l'épée
                )),

                new PatternCompositeStrategie(
                        new ArrayList<>(List.of(
                               new ConditionNombreDeTour(comportementEpee, new PatternDeplacementEpee(comportementEpee),new ComparateurInfEgal(),2)
                        )),
                        comportementEpee::finit
                )
        );
    }

}
