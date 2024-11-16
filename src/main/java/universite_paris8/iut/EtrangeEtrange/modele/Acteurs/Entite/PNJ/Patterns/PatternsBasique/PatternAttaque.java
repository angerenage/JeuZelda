package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Monstre;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;


public class PatternAttaque implements Pattern
{
    private final Monstre monstre;

    public PatternAttaque(Monstre monstre){
        this.monstre = monstre;
    }

    @Override
    public void effectue() {
        monstre.faitUneAttaque();
    }
}
