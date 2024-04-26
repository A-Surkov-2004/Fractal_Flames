package project4.modifiers;

public class HyperbolicMod extends BasicMod {

    public HyperbolicMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        double r = Math.sqrt (x * x + y * y);
        double t = Math.atan2(x,y);
        newX = Math.sin(t)/r;
        newY = r * Math.cos(t);
        return new double[] {newX, newY};
    }
}
