package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Dropable;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Offensif;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionDelaieRespecter;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action.ActionVendre;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action.Soigner;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class Marchand extends NPEs implements Dropable {

    private final Sac sac;
    private Prompt prompt;

    public Marchand(double x, double y, Direction direction) {
        super(x, y, direction, 10, 10, 10, 0.5, new Hitbox(0.5, 0.5));
        this.sac = new Sac();
        initPrompt();
    }

    private void genereMarchandises() {
        sac.ajoutItem(new Epee());
        sac.ajoutItem(new Arc());
        sac.ajoutItem(new Potion());
    }

    @Override
    protected Pattern initPattern() {
        return new ConditionDelaieRespecter(this::genereMarchandises, 5000);
    }

    @Override
    public String typeActeur() {
        return "marchand";
    }

    @Override
    public void dropApresMort() {}

    @Override
    public boolean estUnEnemie() {
        return false;
    }

    @Override
    public void subitAttaque(Dommageable causeDegat, Offensif entiteOffensif) {}

    private void initPrompt() {
        Prompt racine = new Prompt("Bonjour ! Que vous ramène ici ?", null);

        Prompt reponseRacine1 = new Prompt("Voici ce que je propose.", new ActionVendre(this));
        Prompt reponseRacine2 = new Prompt("Vous avez entendu parler du monstre qui rôde dans les coins", null);

        racine.ajoutPrompt(reponseRacine1, "J'aimerais marchander un peu avec vous");
        racine.ajoutPrompt(reponseRacine2, "J'aimerais parler un peu...");

        Prompt reponseReponseRacine2 = new Prompt("Faites attention... D'ailleurs, attendez, je vais vous soigner !", new Soigner(monde.getJoueur()));
        reponseRacine2.ajoutPrompt(reponseReponseRacine2, "");

        this.prompt = racine;
    }

    @Override
    public void drop() {}

    public Sac getMarchandise() {
        return sac;
    }

    public Prompt getPrompt() {
        return prompt;
    }
}
