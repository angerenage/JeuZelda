package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.action.Action;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.condition.Condition;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.Transition;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.TransitionConditionnelle;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.TransitionSimple;

import java.util.*;

public class PromptNode {
    private final String textePrompt;
    private final Action action;
    private final Map<ChoixPrompt, Transition> transitions;
    private PromptNode fallback;

    public PromptNode(String textePrompt, Action action) {
        this.textePrompt = textePrompt;
        this.action = action;
        this.transitions = new HashMap<>();
    }

    public void ajouterTransition(ChoixPrompt choix, PromptNode suivant) {
        transitions.put(choix, new TransitionSimple(suivant));
    }

    public void ajouterTransition(ChoixPrompt choix, PromptNode suivant, Condition condition) {
        transitions.put(choix, new TransitionConditionnelle(condition, suivant));
    }

    public void setFallback(PromptNode fallback) {
        this.fallback = fallback;
    }

    public PromptNode getSuivant(ChoixPrompt choix) {

        System.out.println("Tentative de transition pour le choix : " + choix.getDisplayName());
        Transition transition = transitions.get(choix);

        if (transition != null) {
            System.out.println("Transition trouvée, vérification de la condition...");
            if (transition.transitionPossible()) {
                System.out.println("Condition remplie, transition vers le nœud suivant.");
                return transition.getSuivant();
            } else {
                System.out.println("Condition non remplie. Fallback utilisé.");
            }
        } else {
            System.out.println("Pas de transition pour ce choix.");
        }

        return fallback;
    }


    public String afficherPrompt() {
        if (action != null){
            action.execute();
        }
        return textePrompt;
    }

    public Map<ChoixPrompt, Transition> getTransitions() {
        return transitions;
    }

    public ArrayList<String> getChoixPossibles() {
        ArrayList<String> choixPossibles = new ArrayList<>();
        for (Map.Entry<ChoixPrompt, Transition> entry : transitions.entrySet()) {
            choixPossibles.add(entry.getKey().getDisplayName());
        }
        return choixPossibles;
    }
}
