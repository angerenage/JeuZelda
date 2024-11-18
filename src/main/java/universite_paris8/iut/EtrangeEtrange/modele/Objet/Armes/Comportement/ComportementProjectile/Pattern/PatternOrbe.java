package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.Pattern;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComposite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternDeplacementAleatoire;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternSeDirigerVersCible;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.PatternSetPosition;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.ComportementOrbe;

import java.util.ArrayList;

public class PatternOrbe implements Pattern
{

    private ComportementOrbe comportementOrbe;
    private Pattern patternEnCour;

    public PatternOrbe(ComportementOrbe comportementOrbe)
    {
        this.comportementOrbe = comportementOrbe;
        initPattern();
    }

    @Override
    public void effectue() {
        patternEnCour.effectue();
    }



    private void initPattern()
    {
        ArrayList<Pattern> listPattern = new ArrayList<>();
        listPattern.add(new PatternSetPosition(comportementOrbe));
        listPattern.add(() -> patternEnCour = new PatternSeDirigerVersCible(comportementOrbe,comportementOrbe.getCible()));

        patternEnCour = new PatternComposite(listPattern);
    }
}
