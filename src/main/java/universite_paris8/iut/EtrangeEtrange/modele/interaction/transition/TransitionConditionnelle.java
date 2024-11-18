package universite_paris8.iut.EtrangeEtrange.modele.interaction.transition;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.condition.Condition;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt.PromptNode;

public class TransitionConditionnelle implements Transition{
    private final Condition condition;
    private final PromptNode suivant;
    private final PromptNode fallback;

    public TransitionConditionnelle(Condition condition, PromptNode suivant, PromptNode fallback) {
        this.condition = condition;
        this.suivant = suivant;
        this.fallback = fallback;
    }

    public boolean transitionPossible() {
        return condition == null || condition.estRemplie();
    }

    public PromptNode getSuivant() {
        if (condition == null || condition.estRemplie()) {
            return suivant;
        }
        return fallback;
    }
}
