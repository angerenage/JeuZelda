package universite_paris8.iut.EtrangeEtrange.modele.Map;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Bloc.Bloc;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Slime;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.FabriquePnj;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.FileReaderTemplate;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class MonstreReader extends FileReaderTemplate {
	private final Monde monde;
    
	private static final Class<? extends Acteur>[] ACTOR_TYPES = new Class[]{Bloc.class, RoiSquelette.class, Squelette.class, Slime.class, Marchand.class};

    public MonstreReader(Monde monde) {
        this.monde = monde;
    }

    @Override
    public void processValue(int value, int x, int y) {
        if (value != -1) {
            FabriquePnj.fabriquePnj(ACTOR_TYPES[value], 1, monde, new Position(x + 0.5, y + 0.5), false);
        }
    }	
}
