package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.StategyDeplacement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;

public abstract class DeplacementStrategy
{
    private Acteur acteur;

    public DeplacementStrategy(Acteur acteur) {
        this.acteur = acteur;
    }

    public abstract void seDeplace();
}
