package fractalapp;

import edu.java.bot.UserClass;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drawer {

    private final static Logger LOGGER = LogManager.getLogger();
    private final BufferedImage myPicture;
    private final Graphics2D g;

    public Drawer(String imPath) throws IOException {
        LOGGER.always().log(imPath);
        myPicture = ImageIO.read(new File(imPath));
        LOGGER.always().log("imagePath");
        g = (Graphics2D) myPicture.getGraphics();
        g.setStroke(new BasicStroke(1F));
    }

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
        recordPng();
    }

    private void recordPng() throws IOException {
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
