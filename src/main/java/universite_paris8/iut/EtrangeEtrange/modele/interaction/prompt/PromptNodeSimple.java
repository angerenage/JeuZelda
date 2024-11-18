package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

import java.util.ArrayList;
import java.util.List;

public class PromptNodeSimple extends PromptNode {

    public PromptNodeSimple(String textePrompt) {
        super(textePrompt);
    }

    @Override
    public String afficherPrompt() {
        return textePrompt;
    }

    @Override
    public PromptNode getSuivant(String choix) {
        return null;
    }

    @Override
    public List<String> getChoixPossibles() {
        return new ArrayList<>();
    }
}
