package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;

public class Fonts implements Disposable {

    public static BitmapFont fontF1;

    public static void initFonts(){
        fontF1 = new BitmapFont();
    }

    @Override
    public void dispose() {
        fontF1.dispose();
    }
}
