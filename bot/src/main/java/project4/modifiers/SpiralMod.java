package project4.modifiers;

public class SpiralMod extends BasicMod {

    public SpiralMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        double r = Math.sqrt (x * x + y * y);
        double t = Math.atan2(x,y);
        newX = (1/r) * Math.cos(t) + Math.sin(r);
        newY = (1/r) * Math.sin(t) + Math.cos(r);
        return new double[] {newX, newY};
    }
}
