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

        System.out.println("[DEBUG] PromptNodeShop initialisé avec le texte : " + textePrompt);
    }

    @Override
    public String afficherPrompt() {
        System.out.println("[DEBUG] Affichage du prompt : " + textePrompt);
        return textePrompt;
    }

    @Override
    public PromptNode getSuivant(String choix) {
        System.out.println("[DEBUG] getSuivant appelé avec le choix : " + choix);
        Transition transition = transitions.get(choix);

        if (transition != null) {
            System.out.println("[DEBUG] Transition trouvée pour le choix : " + choix);
        } else {
            System.out.println("[DEBUG] Aucune transition trouvée pour le choix : " + choix);
        }

        return (transition != null) ? transition.getSuivant() : null;
    }

    @Override
    public List<String> getChoixPossibles() {
        System.out.println("[DEBUG] Génération des choix possibles...");
        List<String> choixPossibles = new ArrayList<>();

        for (Emplacement<Objet> emplacement : marchand.getMarchandise().getInventaire()) {
            System.out.println("[DEBUG] Parcours de l'inventaire du marchand...");
            List<Objet> objets = emplacement.enleverToutLesObjets();
            System.out.println("[DEBUG] Emplacement : " + emplacement);
            System.out.println("[DEBUG] Objets trouvés : " + objets);
            if (objets != null) {
                for (Objet objet : objets) {
                    String choixObjet = String.format("%s     [%d]", objet.getNom(), objet.prixAchat());
                    System.out.println("[DEBUG] Ajout d'un choix pour l'objet : " + choixObjet);

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
                System.out.println("[DEBUG] Aucun objet trouvé dans cet emplacement.");
            }
        }

        // Ajout de l'option "Quitter"
        System.out.println("[DEBUG] Ajout de l'option 'Quitter'.");
        transitions.put("Quitter", new TransitionSimple(quitter));
        choixPossibles.add("Quitter");

        System.out.println("[DEBUG] Choix possibles générés : " + choixPossibles);
        return choixPossibles;
    }
}
