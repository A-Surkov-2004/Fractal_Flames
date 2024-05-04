package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.Set;

import static edu.java.bot.CommandReader.allColors;
import static edu.java.bot.CommandReader.allExe;
import static edu.java.bot.UserDataMapClass.userData;


public class ShowCollorsExecuter extends BasicCommandExecuter {
    public ShowCollorsExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {

        long id = update.message().chat().id();
        if (userData.containsKey(id)) {
            Set<BasicCommandExecuter> executers = allColors;
            StringBuilder commands = new StringBuilder();
            commands.append("Выбор цвета:\n");
            for (BasicCommandExecuter color : executers) {
                commands.append(color.getName());
                commands.append(" - ");
                commands.append(color.getDescription());
                commands.append("\n");
            }
            reply = (new SendMessage(id, commands.toString()));
        }
        return reply;
    }
}
