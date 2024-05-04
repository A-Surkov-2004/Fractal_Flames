package project4;

import edu.java.bot.UserClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        pixels = render.gammaCor(pixels, 2.3);
    }

    @Test
    @DisplayName("Одинаковые инструкции")
    void test7() throws Exception {

        // 1 = standalone method
        // 2 = tg_bot method


        // given1

        //given2
        UserClass user = new UserClass();

        //when1
        List<String> commandLine = new ArrayList<>();
        commandLine.add("sphere");
        commandLine.add("swirl");
        commandLine.add("horseshoe");

        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine);

        //when2
        user.addLine();
        user.addCommandWord("sphere");
        user.addCommandWord("swirl");
        user.addCommandWord("horseshoe");

        //then
        assertThat(user.getCommands())
            .isEqualTo(instructions);
    }


}
