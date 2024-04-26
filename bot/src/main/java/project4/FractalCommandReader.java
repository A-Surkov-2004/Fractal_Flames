package project4;


import project4.modifiers.BasicMod;
import project4.modifiers.DiamondMod;
import project4.modifiers.DiskMod;
import project4.modifiers.FisheyeMod;
import project4.modifiers.HandkerchiefMod;
import project4.modifiers.HeartMod;
import project4.modifiers.HorseshoeMod;
import project4.modifiers.HyperbolicMod;
import project4.modifiers.PdjMod;
import project4.modifiers.PillowMod;
import project4.modifiers.PolarMod;
import project4.modifiers.SinMod;
import project4.modifiers.SphereMod;
import project4.modifiers.SpiralMod;
import project4.modifiers.SwirlMod;
import project4.modifiers.WavesMod;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FractalCommandReader {

    private Map<String, BasicMod> allCommands = new HashMap<>();;
    public FractalCommandReader(){
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

    public Set<String> getAllCommands(){
        return allCommands.keySet();
    }
}
