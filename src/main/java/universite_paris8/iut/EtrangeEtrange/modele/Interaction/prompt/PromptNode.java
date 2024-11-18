package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

import java.util.List;

public abstract class PromptNode {
    protected final String textePrompt;

    public PromptNode(String textePrompt) {
        this.textePrompt = textePrompt;
    }

    public abstract String afficherPrompt();

    public abstract PromptNode getSuivant(String choix);

    public abstract List<String> getChoixPossibles();
}
