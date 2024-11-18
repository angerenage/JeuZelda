package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceDebloquerAction.CompetenceCourir;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceDebloquerAction.CompetenceInvocation;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceStats.CompetenceUpAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceStats.CompetenceUpAttaqueSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceStats.CompetenceUpDefense;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceStats.CompetenceUpDefenseSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceStats.CompetenceUpPV;

public class Competences {
    private Competence root;

    public Competences() {
        this.root = creerArbre();
    }

    private Competence creerArbre() {
        Competence courir = new CompetenceCourir();

        Competence attaqueSpecial = new CompetenceUpAttaqueSpecial();
        courir.ajouterEnfant(attaqueSpecial);

        Competence attaque = new CompetenceUpAttaque();
        attaqueSpecial.ajouterEnfant(attaque);

        Competence vitesse = new CompetenceUpPV();
        attaqueSpecial.ajouterEnfant(vitesse);

        Competence pv = new CompetenceUpPV();
        vitesse.ajouterEnfant(pv);

        Competence defense = new CompetenceUpDefense();
        vitesse.ajouterEnfant(defense);

        Competence defenseSpecial = new CompetenceUpDefenseSpecial();
        defense.ajouterEnfant(defenseSpecial);

        Competence invoquer = new CompetenceInvocation();
        defenseSpecial.ajouterEnfant(invoquer);

        return courir;
    }

    public Competence getRoot() {
        return root;
    }
}
