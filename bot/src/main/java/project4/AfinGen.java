package project4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project4.Afin;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("MagicNumber")
public class AfinGen {
    private final static Logger LOGGER = LogManager.getLogger();
    private int redLowerBorder = 50;
    private int greenLowerBorder = 50;
    private int blueLowerBorder = 50;
    private int redUpperBorder = 255;
    private int greenUpperBorder = 255;
    private int blueUpperBorder = 255;

    public void setColorBorders(
        int lowerRed,
        int loweGreen,
        int lowerBlue,
        int upperRed,
        int upperGreen,
        int upperBlue
    ) {
        redLowerBorder = Math.abs(lowerRed) % 256;
        greenLowerBorder = Math.abs(loweGreen) % 256;
        blueLowerBorder = Math.abs(lowerBlue) % 256;

        redUpperBorder = Math.abs(upperRed) % 256;
        greenUpperBorder = Math.abs(upperGreen) % 256;
        blueUpperBorder = Math.abs(upperBlue) % 256;
    }

    public Afin[] genAfin(int eqCount) {
        Afin[] afins = new Afin[eqCount];

        int totalRetryes = 0;

        for (int i = 0; i < eqCount; i++) {

            totalRetryes--;

            double c = 1;
            double f = 1;
            double a = 1;
            double b = 1;
            double d = 1;
            double e = 1;

            while (a * a + b * b + d + d + e * e >= 1 + Math.pow(a * e - b * d, 2)) {
                totalRetryes++;
                c = ThreadLocalRandom.current().nextDouble(2) - 1;
                f = ThreadLocalRandom.current().nextDouble(2) - 1;
                a = ThreadLocalRandom.current().nextDouble(2) - 1;
                b = ThreadLocalRandom.current().nextDouble(2) - 1;
                double dCap = Math.sqrt(1 - a * a);
                d = ThreadLocalRandom.current().nextDouble(dCap * 2 - dCap);
                double eCap = Math.sqrt(1 - b * b);
                e = ThreadLocalRandom.current().nextDouble(eCap * 2 - eCap);
            }
            int red = ThreadLocalRandom.current().nextInt(redLowerBorder, redUpperBorder);
            int green = ThreadLocalRandom.current().nextInt(greenLowerBorder, greenUpperBorder);
            int blue = ThreadLocalRandom.current().nextInt(blueLowerBorder, blueUpperBorder);
            afins[i] = new Afin(a, b, c, d, e, f, red, green, blue);
        }
        LOGGER.info(totalRetryes);
        return afins;
    }
}
