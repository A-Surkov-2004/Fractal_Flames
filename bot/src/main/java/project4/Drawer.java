package project4;

import edu.java.bot.UserClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;



public class Drawer {

    String imagePath;
    BufferedImage myPicture;
    Graphics2D g;

    public Drawer(String imPath) throws IOException {
        imagePath = imPath;
        System.out.println(imagePath);
        myPicture = ImageIO.read(new File(imagePath));
        System.out.println("imagePath");
        g = (Graphics2D) myPicture.getGraphics();
        g.setStroke(new BasicStroke(1F));
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public void draw(Pixel[][] pixels) throws InterruptedException, IOException {
        for (int i = 0; i < pixels.length; i++) { // № строки == y
            for (int j = 0; j < pixels[i].length; j++) { // № стобеца == x
                if (pixels[i][j] != null) {
                    Color curColor = new Color(pixels[i][j].r, pixels[i][j].g, pixels[i][j].b);
                    g.setColor(curColor);
                    g.drawOval(j, i, 1, 1);
                }
            }
        }
        record();
    }

    private void record() throws IOException {
        Path p4 = UserClass.TEMPERAL_IMAGE_PATH;
        File outputfile;
        if (!Files.exists(p4)) {
            outputfile = Files.createFile(p4).toFile();
        } else {
            outputfile = p4.toFile();
        }
        ImageIO.write(myPicture, "png", outputfile);
    }
}
