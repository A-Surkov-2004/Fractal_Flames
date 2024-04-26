package project4.modifiers;

public class SwirlMod extends BasicMod {

    public SwirlMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        newX = x * Math.sin(r * r) - y * Math.cos(r * r);
        newY = x * Math.cos(r * r) - y * Math.sin(r * r);
        return new double[] {newX, newY};
    }
}
