package project4.modifiers;

public class DiskMod extends BasicMod {

    public DiskMod(String name) {
        super(name);
    }

    @Override
    public double[] modify(double x, double y) {
        newX = (1 / Math.PI) * Math.atan(y / x) * Math.sin(Math.PI * Math.sqrt(x * x + y * y));
        newY = (1 / Math.PI) * Math.atan(y / x) * Math.cos(Math.PI * Math.sqrt(x * x + y * y));
        return new double[] {newX, newY};
    }
}
