package project4.modifiers;

public class PillowMod extends BasicMod {

    public PillowMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        newX = x / (Math.abs(Math.sin(x)) + Math.abs(Math.cos(y)));
        newY = y / (Math.abs(Math.cos(x)) + Math.abs(Math.sin(y)));
        return new double[] {newX, newY};
    }
}
