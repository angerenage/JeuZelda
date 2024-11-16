package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionDelaieRespecter;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.action.ActionVendre;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.action.ActionSoigner;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt.ChoixPrompt;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt.PromptNode;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt.PromptGraph;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.*;


import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;


public class Marchand extends NPEs implements Interagisable {

    private final Sac sac;

    private PromptGraph promptGraph;

    public Marchand(double x, double y, Direction direction) {
        super(x, y, direction, 10, 10 , 10, 0.5, new Hitbox(0.5,0.5));
        this.sac = new Sac();
        initPrompt();
    }


    public void genereMarchandises()
    {
        sac.ajoutItem(new Epee());
        sac.ajoutItem(new Arc());
        sac.ajoutItem(new Potion());
    }

    @Override
    protected Pattern initPattern() {
        return new ConditionDelaieRespecter(this::genereMarchandises,5000);
    }

    @Override
    public String typeActeur() {
        return "marchand";
    }

    @Override
    public void dropApresMort() {

    }

    @Override
    public boolean estUnEnemie() {
        return false;
    }

    @Override
    public  void subitAttaque(Dommageable causeDegat, Offensif entiteOffensif) {

    }

    private void initPrompt() {
        PromptNode racine = new PromptNode("Bonjour ! Que vous ramene ici ?", null);

        PromptNode p1 = new PromptNode("Voici ce que je propose.", new ActionVendre(this));
        PromptNode p2 = new PromptNode("Vous avez entendu parlé du monstre qui rôde dans dans les coins", null);

        racine.ajouterTransition(ChoixPrompt.MARCHANDER, p1, null);
        racine.ajouterTransition(ChoixPrompt.PARLER, p2, null);

        PromptNode p21 = new PromptNode("Faite attention...   D'ailleur, attendez je vais vous soigner !", new ActionSoigner());

        p2.ajouterTransition(ChoixPrompt.PARLER, p21, null);

        this.promptGraph = new PromptGraph(racine);

//        Prompt racine = new Prompt("Bonjour ! Que vous ramene ici ?", null);
//
//        Prompt reponseRacine1 = new Prompt("Voici ce que je propose.", new ActionVendre(this));
//
//        Prompt reponseRacine2 = new Prompt("Vous avez entendu parlé du monstre qui rôde dans dans les coins", null);
//
//        racine.ajoutPrompt(reponseRacine1, "J'aimerais marchander un peu avec avec vous");
//        racine.ajoutPrompt(reponseRacine2, "J'aimerais parler un peu..");
//
//        Prompt reponseReponceRacine2 = new Prompt("Faite attention...   D'ailleur, attendez je vais vous soigner !", new Soigner(monde.getJoueur()));
//        reponseRacine2.ajoutPrompt(reponseReponceRacine2, "");
//
//        prompt = racine;
    }

    public Sac getMarchandise() {
        return sac;
    }

    @Override
    public PromptGraph getPromptGraph() {
        return this.promptGraph;
    }
}
