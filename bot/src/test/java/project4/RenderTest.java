package project4;

import edu.java.bot.UserClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RenderTest {

    private final static Logger LOGGER = LogManager.getLogger();
    Path p = UserClass.EMPTY_IMAGE_PATH;
    String path = p.toString();
    @Test
    @DisplayName("Сфера-воронка")
    void test6() throws Exception {

        // given
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        //mod.pillow = true;
        //mod.disk = true;

        //when
        render.setRatio(2.7777*2, 2*2);
        afinGen.setColorBorders(50, 50, 150, 150, 150, 255);

        List<String> commandLine = new ArrayList<>();
        commandLine.add("sphere");
        commandLine.add("swirl");
        commandLine.add("horseshoe");

        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine);

        Pixel[][] pixels = render.render(afinGen.genAfin(100), 1000, 1000, instructions);
        pixels = render.gammaCor(pixels);
        //Drawer painter = new Drawer(path);
        //painter.draw(pixels);

    }

}
