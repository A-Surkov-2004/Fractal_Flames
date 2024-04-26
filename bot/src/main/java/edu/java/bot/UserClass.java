package edu.java.bot;

import edu.java.bot.CommandExecuters.SetColorCommands.ColorsEnum;
import project4.AfinGen;
import project4.ColorReader;
import project4.Drawer;
import project4.FractalCommandReader;
import project4.Pixel;
import project4.Render;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserClass {


    private static final double BASIC_X_RATIO = 1.777;
    private static final double BASIC_Y_RATIO = 1;

    public static final Path TEMPERAL_IMAGE_PATH = Path.of("bot","src","main", "java", "project4", "tempFiles", "Image.jpg");
    public static final Path EMPTY_IMAGE_PATH = Path.of("bot","src","main", "java", "project4", "Image.jpg");
    private static final int MAX_WORDS_IN_CL = 10;
    private static final int MAX_LINES = 10;
    public final static String DEFAULT_STATE = "default";
    private String state;
    private Set<String> links = new HashSet<>();
    private ColorsEnum color = ColorsEnum.RED;
    private double ratio = 1;

    FractalCommandReader cmr = new FractalCommandReader();

    private int currentCL;

    private List<List<String>> commands = new ArrayList<>();

    public UserClass() {
        state = DEFAULT_STATE;
    }

    public void stateReset() {
        state = DEFAULT_STATE;
    }

    public void stateSet(String newState) {
        state = newState;
    }

    public String stateGet() {
        return state;
    }

    public void linkAdd(String link) {
        links.add(link);
    }

    public void linkRemove(String link) {
        links.remove(link);
    }
    public Set<String> linksGet() {
        return links;
    }
    public void setColor(ColorsEnum color){
        this.color = color;
    }
    public ColorsEnum getColor(){
        return this.color;
    }
    public boolean setRatio(double ratio){
        if(ratio >= 0.1 && ratio <= 10){
            this.ratio = ratio;
            return true;
        }
        else {
            return false;
        }
    }
    public double getRatio(){
        return this.ratio;
    }

    public boolean addCommandWord(String command){
        if(cmr.getAllCommands().contains(command) && currentCL < commands.size() && commands.get(currentCL).size() <= MAX_WORDS_IN_CL){
            commands.get(currentCL).add(command);
            return true;
        }
        return false;
    }

    public boolean removeWordFromLine(){
        if(!commands.isEmpty()){
            commands.removeLast();
            return true;
        }
        return false;
    }


    public boolean addLine(){
        if(commands.size() <= MAX_LINES){
            commands.add(new ArrayList<>());
            return true;
        }
        return false;
    }

    public boolean removeLine(){
        if(commands.size() > 1) {
            commands.remove(currentCL);
            return true;
        }
        return false;
    }

    public boolean clearLine(){
        commands.set(currentCL, new ArrayList<>());
        return true;
    }

    public boolean setCL(int newCL){
        if(newCL >= 0 && newCL < commands.size()){
            currentCL = newCL;
            return true;
        }
        return false;
    }

    public int getCL(){
        return currentCL;
    }

    public String printCommands(){
        StringBuilder sb = new StringBuilder();
        sb.append("Текущий список команд: \n");
        for(int i = 0; i < commands.size(); i++){
            sb.append(i);
            sb.append(") ");
            for (int j = 0; j < commands.get(i).size(); j++){
                sb.append(commands.get(i).get(j));
                sb.append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public List<List<String>> getCommands(){
        return commands;
    }

    public void generate() throws IOException {
        ColorReader colorReader = new ColorReader();
        Drawer d = new Drawer(EMPTY_IMAGE_PATH.toString());

        Render render = new Render();

        render.setRatio(BASIC_X_RATIO*this.ratio, BASIC_Y_RATIO*this.ratio);
        AfinGen afinGen = colorReader.gerateAfins(this.color);
        System.out.println(commands.get(0));
        Pixel[][] pixels = render.render(afinGen.genAfin(100), 1000, 10000, commands);
        pixels = render.gammaCor(pixels);
        try {
            d.draw(pixels);
        }catch (InterruptedException e){
            System.out.println(e.getStackTrace());
        }
    }
}
