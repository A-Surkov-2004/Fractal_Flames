package project4.modifiers;

public class HorseshoeMod extends BasicMod {

    public HorseshoeMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        newX = (1 / r) * (x - y) * (x + y);
        newY = 2 * x * y * (1 / r);
        return new double[] {newX, newY};
    }
}
