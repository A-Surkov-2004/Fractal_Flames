package org.project4;

import org.project4.modifiers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandReader {

    private Map<String, BasicMod> allCommands = new HashMap<>();;
    public CommandReader(){
        BasicMod now;

        now = new DiskMod("disk");
        allCommands.put(now.name, now);

        now = new HeartMod("heart");
        allCommands.put(now.name, now);

        now = new HorseshoeMod("horseshoe");
        allCommands.put(now.name, now);

        now = new PdjMod("pdj");
        allCommands.put(now.name, now);

        now = new PillowMod("pillow");
        allCommands.put(now.name, now);

        now = new PolarMod("polar");
        allCommands.put(now.name, now);

        now = new SinMod("sin");
        allCommands.put(now.name, now);

        now = new SphereMod("sphere");
        allCommands.put(now.name, now);

        now = new SwirlMod("swirl");
        allCommands.put(now.name, now);

        now = new DiamondMod("diamond");
        allCommands.put(now.name, now);

        now = new FisheyeMod("fisheye");
        allCommands.put(now.name, now);

        now = new HandkerchiefMod("handkerchief");
        allCommands.put(now.name, now);

        now = new HyperbolicMod("hyperbolic");
        allCommands.put(now.name, now);

        now = new SpiralMod("spiral");
        allCommands.put(now.name, now);

        now = new WavesMod("waves");
        allCommands.put(now.name, now);

        now = new WavesMod("fisheye");
        allCommands.put(now.name, now);

    }
    public double[] readCommand(List<String> instructionLine, double x, double y) {
        double[] xy = new double[] {x,y};
        for (String iWord : instructionLine) {
            xy = allCommands.get(iWord).modify(x, y);
            x = xy[0];
            y = xy[1];
        }
        return xy;
    }
}
