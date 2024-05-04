package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.Set;
import static edu.java.bot.CommandReader.allColors;
import static edu.java.bot.CommandReader.allMods;
import static edu.java.bot.UserDataMapClass.userData;


public class ShowModifiers extends BasicCommandExecuter {
    public ShowModifiers(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {

        long id = update.message().chat().id();
        if (userData.containsKey(id)) {
            Set<BasicCommandExecuter> executers = allMods;
            StringBuilder commands = new StringBuilder();
            commands.append("Доступные модификаторы:\n");
            commands.append("lines (default mod, only when line is empty)");
            for (BasicCommandExecuter mod : executers) {
                commands.append(mod.getName());
                //commands.append(" - ");
                //commands.append(mod.getDescription());
                commands.append("\n");
            }
            reply = (new SendMessage(id, commands.toString()));
        }
        return reply;
    }
}
