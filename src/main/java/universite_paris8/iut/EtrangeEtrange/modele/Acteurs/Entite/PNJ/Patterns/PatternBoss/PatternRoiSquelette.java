package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternBoss;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComposite;

import static universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConstructeurDePattern.initPattern;

public class PatternRoiSquelette implements Pattern
{
    private RoiSquelette  roiSquelette;


    public PatternRoiSquelette(RoiSquelette roiSquelette)
    {
        super();
        this.roiSquelette = roiSquelette;
    }


    @Override
    public void effectue() {

    }
}
