package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain.Lambda;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.vues.Deplacement;

public class gestionAffichageSprite implements ListChangeListener<Entite> {
    private Pane paneEntite;
    public gestionAffichageSprite(Pane paneEntite){
        this.paneEntite = paneEntite;
    }
    @Override
    public void onChanged(Change<? extends Entite> change) {
        while(change.next()){
            for (Entite entite : change.getAddedSubList()) {
                creeSprite(entite);
            }
            for(Entite entite : change.getRemoved())
                suprimmerSprite(entite);
        }
    }
    /**
     * Choisi le skin de sprite adéquat en fonction de la class de l'entité, et crée son sprite animé qui est directement ajouté à la vue
     * @param entite
     */
    public void creeSprite(Entite entite){
        String skin;
        if (entite.getClass().equals(Guerrier.class)) {
            skin = "chevalier";
        } else if (entite.getClass().equals(Lambda.class)) {
            skin = "squelette";
        } else {
            skin = "pnjtest";
        }

        AnimationSprite animationSprite = new AnimationSprite(entite, skin);

        /*
        ATTENTION : Il faut modifier les méthodes du code, pour que l'animation de marche se déclenche lorsque l'entite
        marche, et non l'activer dès le début que l'entite est ajouter à la liste
         */
        animationSprite.debutAnimationMarche();


        paneEntite.getChildren().add(animationSprite.getSprite());
    }

    /**
     * Suprimme le sprite de la vue dès qu'une entité est retiré de la liste des entités du monde
     * @param entite
     */
    public void suprimmerSprite(Entite entite){
        paneEntite.getChildren().remove(paneEntite.lookup(entite.getId()+""));
    }
}
