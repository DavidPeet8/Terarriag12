package gdx.game;

import com.badlogic.gdx.Game;
import gdx.game.ScrBetterHitDetection.*;
import gdx.game.ScrArrayHitDetection.*;
import gdx.game.ScrGravity.ScrGravity;
import gdx.game.ScrLoad.*;
import gdx.game.ScrMenu.*;
import gdx.game.ScrPlay.*;

public class GamTerarria extends Game {

    ScrPlay scrPlay;
    ScrMenu scrMenu;
    ScrBetterHitDetection scrBetterHitDetection;
    ScrGravity scrGravity;
    ScrArrayHitDetection scrArrayHitDetection;
    public ScrLoad scrLoad;
    Long lSeed = 80L; // must have seed end in L ex 20L to signify long type

    public int nScreen;

    @Override
    public void create() {
        scrMenu = new ScrMenu(this);
        scrLoad = new ScrLoad(this, lSeed);
        scrPlay = new ScrPlay(this);
        scrBetterHitDetection = new ScrBetterHitDetection(this);
        scrGravity = new ScrGravity(this);
        scrArrayHitDetection = new ScrArrayHitDetection(this);

        nScreen = 5;

        updateState(nScreen);
    }

    public void updateState(int nScreen) {
        if (nScreen == 0) {
            setScreen(scrMenu);
            System.out.println("menu");
        } else if (nScreen == 1) {
            setScreen(scrLoad);
            System.out.println("load");
        } else if (nScreen == 2) {
            setScreen(scrPlay);
            System.out.println("play");
        } else if (nScreen == 3) {
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
