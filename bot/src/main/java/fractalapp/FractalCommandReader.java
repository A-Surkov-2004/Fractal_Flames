package fractalapp;

import fractalapp.modifiers.BasicMod;
import fractalapp.modifiers.DiamondMod;
import fractalapp.modifiers.DiskMod;
import fractalapp.modifiers.HandkerchiefMod;
import fractalapp.modifiers.HeartMod;
import fractalapp.modifiers.HorseshoeMod;
import fractalapp.modifiers.HyperbolicMod;
import fractalapp.modifiers.PdjMod;
import fractalapp.modifiers.PillowMod;
import fractalapp.modifiers.PolarMod;
import fractalapp.modifiers.SinMod;
import fractalapp.modifiers.SphereMod;
import fractalapp.modifiers.SpiralMod;
import fractalapp.modifiers.SwirlMod;
import fractalapp.modifiers.WavesMod;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FractalCommandReader {

    private final Map<String, BasicMod> allCommands = new HashMap<>();

    public FractalCommandReader() {
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

    public double[] readCommand(List<String> instructionLine, double firstx, double firsty) {
        double x = firstx;
        double y = firsty;
        double[] xy = new double[] {x, y};
        for (String iWord : instructionLine) {
            xy = allCommands.get(iWord).modify(x, y);
            x = xy[0];
            y = xy[1];
        }
        return xy;
    }

    public Set<String> getAllCommands() {
        return allCommands.keySet();
    }
}
