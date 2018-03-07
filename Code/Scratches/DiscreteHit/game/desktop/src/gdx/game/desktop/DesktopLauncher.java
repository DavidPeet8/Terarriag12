package gdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gdx.game.GamTerarria;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "TArRIa";
        cfg.height = 650;
        cfg.width = 1200;
        new LwjglApplication(new GamTerarria(), cfg);
    }
}
