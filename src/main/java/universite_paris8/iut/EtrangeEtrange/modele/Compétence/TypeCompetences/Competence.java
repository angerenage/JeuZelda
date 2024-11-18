package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences;

import java.util.ArrayList;
import java.util.List;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;

public abstract class Competence {
    private boolean estDebloquer;
    protected int niveauCompetence;

    private final List<Competence> parents;
	private final List<Competence> enfants;

    public Competence() {
        this.estDebloquer = false;
        this.niveauCompetence = 0;
        this.parents = new ArrayList<>();
		this.enfants = new ArrayList<>();
    }

    public void ajouterParent(Competence parent) {
        if (!this.parents.contains(parent)) {
			this.parents.add(parent);
			parent.ajouterEnfant(this);
		}
    }

	public void ajouterEnfant(Competence enfant) {
		if (!this.enfants.contains(enfant)) {
			this.enfants.add(enfant);
			enfant.ajouterParent(this);
		}
	}

    public List<Competence> getParents() {
        return this.parents;
    }

	public List<Competence> getEnfants() {
		return this.enfants;
	}

    public boolean estDebloquer() {
        return this.estDebloquer;
    }

    public void debloquer() {
        if (peutEtreDebloquer()) {
            this.estDebloquer = true;
        }
    }

    private boolean peutEtreDebloquer() {
        for (Competence parent : parents) {
            if (!parent.estDebloquer()) return false;
        }
        return true;
    }

    public abstract int niveauMax();

    public abstract void monterDeNiveau(Joueur joueur);
}