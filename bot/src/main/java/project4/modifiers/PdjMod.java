package project4.modifiers;

public class PdjMod extends BasicMod {

    public PdjMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        newX = Math.sin(y) - Math.cos(x);
        newY = Math.sin(x) - Math.cos(y);
        return new double[] {newX, newY};
    }
}
