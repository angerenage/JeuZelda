package universite_paris8.iut.EtrangeEtrange.modele.interaction;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt.PromptNode;

public class InteractionManager {
    private PromptNode noeudInitial;
    private PromptNode noeudActuel;

    public InteractionManager(PromptNode noeudInitial) {
        this.noeudInitial = noeudInitial;
    }

    public void initPrompt() {
        noeudActuel = noeudInitial;
    }

    public boolean noeudExists() {
        return noeudActuel != null;
    }

    public PromptNode getNoeudActuel() {
        return noeudActuel;
    }

    public void avancerPrompt(String choix) {
        PromptNode suivant = noeudActuel.getSuivant(choix);
        noeudActuel = suivant;
    }
}
