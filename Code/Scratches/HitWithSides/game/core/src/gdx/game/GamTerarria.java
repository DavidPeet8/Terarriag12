package gdx.game;

import com.badlogic.gdx.Game;
import gdx.game.ScrBetterHitDetection.*;
import gdx.game.ScrArrayHitDetection.*;
import gdx.game.ScrGravity.ScrGravity;


public class GamTerarria extends Game {

    ScrBetterHitDetection scrBetterHitDetection;
    ScrGravity scrGravity;
    ScrArrayHitDetection scrArrayHitDetection;

    public int nScreen;

    @Override
    public void create() {
        scrBetterHitDetection = new ScrBetterHitDetection(this);
        scrGravity = new ScrGravity(this);
        scrArrayHitDetection = new ScrArrayHitDetection(this);

        nScreen = 3;

        updateState(nScreen);
    }

    public void updateState(int nScreen) {
        if (nScreen == 3) {
            setScreen(scrBetterHitDetection);
            System.out.println("Better hit");
        } else if (nScreen == 4) {
            setScreen(scrGravity);
            System.out.println("Better hit");
        } else if (nScreen == 5) {
            setScreen(scrArrayHitDetection);
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
