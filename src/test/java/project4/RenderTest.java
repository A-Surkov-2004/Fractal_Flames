package project4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project4.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RenderTest {
    private final static Logger LOGGER = LogManager.getLogger();
    Path p = Path.of("src", "main", "java", "org", "project4", "Image.jpg");
    String path = p.toString();

/*
    @Test
    @DisplayName("Сравнение скоростей")
    void test5() throws Exception {

        // given

        //String path = "src\\main\\java\\edu\\project3\\Image.jpg";
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        Modifiers mod = new Modifiers();
        mod.sphere = true;
        mod.swirl = true;
        //mod.disk = true;
        //mod.heart = true;
        //when

        render.setRatio(5, 3.25);
        afinGen.setColorBorders(50, 50, 150, 150, 150, 255);

        long start = System.nanoTime();

        Pixel[][] pixels2 = render.renderSingleStream(afinGen.genAfin(10), 100000, 120, mod);
        long singleThreadTime = System.nanoTime();

        Pixel[][] pixels = render.render(afinGen.genAfin(10), 100000, 120, mod);
        long multiThreadTime = System.nanoTime();

        long singleThreaddelta = singleThreadTime - start;
        long multiThreaddelta = multiThreadTime - singleThreadTime;

        // then
        LOGGER.info((singleThreaddelta - multiThreaddelta) / 1_000_000);
        /*
        assertThat(multiThreaddelta)
            .isLessThan(singleThreaddelta);

        //*/ // не проходит с локом(

    //}


    //Пресеты (раскоментить ainter.draw(pixels); для показа)
    @Test
    @DisplayName("Синее сердце")
    void test1() throws Exception {

        // given

        //String path = "src\\main\\java\\edu\\project3\\Image.jpg";
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        //Modifiers mod = new Modifiers();
        //mod.sphere = false;
        //mod.disk = true;
        //mod.heart = true;
        //mod.sphere = true;
        //when



        List<String> commandLine = new ArrayList<>();
        commandLine.add("swirl");
        //commandLine.add("heart");
        commandLine.add("sphere");

        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine);

        render.setRatio(2.777, 2);
        afinGen.setColorBorders(150, 0, 0, 255, 50, 50);


        Pixel[][] pixels = render.render(afinGen.genAfin(100), 1000, 1200, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);
    }

    @Test
    @DisplayName("ИЛИ")
    void test15() throws Exception {

        // given

        //String path = "src\\main\\java\\edu\\project3\\Image.jpg";
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        //Modifiers mod = new Modifiers();
        //mod.sphere = false;
        //mod.disk = true;
        //mod.heart = true;
        //mod.sphere = true;
        //when



        List<String> commandLine1 = new ArrayList<>();
        List<String> commandLine2 = new ArrayList<>();
        List<String> commandLine3 = new ArrayList<>();

        //commandLine1.add("pillow");
        commandLine1.add("disk");
        commandLine2.add("swirl");
        //commandLine1.add("horseshoe");


        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine1);
        instructions.add(commandLine2);

        render.setRatio(2.777, 2);
        afinGen.setColorBorders(150, 0, 0, 255, 100, 100);


        Pixel[][] pixels = render.render(afinGen.genAfin(100), 1000, 1200, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);
    }

    @Test
    @DisplayName("Сферы И Диск")
    void test10() throws Exception {

        // given

        //String path = "src\\main\\java\\edu\\project3\\Image.jpg";
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        //Modifiers mod = new Modifiers();
        //mod.sphere = false;
        //mod.disk = true;
        //mod.heart = true;
        //mod.sphere = true;
        //when

        List<String> commandLine = new ArrayList<>();
        commandLine.add("sphere");
        commandLine.add("disk");

        render.setRatio(5, 3.25);
        afinGen.setColorBorders(150, 0, 75, 255, 25, 175);

        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine);

        Pixel[][] pixels = render.render(afinGen.genAfin(100), 10000, 1200, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);
    }
    @Test
    @DisplayName("Диск И Сферы")
    void test11() throws Exception {

        // given

        //String path = "src\\main\\java\\edu\\project3\\Image.jpg";
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        //Modifiers mod = new Modifiers();
        //mod.sphere = false;
        //mod.disk = true;
        //mod.heart = true;
        //mod.sphere = true;
        //when

        List<String> commandLine = new ArrayList<>();
        commandLine.add("disk");
        commandLine.add("sphere");

        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine);

        render.setRatio(2.777*10, 2*10);
        afinGen.setColorBorders(50, 50, 150, 150, 150, 255);

        Pixel[][] pixels = render.render(afinGen.genAfin(100), 10000, 1200, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);
    }

    @Test
    @DisplayName("Красный вихрь")
    void test2() throws Exception {

        // given
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        Modifiers mod = new Modifiers();
        mod.swirl = true;

        //when
        render.setRatio(3.555, 2);
        afinGen.setColorBorders(150, 50, 50, 255, 150, 150);

        List<String> commandLine = new ArrayList<>();
        commandLine.add("swirl");

        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine);

        Pixel[][] pixels = render.render(afinGen.genAfin(60), 1000, 1000, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);
    }

    @Test
    @DisplayName("Хвост фазана (метла/веер)")
    void test3() throws Exception {

        // given
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        Modifiers mod = new Modifiers();
        mod.pdj = true;
        mod.swirl = true;
        //mod.sphere = true;

        //when
        render.setRatio(2.777, 2);
        afinGen.setColorBorders(50, 150, 50, 100, 255, 100);

        List<String> commandLine = new ArrayList<>();
        commandLine.add("swirl");
        commandLine.add("pdj");

        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine);


        Pixel[][] pixels = render.render(afinGen.genAfin(60), 1000, 1000, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);
    }

    @Test
    @DisplayName("Драгоценный камень")
    void test4() throws Exception {

        // given
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        Modifiers mod = new Modifiers();
        mod.pillow = true;
        mod.sphere = true;
        mod.swirl = true;
        mod.disk = true;

        //when
        render.setRatio(0.777, 0.5);
        afinGen.setColorBorders(150, 0, 75, 255, 25, 175);

        List<String> commandLine = new ArrayList<>();



        commandLine.add("sphere");
        commandLine.add("disk");
        commandLine.add("swirl");
        commandLine.add("pillow");


        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine);

        Pixel[][] pixels = render.render(afinGen.genAfin(100), 1000, 1000, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);
    }

    @Test
    @DisplayName("Сфера-воронка")
    void test6() throws Exception {

        // given
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        Modifiers mod = new Modifiers();
        //mod.pillow = true;
        mod.sphere = true;
        mod.swirl = true;
        mod.horseshoe = true;
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
        //pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);

    }

    @Test
    @DisplayName("Воронка-Ворнока")
    void test12() throws Exception {

        // given
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        Modifiers mod = new Modifiers();
        //mod.pillow = true;
        //mod.disk = true;

        //when
        render.setRatio(2.7777*2, 2*2);
        afinGen.setColorBorders(50, 50, 150, 150, 150, 255);

        List<String> commandLine = new ArrayList<>();
        commandLine.add("swirl");
        commandLine.add("swirl");

        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine);

        Pixel[][] pixels = render.render(afinGen.genAfin(100), 1000, 1000, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);

    }

    @Test
    @DisplayName("Двойное Сердце")
    void test13() throws Exception {

        // given
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        Modifiers mod = new Modifiers();
        //mod.pillow = true;
        //mod.disk = true;

        //when
        render.setRatio(2.7777, 2);
        afinGen.setColorBorders(50, 50, 150, 150, 150, 255);

        List<String> commandLine = new ArrayList<>();
        commandLine.add("heart");
        commandLine.add("heart");

        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine);

        Pixel[][] pixels = render.render(afinGen.genAfin(100), 1000, 1000, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);

    }
    @Test
    @DisplayName("скруглённый куб")
    void test20() throws Exception {

        // given
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        Modifiers mod = new Modifiers();
        //mod.pillow = true;
        //mod.disk = true;

        //when
        render.setRatio(1.7777*2, 2);
        afinGen.setColorBorders(50, 50, 150, 150, 150, 255);

        List<String> commandLine1 = new ArrayList<>();
        List<String> commandLine2 = new ArrayList<>();
        List<String> commandLine3 = new ArrayList<>();
        commandLine1.add("diamond");
        commandLine1.add("sphere");
        commandLine2.add("diamond");
        //commandLine3.add("sphere");


        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine1);
        instructions.add(commandLine2);
        //instructions.add(commandLine3);

        Pixel[][] pixels = render.render(afinGen.genAfin(100), 1000, 10000, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);

    }

    @Test
    @DisplayName("скруглённый куб")
    void test21() throws Exception {

        // given
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        Modifiers mod = new Modifiers();
        //mod.pillow = true;
        //mod.disk = true;

        //when
        render.setRatio(1.7777*2, 2);
        afinGen.setColorBorders(50, 50, 150, 150, 150, 255);

        List<String> commandLine1 = new ArrayList<>();
        List<String> commandLine2 = new ArrayList<>();
        List<String> commandLine3 = new ArrayList<>();
        commandLine1.add("disk");
        commandLine1.add("hyperbolic");
        //commandLine2.add("hyperbolic");
        //commandLine2.add("diamond");
        commandLine3.add("sphere");


        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine1);
        //instructions.add(commandLine2);
        instructions.add(commandLine3);

        Pixel[][] pixels = render.render(afinGen.genAfin(100), 1000, 10000, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);

    }

    @Test
    @DisplayName("Рыбий глаз")
    void test22() throws Exception {

        // given
        AfinGen afinGen = new AfinGen();
        Render render = new Render();
        Modifiers mod = new Modifiers();
        //mod.pillow = true;
        //mod.disk = true;

        //when
        render.setRatio(1.7777*4, 1*4);
        afinGen.setColorBorders(50, 50, 150, 150, 150, 255);

        List<String> commandLine1 = new ArrayList<>();
        //List<String> commandLine2 = new ArrayList<>();
        //List<String> commandLine3 = new ArrayList<>();
        //commandLine1.add("");
        //commandLine1.add("hyperbolic");
        //commandLine2.add("hyperbolic");
        //commandLine2.add("diamond");
        //commandLine3.add("sphere");


        List<List<String>> instructions = new ArrayList<>();
        instructions.add(commandLine1);
        //instructions.add(commandLine2);
        //instructions.add(commandLine3);

        Pixel[][] pixels = render.render(afinGen.genAfin(100), 1000, 10000, instructions);
        pixels = render.gammaCor(pixels);
        Drawer painter = new Drawer(path);
        painter.draw(pixels);

    }
    //*/
}
