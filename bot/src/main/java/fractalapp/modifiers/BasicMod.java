package fractalapp.modifiers;

public abstract class BasicMod {
    public String name;
    protected double newX;
    protected double newY;

    public BasicMod(String name) {
        this.name = name;
    }

    abstract public double[] modify(double x, double y);
}
