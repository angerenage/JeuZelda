package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FabriquePnj {
    public static void fabriquePnj(Class<? extends Acteur> typePnj, int nombre, Monde monde, Position position, boolean randomOffset) {
        try {
            Constructor<? extends Acteur> constructor = typePnj.getDeclaredConstructor(double.class, double.class, Direction.class);

			double offsetX = 0, offsetY = 0;
			if (randomOffset) {
				offsetX = Math.random() * 2 + 0.5;
				offsetY = Math.random() * 2 + 0.5;
			}

            for (int i = 0; i < nombre; i++) {
                Acteur pnj = constructor.newInstance(position.getX() + offsetX, position.getY() + offsetY, Direction.BAS);
                monde.ajoutActeur(pnj);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
