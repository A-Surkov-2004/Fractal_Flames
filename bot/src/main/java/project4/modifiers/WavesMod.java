package project4.modifiers;

import project4.Render;

public class WavesMod extends BasicMod {

    public WavesMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {

        double[] rands =  Render.rands;
        newX = x + rands[0] * Math.sin(y/(rands[1]*rands[1]));
        newY = y + rands[2] * Math.sin(x/(rands[3]*rands[3]));
        return new double[] {newX, newY};
    }
}
