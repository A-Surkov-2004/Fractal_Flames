package project4.modifiers;

public class SphereMod extends BasicMod {

    public SphereMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        newX = x / (x * x + y * y);
        newY = y / (x * x + y * y);
        return new double[] {newX, newY};
    }
}
