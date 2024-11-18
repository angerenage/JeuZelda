package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Offensif;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionDelaieRespecter;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.InteractionManager;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.action.ActionSoigner;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.condition.ConditionJoueurBlesse;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt.*;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.TransitionConditionnelle;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.TransitionSimple;

public class Marchand extends NPEs implements Interagisable {

    private final Sac sac;
    private InteractionManager interactionManager;

    public Marchand(double x, double y, Direction direction) {
        super(x, y, direction, 10, 10, 10, 0.5, new Hitbox(0.5, 0.5));
        this.sac = new Sac();
        initPrompt();
    }

    public void genereMarchandises() {
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

    private void initPrompt() {
        // Création du nœud racine
        PromptNodeTransition racine = new PromptNodeTransition("Bonjour ! Que vous amène ici ?");

        // === Commerce ===
        PromptNodeShop commerceNode = new PromptNodeShop(
                "Voici ce que je propose.",
                this,
                new PromptNodeSimple("Vous avez acheté l'objet %s pour %d."),
                new PromptNodeSimple("Vous n'avez pas assez d'argent pour acheter cet objet."),
                new PromptNodeSimple("À la prochaine."),
                new PromptNodeSimple("Au revoir.")
        );

        // === Discussion ===
        PromptNodeTransition discuterNode = new PromptNodeTransition("Vous avez entendu parler du monstre qui rôde dans les environs ?");
        PromptNodeTransition p21 = new PromptNodeTransition("Faites attention... C'est un dur !");
        PromptNodeTransition p22 = new PromptNodeTransition("Faites attention... Il est terrifiant !");
        discuterNode.ajouterTransition("Non", new TransitionSimple(p22));
        discuterNode.ajouterTransition("Oui", new TransitionSimple(p21));

        // === Soin ===
        PromptNodeTransition p3 = new PromptNodeTransition("J'ai de quoi te soigner, ça t'intéresse ?");
        PromptNode p4 = new PromptNodeSimple("Okay, à la prochaine.");
        PromptNode p31 = new PromptNodeAction("Voilà, c'est fait !", new ActionSoigner());

        // Ajout des transitions pour les soins
        p3.ajouterTransition("Non", new TransitionSimple(p4));
        p3.ajouterTransition("Oui", new TransitionConditionnelle(
                new ConditionJoueurBlesse(),
                p31,
                new PromptNodeSimple("Vous n'avez pas besoin de soin.")
        ));

        // Liaison avec les nœuds de discussion
        p21.ajouterTransition("Suivant", new TransitionSimple(p3));
        p22.ajouterTransition("Suivant", new TransitionSimple(p3));

        // === Ajout des transitions initiales à la racine ===
        racine.ajouterTransition("Je souhaite commercer...", new TransitionSimple(commerceNode));
        racine.ajouterTransition("Je souhaite discuter un peu...", new TransitionSimple(discuterNode));

        // Assignation de l'arbre de dialogue au gestionnaire d'interactions
        this.interactionManager = new InteractionManager(racine);
    }


    public Sac getMarchandise() {
        return sac;
    }

    @Override
    public InteractionManager getPromptGraph() {
        return this.interactionManager;
    }
}
