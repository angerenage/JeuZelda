package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Defense;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.DefenseSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

/**
 * Représente un être vivant dans le monde du jeu.
 * Cette classe étend la classe abstraite Acteur
 */
public abstract class Entite extends Acteur {
    protected Defense statsDefense;
    protected DefenseSpecial statsDefenseSpecial;

    /**
     * Crée une nouvelle instance d'Entite avec les paramètres spécifiés.
     *
     * @param x                La position horizontale de l'entité dans le monde.
     * @param y                La position verticale de l'entité dans le monde.
     * @param direction        La direction initiale de l'entité.
     * @param pv               Les points de vie de l'entité.
     * @param defense          La défense de l'entité.
     * @param defenseSpecial   La défense spéciale de l'entité.
     * @param vitesse          La vitesse de déplacement de l'entité.
     * @param hitbox           La hitbox de l'entité.
     */
    public Entite(double x,double y,Direction direction,double pv,double defense,double defenseSpecial,double vitesse, Hitbox hitbox) {
        super(x,y,direction,pv,vitesse,hitbox);
        this.statsDefense = new Defense(defense);
        this.statsDefenseSpecial = new DefenseSpecial(defenseSpecial);
    }

    /**
     * Subit des dégâts infligés par une source dommageable.
     * @param causeDegat La source de dégâts.
     */
    public void subitAttaque(Dommageable causeDegat, Offensif entiteOffensif) {
        enlevePv((calculeDegatPhysique(entiteOffensif.getAttaque(),causeDegat.degatPhysique()) + calculeDegatSpecial(causeDegat.degatSpecial(), entiteOffensif.getAttaqueSpecial()))/2);
    }

    public void subitCollision(Acteur acteur) {
        acteur.causeCollision(this);
    }

    public void causeCollision(Acteur acteur) {
        acteur.seFaitPousser(this);
    }

    @Override
    public void seFaitPousser(Acteur acteur) {

    }


    /**
     * Calcule les dégâts physiques subis par l'entité.
     * @param attaqueEntite       Les dégâts physiques infligés.
     * @param degatArme La force de l'entité qui inflige les dégâts.
     * @return Les dégâts physiques subis.
     */
    protected double calculeDegatPhysique(double attaqueEntite, double degatArme) {
        return Math.abs(attaqueEntite+degatArme - statsDefense.getDefense());
    }

    /**
     * Calcule les dégâts spéciaux subis par l'entité.
     *
     * @param attaqueSpecialEntite Les dégâts spéciaux infligés.
     * @param degatArme    La force de l'entité qui inflige les dégâts spéciaux.
     * @return Les dégâts spéciaux subis.
     */
    protected double calculeDegatSpecial(double attaqueSpecialEntite, double degatArme) {
        return Math.abs(attaqueSpecialEntite+degatArme - statsDefenseSpecial.getDefenseSpecial());
    }
}
