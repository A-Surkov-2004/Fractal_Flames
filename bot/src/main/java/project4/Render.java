package project4;

import edu.java.bot.UserClass;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class Render {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static int X_RES = 1920;
    private final static int Y_RES = 1080;
    private double xRatioDispers = 1.777;
    private double yRatioDispers = 1;
    private int totalPix = 0;
    Path p = UserClass.EMPTY_IMAGE_PATH;
    String path = p.toString();
    //Drawer d = new Drawer(path);

    public static double[] rands = new double[4];

    private Pixel[][] pixels;
    private final ReadWriteLock readWriteLock
        = new ReentrantReadWriteLock();
    private final Lock writeLock
        = readWriteLock.writeLock();

    public Render() throws IOException {
    }

    public void setRatio(double xdesp, double ydesp) {
        xRatioDispers = xdesp;
        yRatioDispers = ydesp;
    }

    public Pixel[][] render(Afin[] afins, int n, int it, List<List<String>> instructions) {
        pixels = new Pixel[Y_RES][X_RES]; // [y][x] - y строк пикселей длиной в x
        Runnable task1 = () -> {
            try {
                renderTask(afins, it, instructions);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        /*

        try(ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())){
            for (int i = 0; i < n; i++) {
                service.submit(task1);
            }
        }

        System.out.println(totalPix);
         //*/


///*
        for (int i = 0; i < n; i++) {
            try {
                renderTask(afins, it, instructions);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

 //*/

        return pixels;

    }

    /*public Pixel[][] renderSingleStream(Afin[] afins, int n, int it, Modifiers mod) {

        pixels = new Pixel[Y_RES][X_RES];

        for (int i = 0; i < n; i++) {
            double newX = ThreadLocalRandom.current().nextDouble(xRatioDispers * 2) - xRatioDispers;
            double newY = ThreadLocalRandom.current().nextDouble(yRatioDispers * 2) - yRatioDispers;

            for (int step = -20; step < it; step++) {
                int afinIndex = ThreadLocalRandom.current().nextInt(afins.length);
                double x = afins[afinIndex].a() * newX + afins[afinIndex].b() * newY + afins[afinIndex].c();
                double y = afins[afinIndex].d() * newY + afins[afinIndex].e() * newY + afins[afinIndex].f();

                double[] modified = mod.modify(x, y);
                newX = modified[0];
                newY = modified[1];

                if (step >= 0) {
                    int x1 =
                        Math.abs((int) (X_RES - Math.round(((xRatioDispers - newX) / (xRatioDispers * 2)) * X_RES)));

                    int y1 =
                        Math.abs((int) (Y_RES - Math.round(((yRatioDispers - newY) / (yRatioDispers * 2)) * Y_RES)));

                    if (x1 < X_RES - 1 && y1 < Y_RES - 1) {
                        if (pixels[y1][x1] == null) {
                            pixels[y1][x1] = new Pixel(0, 0, 0, 0);
                        }
                        if (pixels[y1][x1].hits == 0) {
                            pixels[y1][x1].r = afins[afinIndex].red();
                            pixels[y1][x1].g = afins[afinIndex].green();
                            pixels[y1][x1].b = afins[afinIndex].blue();
                        } else {
                            pixels[y1][x1].r = (pixels[y1][x1].r + afins[afinIndex].red()) / 2;
                            pixels[y1][x1].g = (pixels[y1][x1].g + afins[afinIndex].green()) / 2;
                            pixels[y1][x1].b = (pixels[y1][x1].b + afins[afinIndex].blue()) / 2;
                        }
                        pixels[y1][x1].hits++;
                    }
                }
            }
        }
        return pixels;
    }

     */

    public Pixel[][] gammaCor(Pixel[][] pixels, double gamma) {
        double max = 0;
        for (int row = 0; row < Y_RES; row++) {
            for (int col = 0; col < X_RES; col++) {
                if (pixels[row][col] != null) {
                    pixels[row][col].normal = Math.log10(pixels[row][col].hits);
                    if (pixels[row][col].normal > max) {
                        max = pixels[row][col].normal;
                    }
                }
            }
        }
        for (int row = 0; row < Y_RES; row++) {
            for (int col = 0; col < X_RES; col++) {
                if (pixels[row][col] != null) {
                    pixels[row][col].normal /= max;
                    pixels[row][col].r = (int) (pixels[row][col].r * Math.pow(pixels[row][col].normal, 1 / gamma));
                    pixels[row][col].g = (int) (pixels[row][col].g * Math.pow(pixels[row][col].normal, 1 / gamma));
                    pixels[row][col].b = (int) (pixels[row][col].b * Math.pow(pixels[row][col].normal, 1 / gamma));
                }
            }
        }
        return pixels;
    }

    private void renderTask(Afin[] afins, int it, List<List<String>> instructions) throws IOException, InterruptedException {
        //LOGGER.info(Thread.currentThread().getName());

        int randseed = ThreadLocalRandom.current().nextInt(instructions.size());



        double newX = ThreadLocalRandom.current().nextDouble(xRatioDispers * 2) - xRatioDispers;
        double newY = ThreadLocalRandom.current().nextDouble(yRatioDispers * 2) - yRatioDispers;

        for (int step = -20; step < it; step++)
        {

            rands[0] = ThreadLocalRandom.current().nextDouble(-2,2);
            rands[1] = ThreadLocalRandom.current().nextDouble(-2,2);
            rands[2] = ThreadLocalRandom.current().nextDouble(-2,2);
            rands[3] = ThreadLocalRandom.current().nextDouble(-2,2);

            randseed = ThreadLocalRandom.current().nextInt(instructions.size());
            int afinIndex = ThreadLocalRandom.current().nextInt(afins.length);
            double x = afins[afinIndex].a() * newX + afins[afinIndex].b() * newY + afins[afinIndex].c();
            double y = afins[afinIndex].d() * newY + afins[afinIndex].e() * newY + afins[afinIndex].f();


            FractalCommandReader cmr = new FractalCommandReader();
            List<String> instructionLine = instructions.get(randseed);
            double[] modified = cmr.readCommand(instructionLine, x,y);
            //double[] modified = mod.modify(x, y);
            newX = modified[0];
            newY = modified[1];


            if (step >= 0) {
                int x1 = Math.abs((int) (X_RES - Math.round(((xRatioDispers - newX) / (xRatioDispers * 2)) * X_RES)));

                int y1 = Math.abs((int) (Y_RES - Math.round(((yRatioDispers - newY) / (yRatioDispers * 2)) * Y_RES)));

                writeLock.lock();
                if (x1 < X_RES - 1 && y1 < Y_RES - 1) {
                    if (pixels[y1][x1] == null) {
                        pixels[y1][x1] = new Pixel(0, 0, 0, 0);
                    }
                    if (pixels[y1][x1].hits == 0) {
                        pixels[y1][x1].r = afins[afinIndex].red();
                        pixels[y1][x1].g = afins[afinIndex].green();
                        pixels[y1][x1].b = afins[afinIndex].blue();
                    } else {
                        pixels[y1][x1].r = (pixels[y1][x1].r + afins[afinIndex].red()) / 2;
                        pixels[y1][x1].g = (pixels[y1][x1].g + afins[afinIndex].green()) / 2;
                        pixels[y1][x1].b = (pixels[y1][x1].b + afins[afinIndex].blue()) / 2;
                    }
                    pixels[y1][x1].hits++;
                    totalPix++;
                    if(step % 1000 == 0){


                    }
                }
                writeLock.unlock();
            }
        }
    }
}
