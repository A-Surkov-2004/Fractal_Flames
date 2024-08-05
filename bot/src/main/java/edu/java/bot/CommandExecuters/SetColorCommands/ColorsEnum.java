package edu.java.bot.CommandExecuters.SetColorCommands;

public class ColorsEnum {

    private String name;

    public ColorsEnum(String name) {
        this.name = name;
    }

    public static final ColorsEnum RED = new ColorsEnum("КРАСНЫЙ");
    public static final ColorsEnum ORANGE = new ColorsEnum("ОРАНЖЕВЫЙ");
    public static final ColorsEnum YELLOW = new ColorsEnum("ЖЕЛТЫЙ");
    public static final ColorsEnum GREEN = new ColorsEnum("ЗЕЛЕНЫЙ");
    public static final ColorsEnum BLUE = new ColorsEnum("ГОЛУБОЙ");
    public static final ColorsEnum PURPLE = new ColorsEnum("СИРЕНЕВЫЙ");
    public static final ColorsEnum PINK = new ColorsEnum("РОЗОВЫЙ");
    public static final ColorsEnum EVERY = new ColorsEnum("ЛЮБОЙ");

    @Override
    public String toString() {
        return name;
    }
}
