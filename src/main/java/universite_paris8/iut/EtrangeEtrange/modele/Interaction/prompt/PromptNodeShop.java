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

public class PromptNodeShop extends PromptNode{
    private final Marchand marchand;
    private final Map<String, Transition> transitions;
    private final PromptNode quitter;
    private final PromptNode achatfail;
    private final PromptNode achatsucess;
    private final PromptNode suivant;

    public PromptNodeShop(String textePrompt, Marchand marchand, PromptNode achatsucess, PromptNode achatfail, PromptNode suivant, PromptNode quitter) {
        super(textePrompt);
        this.marchand = marchand;
        this.quitter = quitter;
        this.achatfail = achatfail;
        this.achatsucess = achatsucess;
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
        if (transition != null)
            return transition.getSuivant();
        return null;
    }

    @Override
    public List<String> getChoixPossibles() {
        List<String> choixPossibles = new ArrayList<>();
        for (Emplacement<Objet> emplacement : marchand.getMarchandise().getInventaire()) {
            ArrayList<Objet> objets = emplacement.enleverToutLesObjets();
            for (Objet objet : objets) {
                String objetString = objet.getNom() + "     [" + objet.prixAchat() + "]";
                this.transitions.put(objetString, new TransitionConditionnelle(new ConditionJoueurPeutAcheter(objet), new PromptNodeAction(String.format(achatsucess.afficherPrompt(), objet.getNom(), objet.prixAchat()), new ActionAchat(objet), (Map)(new HashMap<>()).put("Suivant", new TransitionSimple(suivant))), achatfail));
                choixPossibles.add(objetString);
            }
        }
        this.transitions.put("Quitter", new TransitionSimple(quitter));
        choixPossibles.add("Quitter");

        return choixPossibles;
    }

}
