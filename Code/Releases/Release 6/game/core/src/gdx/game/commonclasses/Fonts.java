package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;

public class Fonts implements Disposable {

    public static BitmapFont F1;

    public static void initFonts(){
        F1 = new BitmapFont();
    }

    @Override
    public void dispose() {
        F1.dispose();
    }
}
