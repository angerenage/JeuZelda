package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternDeplacementAleatoire;
import universite_paris8.iut.EtrangeEtrange.modele.interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.Piece;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ParametreMonstre;

public class Slime extends NPEs {
    /**
     * Crée une nouvelle entité offensif.
     *
     * @param x         La position horizontale de l'entité.
     * @param y         La position verticale de l'entité.
     * @param direction La direction dans laquelle l'entité est orientée.
     * @param hitbox    La hitbox de l'entité.
     */
    public Slime(double x, double y, Direction direction, Hitbox hitbox) {
        super(x, y, direction,
              ParametreMonstre.PV_SLIME,
              ParametreMonstre.DEFENSE_SLIME,
              ParametreMonstre.DEFENSE_SPECIALE_SLIME,
              ParametreMonstre.VITESSE_SLIME,
              hitbox);
    }

    @Override
    protected Pattern initPattern() {
        return new PatternDeplacementAleatoire(this);
    }

    @Override
    public String typeActeur() {
        return "slime";
    }

    @Override
    public void dropApresMort() {
        double x = getPosition().getX();
        double y = getPosition().getY();
        getMonde().ajouterDropAuSol(new DropAuSol(new Piece(), new Position(x, y)));
    }

    @Override
    public boolean estUnEnemie() {
        return true;
    }

    @Override
    public Prompt getPrompt() {
       return  null;
    }
}
