package edu.java.bot.CommandExecuters.SetColorCommands;

public class ColorsEnum {

    private String name;

    public ColorsEnum(String name) {
        this.name = name;
    }

    public static ColorsEnum RED = new ColorsEnum("КРАСНЫЙ");
    public static ColorsEnum ORANGE = new ColorsEnum("ОРАНЖЕВЫЙ");
    public static ColorsEnum YELLOW = new ColorsEnum("ЖЕЛТЫЙ");
    public static ColorsEnum GREEN = new ColorsEnum("ЗЕЛЕНЫЙ");
    public static ColorsEnum BLUE = new ColorsEnum("ГОЛУБОЙ");
    public static ColorsEnum PURPLE = new ColorsEnum("СИРЕНЕВЫЙ");
    public static ColorsEnum PINK = new ColorsEnum("РОЗОВЫЙ");

    public static ColorsEnum EVERY = new ColorsEnum("ЛЮБОЙ");

    @Override
    public String toString() {
        return name;
    }
}
