package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.Set;
import static edu.java.bot.CommandReader.allConfigs;
import static edu.java.bot.CommandReader.allMods;
import static edu.java.bot.UserDataMapClass.userData;


public class ShowConfEdits extends BasicCommandExecuter {
    public ShowConfEdits(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {

        long id = update.message().chat().id();
        if (userData.containsKey(id)) {
            Set<BasicCommandExecuter> executers = allConfigs;
            StringBuilder commands = new StringBuilder();
            commands.append("Доступные модификаторы:\n");
            for (BasicCommandExecuter config : executers) {
                commands.append(config.getName());
                commands.append(" - ");
                commands.append(config.getDescription());
                commands.append("\n");
            }
            reply = (new SendMessage(id, commands.toString()));
        }
        return reply;
    }
}
