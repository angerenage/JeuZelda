package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.action.Action;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.Transition;

import java.util.*;

public class PromptNodeAction extends PromptNode implements Transitionnable {
    private final Action action;
    private final Map<String, Transition> transitions;

    // Constructeur sans transitions initiales
    public PromptNodeAction(String textePrompt, Action action) {
        super(textePrompt);
        this.action = action;
        this.transitions = new HashMap<>();
    }

    // Constructeur avec transitions initiales
    public PromptNodeAction(String textePrompt, Action action, Map<String, Transition> transitions) {
        super(textePrompt);
        this.action = action;
        this.transitions = new HashMap<>(transitions);
    }

    @Override
    public void ajouterTransition(String choix, Transition transition) {
        transitions.put(choix, transition);
    }

    @Override
    public void supprimerTransition(String choix) {
        transitions.remove(choix);
    }

    @Override
    public PromptNode getSuivant(String choix) {
        Transition transition = transitions.get(choix);
        if (transition != null) {
            return transition.getSuivant();
        }
        return null;
    }

    @Override
    public List<String> getChoixPossibles() {
        return new ArrayList<>(transitions.keySet());
    }

    @Override
    public String afficherPrompt() {
        action.execute();
        return textePrompt;
    }
}
