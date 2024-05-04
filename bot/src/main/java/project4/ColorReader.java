package project4;

import edu.java.bot.CommandExecuters.SetColorCommands.ColorsEnum;

public class ColorReader {



    public AfinGen gerateAfins(ColorsEnum color){
        AfinGen afinGen = new AfinGen();


        if(color == ColorsEnum.RED){
            afinGen.setColorBorders(150, 0, 0, 255, 100, 100);
        }
        if(color == ColorsEnum.ORANGE){
            afinGen.setColorBorders(150, 50, 0, 255, 150, 100);
        }
        if(color == ColorsEnum.YELLOW){
            afinGen.setColorBorders(150, 150, 0, 255, 255, 100);
        }
        if(color == ColorsEnum.GREEN){
            afinGen.setColorBorders(50, 150, 50, 100, 255, 100);
        }
        if(color == ColorsEnum.BLUE){
            afinGen.setColorBorders(50, 50, 150, 150, 150, 255);
        }
        if(color == ColorsEnum.PURPLE){
            afinGen.setColorBorders(150, 0, 150, 255, 100, 255);
        }
        if(color == ColorsEnum.PINK){
            afinGen.setColorBorders(200, 0, 200, 255, 125, 255);
        }
        if (color == ColorsEnum.EVERY){
            afinGen.setColorBorders(50, 50, 50, 255, 255, 255);
        }
        return afinGen;
    }
}
