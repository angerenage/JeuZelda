package universite_paris8.iut.EtrangeEtrange.modele.constantes;

import javafx.scene.text.HitInfo;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class ParametreMonstre {
    // Paramètres pour Squelette
    public static final int PV_SQUELETTE = 100;
    public static final int ATTAQUE_SQUELETTE = 10;
    public static final int DEFENSE_SQUELETTE = 10;
    public static final int ATTAQUE_SPECIALE_SQUELETTE = 1;
    public static final int DEFENSE_SPECIALE_SQUELETTE = 10;
    public static final double VITESSE_SQUELETTE = 0.005;
	public static final Hitbox HITBOX_SQUELETTE = new Hitbox(0.5, 0.5);

    // Paramètres pour Slime
    public static final int PV_SLIME = 100;
    public static final int DEFENSE_SLIME = 20;
    public static final int DEFENSE_SPECIALE_SLIME = 50;
    public static final double VITESSE_SLIME = 0.005;
	public static final Hitbox HITBOX_SLIME = new Hitbox(0.25, 0.5);

    // Paramètres pour Roi Squelette
    public static final int PV_ROI_SQUELETTE = 1000;
    public static final int ATTAQUE_ROI_SQUELETTE = 20;
    public static final int DEFENSE_ROI_SQUELETTE = 20;
    public static final int ATTAQUE_SPECIALE_ROI_SQUELETTE = 20;
    public static final int DEFENSE_SPECIALE_ROI_SQUELETTE = 20;
    public static final double VITESSE_ROI_SQUELETTE = 0.003;

	// Paramètres pour Bloc
	public static final int PV_BLOC = 1;
	public static final Hitbox HITBOX_BLOC = new Hitbox(1, 1);
}
