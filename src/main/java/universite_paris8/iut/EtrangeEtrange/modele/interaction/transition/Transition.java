package universite_paris8.iut.EtrangeEtrange.modele.interaction.transition;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt.PromptNode;

public interface Transition {
    boolean transitionPossible();
    PromptNode getSuivant();
}
