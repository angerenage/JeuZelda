package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.Pattern;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComposite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.ComportementFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.PatternSetPosition;

import java.util.ArrayList;

public class PatternFleche implements Pattern
{
    private Pattern patternActuel;
    private final ComportementFleche comportementFleche;

    public PatternFleche(ComportementFleche comportementFleche){
        this.comportementFleche = comportementFleche;
        initPattern();
    }

    private void initPattern(){
        ArrayList<Pattern> listPattern  = new ArrayList<>();

        listPattern.add(new PatternSetPosition(comportementFleche));
        listPattern.add(()-> patternActuel = () -> comportementFleche.seDeplace(1) );

        patternActuel = new PatternComposite(listPattern);
    }

    @Override
    public void effectue() {
        patternActuel.effectue();
    }
}
