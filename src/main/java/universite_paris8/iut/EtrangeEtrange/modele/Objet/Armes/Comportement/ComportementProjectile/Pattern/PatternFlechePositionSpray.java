package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.Pattern;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComposite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.ComportementFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.PatternSetPositionEnSpray;

import java.util.ArrayList;

public class PatternFlechePositionSpray implements Pattern
{
    private Pattern patternActuel;
    private ComportementFleche comportementFleche;

    public PatternFlechePositionSpray(ComportementFleche comportementFleche){
        initPattern();
        this.comportementFleche = comportementFleche;
    }

    private void initPattern(){
        ArrayList<Pattern> listPattern  = new ArrayList<>();

        listPattern.add(new PatternSetPositionEnSpray(comportementFleche));
        listPattern.add(()-> patternActuel = () -> comportementFleche.seDeplace(1));

        patternActuel = new PatternComposite(listPattern);
    }

    @Override
    public void effectue() {
        patternActuel.effectue();
    }
}
