package project4.modifiers;

public class HandkerchiefMod extends BasicMod {

    public HandkerchiefMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        double r = Math.sqrt (x * x + y * y);
        double t = Math.atan2(x,y);
        newX = r * Math.sin(t+r);
        newY = r * Math.cos(t-r);
        return new double[] {newX, newY};
    }
}
