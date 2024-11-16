package universite_paris8.iut.EtrangeEtrange.modele.interaction.condition;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt.PromptNode;

public class TransitionConditionnelle {
    private final Condition condition;
    private final PromptNode suivant;

    public TransitionConditionnelle(Condition condition, PromptNode suivant) {
        this.condition = condition;
        this.suivant = suivant;
    }

    public boolean estAccessible() {
        return condition == null || condition.estRemplie();
    }

    public PromptNode getSuivant() {
        return suivant;
    }
}
