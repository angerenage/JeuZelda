package universite_paris8.iut.EtrangeEtrange;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.EtrangeEtrange.controller.SwitchScene;
import universite_paris8.iut.EtrangeEtrange.vues.constantes.ConstantesAffichage;
import universite_paris8.iut.EtrangeEtrange.vues.constantes.PathRessources;

import java.io.IOException;

public class Runner extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        initializeStage(stage);
    }

    public static void restartGame(Stage stage) throws IOException {
        initializeStage(stage);
    }

    private static void initializeStage(Stage stage) throws IOException {
        SwitchScene switchScene = SwitchScene.getSwitchScene();
        switchScene.setStage(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource(PathRessources.FXML_MENU_DEBUT_JEU));
        Scene scene = new Scene(fxmlLoader.load(), ConstantesAffichage.largeurEcran, ConstantesAffichage.hauteurEcran);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}



