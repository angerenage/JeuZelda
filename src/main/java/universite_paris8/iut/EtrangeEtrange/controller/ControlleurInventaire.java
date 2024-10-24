package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.vues.Menus.Inventaire.gestionAffichageInventaire;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlleurInventaire implements Initializable {
    public VBox VBox;
    /**
     * Texte "Inventaire" dans le menu inventaire
     */
    @FXML
    private ImageView titreInventaire;
    /**
     * Texte "Main droite" dans le menu inventaire
     */
    @FXML
    private ImageView titreMainDroite;
    /**
     * Texte "Main gauche" dans le menu inventaire
     */
    @FXML
    private ImageView titreMainGauche;
    /**
     * TilePane qui va stocker les images des objets dans l'inventaire
     */
    @FXML
    private TilePane objetsInventaire;
    /**
     * Tilepane qui va stocker les images des cases de stockage de l'inventaire
     */
    @FXML
    private TilePane caseStockageInventaire;
    /**
     * Tilepane qui va stocker les quantités de chaque objets présent dans l'inventaire
     */
    @FXML
    public TilePane quantitéObjetInventaire;
    /**
     * Pane qui permet de stocker l'ImageView de la case de stockage de la main droite et l'ImageView de l'objet dans la main droite
     */
    @FXML
    private Pane conteneurObjetMainDroite;
    /**
     * ImageView de l'objet dans la main droite
     */
    private ImageView objetMainDroite;
    /**
     * Pane qui permet de stocker l'ImageView de la case de stockage de la main droite et l'ImageView de l'objet dans la main gauche
     */
    @FXML
    private Pane conteneurObjetMainGauche;
    /**
     * ImageView de l'objet dans la main gauche
     */
    @FXML
    private ImageView objetMainGauche;
    /**
     * Switch donnéees qui permet de récupérer les données nécessaires (voir class SwitchScene)
     */
    private SwitchScene switchScene;

    /**
     * Entier qui permet de savoir quelle case de l'inventaire est sélectionné pour échanger l'objet avec la main droite ou gauche
     */
    private IntegerProperty caseSurvole;
    private int caseVerouille;
    private Joueur joueur;
    private gestionAffichageInventaire gestionAffichageInventaire;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        caseSurvole = new SimpleIntegerProperty(0);
        caseVerouille = -1; // Rien de sélectionné
        switchScene = SwitchScene.getSwitchScene();
        switchScene.setControlleurInventaire(this);
        joueur = switchScene.getJoueur();

        // Récupération Pane + Joueur
        switchScene = SwitchScene.getSwitchScene();

        gestionAffichageInventaire = new gestionAffichageInventaire(switchScene.getJoueur(), objetsInventaire, caseStockageInventaire, quantitéObjetInventaire,
                conteneurObjetMainDroite, objetMainDroite, conteneurObjetMainGauche, objetMainGauche, titreInventaire, titreMainDroite, titreMainGauche);
        gestionAffichageInventaire.listenerCaseSurvole(caseSurvole);


    }

    public void keyBoardInventaire(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        int tailleInventaire = joueur.getSac().getTailleMax();

        if(keyCode==ConstantesClavier.menuDeplacementGauche)
            if (caseSurvole.get() == 0)
                caseSurvole.set(tailleInventaire - 1);
            else
                caseSurvole.set(caseSurvole.get() - 1);
        else if(keyCode==ConstantesClavier.menuDeplacementDroite)
            if (caseSurvole.get() == tailleInventaire - 1)
                caseSurvole.set(0);
            else
                caseSurvole.set(caseSurvole.get() + 1);
        else if(keyCode==ConstantesClavier.menuDeplacementBas)
            if(caseSurvole.get() < tailleInventaire)
                caseSurvole.set(tailleInventaire);
            else
                caseSurvole.set(tailleInventaire+1);
        else if(keyCode==ConstantesClavier.menuDeplacementHaut)
            if(caseSurvole.get() == tailleInventaire+1)
                caseSurvole.set(tailleInventaire);
            else
                caseSurvole.set(0);
        else if(keyCode==ConstantesClavier.menuSelectionner) {
            if(caseVerouille!=-1)
                joueur.getSac().echangerEmplacement(joueur, caseVerouille, caseSurvole.get());
            if(caseVerouille!=caseSurvole.get())
                gestionAffichageInventaire.affichageInventaire(joueur.getSac());
            caseVerouille=caseSurvole.get();
            gestionAffichageInventaire.caseVerouille(caseSurvole.get());
        }
    }
    public void requestFocus(){
        VBox.requestFocus();
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        this.VBox.requestFocus();
    }
}
