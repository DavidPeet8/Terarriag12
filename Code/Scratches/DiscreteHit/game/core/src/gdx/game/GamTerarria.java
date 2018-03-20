package gdx.game;

import gdx.game.ScrDiscreteDetection.ScrDiscreteHitDetection;
import com.badlogic.gdx.Game;

public class GamTerarria extends Game {

    ScrDiscreteHitDetection scrDiscreteHitDetection;
    Long lSeed = 80L; // must have seed end in L ex 20L to signify long type

    public int nScreen;

    @Override
    public void create() {

        scrDiscreteHitDetection = new ScrDiscreteHitDetection(this);

        nScreen = 5;

        updateState(nScreen);
    }

    public void updateState(int nScreen) {

        if (nScreen == 5) {
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
