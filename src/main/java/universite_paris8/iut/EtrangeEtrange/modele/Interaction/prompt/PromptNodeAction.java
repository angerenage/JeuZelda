package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.action.Action;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.condition.Condition;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.Transition;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.TransitionConditionnelle;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.TransitionSimple;

import java.util.*;

public class PromptNodeAction extends PromptNode {
    private final Action action;
    private final Map<String, Transition> transitions;

    public PromptNodeAction(String textePrompt, Action action, Map<String, Transition> transitions) {
        super(textePrompt);
        this.action = action;
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
        action.execute();
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
