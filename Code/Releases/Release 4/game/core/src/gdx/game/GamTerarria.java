package gdx.game;

import com.badlogic.gdx.Game;
import gdx.game.ScrLoad.*;
import gdx.game.ScrMenu.*;
import gdx.game.ScrPlay.*;

public class GamTerarria extends Game {

    //<editor-fold desc="Init">
    ScrPlay scrPlay;
    ScrMenu scrMenu;
    ScrLoad scrLoad;
    Long lSeed = 80L; // must have seed end in L ex 20L to signify long type

    public int nScreen;
    //</editor-fold>

	@Override
	public void create () {
        scrMenu = new ScrMenu(this);
        scrLoad = new ScrLoad(this, lSeed);
        scrPlay = new ScrPlay(this);

        nScreen = 1;

        updateState(nScreen);
	}

	public void updateState(int nScreen){
	if (nScreen == 0){
            setScreen(scrMenu);
            System.out.println("menu");
        }else if(nScreen == 1){
            setScreen(scrLoad);
            System.out.println("load");
        }else if(nScreen == 2){
            setScreen(scrPlay);
            System.out.println("play");
        }
    }

	@Override
	public void render () {
		super.render();
	}

	@Override
    public void dispose(){
	    super.dispose();
    }

    
}
