package gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import gdx.game.ScrLoad.*;
import gdx.game.ScrMenu.*;
import gdx.game.ScrPlay.*;
import gdx.game.commonclasses.Constants;

public class GamTerarria extends Game {
    ScrPlay scrPlay;
    ScrMenu scrMenu;
    public ScrLoad scrLoad;
    Long lSeed = 80L; // must have seed end in L ex 20L to signify long type


    public int nScreen;
	
	@Override
	public void create () {
        Viewport viewport = new ExtendViewport(Constants.WORLDWIDTH, Constants.WORLDHEIGHT);
        
        scrMenu = new ScrMenu(this, viewport);
        scrLoad = new ScrLoad(this, lSeed, viewport);
        scrPlay = new ScrPlay(this, viewport);

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
            //render game, game calls everyone elses render functions
		super.render();
	}

	@Override
    public void dispose(){
	    super.dispose();
    }

    
}
