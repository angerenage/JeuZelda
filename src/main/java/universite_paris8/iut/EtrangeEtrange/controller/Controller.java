package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain.Lambda;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.vues.Deplacement;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.AnimationSprite;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.GestionCauseDegat;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.GestionCauseDegat;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.gestionAffichageSprite;
import universite_paris8.iut.EtrangeEtrange.vues.gestionAffichageMap;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TilePane TilePaneSol;
    @FXML
    private TilePane TilePaneTraversable;
    @FXML
    private TilePane TilePaneNontraversable;
    @FXML
    private Pane paneEntite;
    private Monde monde;
    private Joueur joueur;
    private Timeline gameLoop;
    private AnimationSprite spriteJoueur;
    int temps = 0;
    private Deplacement deplacement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMonde();
        initJoueur();
        initPane();

        gestionAffichageSprite gestionAffichageSprite = new gestionAffichageSprite(paneEntite);
        monde.setListenerListeEntites(gestionAffichageSprite);

        GestionCauseDegat gestionCauseDegat = new GestionCauseDegat(paneEntite);
        monde.setListenerProjectile(gestionCauseDegat);


        gestionAffichageMap gestionAffichageMap = new gestionAffichageMap(monde, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        gestionAffichageMap.afficherMonde();


        for(int i = 0 ; i < 30 ; i++) {
            Lambda lambda = new Lambda(monde, 16, 16, Direction.GAUCHE, new Hitbox(0.50, 0.50));
            monde.ajoutEntite(lambda);
        }


        deplacement = new Deplacement(joueur,spriteJoueur);
        initGameLoop();
        gameLoop.play();
    }


    private void initGameLoop() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame
                (
                    Duration.seconds(0.1),

                    (ev ->
                    {

                        for (Entite entite : monde.getEntities())
                        {
                            Lambda lambda1 = (Lambda) entite;
                            lambda1.action();
                        }

                        monde.verificationCollisionAvecArme();
                        monde.miseAjourCauseDegats();

                    })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void initPane(){
        // Initialisation taille en fonction de la taille de la map
        TilePaneSol.setMaxSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneSol.setMinSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);

        TilePaneTraversable.setMaxSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneTraversable.setMinSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);

        TilePaneNontraversable.setMaxSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);
        TilePaneNontraversable.setMinSize(Monde.getSizeMondeLargeur()*Constantes.tailleTile, Monde.getSizeMondeHauteur()*Constantes.tailleTile);


        // Listener pour que la TilePane et la Pane suivent le joueur
        joueur.getPosition().getXProperty().addListener((obs, old, nouv)->
                paneEntite.setTranslateX(-joueur.getPosition().getX()*Constantes.tailleTile+Constantes.largeurEcran/2.0)
        );
        joueur.getPosition().getYProperty().addListener((obs, old, nouv)->
                paneEntite.setTranslateY(-joueur.getPosition().getY()*Constantes.tailleTile+Constantes.hauteurEcran/2.0)
        );

        paneEntite.setTranslateX(-joueur.getPosition().getX()*Constantes.tailleTile+Constantes.largeurEcran/2.0);
        paneEntite.setTranslateY(-joueur.getPosition().getY()*Constantes.tailleTile+Constantes.hauteurEcran/2.0);
    }
    public void initMonde()
    {
        monde = new Monde("src/main/resources/universite_paris8/iut/EtrangeEtrange/TiledMap/", "maptest", Monde.getSizeMondeHauteur(), Monde.getSizeMondeLargeur());
    }

    public void initJoueur(){
        // Initialisation Coordonnées centre monde et des listeners
        joueur = new Guerrier(monde, Monde.getxPointDeDepart(), Monde.getyPointDeDepart(), Direction.BAS);

        spriteJoueur = new AnimationSprite(joueur, "chevalier");

        // Ajout du cercle au panneau paneEntité
        paneEntite.getChildren().add(spriteJoueur.getSpriteEntite());
    }

    public void keyPressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode){
            case Q:
                deplacement.addKeyCode(KeyCode.Q);
                break;
            case D:
                deplacement.addKeyCode(KeyCode.D);
                break;
            case Z:
                deplacement.addKeyCode(KeyCode.Z);
                break;
            case S:
                deplacement.addKeyCode(KeyCode.S);
                break;
            case M:
                joueur.enlevePv(60);
                break;
        }

    }
    public void onKeyReleased(KeyEvent keyEvent) {
        spriteJoueur.finAnimationMarche();
        deplacement.removeKeyCode(keyEvent.getCode());
    }



    public void mouseClick(MouseEvent mouseEvent)
    {
        this.paneEntite.requestFocus();

        if (mouseEvent.getButton() == MouseButton.PRIMARY)
            this.joueur.actionMainDroite();
    }


}