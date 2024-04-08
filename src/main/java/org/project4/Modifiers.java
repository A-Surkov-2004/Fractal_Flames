package org.project4;

public class Modifiers {

    public boolean sin = false;
    public boolean sphere = false;
    public boolean polar = false;
    public boolean heart = false;
    public boolean disk = false;
    public boolean swirl = false;
    public boolean horseshoe = false;
    public boolean pdj = false;
    public boolean pillow = false;

    public Modifiers() {
    }

    public double[] modify(double x0, double y0) {
        double x = x0;
        double y = y0;
        double newX;
        double newY;
        if (sin) {
            newX = Math.sin(x);
            newY = Math.sin(y);
            x = newX;
            y = newY;
        }
        if (sphere) {
            newX = x / (x * x + y * y);
            newY = y / (x * x + y * y);
            x = newX;
            y = newY;
        }
        if (polar) {
            newX = Math.atan(y / x) / Math.PI;
            newY = Math.sqrt(x * x + y * y) - 1;
            x = newX;
            y = newY;
        }

        if (disk) {
            newX = (1 / Math.PI) * Math.atan(y / x) * Math.sin(Math.PI * Math.sqrt(x * x + y * y));
            newY = (1 / Math.PI) * Math.atan(y / x) * Math.cos(Math.PI * Math.sqrt(x * x + y * y));
            x = newX;
            y = newY;
        }
        if (heart) {
            newX = Math.sqrt(x * x + y * y) * Math.sin(Math.sqrt(x * x + y * y) * Math.atan(y / x));
            newY = -1 * Math.sqrt(x * x + y * y) * Math.cos(Math.sqrt(x * x + y * y) * Math.atan(y / x));
            x = newX;
            y = newY;
        }
        if (swirl) {
            double r = Math.sqrt(x * x + y * y);
            newX = x * Math.sin(r * r) - y * Math.cos(r * r);
            newY = x * Math.cos(r * r) - y * Math.sin(r * r);
            x = newX;
            y = newY;
        }
        if (horseshoe) {
            double r = Math.sqrt(x * x + y * y);
            newX = (1 / r) * (x - y) * (x + y);
            newY = 2 * x * y * (1 / r);
            x = newX;
            y = newY;
        }
        if (pdj) {
            newX = Math.sin(y) - Math.cos(x);
            newY = Math.sin(x) - Math.cos(y);
            x = newX;
            y = newY;
        }
        if (pillow) {
            double r = Math.sqrt(x * x + y * y);
            newX = x / (Math.abs(Math.sin(x)) + Math.abs(Math.cos(y)));
            newY = y / (Math.abs(Math.cos(x)) + Math.abs(Math.sin(y)));
            x = newX;
            y = newY;
        }

        return new double[] {x, y};
    }
}
