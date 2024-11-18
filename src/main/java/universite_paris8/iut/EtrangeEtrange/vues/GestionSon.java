package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.media.AudioClip;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementEpee.Pattern.ComportementAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.ComportementFleche;

import java.io.File;

public class GestionSon
{

    private AudioClip musiqueFond;
    private AudioClip musiqueGameOver;

    public GestionSon()
    {
        musiqueFond = new AudioClip(new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/musiqueGame.mp3").toURI().toString());
        musiqueGameOver = new AudioClip(new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/musiqueGameOver.mp3").toURI().toString());
        musiqueFond.setCycleCount(AudioClip.INDEFINITE);
        musiqueFond.play(0.1);
    }

    public void gameOver(){
        musiqueFond.stop();
        musiqueGameOver.setCycleCount(AudioClip.INDEFINITE);
        musiqueGameOver.play(0.1);
    }

    public void stopMusique(){
        musiqueFond.stop();
        musiqueGameOver.stop();
    }
    public void lanceSong(Objet objet)
    {
        if(objet.getNom()!=null && objet.getNom() !="livremagique" && objet.getNom() !="arc" ) {
            AudioClip mediaPlayer = new AudioClip(new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/" + objet.getNom() + ".mp3").toURI().toString());
            mediaPlayer.play();
        }
    }

    public void lanceSong(Acteur acteur)
    {

        if (acteur != null)
        {
            String nom = "";

            if (acteur instanceof ComportementFleche)
                nom = "fleche";
            else if (acteur instanceof ComportementAttaqueEpee)
                nom = "epee";


            if (!nom.isEmpty())
            {
                AudioClip mediaPlayer = new AudioClip(new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/" + nom + ".mp3").toURI().toString());
                mediaPlayer.play();
            }
        }

    }




}