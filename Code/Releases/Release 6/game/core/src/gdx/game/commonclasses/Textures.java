package gdx.game.commonclasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;

public class Textures implements Disposable{

    //use arrays or use a whole wack of variables? one way is cleaner code wise, the other is far more readable
    //or do i need array for item to tile comverion to make item more efficiant
    //or make it hold tex name in json

    //<editor-fold desc="Declare">
    //boxes
    public static Texture boxGrass, boxDirt, boxStone, boxCopper, boxTin, boxIron, boxLead, boxGold, boxCobalt, boxDemonite,
            boxCrimtane, boxOrhicallum, boxClout, boxLeaves, boxWood, boxTungsten, boxPlatinum, boxSilver, boxCloud, boxMoss,
            boxPalladium, boxMythril, boxAdamantite, boxTitanium, boxWater, boxLava, boxHoney;

    public static Texture toolCopperPickaxe, toolCopperSword, toolCopperAxe, toolCopperShovel,
                        toolIronPickaxe, toolIronSword, toolIronAxe, toolIronShovel,
                        toolGoldPickaxe, toolGoldSword, toolGoldAxe, toolGoldShovel,
                        toolCorruptPickaxe, toolCorruptSword, toolCorruptAxe, toolCorruptShovel,
                        toolCrimsonPickaxe, toolCrimsonSword, toolCrimsonAxe, toolCrimsonShovel,
                        toolPalladiumPickaxe, toolPalladiumSword, toolPalladiumAxe, toolPalladiumShovel;

    public static Texture itemTorch;


    //load
    public static Texture texLoad;

    //play
    public static Texture texBack;
    public static Texture texPlay;
    public static Texture texActive;

    //json values
    public static JsonValue jvTile, jvGrass, jvDirt, jvStone, jvTin, jvCopper;

    public static JsonValue jvIron, jvLead, jvTungsten;

    public static JsonValue jvGold, jvPlatinum, jvSilver;

    public static JsonValue jvCrimtane, jvDemonite;

    public static JsonValue jvCobalt, jvPalladium, jvMythril, jvOrhicallum, jvAdamantite, jvTitanium;

    public static JsonValue jvWater, jvLava, jvHoney, jvClout;

    public static HashMap itemHashMap = new HashMap();
    //</editor-fold>

