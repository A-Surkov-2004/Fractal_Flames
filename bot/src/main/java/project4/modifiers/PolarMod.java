package project4.modifiers;

public class PolarMod extends BasicMod {

    public PolarMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        newX = Math.atan(y / x) / Math.PI;
        newY = Math.sqrt(x * x + y * y) - 1;
        return new double[] {newX, newY};
    }
}
