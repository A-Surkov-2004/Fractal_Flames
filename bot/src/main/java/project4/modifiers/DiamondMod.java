package project4.modifiers;

public class DiamondMod extends BasicMod {

    public DiamondMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        double r = Math.sqrt (x * x + y * y);
        double t = Math.atan2(x,y);
        newX = Math.sin(t)*Math.cos(r);
        newY = Math.sin(r) * Math.cos(t);
        return new double[] {newX, newY};
    }
}
