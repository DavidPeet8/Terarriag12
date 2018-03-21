package gdx.game;

import gdx.game.ScrDiscreteDetection.ScrDiscreteHitDetection;
import com.badlogic.gdx.Game;

public class GamTerarria extends Game {

    ScrDiscreteHitDetection scrDiscreteHitDetection;

    public int nScreen;

    @Override
    public void create() {

        scrDiscreteHitDetection = new ScrDiscreteHitDetection(this);

        nScreen = 0;

        updateState(nScreen);
    }

    public void updateState(int nScreen) {

        if (nScreen == 0) {
            setScreen(scrDiscreteHitDetection);
        }
    }

    @Override
    public void render() {
        //render game, game calls everyone elses render functions
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
