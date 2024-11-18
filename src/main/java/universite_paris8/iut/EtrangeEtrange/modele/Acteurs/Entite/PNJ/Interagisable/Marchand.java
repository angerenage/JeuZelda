package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionDelaieRespecter;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt.ChoixPrompt;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.InteractionManager;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.*;


import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;


public class Marchand extends NPEs implements Interagisable {

    private final Sac sac;

    private InteractionManager interactionManager;

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
        //PromptNode racine = new PromptNode("Bonjour ! Que vous amene ici ?", null);
//
        //PromptNode p1 = new PromptNode("Voici ce que je propose.", new ActionVendre(this));
        //PromptNode p2 = new PromptNode("Vous avez entendu parler du monstre qui rôde dans dans les coins", null);
//
        //racine.ajouterTransition(ChoixPrompt.MARCHANDER, p1);
        //racine.ajouterTransition(ChoixPrompt.PARLER, p2);
//
        //PromptNode p21 = new PromptNode("Faites attention...  C'est un dur !", null);
        //PromptNode p22 = new PromptNode("Faites attention...  Il est terrifiant !", null);
//
        //p2.ajouterTransition(ChoixPrompt.NON, p22);
        //p2.ajouterTransition(ChoixPrompt.OUI, p21);
//
        //PromptNode p3 = new PromptNode("J'ai de quoi te soigner, ca t'interesse ?", null);
        //PromptNode p4 = new PromptNode("Okay, à la prochaine", null);
//
        //p21.ajouterTransition(ChoixPrompt.SUIVANT, p3);
        //p22.ajouterTransition(ChoixPrompt.SUIVANT, p3);
//
        //PromptNode p31 = new PromptNode("Voila c'est fait", new ActionSoigner());
//
        //p3.ajouterTransition(ChoixPrompt.NON, p4);
        //p3.ajouterTransition(ChoixPrompt.OUI, p31, new ConditionJoueurBlesse());
        //p3.setFallback(new PromptNode("Vous n'avez pas besoin de soin", null));
//
//
        //this.interactionManager = new InteractionManager(racine);

    }

    public Sac getMarchandise() {
        return sac;
    }

    @Override
    public InteractionManager getPromptGraph() {
        return this.interactionManager;
    }
}