    public static void initTex(){

        //-------------------------Boxes--------------------------------

        boxGrass = new Texture("Boxes/BoxGrass.png");
        boxDirt = new Texture("Boxes/BoxDirt.png");
        boxStone = new Texture("Boxes/BoxStone.png");
        boxCopper = new Texture("Boxes/BoxCopper.png");
        boxTin = new Texture("Boxes/BoxTin.png");
        boxIron = new Texture("Boxes/BoxIron.png");
        boxLead = new Texture("Boxes/BoxLead.png");
        boxGold = new Texture("Boxes/BoxGold.png");
        boxCobalt = new Texture("Boxes/BoxCobalt.png");
        boxDemonite = new Texture("Boxes/BoxDemonite.png");
        boxCrimtane = new Texture("Boxes/BoxCrimtane.png");
        boxOrhicallum = new Texture("Boxes/BoxOrhicallum.png");
        boxClout = new Texture("Boxes/BoxClout.png");
        boxLeaves = new Texture("Boxes/BoxLeaves.png");
        boxWood = new Texture("Boxes/BoxWood.png");
        boxTungsten = new Texture("Boxes/BoxTungsten.png");
        boxPlatinum = new Texture("Boxes/BoxPlatinum.png");
        boxSilver = new Texture("Boxes/BoxSilver.png");
        boxCloud = new Texture("Boxes/BoxCloud.png");
        boxMoss = new Texture("Boxes/BoxMoss.png");
        boxPalladium = new Texture("Boxes/BoxPalladium.png");
        boxMythril = new Texture("Boxes/BoxMythril.png");
        boxAdamantite = new Texture("Boxes/BoxAdamantite.png");
        boxTitanium = new Texture("Boxes/BoxTitanium.png");
        boxWater = new Texture("Boxes/BoxWater.png");
        boxLava = new Texture("Boxes/BoxLava.png");
        boxHoney = new Texture("Boxes/BoxHoney.png");

        //----------------------Other Textures--------------------------

        texLoad = new Texture("Load.jpg");

        texBack = new Texture("Back.jpg");
        texPlay = new Texture("player.jpg");

        //-----------------------Tools----------------------------------

        //theres has to be a better way to do this --had a talk with you about this, could not find a better way to do this

        itemHashMap.put("toolCopperPickaxe", toolCopperPickaxe = new Texture("Tools/ToolCopperPickaxe.png"));
        itemHashMap.put("toolCopperSword", toolCopperSword = new Texture("Tools/ToolCopperSword.png"));
        itemHashMap.put("toolCopperAxe", toolCopperAxe = new Texture("Tools/ToolCopperAxe.png"));
        itemHashMap.put("toolCopperShovel", toolCopperShovel = new Texture("Tools/ToolCopperShovel.png"));

        itemHashMap.put("toolIronPickaxe", toolIronPickaxe = new Texture("Tools/ToolIronPickaxe.png"));
        itemHashMap.put("toolIronSword", toolIronSword = new Texture("Tools/ToolIronSword.png"));
        itemHashMap.put("toolIronAxe", toolIronAxe = new Texture("Tools/ToolIronAxe.png"));
        itemHashMap.put("toolIronShovel", toolIronShovel = new Texture("Tools/ToolIronShovel.png"));

        itemHashMap.put("toolGoldPickaxe", toolGoldPickaxe = new Texture("Tools/ToolGoldPickaxe.png"));
        itemHashMap.put("toolGoldSword", toolGoldSword = new Texture("Tools/ToolGoldSword.png"));
        itemHashMap.put("toolGoldAxe", toolGoldAxe = new Texture("Tools/ToolGoldAxe.png"));
        itemHashMap.put("toolGoldShovel", toolGoldShovel = new Texture("Tools/ToolGoldShovel.png"));

        itemHashMap.put("toolCrimsonPickaxe", toolCrimsonPickaxe = new Texture("Tools/ToolCrimsonPickaxe.png"));
        itemHashMap.put("toolCrimsonSword", toolCrimsonSword = new Texture("Tools/ToolCrimsonSword.png"));
        itemHashMap.put("toolCrimsonAxe", toolCrimsonAxe = new Texture("Tools/ToolCrimsonAxe.png"));
        itemHashMap.put("toolCrimsonShovel", toolCrimsonShovel = new Texture("Tools/ToolCrimsonShovel.png"));

        itemHashMap.put("toolCorruptPickaxe", toolCorruptPickaxe = new Texture("Tools/ToolCorruptPickaxe.png"));
        itemHashMap.put("toolCorruptSword", toolCorruptSword = new Texture("Tools/ToolCorruptSword.png"));
        itemHashMap.put("toolCorruptAxe", toolCorruptAxe = new Texture("Tools/ToolCorruptAxe.png"));
        itemHashMap.put("toolCorruptShovel", toolCorruptShovel = new Texture("Tools/ToolCorruptShovel.png"));

        itemHashMap.put("toolPalladiumPickaxe", toolPalladiumPickaxe = new Texture("Tools/ToolPalladiumPickaxe.png"));
        itemHashMap.put("toolPalladiumSword", toolPalladiumSword = new Texture("Tools/ToolPalladiumSword.png"));
        itemHashMap.put("toolPalladiumAxe", toolPalladiumAxe = new Texture("Tools/ToolPalladiumAxe.png"));
        itemHashMap.put("toolPalladiumShovel", toolPalladiumShovel = new Texture("Tools/ToolPalladiumShovel.png"));



        //---------------------------Items---------------------------------------

        itemTorch = new Texture("Tools/ItemTorch.png");
        texActive = new Texture("Tools/Active.png");

    }

    public static void initJSON(){
        JsonReader jrJson = new JsonReader();
        jvTile = jrJson.parse(Gdx.files.local("JSON/Tile.json"));

        jvGrass = jvTile.get("2");
        jvDirt = jvTile.get("3");
        jvStone = jvTile.get("5");
        jvTin = jvTile.get("6");
        jvCopper = jvTile.get("7");

        jvIron = jvTile.get("8");
        jvLead = jvTile.get("9");
        jvTungsten = jvTile.get("10");

        jvGold = jvTile.get("11");
        jvPlatinum = jvTile.get("12");
        jvSilver = jvTile.get("13");

        jvCrimtane = jvTile.get("16");
        jvDemonite = jvTile.get("17");

        jvCobalt = jvTile.get("18");
        jvPalladium = jvTile.get("19");
        jvMythril = jvTile.get("20");
        jvOrhicallum = jvTile.get("21");
        jvAdamantite = jvTile.get("22");
        jvTitanium = jvTile.get("23");

        jvWater = jvTile.get("24");
        jvLava = jvTile.get("25");
        jvHoney = jvTile.get("26");
        jvClout = jvTile.get("27");
    }

    @Override
    public void dispose() {
        boxGrass.dispose();
        boxStone.dispose();
        boxGrass.dispose();
        boxDirt.dispose();
        boxStone.dispose();
        boxCopper.dispose();
        boxTin.dispose();
        boxIron.dispose();
        boxLead.dispose();
        boxGold.dispose();
        boxCobalt.dispose();
        boxDemonite.dispose();
        boxCrimtane.dispose();
        boxOrhicallum.dispose();
        boxClout.dispose();
        boxLeaves.dispose();
        boxWood.dispose();
        boxTungsten.dispose();
        boxPlatinum.dispose();
        boxSilver.dispose();
        boxCloud.dispose();
        boxMoss.dispose();
        boxPalladium.dispose();
        boxMythril.dispose();
        boxAdamantite.dispose();
        boxTitanium.dispose();
        boxWater.dispose();
        boxLava.dispose();
        boxHoney.dispose();

        texPlay.dispose();
        texBack.dispose();
        texLoad.dispose();
    }
}
