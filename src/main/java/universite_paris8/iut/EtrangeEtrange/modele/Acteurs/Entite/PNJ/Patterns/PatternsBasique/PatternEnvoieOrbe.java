package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Monstre;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Orbe;

public class PatternEnvoieOrbe implements Pattern
{
    private Monstre monstre;
    private Joueur cible;

    public PatternEnvoieOrbe(Monstre monstre, Joueur cible)
    {
        this.monstre = monstre;
        this.cible = cible;
    }

    public PatternEnvoieOrbe(Monstre monstre)
    {
        this.monstre = monstre;
        this.cible = null;
    }

    @Override
    public void effectue() {

        Orbe orbe = new Orbe();

        orbe.utiliseePar(monstre);
    }
}
