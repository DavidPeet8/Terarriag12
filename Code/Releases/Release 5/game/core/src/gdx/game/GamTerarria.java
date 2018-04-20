package gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import gdx.game.ScrDeath.ScrDeath;
import gdx.game.ScrInvenoryGUI.ScrInventory;
import gdx.game.ScrLoad.*;
import gdx.game.ScrMenu.*;
import gdx.game.ScrPlay.*;
import gdx.game.commonclasses.Textures;

public class GamTerarria extends Game {

    //<editor-fold desc="Init">
    ScrPlay scrPlay;
    ScrMenu scrMenu;
    ScrLoad scrLoad;
    ScrInventory scrInventory;
    ScrDeath scrDeath;
    Long lSeed = 80L; // must have seed end in L ex 20L to signify long type

    public int nScreen;
    //</editor-fold>

	@Override
	public void create () {
        Textures.initTex();
        Textures.initJSON();

        scrMenu = new ScrMenu(this);
        scrLoad = new ScrLoad(this, lSeed);
        scrInventory = new ScrInventory(this);
        scrPlay = new ScrPlay(this);
        scrDeath = new ScrDeath(this);

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
        }else if(nScreen == 3){
        setScreen(scrInventory);
        System.out.println("Inventory");
        }else if (nScreen == 4){
	        setScreen(scrDeath);
            System.out.println("respawn");
        }
    }

    public ScrPlay getScrPlay(){
	    return scrPlay;
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

//cannot have one master camera and viewport as it passes a reference, therefore when we change the referance, we change the actual object everywehre
//that is why we had a viewport or camera updating all other instances of it