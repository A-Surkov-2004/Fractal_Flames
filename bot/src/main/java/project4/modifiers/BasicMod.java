package project4.modifiers;

import java.security.PublicKey;

public abstract class BasicMod {
    public String name;
    protected double newX, newY;
    public BasicMod(String name){
        this.name = name;
    }

    abstract public double[] modify(double x, double y);
}
