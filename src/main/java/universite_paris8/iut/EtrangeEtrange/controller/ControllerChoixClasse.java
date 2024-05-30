package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerChoixClasse implements Initializable
{


    @FXML
    private HBox hbox;

    @FXML
    private Label nomClasse;
    @FXML
    private Label descriptionClasse;


    @FXML
    private ProgressBar statPv;
    @FXML
    private ProgressBar statAttaque;
    @FXML
    private ProgressBar statDefense;
    @FXML
    private ProgressBar statAttaqueSpecial;
    @FXML
    private ProgressBar statDefenseSpecial;
    @FXML
    private ProgressBar statVitesse;



    private double pvPlusElevee;
    private double atkPlusElevee;
    private double defPlusElevee;
    private double atkSpePlusElevee;
    private double defSpePlusElevee;
    private double vitPlusElevee;






    private String[] nomGuerrier;

    private StringProperty nomActuelle;

    private int classActuelle;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        hbox.requestFocus();


        this.nomGuerrier = new String[4];
        this.nomGuerrier[0] = "Guerrier";
        this.nomGuerrier[1] = "Archer";
        this.nomGuerrier[2] = "Mage";
        this.nomGuerrier[3] = "Necromancier";
        this.classActuelle = 0;

        this.pvPlusElevee = ConstantesPersonnages.pvPlusHaut();
        this.atkPlusElevee = ConstantesPersonnages.attaquePlusHaute();
        this.defPlusElevee = ConstantesPersonnages.defensePlusHaute();
        this.atkSpePlusElevee = ConstantesPersonnages.attaqueSpecialPlusHaute();
        this.defSpePlusElevee = ConstantesPersonnages.defenseSpecialPlusHaute();
        this.vitPlusElevee = ConstantesPersonnages.vitessePlusHaute();


        this.nomActuelle = new SimpleStringProperty();

        initListenerClasse();
        this.nomActuelle.set("Guerrier");
        this.classActuelle = 0;
    }


    private void initListenerClasse()
    {
        this.nomActuelle.addListener(e ->
        {
            String guerrier = nomActuelle.get();

            if (guerrier.equals("Guerrier"))
            {
                this.nomClasse.setText("Guerrier");
                changeValueProgressBar(ConstantesPersonnages.GUERRIER_PV,ConstantesPersonnages.GUERRIER_ATTAQUE,ConstantesPersonnages.GUERRIER_DEFENSE,ConstantesPersonnages.GUERRIER_ATTAQUE_SPECIAL,ConstantesPersonnages.GUERRIER_DEFENSE_SEPCIAL,ConstantesPersonnages.GUERRIER_VITESSE);
                this.descriptionClasse.setText(ConstantesPersonnages.descriptionGuerrier().toString());
            }
            else if (guerrier.equals("Archer"))
            {
                this.nomClasse.setText("Archer");
                changeValueProgressBar(ConstantesPersonnages.ARCHER_PV,ConstantesPersonnages.ARCHER_ATTAQUE,ConstantesPersonnages.ARCHER_DEFENSE,ConstantesPersonnages.ARCHER_ATTAQUE_SPECIAL,ConstantesPersonnages.ARCHER_DEFENSE_SEPCIAL,ConstantesPersonnages.ARCHER_VITESSE);
                this.descriptionClasse.setText(ConstantesPersonnages.descriptionArcher().toString());
            }
            else if (guerrier.equals("Mage"))
            {
                this.nomClasse.setText("Mage");
                changeValueProgressBar(ConstantesPersonnages.MAGE_PV,ConstantesPersonnages.MAGE_ATTAQUE,ConstantesPersonnages.MAGE_DEFENSE,ConstantesPersonnages.MAGE_ATTAQUE_SPECIAL,ConstantesPersonnages.MAGE_DEFENSE_SEPCIAL,ConstantesPersonnages.MAGE_VITESSE);
                this.descriptionClasse.setText(ConstantesPersonnages.descriptionMage().toString());
            }
            else if (guerrier.equals("Necromancier"))
            {
                this.nomClasse.setText("Necromancier");
                changeValueProgressBar(ConstantesPersonnages.NECROMANCIER_PV,ConstantesPersonnages.NECROMANCIER_ATTAQUE,ConstantesPersonnages.NECROMANCIER_DEFENSE,ConstantesPersonnages.NECROMANCIER_ATTAQUE_SPECIAL,ConstantesPersonnages.NECROMANCIER_DEFENSE_SEPCIAL,ConstantesPersonnages.NECROMANCIER_VITESSE);
                this.descriptionClasse.setText(ConstantesPersonnages.descriptionNecromancier().toString());
            }

            miseAjourProgressBar(statPv,statAttaque,statDefense,statAttaqueSpecial,statDefenseSpecial,statVitesse);
        });
    }

    private void miseAjourProgressBar(ProgressBar... progressBars)
    {
        for (ProgressBar bar : progressBars)
            bar.setStyle(couleurProgressBar(bar.getProgress()));
    }


    private String couleurProgressBar(double progression)
    {
        StringBuilder fxCouleur = new StringBuilder("-fx-accent: ");
        String couleur;

        if (progression < 0.2)
            couleur = " red;";
        else if (progression < 0.4)
            couleur = "  #CCCC00;";
        else if (progression < 0.6)
            couleur = " orange;";
        else if (progression < 0.85)
            couleur = " green;";
        else
            couleur = " blue;";

        fxCouleur.append(couleur);

        return fxCouleur.toString();
    }
    private void changeValueProgressBar(double pv,double atk,double def,double atkSpe,double defSpe,double vit)
    {
        this.statPv.setProgress(pv/pvPlusElevee);
        this.statAttaque.setProgress(atk/atkPlusElevee);
        this.statDefense.setProgress(def/defPlusElevee);
        this.statAttaqueSpecial.setProgress(atkSpe/atkSpePlusElevee);
        this.statDefenseSpecial.setProgress(defSpe/defSpePlusElevee);
        this.statVitesse.setProgress(vit/vitPlusElevee);
    }


    @FXML
    public void changeClasse(KeyEvent keyEvent)
    {


        if(keyEvent.getCode() == KeyCode.Q)
        {
            this.classActuelle = (this.classActuelle - 1) % this.nomGuerrier.length;
            this.nomActuelle.set(nomGuerrier[classActuelle]);
        }
        else if (keyEvent.getCode() == KeyCode.D)
        {
            this.classActuelle = (this.classActuelle + 1) % this.nomGuerrier.length;
            this.nomActuelle.set(nomGuerrier[classActuelle]);
        }
    }

    @FXML
    public void mouseClick(MouseEvent mouseEvent)
    {
        this.hbox.requestFocus();
    }
}
