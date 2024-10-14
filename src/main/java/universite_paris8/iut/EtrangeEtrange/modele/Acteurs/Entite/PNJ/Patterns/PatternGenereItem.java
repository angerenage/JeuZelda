package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable.Marchand;

public class PatternGenereItem implements Pattern
{
    private Marchand marchand;

    @Override
    public void effectue() {
        marchand.genereMarchandises();
    }
}
