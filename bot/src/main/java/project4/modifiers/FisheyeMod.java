package project4.modifiers;

public class FisheyeMod extends BasicMod {

    public FisheyeMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        double r = 2/ (1+Math.sqrt(x * x + y * y));
        newX = r*y;
        newY = r*x;
        return new double[] {newX, newY};
    }
}
