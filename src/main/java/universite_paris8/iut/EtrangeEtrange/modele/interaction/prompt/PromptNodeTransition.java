package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.Transition;

import java.util.*;

public class PromptNodeTransition extends PromptNode implements Transitionnable {
    private final Map<String, Transition> transitions;

    // Constructeur sans transitions initiales
    public PromptNodeTransition(String textePrompt) {
        super(textePrompt);
        this.transitions = new HashMap<>();
    }

    public PromptNodeTransition(String textePrompt, Map<String, Transition> transitions) {
        super(textePrompt);
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
        return textePrompt;
    }
}
