import org.junit.Test;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.Prompte.GestionPrompt;

public class PromptPnjTest
{
    private GestionPrompt gestionPrompt;

    private void initGestionPrompt()
    {


    }


    @Test
    public void testLogiquePrompt()
    {
        initGestionPrompt();
        System.out.println(gestionPrompt.getPrompt().getTextePrompt());
        gestionPrompt.promptSuivant("");
        System.out.println(gestionPrompt.getPrompt().getTextePrompt());

    }
}
