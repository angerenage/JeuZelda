package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public class PatternDeplacementAleatoire implements Pattern
{
    private NPEs npe;

    public PatternDeplacementAleatoire(NPEs npe) {
        this.npe = npe;
    }


    @Override
    public void effectue() {
        if (npe.peutSeDeplacer()) {
            if(Math.random()>0.95){
                npe.setSeDeplace(false);
            }
            else {
                npe.seDeplace(1);
            }
        }
        else if(Math.random()>0.95)
            npe.setSeDeplace(true);

        if(Math.random()>0.95)
            npe.setDirection(Direction.randomDirection());
    }
}
