package com.huskygames.rekhid;

import com.huskygames.rekhid.slugger.input.ButtonEvent;
import com.huskygames.rekhid.slugger.menu.Menu;
import com.huskygames.rekhid.slugger.menu.MenuItem;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import com.huskygames.rekhid.slugger.input.ControllerInput;
import com.huskygames.rekhid.slugger.menu.TextButton;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.actor.StickMan;
import com.huskygames.rekhid.slugger.GamePanel;
import com.huskygames.rekhid.slugger.actor.AI.FsmProf;
import com.huskygames.rekhid.slugger.actor.Fighter;
import com.huskygames.rekhid.slugger.input.ControllerInput;
import com.huskygames.rekhid.slugger.physics.PhysicsManager;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.resource.ResourceManager;
import com.huskygames.rekhid.slugger.sound.SoundThread;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.FileUtilities;
import com.huskygames.rekhid.slugger.world.LevelComputers;
import com.huskygames.rekhid.slugger.world.World;
import net.java.games.input.Controller;
import net.java.games.input.Component;
import net.java.games.input.ControllerEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.text.resources.cldr.sr.FormatData_sr_Latn_ME;

import java.awt.*;
import java.util.Queue;

public class MainMenu extends Menu {

    private static final Logger logger = LogManager.getLogger(MainMenu.class.getName());
    private final Rekhid parent;

    public MainMenu(Rekhid parent) {
        this.parent = parent;

        LoadedImage background = (LoadedImage) parent.getResourceManager().requestResource(Resource.MAIN_MENU_BG);
        setBackground(background.getImage());


    }

    public void onTick(){
        TextButton item1 = new TextButton("PRESS START TO CONTINUE", 300, 400, Color.BLACK);
        getItems().add(item1);

        ControllerInput input = Rekhid.getInstance().getControllerManager();

        Component[] componentList = input.getValidControllers().get(3).getComponents();

        for (int i = 0; i < input.getValidControllers().size(); i++) {
            for (int j = 0; j < 10; j++) {
                input.getValidControllers().get(i).poll();
                if (componentList[j].getPollData() != 0) {
                    Rekhid.getInstance().setControllerForPlayer1(i);
                    Rekhid.getInstance().setGameState(Rekhid.GameState.CHARACTER_SELECT);
                }
            }
        }
    }
}
