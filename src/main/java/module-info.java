module com.example.essaie {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires javafx.media;
    requires junit;
    requires org.junit.jupiter.api;
    requires java.desktop;
    requires jcommander;

    opens universite_paris8.iut.EtrangeEtrange to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange;
    exports universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite;
    opens universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.controller;
    opens universite_paris8.iut.EtrangeEtrange.controller to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.modele.constantes;
    opens universite_paris8.iut.EtrangeEtrange.modele.constantes to javafx.fxml;

    exports universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;
    opens universite_paris8.iut.EtrangeEtrange.modele.Utilitaire to javafx.fxml;
    
}