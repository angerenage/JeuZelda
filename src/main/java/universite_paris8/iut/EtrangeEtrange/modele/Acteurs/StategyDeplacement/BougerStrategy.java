package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.StategyDeplacement;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;

public abstract class BougerStrategy
{
    private Acteur acteur;

    public BougerStrategy(Acteur acteur) {
        this.acteur = acteur;
    }

    public abstract void bouger();
}
