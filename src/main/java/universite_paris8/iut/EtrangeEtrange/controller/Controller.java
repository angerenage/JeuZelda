package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;


import universite_paris8.iut.EtrangeEtrange.Runner;
import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Bloc.Bloc;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.GestionPrompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Monstre.Slime;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Monstre.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Archer;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantePrompt;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.PieceOr;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.vues.AfficheBulleConversation;
import universite_paris8.iut.EtrangeEtrange.vues.BarreDeVie.GestionAffichageVieJoueur;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.DropAuSol.gestionAffichageSpriteDropAuSol;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.GestionAffichageSpriteEntite;


import universite_paris8.iut.EtrangeEtrange.vues.Sprite.GestionActeur;
import universite_paris8.iut.EtrangeEtrange.vues.gestionAffichageMap;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Boss.RoiSquelette;

import java.io.File;
import java.io.IOException;
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
    @FXML
    private HBox hboxCoeurs;

    @FXML
    private Pane paneInteraction;

    private Monde monde;
    private Joueur joueur;
    private Timeline gameLoop;
    

    private SwitchScene switchDonnees;
    private GestionAffichageVieJoueur vueVie; // La vue qui gère l'affichage des PV
//-----------------------------------------------//

    private boolean interactionAvecPnj = false;
    private ListView<String> listProposition;
    private Label textePnj;
    private AfficheBulleConversation afficheBulleConversation;

    private GestionPrompt gestionPrompt;





    //---------------------------------------------------//






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchDonnees = switchDonnees.getSwitchScene();
        switchDonnees.setControllerJeu(this);
        initMonde();
        initJoueur();
        initVie();



        switchDonnees.setJoueur(joueur);
        initPane();

        GestionAffichageSpriteEntite gestionAffichageSprite = new GestionAffichageSpriteEntite(paneEntite);
        monde.setListenerListeEntites(gestionAffichageSprite);
        gestionAffichageSprite.ajouterJoueur(joueur);

        GestionActeur gestionCauseDegat = new GestionActeur(monde,paneEntite);
        monde.setListenerActeur(gestionCauseDegat);


        gestionAffichageMap gestionAffichageMap = new gestionAffichageMap(monde, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        gestionAffichageMap.afficherMondeJSON();

        gestionAffichageSpriteDropAuSol gestionAffichageDropAuSol = new gestionAffichageSpriteDropAuSol(paneEntite);
        monde.setListenerListeDropsAuSol(gestionAffichageDropAuSol);
        monde.ajouterDropAuSol(new DropAuSol(new Arc(), 1, new Position(23, 23), joueur));
        
        monde.setJoueur(joueur);
        initBoss();

        Bloc bloc = new Bloc( monde, 11, 11, Direction.BAS, 1, 0, new Hitbox(1,1));
        monde.ajoutActeur(bloc);
        monde.ajoutActeur(new Marchand(monde,12,12,Direction.BAS));
        initGameLoop();
        gameLoop.play();

    }

    private void initBoss() {
        monde.ajoutActeur(new RoiSquelette(monde,5.5,27.5,Direction.DROITE));
    }

    private void initVie() {
        vueVie = new GestionAffichageVieJoueur(joueur.getStatsPv());
        vueVie.setHboxCoeurs(hboxCoeurs); // Passez l'HBox à la gestion de la vue
        vueVie.initialize(); // Initialiser la vue

    }


    private void initGameLoop() {
        gameLoop = new Timeline();

        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.seconds(0.005), (ev -> {monde.unTour();}));
        gameLoop.getKeyFrames().add(kf);
    }

    public void initPane(){
        // Initialisation taille en fonction de la taille de la map
        int largeur = Monde.getSizeMondeLargeur()*Constantes.tailleTile;
        int hauteur = Monde.getSizeMondeHauteur()*Constantes.tailleTile;

        TilePaneSol.setMaxSize(largeur, hauteur);
        TilePaneSol.setMinSize(largeur, hauteur);

        TilePaneTraversable.setMaxSize(largeur, hauteur);
        TilePaneTraversable.setMinSize(largeur, hauteur);

        TilePaneNontraversable.setMaxSize(largeur, hauteur);
        TilePaneNontraversable.setMinSize(largeur, hauteur);


        // Listener pour que la TilePane et la Pane suivent le joueur
        joueur.getPosition().getXProperty().addListener((obs, old, nouv)-> {
            paneEntite.setTranslateX(scrollMap(joueur.getPosition().getX(), Constantes.largeurEcran, paneEntite.getTranslateX()));
        });
        joueur.getPosition().getYProperty().addListener((obs, old, nouv)-> {
            paneEntite.setTranslateY(scrollMap(joueur.getPosition().getY(), Constantes.hauteurEcran, paneEntite.getTranslateY()));
        });

        paneEntite.setTranslateX(scrollMap(joueur.getPosition().getX(), Constantes.largeurEcran, paneEntite.getTranslateX()));
        paneEntite.setTranslateY(scrollMap(joueur.getPosition().getY(), Constantes.hauteurEcran, paneEntite.getTranslateY()));
    }

    /**
     * Permet de déplacer l'affichage lorsque le joueur se déplace :
     * @param position : Position du joueur
     * @param longueurAxe : Hauteur ou largeur de l'écran
     */
    public double scrollMap(double position, int longueurAxe, double positionInitiale){
        if (-position * Constantes.tailleTile + longueurAxe / 2.0 < 0)
            if (-position * Constantes.tailleTile + longueurAxe / 2.0 > -Monde.getSizeMondeLargeur()*Constantes.tailleTile+longueurAxe )
                return -position * Constantes.tailleTile + longueurAxe / 2.0;
        return positionInitiale;
    }
    public void initMonde()
    {
        monde = new Monde("src/main/resources/universite_paris8/iut/EtrangeEtrange/TiledMap/", "maptest", Monde.getSizeMondeHauteur(), Monde.getSizeMondeLargeur());
    }

    public void initJoueur() {
        String guerrier = switchDonnees.getClasseJoueur();

        if (guerrier.equals("Guerrier")) {
            joueur = new Guerrier(monde, Monde.getxPointDeDepart(), Monde.getyPointDeDepart(), Direction.BAS);
        } else if (guerrier.equals("Archer")) {
            joueur = new Archer(monde, Monde.getxPointDeDepart(), Monde.getyPointDeDepart(), Direction.BAS);
        } else if (guerrier.equals("Mage")) {
            // pas encore implementer
        } else if (guerrier.equals("Necromancier")) {
            // pas encore implementer
        }
        switchDonnees.setJoueur(joueur);
        monde.setJoueur(joueur);
        joueur.getSac().ajoutItem(new Epee());
        joueur.getSac().ajoutItem(new LivreMagique());
        joueur.getSac().ajoutItem(new Arc());
        joueur.getSac().ajoutItem(new Potion());
        joueur.getSac().ajoutItem(new PieceOr());


        joueur.getStatsPv().getPvActuelleProperty().addListener((obs, old, nouv) -> {
            if (nouv.doubleValue() <= 0) {
                try {
                    switchDonnees.gameOver();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    public void keyPressed(KeyEvent keyEvent) throws IOException
    {
        KeyCode keyCode = keyEvent.getCode();

        if(!interactionAvecPnj) {
            if (keyCode == ConstantesClavier.deplacementHaut) {
                joueur.setDirection(Direction.HAUT);
                joueur.setSeDeplace(true);
            }
            else if (keyCode == ConstantesClavier.deplacementDroite) {
                joueur.setDirection(Direction.DROITE);
                joueur.setSeDeplace(true);
            }
            else if (keyCode == ConstantesClavier.deplacementGauche) {
                joueur.setDirection(Direction.GAUCHE);
                joueur.setSeDeplace(true);
            }
            else if (keyCode == ConstantesClavier.deplacementBas) {
                joueur.setDirection(Direction.BAS);
                joueur.setSeDeplace(true);
            }
            else if (keyCode == ConstantesClavier.recupererObjetSol)
                joueur.ramasserObjet();
            else if (keyCode == ConstantesClavier.degattest)
                joueur.enlevePv(10);
            else if(keyCode == ConstantesClavier.attaquer) {
                joueur.actionMainDroite();
                if(joueur.getObjetMainDroite() instanceof Potion){

                    /*

                    Media media = new Media(new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/potion.mp3").toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();

                    */

                }
            }
            else if (keyCode == KeyCode.B)
                interaction();
            else if (keyCode == ConstantesClavier.inventaire)
                ouvrirMenu();
        }
        else{
            handleInteractionPnj(keyEvent);
        }


    }




    public void onKeyReleased(KeyEvent keyEvent)
    {
            KeyCode touche = keyEvent.getCode();

            if (!interactionAvecPnj)
            {
                if(touche==ConstantesClavier.deplacementHaut) {
                    joueur.enleveDirection(Direction.HAUT);
                    joueur.setSeDeplace(false);
                }
                else if(touche==ConstantesClavier.deplacementDroite) {
                    joueur.enleveDirection(Direction.DROITE);
                    joueur.setSeDeplace(false);
                }
                else if(touche==ConstantesClavier.deplacementGauche) {
                    joueur.enleveDirection(Direction.GAUCHE);
                    joueur.setSeDeplace(false);
                }
                else if(touche==ConstantesClavier.deplacementBas) {
                    joueur.enleveDirection(Direction.BAS);
                    joueur.setSeDeplace(false);
                }
                else if(touche==ConstantesClavier.courrir)
                    joueur.estEntrainDeCourir(false);
            }



    }

    public void mouseClick(MouseEvent mouseEvent)
    {
        this.paneEntite.requestFocus();

        if (mouseEvent.getButton() == MouseButton.PRIMARY)
            this.joueur.actionMainDroite();
    }

    public void ouvrirMenu() throws IOException {
        if(switchDonnees.getSceneMenu()==null){
            FXMLLoader fxmlLoaderMenu = new FXMLLoader(Runner.class.getResource("menuView.fxml"));
            Scene sceneMenu = new Scene(fxmlLoaderMenu.load(), Constantes.largeurEcran, Constantes.hauteurEcran);
            switchDonnees.setSceneMenu(sceneMenu);
            switchDonnees.setControllerMenu(fxmlLoaderMenu.getController());
        }
        switchDonnees.envoyerPanes(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
        switchDonnees.getControllerMenu().recupererDonnees();
        switchDonnees.getStage().setScene(switchDonnees.getSceneMenu());
        switchDonnees.getStage().show();

    }

    public void recupererDonnees() {
        switchDonnees.recupererPane(paneEntite, TilePaneSol, TilePaneTraversable, TilePaneNontraversable);
    }




    //-------------------------------------------------------------------------------------------//
    //                                      PNJ                                                 //
    //-----------------------------------------------------------------------------------------//




    public void interaction()
    {
        Acteur acteur = monde.interactionAvecActeur();
        System.out.println(acteur);

        if (acteur != null)
        {
            Prompt prompt = acteur.getPrompt();


            if (prompt != null)
            {
                this.interactionAvecPnj = true;

                this.afficheBulleConversation = new AfficheBulleConversation(joueur,acteur,paneInteraction);
                this.listProposition = afficheBulleConversation.getListProposition();
                this.textePnj = afficheBulleConversation.getTextePnj();

                this.gestionPrompt = new GestionPrompt(prompt);
                this.afficheBulleConversation.affichePrompt(gestionPrompt.getPrompt());

            }
        }
    }


    // Permet de passer un tour du prompt

    private void promptSuivant() {
        // Exécute l'action associée au prompt actuel, s'il y en a une
        if (gestionPrompt.getPrompt().getAction() != null) {
            Prompt pr = gestionPrompt.getPrompt().getAction().execute();

            // Si l'action retourne un nouveau prompt, continuez avec ce nouveau prompt
            if (pr != null) {
                gestionPrompt = new GestionPrompt(pr);
                this.afficheBulleConversation.affichePrompt(gestionPrompt.getPrompt());
                return; // Arrêtez ici pour éviter d'exécuter le reste du code
            }
        }

        // Passer au prompt suivant basé sur le choix sélectionné
        gestionPrompt.promptSuivant(choixSelectionner());

        // Affiche le nouveau prompt si disponible
        if (gestionPrompt.getPrompt() != null) {
            this.afficheBulleConversation.affichePrompt(gestionPrompt.getPrompt());
        } else {
            interactionFinie(); // Terminer l'interaction si aucun prompt suivant n'est disponible
        }
    }








    //  Permet de changer le choix de réponse
    private void defile(int scroll)
    {
        int index = listProposition.getSelectionModel().getSelectedIndex();
        int indexSuivant = index + scroll;

        if (indexSuivant >= 0 && indexSuivant < listProposition.getItems().size())
        {
            listProposition.getSelectionModel().select(indexSuivant);
            listProposition.scrollTo(indexSuivant);
        }
    }


    public void interactionFinie()
    {
        this.textePnj.setVisible(false);
        this.listProposition.setVisible(false);
        this.interactionAvecPnj = false;

    }


    //  Retourne le choix séléctionner
    private String choixSelectionner()
    {
        return listProposition.getSelectionModel().getSelectedItem();
    }



    //
    private void handleInteractionPnj(KeyEvent event)
    {
        KeyCode keyCode = event.getCode();

        if (keyCode == KeyCode.ENTER)
        {
            promptSuivant();
        }
        else if (keyCode == KeyCode.S || keyCode == KeyCode.D)
        {
            defile(1);
        }
        else if (keyCode == KeyCode.Z || keyCode == KeyCode.Q)
        {
            defile(-1);
        }
    }



















}