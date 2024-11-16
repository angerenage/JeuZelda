package universite_paris8.iut.EtrangeEtrange.modele.interaction.transition;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.condition.Condition;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt.PromptNode;

public class TransitionConditionnelle implements Transition{
    private final Condition condition;
    private final PromptNode suivant;

    public TransitionConditionnelle(Condition condition, PromptNode suivant) {
        this.condition = condition;
        this.suivant = suivant;
    }

    public boolean transitionPossible() {
        return condition == null || condition.estRemplie();
    }

    public PromptNode getSuivant() {
        return suivant;
    }
}
