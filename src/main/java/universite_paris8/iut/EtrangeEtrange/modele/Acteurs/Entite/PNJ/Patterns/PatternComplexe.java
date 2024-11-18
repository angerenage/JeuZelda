package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;

import java.util.ArrayList;

public abstract class PatternComplexe implements Pattern
{
    private ArrayList<Pattern> patterns;


    public PatternComplexe(ArrayList<Pattern> patterns)
    {
        this.patterns = patterns;
    }

    public PatternComplexe()
    {
        this.patterns = new ArrayList<>();

    }


    public void addPattern(Pattern pattern)
    {
        patterns.add(pattern);
    }

    protected abstract int getIndex();

    @Override
    public void effectue() {
        patterns.get(getIndex()).effectue();
    }
}
