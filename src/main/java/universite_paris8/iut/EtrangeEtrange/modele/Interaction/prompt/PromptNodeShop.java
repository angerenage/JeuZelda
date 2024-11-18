package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Emplacement;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.action.ActionAchat;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.condition.ConditionJoueurPeutAcheter;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.Transition;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.TransitionConditionnelle;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.TransitionSimple;

import java.util.*;

public class PromptNodeShop extends PromptNode {
    private final Marchand marchand;
    private final Map<String, Transition> transitions;
    private final PromptNode quitter;
    private final PromptNode achatFail;
    private final PromptNode achatSuccess;
    private final PromptNode suivant;

    public PromptNodeShop(String textePrompt, Marchand marchand, PromptNode achatSuccess, PromptNode achatFail, PromptNode suivant, PromptNode quitter) {
        super(textePrompt);
        this.marchand = marchand;
        this.quitter = quitter;
        this.achatFail = achatFail;
        this.achatSuccess = achatSuccess;
        this.suivant = suivant;
        this.transitions = new HashMap<>();
    }

    @Override
    public String afficherPrompt() {
        return textePrompt;
    }

    @Override
    public PromptNode getSuivant(String choix) {
        Transition transition = transitions.get(choix);

        return (transition != null) ? transition.getSuivant() : null;
    }

    @Override
    public List<String> getChoixPossibles() {
        List<String> choixPossibles = new ArrayList<>();

        for (Emplacement<Objet> emplacement : marchand.getMarchandise().getInventaire()) {
            List<Objet> objets = emplacement.enleverToutLesObjets();
            if (objets != null) {
                for (Objet objet : objets) {
                    String choixObjet = String.format("%s     [%d]", objet.getNom(), objet.prixAchat());

                    Map<String, Transition> transitionsSuivant = new HashMap<>();
                    transitionsSuivant.put("Suivant", new TransitionSimple(suivant));

                    PromptNodeAction actionNode = new PromptNodeAction(
                            String.format(achatSuccess.afficherPrompt(), objet.getNom(), objet.prixAchat()),
                            new ActionAchat(objet),
                            transitionsSuivant
                    );

                    transitions.put(choixObjet, new TransitionConditionnelle(
                            new ConditionJoueurPeutAcheter(objet),
                            actionNode,
                            achatFail
                    ));

                    choixPossibles.add(choixObjet);
                }
            } else {
            }
        }

        // Ajout de l'option "Quitter"
        transitions.put("Quitter", new TransitionSimple(quitter));
        choixPossibles.add("Quitter");

        return choixPossibles;
    }
}
