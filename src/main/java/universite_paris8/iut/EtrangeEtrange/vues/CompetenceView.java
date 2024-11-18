package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competences;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;

import java.util.List;

public class CompetenceView {
    private Competences competences;
    private Joueur joueur;
    private int tailleIcon = 100;
    private Pane pane;
    private ColorAdjust colorAdjust;

    public CompetenceView(Pane pane, Joueur joueur) {
        this.joueur = joueur;
        this.pane = pane;
        this.colorAdjust = new ColorAdjust();
        this.colorAdjust.setBrightness(-0.8);
        this.competences = joueur.getCompetences();

        Competence competence = competences.getRoot();
        int x = 200;
        int y = 20;

        ImageView imageView = spriteIcon(competence);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);

        this.pane.getChildren().add(imageView);

        constructionArbre(competence.getEnfants(), x, y + tailleIcon, tailleIcon);
    }

    public void constructionArbre(List<Competence> tronc, int x, int y, int ecartEntreIcon) {
        if (tronc == null || tronc.isEmpty()) return;

        int xTmp = x;

        for (Competence competence : tronc) {
            ImageView imageView = spriteIcon(competence);
            imageView.setTranslateX(xTmp);
            imageView.setTranslateY(y);
            this.pane.getChildren().add(imageView);
            xTmp += ecartEntreIcon;
            constructionArbre(competence.getEnfants(), x, y +tailleIcon, -ecartEntreIcon);
        }
    }

    private ImageView spriteIcon(Competence competence) {
        Image image = null;
        switch (competence.getClass().getSimpleName()) {
            case "CompetenceUpAttaque" -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/force.png");
            case "CompetenceUpDefense" -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/defense.png");
            case "CompetenceUpPV" -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/coeur.png");
            case "CompetenceUpDefenseSpecial" -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/defensespeciale.png");
            case "CompetenceCourir" -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/sprint.png");
            case "CompetenceInvocation" -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/invocation.png");
            case "CompetenceUpAttaqueSpecial" -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/livremagique.png");
        }

        ImageView imageView = new ImageView();
        imageView.setScaleX(1);
        imageView.setScaleY(1);

        if(!competence.estDebloquer()) imageView.setEffect(colorAdjust);

        imageView.setOnMouseClicked(e -> {
            competence.monterDeNiveau(joueur);
            competence.debloquer();
            imageView.setEffect(null);
        });

        imageView.setImage(image);
        return imageView;
    }
}
