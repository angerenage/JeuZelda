package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;

import java.util.ArrayList;
import java.util.HashMap;

public class Competences {

    private TypeCompetence root;
    private final HashMap<TypeCompetence, ArrayList<TypeCompetence>> mapParent;
    private final HashMap<TypeCompetence, ArrayList<TypeCompetence>> mapEnfant;


    public Competences() {
        this.mapParent = new HashMap<>();
        this.mapEnfant = new HashMap<>();
    }

    public void ajoutCompetence(TypeCompetence competence, ArrayList<TypeCompetence> parents, ArrayList<TypeCompetence> enfants) {
        if (!mapParent.containsKey(competence)) // verifier si competence not in parents
        {
            mapParent.put(competence, parents);
            mapEnfant.put(competence, enfants);
        }
    }

    public void setRoot(TypeCompetence competence) {
        this.root = competence;
    }

    public TypeCompetence getRoot() {
        return this.root;
    }

    public ArrayList<TypeCompetence> getEnfants(TypeCompetence competence) {
        return this.mapEnfant.get(competence);
    }

}
