package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Defense;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.DefenseSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public abstract class Entite extends Acteur {
    protected Defense statsDefense;
    protected DefenseSpecial statsDefenseSpecial;

    public Entite(Monde monde, double x, double y, Direction direction, double pv, double defense, double defenseSpecial, double vitesse, Hitbox hitbox) {
        super(monde, x, y, direction, pv, vitesse, hitbox);
        this.statsDefense = new Defense(defense);
        this.statsDefenseSpecial = new DefenseSpecial(defenseSpecial);
    }

    public void subitAttaque(Dommageable causeDegat, EntiteOffensif entiteOffensif) {
        enlevePv((subitDegatPhysique(entiteOffensif.getAttaque(), causeDegat.degatPhysique()) + subitDegatSpecial(causeDegat.degatSpecial(), entiteOffensif.getAttaqueSpecial())) / 2);
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

    protected double subitDegatPhysique(double attaqueEntite, double degatArme) {
        return Math.abs(attaqueEntite + degatArme - statsDefense.getDefense());
    }

    protected double subitDegatSpecial(double attaqueSpecialEntite, double degatArme) {
        return Math.abs(attaqueSpecialEntite + degatArme - statsDefenseSpecial.getDefenseSpecial());
    }


}
