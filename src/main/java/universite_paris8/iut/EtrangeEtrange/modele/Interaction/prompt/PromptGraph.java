package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

public class PromptGraph {
    private PromptNode noeudInitial;
    private PromptNode noeudActuel;

    public PromptGraph(PromptNode noeudInitial) {
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

    public void avancerPrompt(ChoixPrompt choix) {
        PromptNode suivant = noeudActuel.getSuivant(choix);
        noeudActuel = suivant;
    }
}
