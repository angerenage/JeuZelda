package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.action.Action;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.condition.Condition;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.condition.TransitionConditionnelle;

import java.util.*;

public class PromptNode {
    private final String textePrompt;
    private final Action action;
    private final Map<ChoixPrompt, TransitionConditionnelle> transitions;
    private PromptNode fallback;  // Nœud à afficher si aucune transition n'est valide

    public PromptNode(String textePrompt, Action action) {
        this.textePrompt = textePrompt;
        this.action = action;
        this.transitions = new HashMap<>();
    }

    public void ajouterTransition(ChoixPrompt choix, PromptNode suivant, Condition condition) {
        transitions.put(choix, new TransitionConditionnelle(condition, suivant));
    }

    public void setFallback(PromptNode fallback) {
        this.fallback = fallback;
    }

    public PromptNode getSuivant(ChoixPrompt choix) {
        TransitionConditionnelle transition = transitions.get(choix);
        if (transition != null && transition.estAccessible()) {
            return transition.getSuivant();
        }
        return fallback;
    }

    public void afficherPrompt() {
        System.out.println(textePrompt);
        action.execute();
    }

    public Map<ChoixPrompt, TransitionConditionnelle> getTransitions() {
        return transitions;
    }
}
