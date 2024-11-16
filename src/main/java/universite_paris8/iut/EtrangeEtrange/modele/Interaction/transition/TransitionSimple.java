package universite_paris8.iut.EtrangeEtrange.modele.interaction.transition;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt.PromptNode;

public class TransitionSimple implements Transition {

    private final PromptNode suivant;

    public TransitionSimple(PromptNode suivant) {
        this.suivant = suivant;
    }

    public boolean transitionPossible() {
        return true;
    }

    public PromptNode getSuivant() {
        return suivant;
    }
}
