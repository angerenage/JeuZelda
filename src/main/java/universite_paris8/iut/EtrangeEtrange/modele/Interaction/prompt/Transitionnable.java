package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

import universite_paris8.iut.EtrangeEtrange.modele.interaction.transition.Transition;


public interface Transitionnable {
    void ajouterTransition(String choix, Transition transition);

    void supprimerTransition(String choix);
}