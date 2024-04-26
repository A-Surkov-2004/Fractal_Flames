package project4.modifiers;

public class SinMod extends BasicMod {

    public SinMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        newX = Math.sin(x);
        newY = Math.sin(y);
        return new double[] {newX, newY};
    }
}
