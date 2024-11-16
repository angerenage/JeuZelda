package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

public class PromptGraph {
    private PromptNode noeudActuel;

    public PromptGraph(PromptNode noeudInitial) {
        this.noeudActuel = noeudInitial;
    }

    public void afficherPrompt() {
        noeudActuel.afficherPrompt();
    }

    public void avancerPrompt(ChoixPrompt choix) {
        PromptNode suivant = noeudActuel.getSuivant(choix);
        if (suivant != null) {
            noeudActuel = suivant;
            afficherPrompt();
        }
    }
}
