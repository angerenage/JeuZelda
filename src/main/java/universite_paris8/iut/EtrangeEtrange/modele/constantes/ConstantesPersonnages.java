package universite_paris8.iut.EtrangeEtrange.modele.constantes;

public class ConstantesPersonnages {

    // GUERRIER
    public static final double GUERRIER_PV = 80;
    public static final double GUERRIER_ATTAQUE = 70;
    public static final double GUERRIER_DEFENSE = 50;
    public static final double GUERRIER_ATTAQUE_SPECIAL = 10;
    public static final double GUERRIER_DEFENSE_SEPCIAL = 25;
    public static final double GUERRIER_VITESSE = 0.015;

    // ARCHER
    public static final double ARCHER_PV = 50;
    public static final double ARCHER_ATTAQUE = 50;
    public static final double ARCHER_DEFENSE = 40;
    public static final double ARCHER_ATTAQUE_SPECIAL = 20;
    public static final double ARCHER_DEFENSE_SEPCIAL = 40;
    public static final double ARCHER_VITESSE = 0.015;
    public static final double ARCHER_HITBOX_HAUTEUR = 0.35;
    public static final double ARCHER_HITBOX_LARGEUR = 0.50;

    // MAGE
    public static final double MAGE_PV = 40;
    public static final double MAGE_ATTAQUE = 20;
    public static final double MAGE_DEFENSE = 30;
    public static final double MAGE_ATTAQUE_SPECIAL = 60;
    public static final double MAGE_DEFENSE_SEPCIAL = 45;
    public static final double MAGE_VITESSE = 0.06;

    // NECROMANCIER
    public static final double NECROMANCIER_PV = 60;
    public static final double NECROMANCIER_ATTAQUE = 10;
    public static final double NECROMANCIER_DEFENSE = 30;
    public static final double NECROMANCIER_ATTAQUE_SPECIAL = 45;
    public static final double NECROMANCIER_DEFENSE_SEPCIAL = 50;
    public static final double NECROMANCIER_VITESSE = 0.04;


    // MAX VALUE
    public static final double MAX_PV = GUERRIER_PV;
    public static final double MAX_ATTAQUE = GUERRIER_ATTAQUE;
    public static final double MAX_DEFENSE = GUERRIER_DEFENSE;
    public static final double MAX_ATTAQUE_SPECIAL = MAGE_ATTAQUE_SPECIAL;
    public static final double MAX_DEFENSE_SPECIAL = NECROMANCIER_DEFENSE_SEPCIAL;
    public static final double MAX_VITESSE = MAGE_VITESSE;

}
