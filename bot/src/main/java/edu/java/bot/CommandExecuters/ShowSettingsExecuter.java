package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import static edu.java.bot.UserDataMapClass.userData;

public class ShowSettingsExecuter extends BasicCommandExecuter {

    public ShowSettingsExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {
        long id = update.message().chat().id();

        reply = (new SendMessage(id,  userData.get(id).printAllSettings()));

        return this.reply;
    }
}
