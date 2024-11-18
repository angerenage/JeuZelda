package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Offensif;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Attaque;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.AttaqueSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public abstract class Monstre extends NPEs implements Offensif {

    private Attaque attaque;
    private AttaqueSpecial attaqueSpecial;

    public Monstre(double x, double y, Direction direction, double pv, double attaque,double defense,double attaqueSpecial, double defenseSpecial, double vitesse, Hitbox hitbox) {
        super(x, y, direction, pv, defense, defenseSpecial, vitesse, hitbox);
        this.attaque = new Attaque(attaque);
        this.attaqueSpecial = new AttaqueSpecial(attaqueSpecial);
    }

    public abstract void faitUneAttaque();



    @Override
    public double getAttaque() {
        return attaque.getAttaque();
    }

    @Override
    public double getAttaqueSpecial() {
        return attaqueSpecial.getAttaqueSpecial();
    }


}
