package edu.java.bot.CommandExecuters;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import static edu.java.bot.UserDataMapClass.userData;

public class AddLineExecuter extends BasicCommandExecuter {

    public AddLineExecuter(String name, String description) {
        super(name, description);
    }

    @Override
    public BaseRequest execute(Update update) {
        long id = update.message().chat().id();
        boolean ok = false;
        ok = userData.get(id).addLine();
        if(ok) {
            reply = (new SendMessage(id, "Строка добавлена.\n" +  userData.get(id).printCommands()));
        }else {
            reply = (new SendMessage(id, "Произошла ошибка. Строка не была добавлена"));
        }
        return this.reply;
    }
}
