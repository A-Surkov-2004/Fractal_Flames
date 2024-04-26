package project4.modifiers;

public class HeartMod extends BasicMod {

    public HeartMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        newX = Math.sqrt(x * x + y * y) * Math.sin(Math.sqrt(x * x + y * y) * Math.atan(y / x));
        newY = -1 * Math.sqrt(x * x + y * y) * Math.cos(Math.sqrt(x * x + y * y) * Math.atan(y / x));
        return new double[] {newX, newY};
    }
}
