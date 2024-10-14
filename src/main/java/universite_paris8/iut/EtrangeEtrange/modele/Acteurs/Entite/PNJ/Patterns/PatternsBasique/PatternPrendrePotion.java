package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Boss;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;

public class PatternPrendrePotion implements Pattern
{
    private NPEs npe;


    public PatternPrendrePotion(NPEs npe) {
        this.npe = npe;
    }

    @Override
    public void effectue() {
        new Potion().estUtiliseePar(npe);

    }
}
