package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.UserClass;
import static edu.java.bot.UserDataMapClass.userData;

public class StartCommandExecuter extends BasicCommandExecuter {

    public StartCommandExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {
        long id = update.message().chat().id();
        if (userData.containsKey(id)) {
            reply = new SendMessage(id, "Вы уже зарегестрированы в системе.");
        } else {
            userData.put(id, new UserClass());
            userData.get(id).addLine();
            reply = (new SendMessage(id, "Привет!\n"
                + "'"
                + " ."));
        }
        return reply;
    }
}
