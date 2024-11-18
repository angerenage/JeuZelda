package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Boss;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternMonstre.PatternRoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ParametreMonstre;

public class RoiSquelette extends Boss {
    private final int nbrPotion = 6;
    private final int nbrEpee = 3;
    private Sac sac;
    private Position positionDepart;

    private Pattern pattern;
    public RoiSquelette(double x, double y, Direction direction) {
        super(x, y, direction,
              ParametreMonstre.PV_ROI_SQUELETTE,
              ParametreMonstre.ATTAQUE_ROI_SQUELETTE,
              ParametreMonstre.DEFENSE_ROI_SQUELETTE,
              ParametreMonstre.ATTAQUE_SPECIALE_ROI_SQUELETTE,
              ParametreMonstre.DEFENSE_SPECIALE_ROI_SQUELETTE,
              ParametreMonstre.VITESSE_ROI_SQUELETTE,
              new Hitbox(1,1)
        );
        this.positionDepart = new Position(x, y);
        initInventaire();
        pattern = null;
    }

    private void initInventaire() {
        this.sac = new Sac();
        for (int i = 0; i < nbrEpee; i++) this.sac.ajoutItem(new Epee());
        for (int i = 0; i < nbrPotion; i++) this.sac.ajoutItem(new Potion());
    }

    @Override
    public String typeActeur() {
        return "roisquelette";
    }

    @Override
    public void dropApresMort() {
		Joueur joueur = monde.getJoueur();
        joueur.getCompetences().getRoot().monterDeNiveau(joueur);
    }

    @Override
    public boolean estUnEnemie() {
        return true;
    }

    @Override
    public void faitUneAttaque() {
        Epee epee = sac.trouveObjet(Epee.class);
        if (epee != null) {
            if (epee.utiliseePar(this)) sac.supprimeObjet(epee);
        }
    }

    public void invoqueSquelette() {

    }

    public void teleportePosDepart()
    {
        setPosition(positionDepart);
    }

    @Override
    protected Pattern initPattern() {
        if (pattern == null)
            pattern = new PatternRoiSquelette(this);

        return pattern;
    }
}
