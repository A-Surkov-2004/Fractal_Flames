package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;

import static edu.java.bot.UserDataMapClass.userData;

public class SetCLExecuter extends BasicCommandExecuter {

    public final static String GIVEN_STATE = "changing currentCL";
    public SetCLExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {

        long id = update.message().chat().id();
        userData.get(id).stateSet(GIVEN_STATE);

        reply = (new SendMessage(id, "Укажите номер командной строки. (от 1 до "+userData.get(id).getCommands().size()+")"));

        return this.reply;
    }
}
