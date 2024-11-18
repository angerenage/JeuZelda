package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.Transition;

import java.util.*;

public class PromptNodeTransition extends PromptNode {
    private final Map<String, Transition> transitions;

    public PromptNodeTransition(String textePrompt, Map<String, Transition> transitions) {
        super(textePrompt);
        this.transitions = new HashMap<>(transitions);
    }

    public PromptNode getSuivant(String choix) {
        Transition transition = transitions.get(choix);
        if (transition != null)
            return transition.getSuivant();
        return null;
    }

    @Override
    public String afficherPrompt() {
        return textePrompt;
    }

    @Override
    public List<String> getChoixPossibles() {
        List<String> choixPossibles = new ArrayList<>();
        for (Map.Entry<String, Transition> entry : transitions.entrySet()) {
            choixPossibles.add(entry.getKey());

        }
        return choixPossibles;
    }
}
